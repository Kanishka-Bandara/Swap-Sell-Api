package com.aradnab.boot.general.controller;

import com.aradnab.boot.db_tier.entity.District;
import com.aradnab.boot.db_tier.entity.PostOfficeBox;
import com.aradnab.boot.db_tier.entity.Province;
import com.aradnab.boot.general.model.AddressModel;
import com.aradnab.boot.general.service.CountryService;
import com.aradnab.boot.general.service.DistrictService;
import com.aradnab.boot.general.service.PostOfficeBoxService;
import com.aradnab.boot.general.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/location")
public class LocationController {
    @Autowired
    CountryService countryService;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DistrictService districtService;
    @Autowired
    PostOfficeBoxService postOfficeBoxService;

    @GetMapping("/getAllCountries")
    public ResponseEntity<Map<Integer, String>> getAllCountries() {
        Map<Integer, String> c = new HashMap<>();
        countryService.getAll().forEach(country -> c.put(country.getId(), country.getCountry()));
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/getCountryById/{id}")
    public ResponseEntity<String> getCountryByID(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(countryService.getByID(id).getCountry());
    }


    @GetMapping("/getAllProvinces")
    public ResponseEntity<Map<Integer, String>> getAllProvinces() {
        Map<Integer, String> c = new HashMap<>();
        provinceService.getAll().forEach(province -> c.put(province.getId(), province.getProvince()));
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/getProvinceById/{id}")
    public ResponseEntity<String> getProvinceByID(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(provinceService.getByID(id).getProvince());
    }

    @GetMapping("/getAllProvincesByCountryId/{id}")
    public ResponseEntity<Map<Integer, String>> getProvincesByCountryId(@PathVariable("id") int id) {
        Map<Integer, String> c = new HashMap<>();
        Collection<Province> provincesById = countryService.getByID(id).getProvincesById();
        provincesById.forEach(province -> c.put(province.getId(), province.getProvince()));
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/getAllDistricts")
    public ResponseEntity<Map<Integer, String>> getAllDistricts() {
        Map<Integer, String> c = new HashMap<>();
        districtService.getAll().forEach(district -> c.put(district.getId(), district.getDistrict()));
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/getDistrictById/{id}")
    public ResponseEntity<String> getDistrictByID(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(districtService.getByID(id).getDistrict());
    }

    @GetMapping("/getAllDistrictsByProvinceId/{id}")
    public ResponseEntity<Map<Integer, String>> getDistrictsByProvinceId(@PathVariable("id") int id) {
        Map<Integer, String> c = new HashMap<>();
        Collection<District> districtsById = provinceService.getByID(id).getDistrictsById();
        districtsById.forEach(district -> c.put(district.getId(), district.getDistrict()));
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/getAllPostalCodes")
    public ResponseEntity<List<Map<String, String>>> getAllPostalCodes() {
        List<Map<String, String>> l = new ArrayList<>();
        postOfficeBoxService.getAll().forEach(postOfficeBox -> l.add(getPOBByMap(postOfficeBox)));
        return ResponseEntity.ok().body(l);
    }

    @GetMapping("/getPostalCodeByID/{id}")
    public ResponseEntity<Map<String, String>> getPostalCodeByID(@PathVariable("id") int id) {
        PostOfficeBox postOfficeBox = postOfficeBoxService.getByID(id);
        Map<String, String> c = new HashMap<>();
        c.put("id", postOfficeBox.getId() + "");
        c.put("code", postOfficeBox.getPostalCode());
        c.put("area", postOfficeBox.getPostalArea());
        return ResponseEntity.ok().body(c);
    }

    @GetMapping("/getAllPostalCodesByDistrictId/{id}")
    public ResponseEntity<List<Map<String, String>>> getAllPostalCodesByDistrictId(@PathVariable("id") int id) {
        List<Map<String, String>> l = new ArrayList<>();
        Collection<PostOfficeBox> postOfficeBoxesById = districtService.getByID(id).getPostOfficeBoxesById();
        postOfficeBoxesById.forEach(postOfficeBox -> l.add(getPOBByMap(postOfficeBox)));
        return ResponseEntity.ok().body(l);
    }

    Map<String, String> getPOBByMap(PostOfficeBox postOfficeBox) {
        Map<String, String> c = new HashMap<>();
        c.put("area", postOfficeBox.getPostalArea());
        c.put("code", postOfficeBox.getPostalCode());
        c.put("id", postOfficeBox.getId() + "");
        return c;
    }

}
