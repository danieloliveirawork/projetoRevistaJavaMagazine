package br.com.estudojavamagazine.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import br.com.estudojavamagazine.dao.CategoriaDao;
import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.service.util.ObjectUtil;

@Service("categoriaDao")
public class CategoriaDaoImpl extends GenericDaoImpl<Categoria, Long> implements CategoriaDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Categoria findByName(String nome) {
        List<Categoria> list = entityManager.createNamedQuery(Categoria.FIND_CATEGORIA_BY_NAME, getObjectClass())
                .setParameter("nome", nome)
                .getResultList();
        if (ObjectUtil.isNotNullAndNotEmpty(list)) {
            return list.get(0);
        } else {
            return null;
        }
    }
    
}
