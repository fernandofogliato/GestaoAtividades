package br.com.fogliato.core.dao;

import java.util.List;

import br.com.fogliato.core.dao.GenericDaoImpl.MatchMode;
import br.com.fogliato.core.dao.GenericDaoImpl.Order;

public interface GenericDao {

    <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value);

    <T> List<T> findByProperty(Class<T> clazz, String propertyName, String value, MatchMode matchMode);

    <T> List<T> findAll(Class<T> clazz);

    <T> List<T> findAll(Class<T> clazz, Order order, String... propertiesOrder);

    void persist(Object object);

    void merge(Object object);

    void remove(Object object);
}
