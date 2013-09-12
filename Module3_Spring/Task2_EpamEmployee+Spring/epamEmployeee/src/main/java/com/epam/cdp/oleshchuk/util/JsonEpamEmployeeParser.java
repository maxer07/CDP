package com.epam.cdp.oleshchuk.util;

import com.epam.cdp.oleshchuk.data.EpamEmployeeData;
import com.epam.cdp.oleshchuk.service.VisualizationService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Maksym_Oleshchuk
 * Date: 03.09.13
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public class JsonEpamEmployeeParser {

    public static EpamEmployeeData parseAndGetData() throws IOException {
        EpamEmployeeData epamEmployee;
        String resource = readResource("/epam_json.txt");
        epamEmployee = new Gson().fromJson(resource, EpamEmployeeData.class);
        return epamEmployee;
    }

    private static String readResource(String resourcePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(VisualizationService.class.getResourceAsStream(resourcePath));
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line;
        while ( (line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
