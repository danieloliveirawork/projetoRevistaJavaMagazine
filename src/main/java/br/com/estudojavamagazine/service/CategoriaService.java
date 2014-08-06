package br.com.estudojavamagazine.service;

import java.io.Serializable;
import java.util.List;

import br.com.estudojavamagazine.dao.lang.DaoException;
import br.com.estudojavamagazine.domain.Categoria;
import br.com.estudojavamagazine.service.lang.CategoriaException;

public interface CategoriaService extends Serializable {

	public abstract Categoria saveOrUpdate(Categoria categoria) throws DaoException;

	public abstract Categoria findCategoria(Long codigo) throws DaoException;

	public abstract List<Categoria> findAllCategorias() throws DaoException;

	public abstract void removerCategoria(Long codigo) throws DaoException, CategoriaException;

	public abstract void removerCategoria(Categoria categoria) throws DaoException, CategoriaException;

	public abstract Categoria findByName(String nome);

}
