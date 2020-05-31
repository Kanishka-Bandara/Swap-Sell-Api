package com.aradnab.boot.consumer.controller;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.db_tier.entity.Image;
import com.aradnab.boot.db_tier.entity.Shop;
import com.aradnab.boot.consumer.model.ShopModel;
import com.aradnab.boot.general.service.ImageService;
import com.aradnab.boot.general.service.UserService;
import com.aradnab.boot.general.service.service_controller.ShopServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopServiceInterface shopServiceInterface;
    @Autowired
    ImageService imageService;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ShopModel> create(@RequestBody ShopModel shop) {
        Date d = new Date();
//        BEGIN::Create Image
            Image image = new Image();
//        if (!shop.getImgUrl().isEmpty()) {
            image.setImgUrl(shop.getImgUrl());
            image.setSavedAt(d);
            image.setLastUpdatedAt(d);
            image.setStatus(Status.LIVE_ACTIVE_STATUS);
            image = imageService.create(image);
//        }
//        END::Create Image
        Shop s = new Shop();
        s.setUserId(shop.getOwnerId());
        s.setUserByUserId(userService.getByID(shop.getOwnerId()));
        s.setImageId(image.getId());
        s.setImageByImageId(image);
        s.setIsActive((byte) 1);
        s.setShopName(shop.getShopName());
        s.setSavedAt(d);
        s.setLastUpdatedAt(d);
        s.setStatus(Status.LIVE_ACTIVE_STATUS);
        s.setImageByImageId(image);
        s.setShopRatingsById(null);
        Shop newShop = shopServiceInterface.create(s);
        return ResponseEntity.ok().body(ShopModel.entityToModel(newShop));
    }

    @PostMapping("/edit")
    public ResponseEntity<ShopModel> edit(@RequestBody ShopModel shop) {
        Date d = new Date();
        Image i = new Image();
        i.setImgUrl(shop.getImgUrl());
        i.setSavedAt(d);
        i.setLastUpdatedAt(d);

        i.setStatus(Status.LIVE_ACTIVE_STATUS);
        Image image = imageService.create(i);
        Shop s = new Shop();
        s.setUserId(shop.getOwnerId());
        s.setUserByUserId(userService.getByID(shop.getOwnerId()));
        s.setImageId(image.getId());
        s.setImageByImageId(image);
        s.setIsActive((byte) 1);
        s.setShopName(shop.getShopName());
        s.setSavedAt(d);
        s.setLastUpdatedAt(d);
        s.setStatus(Status.LIVE_ACTIVE_STATUS);
        s.setImageByImageId(image);
        s.setShopRatingsById(null);
        Shop newShop = shopServiceInterface.create(s);
        return ResponseEntity.ok().body(ShopModel.entityToModel(newShop));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ShopModel> getById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(ShopModel.entityToModel(shopServiceInterface.getByID(id)));
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ShopModel> getByUserId(@PathVariable("userId") int userId) {
        return ResponseEntity.ok().body(ShopModel.entityToModel(shopServiceInterface.getByUserID(userId)));
    }

}
