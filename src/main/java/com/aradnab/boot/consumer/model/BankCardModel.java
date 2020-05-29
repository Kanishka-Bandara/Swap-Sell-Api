package com.aradnab.boot.consumer.model;

import com.aradnab.boot.config.DefaultModel;
import com.aradnab.boot.db_tier.entity.BankCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankCardModel extends DefaultModel<BankCardModel, BankCard> {

    public static BankCardModel defaultModel = new BankCardModel();

    private int id;
    private BankModel bank;
    private int userId;
    private int cardTypeId;
    private String cardType;
    private Date expDate;
    private String cardNumber;
    private Byte status;

    @Override
    public BankCardModel entityToModel(BankCard service) {
        return new BankCardModel(
                service.getId(),
                BankModel.defaultModel.entityToModel(service.getBankByBankId()),
                service.getUserId(),
                service.getCardTypeId(),
                service.getCardTypeByCardTypeId().getType(),
                service.getExpDate(),
                service.getCardNumber(),
                service.getStatus()
        );
    }
}
