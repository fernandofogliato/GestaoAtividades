package br.com.fogliato.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class GenericDaoImpl implements Serializable, GenericDao {

	private static final long serialVersionUID = 1L;

	public enum MatchMode { START, END, EXACT, ANYWHERE }

    public enum Order { ASC, DESC }

    @Inject
    protected EntityManager entityManager;

    /**
     * Finds an entity by one of its properties.
     * 
     * @param clazz the entity class.
     * @param propertyName the property name.
     * @param value the value by which to find.
     * @return
     */
    public <T> List<T> findByProperty(Class<T> clazz, String propertyName, Object value) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);

        Root<T> root = query.from(clazz);
        query.where(builder.equal(root.get(propertyName), value));

        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Finds entities by a String property specifying a MatchMode. This search 
     * is case insensitive.
     * 
     * @param clazz the entity class.
     * @param propertyName the property name.
     * @param value the value to check against.
     * @param matchMode the match mode: EXACT, START, END, ANYWHERE.
     * @return
     */
    public <T> List<T> findByProperty(Class<T> clazz, String propertyName, String value, MatchMode matchMode) {
        value = value.toLowerCase();

        switch (matchMode) {
        case START:
            value = value + "%";
            break;
        case END:
            value = "%" + value;
            break;
        case ANYWHERE:
            value = "%" + value + "%";
            break;
        default:
            break;
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.where(builder.like(builder.lower(root.<String>get(propertyName)), value));

        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Finds all objects of an entity class.
     * 
     * @param clazz the entity class.
     * @return
     */
    public <T> List<T> findAll(Class<T> clazz) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        query.from(clazz);
        return entityManager.createQuery(query).getResultList();
    }

    /**
     * Finds all objects of a class by the specified order.
     * 
     * @param clazz the entity class.
     * @param order the order: ASC or DESC.
     * @param propertiesOrder the properties on which to apply the ordering.
     * 
     * @return
     */
    public <T> List<T> findAll(Class<T> clazz, Order order, String... propertiesOrder) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(clazz);
        Root<T> root = query.from(clazz);

        List<javax.persistence.criteria.Order> orders = new ArrayList<>();
        for (String propertyOrder : propertiesOrder) {
            if (order.equals(Order.ASC)) {
                orders.add(builder.asc(root.get(propertyOrder)));
            } else {
                orders.add(builder.desc(root.get(propertyOrder)));
            }
        }
        query.orderBy(orders);

        return entityManager.createQuery(query).getResultList();
    }

    public void persist(Object object) {
        entityManager.getTransaction().begin();
        entityManager.persist(object);
        entityManager.getTransaction().commit();
    }

    public void merge(Object object) {
        entityManager.getTransaction().begin();
        entityManager.merge(object);
        entityManager.getTransaction().commit();
    }

    public void remove(Object object) {
        entityManager.getTransaction().begin();
        entityManager.remove(object);
        entityManager.getTransaction().commit();
    }

}
