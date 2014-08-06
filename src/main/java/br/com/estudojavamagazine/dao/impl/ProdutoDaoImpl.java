package br.com.estudojavamagazine.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import br.com.estudojavamagazine.dao.ProdutoDao;
import br.com.estudojavamagazine.domain.Produto;
import br.com.estudojavamagazine.service.util.ObjectUtil;


@Service("produtoDao")
public class ProdutoDaoImpl extends GenericDaoImpl<Produto, Long> implements ProdutoDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findProdutoByNomeCategoriaAndNomeProduto(String nomeCategoria, String nomeProduto) {
		if (ObjectUtil.isNotNull(nomeCategoria) && ObjectUtil.isNotNull(nomeProduto)) {
			Query query = entityManager.createNamedQuery(Produto.FIND_PRODUTO_BY_CATEGORIA_PRODUTO, getObjectClass())
					.setParameter("nomeCat", nomeCategoria)
					.setParameter("nomeProd", nomeProduto);
			return query.getResultList();
		}
		return new ArrayList<Produto>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> findProdutoByNomeCategoria(String nomeCategoria) {
		if (ObjectUtil.isNotNull(nomeCategoria)) {
			Query query = entityManager.createNamedQuery(Produto.FIND_PRODUTO_BY_CATEGORIA, getObjectClass())
					.setParameter("nomeCat", nomeCategoria);
			return query.getResultList();
		}
		return new ArrayList<Produto>();
	}
	
}
