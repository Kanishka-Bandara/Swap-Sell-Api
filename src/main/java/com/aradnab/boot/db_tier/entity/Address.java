package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Address {
    private int id;
    private String name;
    private int addressTypeId;
    private int userId;
    private Integer postOfficeBoxId;
    private Integer streetId;
    private Integer cityId;
    private Integer districtId;
    private Integer provinceId;
    private int countryId;
    private Byte isDefault;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private AddressType addressTypeByAddressTypeId;
    private User userByUserId;
    private PostOfficeBox postOfficeBoxByPostOfficeBoxId;
    private Street streetByStreetId;
    private City cityByCityId;
    private District districtByDistrictId;
    private Province provinceByProvinceId;
    private Country countryByCountryId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address_type_id", nullable = false)
    public int getAddressTypeId() {
        return addressTypeId;
    }

    public void setAddressTypeId(int addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "post_office_box_id", nullable = true)
    public Integer getPostOfficeBoxId() {
        return postOfficeBoxId;
    }

    public void setPostOfficeBoxId(Integer postOfficeBoxId) {
        this.postOfficeBoxId = postOfficeBoxId;
    }

    @Basic
    @Column(name = "street_id", nullable = true)
    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    @Basic
    @Column(name = "city_id", nullable = true)
    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "district_id", nullable = true)
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "province_id", nullable = true)
    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Basic
    @Column(name = "country_id", nullable = false)
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "is_default", nullable = true)
    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    @Basic
    @Column(name = "saved_at", nullable = true)
    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    @Basic
    @Column(name = "last_updated_at", nullable = true)
    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Basic
    @Column(name = "deleted_at", nullable = true)
    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                addressTypeId == address.addressTypeId &&
                userId == address.userId &&
                countryId == address.countryId &&
                Objects.equals(name, address.name) &&
                Objects.equals(postOfficeBoxId, address.postOfficeBoxId) &&
                Objects.equals(streetId, address.streetId) &&
                Objects.equals(cityId, address.cityId) &&
                Objects.equals(districtId, address.districtId) &&
                Objects.equals(provinceId, address.provinceId) &&
                Objects.equals(isDefault, address.isDefault) &&
                Objects.equals(savedAt, address.savedAt) &&
                Objects.equals(lastUpdatedAt, address.lastUpdatedAt) &&
                Objects.equals(deletedAt, address.deletedAt) &&
                Objects.equals(status, address.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, addressTypeId, userId, postOfficeBoxId, streetId, cityId, districtId, provinceId, countryId, isDefault, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @ManyToOne
    @JoinColumn(name = "address_type_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public AddressType getAddressTypeByAddressTypeId() {
        return addressTypeByAddressTypeId;
    }

    public void setAddressTypeByAddressTypeId(AddressType addressTypeByAddressTypeId) {
        this.addressTypeByAddressTypeId = addressTypeByAddressTypeId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "post_office_box_id", referencedColumnName = "id",insertable = false,updatable = false)
    public PostOfficeBox getPostOfficeBoxByPostOfficeBoxId() {
        return postOfficeBoxByPostOfficeBoxId;
    }

    public void setPostOfficeBoxByPostOfficeBoxId(PostOfficeBox postOfficeBoxByPostOfficeBoxId) {
        this.postOfficeBoxByPostOfficeBoxId = postOfficeBoxByPostOfficeBoxId;
    }

    @ManyToOne
    @JoinColumn(name = "street_id", referencedColumnName = "id",insertable = false,updatable = false)
    public Street getStreetByStreetId() {
        return streetByStreetId;
    }

    public void setStreetByStreetId(Street streetByStreetId) {
        this.streetByStreetId = streetByStreetId;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id",insertable = false,updatable = false)
    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id",insertable = false,updatable = false)
    public District getDistrictByDistrictId() {
        return districtByDistrictId;
    }

    public void setDistrictByDistrictId(District districtByDistrictId) {
        this.districtByDistrictId = districtByDistrictId;
    }

    @ManyToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id",insertable = false,updatable = false)
    public Province getProvinceByProvinceId() {
        return provinceByProvinceId;
    }

    public void setProvinceByProvinceId(Province provinceByProvinceId) {
        this.provinceByProvinceId = provinceByProvinceId;
    }

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
    public Country getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(Country countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }
}
