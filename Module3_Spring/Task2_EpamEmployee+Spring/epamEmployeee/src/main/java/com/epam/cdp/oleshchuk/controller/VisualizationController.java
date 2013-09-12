package com.epam.cdp.oleshchuk.controller;

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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Maksym_Oleshchuk
 * Date: 03.09.13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */
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
        List<String> cities = visualizationService.getFirstData();
        if (city== null || !cities.contains(city) ) {
          city = "Kharkiv";
        }
        modelMap.put("cities",cities);
        ChartModel chartModel = visualizationService.getChartParams(city);
        modelMap.put("json", new Gson().toJson(chartModel));
        return new ModelAndView("index", modelMap);
    }


}

