package br.com.estudojavamagazine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.estudojavamagazine.dao.ProdutoDao;
import br.com.estudojavamagazine.dao.lang.DaoException;
import br.com.estudojavamagazine.domain.Produto;
import br.com.estudojavamagazine.service.ProdutoService;
import br.com.estudojavamagazine.service.lang.ProdutoException;
import br.com.estudojavamagazine.service.util.ObjectUtil;

@Service("produtoService")
@Transactional(propagation = Propagation.REQUIRED)
public class ProdutoServiceimpl implements ProdutoService {

	private static final long serialVersionUID = 3095786918632665898L;
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Override
	public Produto saveOrUpdate(Produto produto) throws ProdutoException {
		try {
			if (ObjectUtil.isNotNull(produto.getCodigo())) {
				produto = produtoDao.update(produto);
			} else {
				produto = produtoDao.save(produto);
			}
		} catch (Exception e) {
			throw new ProdutoException(e);
		}
		return produto;
	}

	@Override
	public Produto findProduto(Long codigo) throws ProdutoException {
		if (ObjectUtil.isNotNull(codigo)) {
			try {
				return produtoDao.searchById(codigo);
			} catch (DaoException e) {
				throw new ProdutoException(e);
			}
		} else {
			return null;
		}
	}

	@Override
	public List<Produto> findAllProdutos() throws ProdutoException {
		try {
			return produtoDao.createNamedQuery(Produto.FIND_ALL_PRODUTO);
		} catch (DaoException e) {
			throw new ProdutoException(e);
		}
	}

	@Override
	public void removerProduto(Produto produto) throws ProdutoException {
		if (ObjectUtil.isNotNull(produto) && ObjectUtil.isNotNull(produto.getCodigo())) {
			removerProduto(produto.getCodigo());
		}
	}
	
	@Override
	public void removerProduto(Long codigo) throws ProdutoException {
		if (ObjectUtil.isNotNull(codigo)) {
			try {
				Produto produto = produtoDao.searchById(codigo);
				if (ObjectUtil.isNotNull(produto)) {
					produtoDao.delete(produto);
				} else {
					throw new ProdutoException("Produto não existe ou já foi removida!");
				}
			} catch (DaoException e) {
				throw new ProdutoException(e);
			}
		}
	}

	@Override
	public List<Produto> findProdutoByNomeCategoriaAndNomeProduto(String nomeCategoria, String nomeProduto) {
		if (ObjectUtil.isNotNull(nomeCategoria) && ObjectUtil.isNotNull(nomeProduto)) {
			return produtoDao.findProdutoByNomeCategoriaAndNomeProduto(nomeCategoria, nomeProduto);
		}
		return new ArrayList<Produto>();
	}

	@Override
	public List<Produto> findProdutoByNomeCategoria(String nomeCategoria) {
		if (ObjectUtil.isNotNull(nomeCategoria)) {
			return produtoDao.findProdutoByNomeCategoria(nomeCategoria);
		}
		return new ArrayList<Produto>();
	}

}
