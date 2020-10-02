package com.aradnab.boot.consumer.controller;

import com.aradnab.boot.consumer.model.HeadCategoryModel;
import com.aradnab.boot.consumer.model.MainCategoryModel;
import com.aradnab.boot.consumer.model.SubCategoryModel;
import com.aradnab.boot.consumer.service.ProductHeadCategoryService;
import com.aradnab.boot.consumer.service.ProductMainCategoryService;
import com.aradnab.boot.consumer.service.ProductSubCategoryService;
import com.aradnab.boot.db_tier.entity.ProductHeadCategory;
import com.aradnab.boot.db_tier.entity.ProductMainCategory;
import com.aradnab.boot.db_tier.entity.ProductSubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/category")
public class ProductCategoryController {

    @Autowired
    ProductHeadCategoryService productHeadCategoryService;
    @Autowired
    ProductMainCategoryService productMainCategoryService;
    @Autowired
    ProductSubCategoryService productSubCategoryService;

    //  BEGIN::Create
    @PostMapping("/head/create")
    public ResponseEntity<HeadCategoryModel> saveHeadCategoryModel(@RequestBody HeadCategoryModel headCategoryModel) {
        Date d = new Date();
        ProductHeadCategory hc = new ProductHeadCategory();
        hc.setCategoryName(headCategoryModel.getCatName());
        hc.setSavedAt(d);
        hc.setLastUpdatedAt(d);
        ProductHeadCategory headCategory = productHeadCategoryService.create(hc);
        return ResponseEntity.ok().body(HeadCategoryModel.defaultModel.entityToModel(headCategory));
    }

    @PostMapping("/main/create")
    public ResponseEntity<MainCategoryModel> saveMainCategoryModel(@RequestBody MainCategoryModel mainCategoryModel) {
        Date d = new Date();
        ProductMainCategory mc = new ProductMainCategory();
        mc.setCategoryName(mainCategoryModel.getCatName());
        mc.setSavedAt(d);
        mc.setLastUpdatedAt(d);
        ProductMainCategory headCategory = productMainCategoryService.create(mc);
        return ResponseEntity.ok().body(MainCategoryModel.defaultModel.entityToModel(headCategory));
    }

    @PostMapping("/sub/create")
    public ResponseEntity<SubCategoryModel> saveSubCategoryModel(@RequestBody SubCategoryModel subCategoryModel) {
        Date d = new Date();
        ProductSubCategory sc = new ProductSubCategory();
        sc.setCategoryName(subCategoryModel.getCatName());
        sc.setSavedAt(d);
        sc.setLastUpdatedAt(d);
        ProductSubCategory headCategory = productSubCategoryService.create(sc);
        return ResponseEntity.ok().body(SubCategoryModel.defaultModel.entityToModel(headCategory));
    }

    //    END::CREATE
//    BEGIN::Get by Id
    @GetMapping("/head/get/{id}")
    public ResponseEntity<HeadCategoryModel> getHeadCategoryModelById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(HeadCategoryModel.defaultModel.entityToModel(productHeadCategoryService.getByID(id)));
    }


    @GetMapping("/main/get/{id}")
    public ResponseEntity<MainCategoryModel> getMainCategoryModelById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(MainCategoryModel.defaultModel.entityToModel(productMainCategoryService.getByID(id)));
    }

    @GetMapping("/sub/get/{id}")
    public ResponseEntity<SubCategoryModel> getSubCategoryModelById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(SubCategoryModel.defaultModel.entityToModel(productSubCategoryService.getByID(id)));
    }
//    END::Get by Id

    //    BEGIN::Get by Name
    @PostMapping("/head/getByName")
    public ResponseEntity<HeadCategoryModel> getHeadCategoryModelByName(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok().body(HeadCategoryModel.defaultModel.entityToModel(productHeadCategoryService.getByName(request.get("name"))));
    }

    @PostMapping("/main/getByName")
    public ResponseEntity<MainCategoryModel> getMainCategoryModelByName(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok().body(MainCategoryModel.defaultModel.entityToModel(productMainCategoryService.getByName(request.get("name"))));
    }

    @PostMapping("/sub/getByName")
    public ResponseEntity<SubCategoryModel> getSubCategoryModelByName(@RequestBody Map<String, String> request) {
        return ResponseEntity.ok().body(SubCategoryModel.defaultModel.entityToModel(productSubCategoryService.getByName(request.get("name"))));
    }

    //    END::Get by Name
//Begin :: Get All
    @GetMapping("/head/getAll")
    public ResponseEntity<List<HeadCategoryModel>> getHeadCategories() {
        List<ProductHeadCategory> all = productHeadCategoryService.getAll();
        System.out.println(all);
        return ResponseEntity.ok().body(HeadCategoryModel.defaultModel.entityToModel(productHeadCategoryService.getAll()));
    }
//End :: Get All
}
