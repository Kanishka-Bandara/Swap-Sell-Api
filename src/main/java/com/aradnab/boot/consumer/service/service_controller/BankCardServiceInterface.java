package com.aradnab.boot.consumer.service.service_controller;

import com.aradnab.boot.db_tier.entity.BankCard;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankCardServiceInterface extends ServiceController<BankCard> {
    List<BankCard> getByUserId(int userId);
}
