package com.aradnab.boot.general.controller;

import com.aradnab.boot.db_tier.entity.Gender;
import com.aradnab.boot.general.model.GenderModel;
import com.aradnab.boot.general.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@RestController
@RequestMapping("/gender")
public class GenderController {
    @Autowired
    GenderService service = new GenderService();

    @GetMapping("/getAll")
    public ResponseEntity<List<Map<String, String>>> getAll() {
        return ResponseEntity.ok().body(GenderModel.dbGenderToModelGenderMapList(service.getAll()));
    }

    @GetMapping("/getGenderByID/{id}")
    public ResponseEntity<GenderModel> getGenderByID(@PathVariable int id) {
        return ResponseEntity.ok().body(GenderModel.dbGenderToModelGender(service.getGenderByID(id, (byte) 1)));
    }

    @GetMapping("/getGenderByName/{gender}")
    public ResponseEntity<GenderModel> getGenderByName(@PathVariable String gender) {
        return ResponseEntity.ok().body(GenderModel.dbGenderToModelGender(service.getGenderByName(gender)));
    }

//    @PostMapping("/create")
//    public ResponseEntity<GenderModel> createGender(@RequestBody GenderModel genderModel) {
//        Gender g = new Gender();
//        g.setGender(genderModel.getGender());
//        return ResponseEntity.ok().body(GenderModel.dbGenderToModelGender(this.service.createGender(g)));
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenderModel> updateGender(@PathVariable int id, @RequestBody Gender gender) {
        return ResponseEntity.ok().body(GenderModel.dbGenderToModelGender(this.service.updateGender(id, gender)));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteGender(@PathVariable int id) {
        final int i = service.deleteGender(id);
        switch (i) {
            case 0:
            case 2:
                return HttpStatus.FAILED_DEPENDENCY;
            case 1:
                return HttpStatus.OK;
        }
        return HttpStatus.OK;
    }
//
//    @GetMapping("/download")
//    public StreamingResponseBody getStreamingResponse() {
//        return new StreamingResponseBody() {
//            @Override
//            public void writeTo(OutputStream out) {
//                for (int i = 0; i < 1000; i++) {
//                    try {
//                        out.write((Integer.toString(i) + " - ")
//                                .getBytes());
//                        out.flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(i);
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//    }
}
