package com.aradnab.boot.general.service.service_controller.initial;

import com.aradnab.boot.general.service.service_controller.CRUDStatus;

import java.util.List;

public interface ServiceController <T>{
    T create(T t);
    T getByID(int id);
    List<T> getAll();
    T update(T t);
    CRUDStatus delete(int id);
}
