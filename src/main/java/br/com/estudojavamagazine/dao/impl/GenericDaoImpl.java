package br.com.estudojavamagazine.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudojavamagazine.dao.GenericDao;
import br.com.estudojavamagazine.dao.lang.DaoException;

/**
 * Classe DAO genérica que implementa a interface {@link GenericDao}.
 * @param <T>
 * @param <ID>
 */
@Service("genericDao")
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Apresenta o log na aplicação.
     */
    private static final Log logger = LogFactory.getLog("GenericDaoImpl");

    /**
     * A classe do objeto.
     */
    private final Class<T> $Class;

    /**
     * Instancia um novo {@link GenericDaoImpl}.
     */
    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        if (getClass().getGenericSuperclass() instanceof ParameterizedType) {
            this.$Class = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } else {
            $Class = (Class<T>) getClass().getGenericSuperclass();
        }
    }

    @Override
    public Class<T> getObjectClass() {
        return this.$Class;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public T save(T object) throws DaoException {
        try {
            logger.debug("Salvando objeto " + object + "...");
            entityManager.persist(object);
            logger.debug("Objeto " + getObjectClass().getSimpleName() + " salvo com sucesso.");
        } catch (Exception e) {
            String messageError = "Erro ao salvar objeto " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }

        return object;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public T merge(T object) throws DaoException {
        try {
            logger.debug("Salvando objeto " + object + "...");
            object = entityManager.merge(object);
            logger.debug("Objeto " + getObjectClass().getSimpleName() + " salvo com sucesso.");
        } catch (Exception e) {
            String messageError = "Erro ao salvar objeto " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }

        return object;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public T load(T object) throws DaoException {
        try {
            logger.debug("Salvando objeto " + object + "...");
            entityManager.refresh(object);
            logger.debug("Objeto " + getObjectClass().getSimpleName() + " salvo com sucesso.");
        } catch (Exception e) {
            String messageError = "Erro ao salvar objeto " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }

        return object;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public T update(T object) throws DaoException {
        try {
            logger.debug("Atualizando objeto " + object + "...");
            entityManager.merge(object);
            logger.debug("Objeto " + getObjectClass().getSimpleName() + " atualizado com sucesso.");
        } catch (Exception e) {
            String messageError = "Erro ao atualizar objeto " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }

        return object;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(T object) throws DaoException {
        try {
            logger.debug("Removendo objeto " + object + "...");
            entityManager.remove(object);
            logger.debug("Objeto " + getObjectClass().getSimpleName() + " removido com sucesso.");
        } catch (Exception e) {
            String messageError = "Erro ao remover objeto " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

    @Override
    public List<T> listSearch(String query) throws DaoException {
        try {
            logger.debug("Executando query e retornando lista de resultados ...");
            return entityManager.createQuery(query, $Class).getResultList();
        } catch (Exception e) {
            String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listSearchParam(String query, Map<String, Object> params) throws DaoException {
        try {
            logger.debug("Executando query ...");
            Query q = entityManager.createQuery(query, $Class);
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
            logger.debug("Retornando lista de resultados...");
            return q.getResultList();
        } catch (Exception e) {
            String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> listSearchParam(String query, Map<String, Object> params, int maximum, int current) throws DaoException {
        try {
            logger.debug("Executando query ...");
            Query q = entityManager.createQuery(query, $Class).setMaxResults(maximum).setFirstResult(current);
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
            logger.debug("Retornando lista de resultados...");
            return q.getResultList();
        } catch (Exception e) {
            String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

    @Override
    public T searchById(ID id) throws DaoException {
        try {
            logger.debug("Obtem objeto com ID = " + id);
            T t = (T) entityManager.find($Class, id);
            return t;
        } catch (Exception e) {
            String messageError = "Erro ao carregar objeto do tipo " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T searchParam(String query, Map<String, Object> params) throws DaoException {
        try {
            logger.debug("Executando query ...");
            Query q = entityManager.createQuery(query, $Class);
            for (String key : params.keySet()) {
                q.setParameter(key, params.get(key));
            }
            logger.debug("Retornando lista de resultados...");
            T t = (T) q.getSingleResult();
            return t;
        } catch (Exception e) {
            String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

    @Override
    public List<T> createNamedQuery(String namedQuery) throws DaoException {
        return entityManager.createNamedQuery(namedQuery, $Class).getResultList();
    }
    
    @Override
    public List<T> all() throws DaoException {
        try {
            logger.debug("Obtendo todos os objetos do tipo " + getObjectClass().getSimpleName() + "...");
            List<T> list = entityManager.createQuery("FROM " + $Class.getName() + " ORDER BY id DESC", $Class).getResultList();
            return list;
        } catch (Exception e) {
            String messageError = "Erro ao executar query para " + getObjectClass().getSimpleName();
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

    @Override
    public void flush() throws DaoException {
        try {
            entityManager.flush();
        } catch (Exception e) {
            String messageError = "Erro ao atualizar da sessão.";
            logger.error(messageError, e);
            throw new DaoException(messageError, e);
        }
    }

}
