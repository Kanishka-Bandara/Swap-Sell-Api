package com.aradnab.boot.consumer.model;

import com.aradnab.boot.config.DefaultModel;
import com.aradnab.boot.db_tier.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankModel extends DefaultModel<BankModel, Bank> {

    public static BankModel defaultModel = new BankModel();

    private int id;
    private String bankName;

    @Override
    public BankModel entityToModel(Bank service) {
        return new BankModel(service.getId(),service.getBankName());
    }
}
