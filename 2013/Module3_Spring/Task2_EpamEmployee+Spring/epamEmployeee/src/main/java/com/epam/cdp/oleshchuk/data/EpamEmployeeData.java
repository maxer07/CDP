package com.epam.cdp.oleshchuk.data;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class EpamEmployeeData {

    private Map<String, Map<String,List<Object>>> EPAM;

    public Map<String, Map<String,List<Object>>> getEPAM(){
        return this.EPAM;
    }

}
