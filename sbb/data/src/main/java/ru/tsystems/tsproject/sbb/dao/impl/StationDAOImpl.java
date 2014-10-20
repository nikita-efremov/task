package ru.tsystems.tsproject.sbb.dao.impl;

import org.apache.log4j.Logger;
import ru.tsystems.tsproject.sbb.dao.api.StationDAO;
import ru.tsystems.tsproject.sbb.entity.Passenger;
import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.exception.DAOException;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: herr
 * Date: 02.10.14
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class StationDAOImpl extends AbstractDAOImpl<Station> implements StationDAO {

    private static final Logger log = Logger.getLogger(StationDAOImpl.class);

	public StationDAOImpl(EntityManager em) {
		super(em);
	}


    public Station getStationByName(String name) throws DAOException {
        try {
            EntityManager entityManager = getEntityManager();
            Query query = entityManager.createQuery("SELECT s FROM Station s where s.name = :name")
                    .setParameter("name", name);
            List stations = query.getResultList();
            if (stations.size() != 0) {
                return  (Station)stations.get(0);
            } else {
                return null;
            }
        } catch (IllegalArgumentException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("020001");
            throw daoException;
        } catch (TransactionRequiredException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("190001");
            throw daoException;
        } catch (QueryTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("150001");
            throw daoException;
        } catch (PessimisticLockException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("160001");
            throw daoException;
        } catch (LockTimeoutException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("170001");
            throw daoException;
        } catch (PersistenceException e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("100001");
            throw daoException;
        } catch (Exception e) {
            DAOException daoException = new DAOException(e.getMessage());
            daoException.initCause(e.getCause());
            daoException.setErrorCode("000001");
            throw daoException;
        }
    }
}
