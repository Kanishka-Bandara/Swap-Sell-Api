package com.aradnab.boot.consumer.controller;

import com.aradnab.boot.config.Status;
import com.aradnab.boot.consumer.model.BankCardModel;
import com.aradnab.boot.consumer.model.BankModel;
import com.aradnab.boot.consumer.service.BankCardService;
import com.aradnab.boot.consumer.service.BankService;
import com.aradnab.boot.consumer.service.CardTypeService;
import com.aradnab.boot.db_tier.entity.BankCard;
import com.aradnab.boot.general.service.service_controller.CRUDStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    CardTypeService cardTypeService;
    @Autowired
    BankService bankService;
    @Autowired
    BankCardService bankCardService;

    //    BEGIN::Bank Card Types Controllers
    //    BEGIN::Get All Types
    @GetMapping("/cardTypes/getAll")
    public ResponseEntity<List<Map<String, String>>> getBankCardTypes() {
        List<Map<String, String>> b = new ArrayList<>();
        cardTypeService.getAll().forEach(cardType -> {
            Map<String, String> m = new HashMap<>();
            m.put("id", cardType.getId() + "");
            m.put("type", cardType.getType());
            b.add(m);
        });
        return ResponseEntity.ok().body(b);
    }
    //    END::Get All Types
    //    END::Bank Card Types Controllers

    //    BEGIN::Bank Controllers
    //    BEGIN::Get All Banks
    @GetMapping("/getAll")
    public ResponseEntity<List<BankModel>> getBanks() {
        return ResponseEntity.ok().body(BankModel.defaultModel.entityToModel(bankService.getAll()));
    }
    //    END::Get All Banks
    //    END::Bank controllers

    //    BEGIN::Bank Card Controllers
    //    BEGIN::Get All Bank Cards by User
    @GetMapping("/card/getByUser/{userId}")
    public ResponseEntity<List<BankCardModel>> getBanks(@PathVariable("userId") int userId) {
        return ResponseEntity.ok().body(BankCardModel.defaultModel.entityToModel(bankCardService.getByUserId(userId)));
    }
    //    END::Get All Banks Cards by User

    //    BEGIN::Save Bank Card
    @PostMapping("/card/create")
    public ResponseEntity<BankCardModel> create(@RequestBody BankCardModel bankCardModel) {
        Date d = new Date();
        BankCard bankCard = new BankCard();
        bankCard.setBankId(bankCardModel.getBank().getId());
        bankCard.setBankByBankId(bankService.getByID(bankCardModel.getBank().getId()));
        bankCard.setUserId(bankCardModel.getUserId());
        bankCard.setCardTypeId(bankCardModel.getCardTypeId());
        bankCard.setExpDate(bankCardModel.getExpDate());
        bankCard.setCardNumber(bankCardModel.getCardNumber());
        bankCard.setSavedAt(d);
        bankCard.setLastUpdatedAt(d);
        bankCard.setStatus(Status.LIVE_ACTIVE_STATUS);
        return ResponseEntity.ok().body(BankCardModel.defaultModel.entityToModel(bankCardService.create(bankCard)));
    }
    //    END::Save Bank Card

    //    BEGIN::Update Bank Card
    @PostMapping("/card/edit")
    public ResponseEntity<BankCardModel> update(@RequestBody BankCardModel bankCardModel) {
        Date d = new Date();
        BankCard bankCard = bankCardService.getByID(bankCardModel.getId());
        if (bankCard.getBankId() != bankCardModel.getBank().getId()) {
            bankCard.setBankId(bankCardModel.getBank().getId());
            bankCard.setBankByBankId(bankService.getByID(bankCardModel.getBank().getId()));
        }
        bankCard.setUserId(bankCardModel.getUserId());
        if (bankCard.getCardTypeId() != bankCardModel.getCardTypeId())
            bankCard.setCardTypeId(bankCardModel.getCardTypeId());
        if (bankCard.getExpDate().equals(bankCardModel.getExpDate())) bankCard.setExpDate(bankCardModel.getExpDate());
        if (bankCard.getCardNumber().equals(bankCardModel.getCardNumber()))
            bankCard.setCardNumber(bankCardModel.getCardNumber());
        bankCard.setLastUpdatedAt(d);
        bankCard.setStatus(Status.LIVE_ACTIVE_STATUS);
        return ResponseEntity.ok().body(BankCardModel.defaultModel.entityToModel(bankCardService.create(bankCard)));
    }

    //    END::Update Bank Card

    //    BEGIN::Delete Bank Card
    @PostMapping("/card/delete")
    public ResponseEntity<HttpStatus> delete(@RequestBody BankCardModel bankCardModel) {
        Date d = new Date();
        BankCard bankCard = bankCardService.getByID(bankCardModel.getId());
        bankCard.setLastUpdatedAt(d);
        bankCard.setStatus(Status.DELETE_STATUS);
        CRUDStatus delete = bankCardService.delete(bankCardModel.getId());
        if (delete == CRUDStatus.DELETED)
            return ResponseEntity.ok().body(HttpStatus.OK);
        return ResponseEntity.ok().body(HttpStatus.FAILED_DEPENDENCY);
    }
    //    END::Delete Bank Card

    //    END::Bank Card controllers

}
