package com.aradnab.boot.general.model;

import com.aradnab.boot.Status;
import com.aradnab.boot.db_tier.entity.Address;
import com.aradnab.boot.db_tier.entity.City;
import com.aradnab.boot.db_tier.entity.District;
import com.aradnab.boot.db_tier.entity.Street;
import com.aradnab.boot.general.service.*;
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
    public ResponseEntity<Map<Integer, String>> getAddressesTypes() {
        Map<Integer, String> t = new HashMap<>();
        addressTypeService.getAll().forEach(addressType -> t.put(addressType.getId(), addressType.getType()));
        return ResponseEntity.ok().body(t);
    }

    @GetMapping("/typeByTypeId/{id}")
    public ResponseEntity<String> getAddressesTypes(@PathVariable int id) {
        return ResponseEntity.ok().body(addressTypeService.getByID(id).getType());
    }

    @GetMapping("/typeIdByType/{type}")
    public ResponseEntity<Integer> typeIdByType(@PathVariable String type) {
        return ResponseEntity.ok().body(addressTypeService.getByName(type).getId());
    }

    @GetMapping("/getAddressById/{id}")
    public ResponseEntity<AddressModel> getGenderByID(@PathVariable int id) {
        return ResponseEntity.ok().body(AddressModel.entityToModel(addressService.getByID(id)));
    }

    @GetMapping("/getAddressesByUserId/{id}")
    public ResponseEntity<List<AddressModel>> getAddressesByUserId(@PathVariable int id) {
        return ResponseEntity.ok().body(AddressModel.entityToModel(userService.getByID(id).getAddressesById()));
    }

    @PostMapping("/create")
    public ResponseEntity<AddressModel> createGender(@RequestBody AddressModel address) {
        return ResponseEntity.ok().body(this.createAddress(address));
    }



    private AddressModel createAddress(AddressModel address) {
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
        a.setAddressTypeId(addressTypeService.getByName(address.type).getId());
        a.setUserId(address.getUserId());
        a.setPostOfficeBoxId(postOfficeBoxService.getByPostalCode(address.getPostalCode()).getId());
        a.setStreetId(street.getId());
        a.setCityId(city.getId());
        a.setDistrictId(district.getId());
        a.setProvinceId(district.getProvinceId());
        a.setCountryId(district.getProvinceByProvinceId().getCountryId());
        a.setIsDefault(address.getIsDefaultAddress());
        a.setSavedAt(date);
        a.setLastUpdatedAt(date);
        a.setStatus(Status.LIVE_ACTIVE_STATUS);
        Address newAddress = this.addressService.create(a);
        //END::Saving address
        return AddressModel.entityToModel(newAddress);
    }


}
