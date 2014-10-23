package ru.tsystems.tsproject.sbb.dao.api;


import ru.tsystems.tsproject.sbb.dao.DAOException;

import java.util.Collection;

/**
 * Base class for all other DAO-classes with all CRUD-operations
 * @param <T>
 *     Entity class, which DAO will extend this base class
 */

public interface CommonDAO <T> {

    /**
     * Create operation: saving new entity to database
     * @param t
     * Entity object
     * @param <T>
     * Entity class, which DAO will extend this base class
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     *
     */
    public <T> void create(T t) throws DAOException;

    /**
     * Get operation: entity will be got from database by its primary key
     * @param tID
     * Primary key of entity - database ID
     * @param <T>
     * Entity class, which DAO will extend this base class
     * @return T
     * Found in database entity
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public <T> T get(int tID) throws DAOException;

    /**
     * Get operation: all entities of specified type will be got from database
     * @param <T>
     * Entity class, which DAO will extend this base class
     * @return Collection
     * Collection of entities, which was found gy get operation
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public <T> Collection getAll() throws DAOException;

    /**
     * Update operation: modified entity will replace its previous version in database
     * @param t
     * Entity object
     * @param <T>
     * Entity class, which DAO will extend this base class
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public <T> void update(T t) throws DAOException;

    /**
     * Delete operation: entity will be removed from database after searching by its primary key  - database ID
     * @param tID
     * Primary key of entity
     * @param <T>
     * Entity class, which DAO will extend this base class
     * @throws DAOException
     * All exceptions thrown in method will be converted to DAOException
     */
    public <T> void delete(int tID) throws DAOException;
}
