package com.epam.cdp.oleshchuk.controller;

import com.epam.cdp.oleshchuk.exception.ServiceException;
import com.epam.cdp.oleshchuk.model.ChartModel;
import com.epam.cdp.oleshchuk.service.VisualizationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class VisualizationController {

    @Autowired
    VisualizationService visualizationService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView viewChart(ModelMap modelMap) throws IOException {
        return viewChartByCity(modelMap, null);
    }

    @RequestMapping(value = "/getChart", method = RequestMethod.GET)
    public ModelAndView viewChartByCity(ModelMap modelMap, @RequestParam(value = "city") String city) throws IOException {
        List<String> cities = new ArrayList<String>();
        Set<String> uniqueCities = null;
        try {
            uniqueCities = visualizationService.getFirstData();
        } catch (ServiceException e) {
            return new ModelAndView("error", modelMap);
        }
        if (uniqueCities != null) {
            cities.addAll(uniqueCities);
        } else {
            return new ModelAndView("error", modelMap);
        }
        if (city == null || !cities.contains(city)) {
            city = "Kharkiv";
        }
        modelMap.put("cities", cities);
        ChartModel chartModel = null;
        try {
            chartModel = visualizationService.getChartParams(city);
        } catch (ServiceException e) {
            return new ModelAndView("error", modelMap);
        }
        modelMap.put("json", new Gson().toJson(chartModel));
        return new ModelAndView("index", modelMap);
    }


}

