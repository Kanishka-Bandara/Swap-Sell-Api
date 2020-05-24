package com.aradnab.boot.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class DefaultModel<M, S> {

    public abstract M entityToModel(S service);

    public List<M> entityToModel(List<S> service) {
        List<M> l = new ArrayList<>();
        service.forEach(s -> {
            l.add(entityToModel(s));
        });
        return l;
    }

    public List<M> entityToModel(Collection<S> service) {
        List<M> l = new ArrayList<>();
        service.forEach(s -> {
            l.add(entityToModel(s));
        });
        return l;
    }
}
