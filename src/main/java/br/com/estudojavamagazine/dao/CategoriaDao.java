package br.com.estudojavamagazine.dao;

import br.com.estudojavamagazine.domain.Categoria;

public interface CategoriaDao extends GenericDao<Categoria, Long> {

    public Categoria findByName(String nome);

}
