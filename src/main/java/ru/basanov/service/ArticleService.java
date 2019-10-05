package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.basanov.model.Article;
import ru.basanov.model.Author;
import ru.basanov.repository.ArticleRepository;
import ru.basanov.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

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

    @PreAuthorize("hasAuthority('user') or hasAuthority('admin')")
    @Transactional
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public Page<Article> getALL(PageRequest pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles;
    }

    @Transactional(readOnly = true)
    public Iterable<Author> getAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Article> get(Long id) {
        return articleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<Article> getByCategoryId(Long id, Pageable pageable) {
        Page<Article> articles = articleRepository.findByCategoryId(id, pageable);
        return articles;
    }

    public Optional<Article> findById(Long s) {
        return articleRepository.findById(s);
    }

    public <S extends Article> Iterable<S> saveAll(Iterable<S> iterable) {
        return articleRepository.saveAll(iterable);
    }

    public boolean existsById(Long s) {
        return articleRepository.existsById(s);
    }

    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    public long count() {
        return articleRepository.count();
    }

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    @Transactional
    public String deleteById(Long id) {
        articleRepository.deleteById(id);
        return "redirec:/";
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public void deleteAll(Iterable<? extends Article> iterable) {
        articleRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        articleRepository.deleteAll();
    }

    @PreAuthorize("hasAuthority('admin')")
    @Transactional
    public void update(Article article) {
        Article updatableArticle = articleRepository.findOne(article.getId());
        updatableArticle.setContent(article.getContent());
        updatableArticle.setTitle(article.getTitle());
        if (article.getCategory() != null) {
            updatableArticle.setCategory(article.getCategory());
        }
        articleRepository.save(updatableArticle);
    }
}
