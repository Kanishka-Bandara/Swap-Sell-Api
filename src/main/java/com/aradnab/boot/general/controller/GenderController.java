package com.aradnab.boot.general.controller;

import com.aradnab.boot.db_tier.entity.Gender;
import com.aradnab.boot.db_tier.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    GenderService service = new GenderService();

    @GetMapping("/getAll")
    public ResponseEntity<List<Gender>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/getGenderByID/{id}")
    public ResponseEntity<Gender> getGenderByID(@PathVariable int id) {
        return ResponseEntity.ok().body(service.getGenderByID(id, (byte) 1));
    }

    @GetMapping("/getGenderByName/{gender}")
    public ResponseEntity<Optional<Gender>> getGenderByName(@PathVariable String gender) {
        return ResponseEntity.ok().body(service.getGenderByName(gender));
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

    @GetMapping("/download")
    public StreamingResponseBody getStreamingResponse() {
        return new StreamingResponseBody() {
            @Override
            public void writeTo(OutputStream out) {
                for (int i = 0; i < 1000; i++) {
                    try {
                        out.write((Integer.toString(i) + " - ")
                                .getBytes());
                        out.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }
}
