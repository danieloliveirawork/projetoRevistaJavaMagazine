package br.com.estudojavamagazine.dao;

import java.util.List;

import br.com.estudojavamagazine.domain.Produto;

public interface ProdutoDao extends GenericDao<Produto, Long> {


	public List<Produto> findProdutoByNomeCategoria(String nomeCategoria);

	public List<Produto> findProdutoByNomeCategoriaAndNomeProduto(String nomeCategoria, String nomeProduto);

}
