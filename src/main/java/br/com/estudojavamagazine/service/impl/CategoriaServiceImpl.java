package br.com.estudojavamagazine.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudojavamagazine.dao.CategoriaDao;
import br.com.estudojavamagazine.dao.lang.DaoException;
import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.service.CategoriaService;
import br.com.estudojavamagazine.service.lang.CategoriaException;
import br.com.estudojavamagazine.service.util.ObjectUtil;

@Service("categoriaService")
@Transactional(propagation = Propagation.REQUIRED)
public class CategoriaServiceImpl implements Serializable, CategoriaService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@Override
	public Categoria saveOrUpdate(Categoria categoria) throws DaoException {
		if (ObjectUtil.isNotNull(categoria)) {
			categoria = categoriaDao.merge(categoria);
		} else {
			categoria = categoriaDao.save(categoria);
		}
		return categoria;
	}
	
	@Override
	public Categoria findCategoria(Long codigo) throws DaoException {
		if (ObjectUtil.isNotNull(codigo)) {
             return categoriaDao.searchById(codigo);
		}
		return null;
	}

	@Override
	public List<Categoria> findAllCategorias() throws DaoException {
		return categoriaDao.createNamedQuery(Categoria.FIND_ALL_CATEGORIAS);
	}

	@Override
	public void removerCategoria(Categoria categoria) throws DaoException, CategoriaException {
		if (ObjectUtil.isNotNull(categoria) && ObjectUtil.isNotNull(categoria.getCodigo())) {
			removerCategoria(categoria.getCodigo());
		}
	}
	
	@Override
	public void removerCategoria(Long codigo) throws DaoException, CategoriaException {
		if (ObjectUtil.isNotNull(codigo)) {
			Categoria categoria = categoriaDao.searchById(codigo);
			if (ObjectUtil.isNotNullAndNotEmpty(categoria.getProdutos())) {
			    throw new CategoriaException("Existem produtos cadastrados para a categoria: " + categoria.getNome());
			} else if (ObjectUtil.isNotNull(categoria)) {
				try {
                    categoriaDao.delete(categoria);
                } catch (DaoException e) {
                    throw new CategoriaException(e);
                }
			} else {
				throw new CategoriaException("Categoria não existe ou já foi removida!");
			}
		}
	}

	@Override
	public Categoria findByName(String nome) {
		return categoriaDao.findByName(nome);
	}
	
}
