package com.aradnab.boot.general.model;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    int id;
    int userId;
    String type;
    String name;
    String postalCode;
    String postalArea;
    String street;
    String city;
    String district;
    String province;
    String country;
    byte isDefaultAddress;
    byte status;

    public static AddressModel entityToModel(int id, int userId, AddressType type, String name, PostOfficeBox postOfficeBox, Street street, City city, District district, Province province, Country country, byte isDefaultAddress, byte status) {
        AddressModel am = new AddressModel();
        am.setId(id);
        am.setUserId(userId);
        am.setType(type.getType());
        am.setName(name);
        am.setPostalCode(postOfficeBox.getPostalCode());
        am.setPostalArea(postOfficeBox.getPostalArea());
        am.setStreet(street.getStreet());
        am.setCity(city.getCity());
        am.setDistrict(district.getDistrict());
        am.setProvince(province.getProvince());
        am.setCountry(country.getCountry());
        am.setIsDefaultAddress(isDefaultAddress);
        am.setStatus(status);
        return am;
    }

    public static AddressModel entityToModel(Address address) {
        AddressModel am = new AddressModel();
        am.setId(address.getId());
        am.setUserId(address.getUserId());
        am.setType(address.getAddressTypeByAddressTypeId().getType());
        am.setName(address.getName());
        am.setPostalCode(address.getPostOfficeBoxByPostOfficeBoxId().getPostalCode());
        am.setPostalArea(address.getPostOfficeBoxByPostOfficeBoxId().getPostalArea());
        am.setStreet(address.getStreetByStreetId().getStreet());
        am.setCity(address.getCityByCityId().getCity());
        am.setDistrict(address.getDistrictByDistrictId().getDistrict());
        am.setProvince(address.getProvinceByProvinceId().getProvince());
        am.setCountry(address.getCountryByCountryId().getCountry());
        am.setIsDefaultAddress(address.getIsDefault());
        am.setStatus(address.getStatus());
        return am;
    }

    public static List<AddressModel> entityToModel(List<Address> addresses) {
        List<AddressModel> l = new ArrayList<>();
        addresses.forEach(address -> {
            if (address.getStatus() != Status.DELETE_STATUS) {
                l.add(entityToModel(address));
            }
        });
        return l;
    }

    public static List<AddressModel> entityToModel(Collection<Address> addresses) {
        List<AddressModel> l = new ArrayList<>();
        addresses.forEach(address -> {
            if (address.getStatus() != Status.DELETE_STATUS) {
                l.add(entityToModel(address));
            }
        });
        return l;
    }

}
