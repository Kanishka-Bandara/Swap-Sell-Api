package com.aradnab.boot.general.controller;

import com.aradnab.boot.general.model.Gender;
import com.aradnab.boot.general.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    GenderService service = new GenderService();

    @GetMapping("/getAll")
    public ResponseEntity<List<Gender>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/getGender/{id}")
    public ResponseEntity<Gender> getGender(@PathVariable int id) {
        return ResponseEntity.ok().body(service.getGenderByID(id, (byte) 1));
    }

    @PostMapping("/create")
    public ResponseEntity<Gender> createGender(@RequestBody Gender gender) {
        System.out.println(gender.getId());
        System.out.println(gender.getGender());
        System.out.println(gender.getSavedAt());
        System.out.println(gender.getStatus());
        return ResponseEntity.ok().body(this.service.createGender(gender));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Gender> updateGender(@PathVariable int id, @RequestBody Gender gender) {
        return ResponseEntity.ok().body(this.service.updateGender(id, gender));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteGender(@PathVariable int id) {
        service.deleteGender(id);
        return HttpStatus.OK;
    }
}
