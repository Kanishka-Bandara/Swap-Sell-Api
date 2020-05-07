package com.aradnab.boot.db_tier.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class City {
    private int id;
    private Integer districtId;
    private String city;
    private Date savedAt;
    private Date lastUpdatedAt;
    private Date deletedAt;
    private Byte status;
    private Collection<Address> addressesById;
    private District districtByDistrictId;
    private Collection<Street> streetsById;

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
    @Column(name = "district_id", nullable = true)
    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 45)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
        City city1 = (City) o;
        return id == city1.id &&
                Objects.equals(districtId, city1.districtId) &&
                Objects.equals(city, city1.city) &&
                Objects.equals(savedAt, city1.savedAt) &&
                Objects.equals(lastUpdatedAt, city1.lastUpdatedAt) &&
                Objects.equals(deletedAt, city1.deletedAt) &&
                Objects.equals(status, city1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, districtId, city, savedAt, lastUpdatedAt, deletedAt, status);
    }

    @OneToMany(mappedBy = "cityByCityId")
    public Collection<Address> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<Address> addressesById) {
        this.addressesById = addressesById;
    }

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id",insertable = false,updatable = false)
    public District getDistrictByDistrictId() {
        return districtByDistrictId;
    }

    public void setDistrictByDistrictId(District districtByDistrictId) {
        this.districtByDistrictId = districtByDistrictId;
    }

    @OneToMany(mappedBy = "cityByCityId")
    public Collection<Street> getStreetsById() {
        return streetsById;
    }

    public void setStreetsById(Collection<Street> streetsById) {
        this.streetsById = streetsById;
    }
}
