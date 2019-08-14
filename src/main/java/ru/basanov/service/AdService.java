package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Ad;
import ru.basanov.repository.AdRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Service
public class AdService {

    @Autowired
    private AdRepository adRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void persist(Object o) {
        entityManager.persist(o);
    }

    @Transactional
    public <T> T merge(T t) {
        return entityManager.merge(t);
    }

    @Transactional
    public void remove(Object o) {
        entityManager.remove(o);
    }

    @Transactional
    public void save(Ad ad) {
        adRepository.save(ad);
    }

    @Transactional(readOnly = true)
    public Page<Ad> getALL(PageRequest pageable) {
        Page<Ad> ads = adRepository.findAll(pageable);
        return ads;
    }

    @Transactional(readOnly = true)
    public Optional<Ad> get(Long id) {
        return adRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Ad> getByCatgoryId(Long id, Pageable pageable) {
        Page<Ad> ads = adRepository.findByCategoryId(id, pageable);
        return ads;
    }


    public Optional<Ad> findById(Long s) {
        return adRepository.findById(s);
    }

    public <S extends Ad> Iterable<S> saveAll(Iterable<S> iterable) {
        return adRepository.saveAll(iterable);
    }

    public boolean existsById(Long s) {
        return adRepository.existsById(s);
    }

    public Iterable<Ad> findAll() {
        return adRepository.findAll();
    }


    public long count() {
        return adRepository.count();
    }

    public void deleteById(Long s) {
        adRepository.deleteById(s);
    }

    public void delete(Ad ad) {
        adRepository.delete(ad);
    }

    public void deleteAll(Iterable<? extends Ad> iterable) {
        adRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        adRepository.deleteAll();
    }

/*
    public void update(Ad ad) {
        adRepository.update(ad);
    }
*/
}
