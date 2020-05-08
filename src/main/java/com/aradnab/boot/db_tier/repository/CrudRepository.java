package com.aradnab.boot.db_tier.repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public class CrudRepository{
    public static List<Object> searchByName(EntityManager manager,Class entityClass,String columnName,String value) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Object> criteria = builder.createQuery(entityClass);
        Root<Object> from = criteria.from(entityClass);
        criteria.select(from);
        ParameterExpression<String> params = builder.parameter(String.class);
        criteria.where(builder.equal(from.get(columnName), params));
        TypedQuery<Object> query = manager.createQuery(criteria);
        query.setParameter(params, value);
        return query.getResultList();
    }
}
