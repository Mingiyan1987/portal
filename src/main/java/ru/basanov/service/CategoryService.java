package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Category;
import ru.basanov.repository.CategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Transactional(readOnly = true)
    public List<Category> getAll() {
        Iterable<Category> categories = categoryRepository.findAll();
        return (List<Category>) categories;
    }

    @Transactional(readOnly = true)
    public Category get(Long id) {
        return categoryRepository.findById(id).get();
    }


    public Optional<Category> findById(Long s) {
        return categoryRepository.findById(s);
    }

    @Transactional
    public <S extends Category> S save(S s) {
        return categoryRepository.save(s);
    }

    public <S extends Category> Iterable<S> saveAll(Iterable<S> iterable) {
        return categoryRepository.saveAll(iterable);
    }

    public boolean existsById(Long s) {
        return categoryRepository.existsById(s);
    }

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Iterable<Category> findAllById(Iterable<Long> iterable) {
        return categoryRepository.findAllById(iterable);
    }

    public long count() {
        return categoryRepository.count();
    }

    public void deleteById(Long s) {
        categoryRepository.deleteById(s);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteAll(Iterable<? extends Category> iterable) {
        categoryRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        categoryRepository.deleteAll();
    }

}
