package com.aradnab.boot.general.controller;

import com.aradnab.boot.db_tier.entity.Country;
import com.aradnab.boot.db_tier.entity.District;
import com.aradnab.boot.db_tier.entity.Province;
import com.aradnab.boot.general.service.CountryService;
import com.aradnab.boot.general.service.DistrictService;
import com.aradnab.boot.general.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/location")
public class LocationController {
    @Autowired
    CountryService countryService;
    @Autowired
    ProvinceService provinceService;
    @Autowired
    DistrictService districtService;

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
        provincesById.forEach(province -> c.put(province.getId(),province.getProvince()));
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
        districtsById.forEach(district -> c.put(district.getId(),district.getDistrict()));
        return ResponseEntity.ok().body(c);
    }

}
