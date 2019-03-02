package ru.basanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.basanov.model.Company;
import ru.basanov.repository.CompanyRepository;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public <S extends Company> S save(S s) {
        return companyRepository.save(s);
    }

    @Transactional(readOnly = true)
    public Optional<Company> findById(String s) {
        return companyRepository.findById(s);
    }

    public <S extends Company> Iterable<S> saveAll(Iterable<S> iterable) {
        return companyRepository.saveAll(iterable);
    }

    public boolean existsById(String s) {
        return companyRepository.existsById(s);
    }

    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    public Iterable<Company> findAllById(Iterable<String> iterable) {
        return companyRepository.findAllById(iterable);
    }

    public long count() {
        return companyRepository.count();
    }

    public void deleteById(String s) {
        companyRepository.deleteById(s);
    }

    public void delete(Company company) {
        companyRepository.delete(company);
    }

    public void deleteAll(Iterable<? extends Company> iterable) {
        companyRepository.deleteAll(iterable);
    }

    public void deleteAll() {
        companyRepository.deleteAll();
    }
}
