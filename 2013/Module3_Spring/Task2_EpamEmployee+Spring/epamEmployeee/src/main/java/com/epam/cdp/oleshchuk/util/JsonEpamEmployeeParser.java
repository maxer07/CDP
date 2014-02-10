package com.epam.cdp.oleshchuk.util;

import com.epam.cdp.oleshchuk.data.EpamEmployeeData;
import com.epam.cdp.oleshchuk.exception.ReadResourceException;
import com.epam.cdp.oleshchuk.service.VisualizationService;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonEpamEmployeeParser {

    public static EpamEmployeeData parseAndGetData() throws ReadResourceException {
        EpamEmployeeData epamEmployee = null;
        String resource = null;
        resource = readResource("/epam_json.txt");
        if (resource != null) {
            epamEmployee = new Gson().fromJson(resource, EpamEmployeeData.class);
        }
        return epamEmployee;
    }

    private static String readResource(String resourcePath) throws ReadResourceException {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(VisualizationService.class.getResourceAsStream(resourcePath))))
        {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new ReadResourceException("Could not read resource " + resourcePath);
        }
        return sb.toString();
    }

}
