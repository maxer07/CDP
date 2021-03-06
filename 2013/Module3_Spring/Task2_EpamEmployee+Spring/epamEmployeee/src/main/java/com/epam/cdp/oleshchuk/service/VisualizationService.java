package com.epam.cdp.oleshchuk.service;

import com.epam.cdp.oleshchuk.data.EpamEmployeeData;
import com.epam.cdp.oleshchuk.exception.ReadResourceException;
import com.epam.cdp.oleshchuk.exception.ServiceException;
import com.epam.cdp.oleshchuk.model.*;
import com.epam.cdp.oleshchuk.util.JsonEpamEmployeeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VisualizationService {

    @Autowired
    private EpamEmployeeData epamEmployeeData;
    @Autowired
    private ChartModel chartModel;

    public Set<String> getFirstData() throws ServiceException {
        Set<String> uniqueCities = null;
        uniqueCities = new TreeSet<String>();
        getEpamEmployeeData();
        for (Map<String, List<Object>> values : epamEmployeeData.getEPAM().values()) {
            for (String city : values.keySet()) {
                uniqueCities.add(city);
            }
        }
        return uniqueCities;
    }

    public ChartModel getChartParams(String city) throws ServiceException {
        getEpamEmployeeData();
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
        createChartModel(new Chart("bar"), new Title(city, 50), new Subtitle(years.toString()), new xAxis(years),
                new yAxis(new Title("Employee", 10)), series, new Legend(true, "top", 0, 40), new PlotOptions(new Bar(true)));
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

    private void getEpamEmployeeData() throws ServiceException {
        if (epamEmployeeData.getEPAM() == null) {
            try {
                this.epamEmployeeData = JsonEpamEmployeeParser.parseAndGetData();
            } catch (ReadResourceException e) {
                throw new ServiceException("Parsing exception");
            }
        }
    }
}
