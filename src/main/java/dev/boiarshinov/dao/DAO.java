package dev.boiarshinov.dao;

import java.util.List;

public interface DAO<T> {

    <T> List<T> getAll() throws DaoException;

    void add(T t) throws DaoException;

    void deleteById(int id) throws DaoException;
}
