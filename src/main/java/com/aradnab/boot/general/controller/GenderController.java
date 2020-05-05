package com.aradnab.boot.general.controller;

import com.aradnab.boot.general.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    GenderService service = new GenderService();

    @GetMapping("getAll")
    public Object getAll() {
        return service.findAllGenders();
    }
}
