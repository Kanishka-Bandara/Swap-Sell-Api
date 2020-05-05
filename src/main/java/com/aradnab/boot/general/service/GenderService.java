package com.aradnab.boot.general.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GenderService {
    public Map<Integer,String> findAllGenders(){
        List<Map<Integer,String>> genders = new ArrayList();
        HashMap<Integer, String> g = new HashMap<>();
        g.put(0,"Male");
        g.put(1,"Female");
        g.put(2,"Not Specified");
        genders.add(g);
        return g;
    }
}
