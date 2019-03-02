package ru.basanov.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public abstract class AbstractEntity {

    @PersistenceContext
    protected EntityManager entityManager;

    public <T> T getEntity(TypedQuery<T> query) {
        final List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public void persist(Object o) {
        entityManager.persist(o);
    }

    public <T> T merge(T t) {
        return entityManager.merge(t);
    }

    public void remove(Object o) {
        entityManager.remove(o);
    }

}
