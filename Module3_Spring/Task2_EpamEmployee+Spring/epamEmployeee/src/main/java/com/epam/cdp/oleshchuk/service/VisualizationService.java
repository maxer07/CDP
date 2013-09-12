package com.epam.cdp.oleshchuk.service;

import com.epam.cdp.oleshchuk.data.EpamEmployeeData;
import com.epam.cdp.oleshchuk.model.*;
import com.epam.cdp.oleshchuk.util.JsonEpamEmployeeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class VisualizationService {

    @Autowired
    private EpamEmployeeData epamEmployeeData;
    @Autowired
    private ChartModel chartModel;

    public List<String> getFirstData() {
        Set<String> uniqueCities = null;
        List<String> cities = null;
        try {
            uniqueCities = new HashSet<String>();
            cities = new ArrayList<String>();
            if (checkEpamEmployeeData()) {
                epamEmployeeData = JsonEpamEmployeeParser.parseAndGetData();
            }
            for (Map<String, List<Object>> values : epamEmployeeData.getEPAM().values()) {
                for (String city : values.keySet()) {
                    uniqueCities.add(city);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        cities.addAll(uniqueCities);
        Collections.sort(cities);
        return cities;
    }


    public ChartModel getChartParams(String city) throws IOException {

        if (checkEpamEmployeeData()) {
            this.epamEmployeeData = JsonEpamEmployeeParser.parseAndGetData();
        }

        Map<String, List<Object>> yearsEmployeeMap = new TreeMap<String, List<Object>>();

        for (Map.Entry<String, Map<String, List<Object>>> entry : epamEmployeeData.getEPAM().entrySet()) {
            List<Object> values = entry.getValue().get(city);

            if (values != null) {
                yearsEmployeeMap.put(entry.getKey(), values);
            }
        }

        List<String> years = new ArrayList<String>();
        for (String s : yearsEmployeeMap.keySet()) {
            years.add(s.substring(4));
        }


        List<Series> series = new ArrayList<Series>();
        for (int i = 3; i >= 0; i--) {
            Series serie = new Series();

            serie.set_jrid("s" + i);
            serie.setName((i + 1) + " quarter");

            List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();

            for (List<Object> list : yearsEmployeeMap.values()) {
                Map<String, Object> value = new LinkedHashMap<String, Object>();
                if (list.size() >= i + 1) {
                    value.put("y", list.get(i));
                }
                values.add(value);
            }

            serie.setData(values);

            series.add(serie);
        }
        createChartModel(new Chart("bar"), new Title(city,50), new Subtitle(years.toString()), new xAxis(years),
                new yAxis(new Title("Employee",10)), series, new Legend(true,"top",0,40), new PlotOptions(new Bar(true)));
        return chartModel;
    }


    private void createChartModel(Chart chart, Title title, Subtitle subtitle, xAxis xAxis, yAxis yAxis, List<Series> series, Legend legend, PlotOptions plotOptions) {
        chartModel.setChart(chart);
        chartModel.setTitle(title);
        chartModel.setSubtitle(subtitle);
        chartModel.setxAxis(xAxis);
        chartModel.setyAxis(yAxis);
        chartModel.setSeries(series);
        chartModel.setLegend(legend);
        chartModel.setPlotOptions(plotOptions);
    }

    private boolean checkEpamEmployeeData() {
        return (epamEmployeeData.getEPAM() == null);
    }
}
