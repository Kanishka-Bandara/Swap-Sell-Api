package com.aradnab.boot.general.controller;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.*;
import com.aradnab.boot.general.model.AddressModel;
import com.aradnab.boot.general.service.*;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    AddressTypeService addressTypeService;
    @Autowired
    PostOfficeBoxService postOfficeBoxService;
    @Autowired
    StreetService streetService;
    @Autowired
    CityService cityService;
    @Autowired
    DistrictService districtService;
    @Autowired
    UserService userService;


    @GetMapping("/types")
    public ResponseEntity<Map<Integer, String>> getTypes() {
        Map<Integer, String> t = new HashMap<>();
        addressTypeService.getAll().forEach(addressType -> t.put(addressType.getId(), addressType.getType()));
        return ResponseEntity.ok().body(t);
    }

    @GetMapping("/type/getById/{id}")
    public ResponseEntity<String> getTypeById(@PathVariable int id) {
        return ResponseEntity.ok().body(addressTypeService.getByID(id).getType());
    }

    @GetMapping("/type/getIdByType/{type}")
    public ResponseEntity<Integer> getTypeIdByType(@PathVariable String type) {
        return ResponseEntity.ok().body(addressTypeService.getByName(type).getId());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<AddressModel> getAddressByID(@PathVariable int id) {
        return ResponseEntity.ok().body(AddressModel.entityToModel(addressService.getByID(id)));
    }

    @GetMapping("/getByUser/{id}")
    public ResponseEntity<List<AddressModel>> getAddressesByUserId(@PathVariable int id) {
        return ResponseEntity.ok().body(AddressModel.entityToModel(userService.getByID(id).getAddressesById()));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressModel> create(@RequestBody AddressModel address) {
        Date date = new Date();
        //BEGIN::Getting District ID
        District district = districtService.getByName(address.getDistrict());
        //END::Getting District ID
        //BEGIN :: Saving city
        City c = new City();
        c.setDistrictId(district.getId());
        c.setCity(address.getCity());
        c.setSavedAt(date);
        c.setLastUpdatedAt(date);
        c.setStatus(Status.LIVE_ACTIVE_STATUS);
        City city = this.cityService.create(c);
        //END :: Saving city
        //BEGIN :: Saving street
        Street s = new Street();
        s.setCityId(city.getId());
        s.setStreet(address.getStreet());
        s.setSavedAt(date);
        s.setLastUpdatedAt(date);
        s.setStatus(Status.LIVE_ACTIVE_STATUS);
        Street street = this.streetService.create(s);
        //END :: Saving street
        //BEGIN::Saving address
        Address a = new Address();
        a.setName(address.getName());
        AddressType addressType = addressTypeService.getByName(address.getType());
        a.setAddressTypeId(addressType.getId());
        a.setUserId(address.getUserId());
        PostOfficeBox poBox = postOfficeBoxService.getByPostalCode(address.getPostalCode());
        a.setPostOfficeBoxId(poBox.getId());
        a.setStreetId(street.getId());
        a.setCityId(city.getId());
        a.setDistrictId(district.getId());
        a.setProvinceId(district.getProvinceId());
        a.setCountryId(district.getProvinceByProvinceId().getCountryId());
        a.setIsDefault(address.getIsDefaultAddress());
        a.setSavedAt(date);
        a.setLastUpdatedAt(date);
        a.setStatus(Status.LIVE_ACTIVE_STATUS);
        a.setAddressTypeByAddressTypeId(addressType);
        a.setPostOfficeBoxByPostOfficeBoxId(poBox);
        a.setStreetByStreetId(street);
        a.setCityByCityId(city);
        a.setDistrictByDistrictId(district);
        a.setProvinceByProvinceId(district.getProvinceByProvinceId());
        a.setCountryByCountryId(district.getProvinceByProvinceId().getCountryByCountryId());
        Address newAddress = this.addressService.create(a);
        //END::Saving address
        return ResponseEntity.ok().body(AddressModel.entityToModel(newAddress));
    }

    @PostMapping("/edit")
    public ResponseEntity<AddressModel> update(@RequestBody AddressModel address) {
        deleteByID(address.getId());
        return create(address);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<CRUDStatus> deleteByID(@PathVariable int id) {
        return ResponseEntity.ok().body(addressService.delete(id));
    }
}
