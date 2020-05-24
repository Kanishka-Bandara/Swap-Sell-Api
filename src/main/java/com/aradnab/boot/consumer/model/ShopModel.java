package com.aradnab.boot.consumer.model;

import com.aradnab.boot.db_tier.entity.Shop;
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
public class ShopModel {
    private int id;
    private String shopID;
    private String shopName;
    private int ownerId;
    private String imgUrl;
    private double rating;
    private int status;

    public static ShopModel entityToModel(Shop shop) {
        ShopModel sm = new ShopModel();
        sm.setId(shop.getId());
        sm.setShopID("SH" + shop.getId());
        sm.setShopName(shop.getShopName());
        sm.setOwnerId(shop.getUserId());
        sm.setImgUrl(shop.getImageByImageId().getImgUrl());
        sm.setRating(5.0);
        sm.setStatus(shop.getStatus());
        return sm;
    }

    public static List<ShopModel> entityToModel(List<Shop> shops) {
        List<ShopModel> l = new ArrayList<>();
        shops.forEach(shop -> l.add(entityToModel(shop)));
        return l;
    }

    public static List<ShopModel> entityToModel(Collection<Shop> shops) {
        List<ShopModel> l = new ArrayList<>();
        shops.forEach(shop -> l.add(entityToModel(shop)));
        return l;
    }

}
