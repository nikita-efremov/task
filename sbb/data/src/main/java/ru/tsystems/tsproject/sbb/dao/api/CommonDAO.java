package ru.tsystems.tsproject.sbb.dao.api;


import ru.tsystems.tsproject.sbb.exception.DAOException;

import java.util.Collection;

public interface CommonDAO <T> {
    public <T> void create(T t) throws DAOException;
    public <T> T get(int tID) throws DAOException;
    public <T> Collection getAll() throws DAOException;
    public <T> void update(T t) throws DAOException;
    public <T> void delete(int tID) throws DAOException;
}
