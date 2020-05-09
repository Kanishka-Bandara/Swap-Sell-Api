package com.aradnab.boot.general.service.service_controller;

import com.aradnab.boot.db_tier.entity.Title;
import com.aradnab.boot.general.service.service_controller.initial.ServiceController;
import org.springframework.data.repository.query.Param;

public interface TitleServiceInterface extends ServiceController<Title> {
    Title getByTitleName(@Param("id") String title);
}
