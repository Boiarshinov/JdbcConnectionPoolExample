package dev.boiarshinov.dao;

import java.util.List;

public interface DAO<T> {

    <T> List<T> getAll();

    void add(T t);

    void deleteById(int id);
}
