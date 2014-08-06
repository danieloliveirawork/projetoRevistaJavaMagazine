package br.com.estudojavamagazine.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.estudojavamagazine.dao.lang.DaoException;

public interface GenericDao <T, ID extends Serializable> {

        /**
         * Obter a classe do objeto.
         * @return a classe do objeto.
         */
        public Class<T> getObjectClass();
        
        /**
         * Persiste o objeto no banco de dados.
         * @param object o objeto que será persistido.
         * @return o objeto atualizado.
         * @throws DaoException 
         */
        public T save(T object) throws DaoException;
        
        /**
         * Persiste o objeto no banco de dados.
         * @param object o objeto que será persistido.
         * @return o objeto atualizado.
         * @throws DaoException 
         */
        public T merge(T object) throws DaoException;
        
        /**
         * Pesquisa o objeto pelo código.
         * @param id o código do objeto que será pesquisado.
         * @return o modelo pesquisado no banco de dados.
         * @throws DaoException 
         */
        public T searchById(ID id) throws DaoException;
        
        /**
         * Atualiza o objeto no banco de dados.
         * @param object o objeto que será atualizado.
         * @return o objeto atualizado.
         * @throws DaoException 
         */
        public T update(T object) throws DaoException;
        
        /**
         * Remove o objeto no banco de dados.
         * @param object o objeto a ser remove no banco de dados.
         * @throws DaoException 
         */
        public void delete(T object) throws DaoException;
        
        /**
         * Executar a named query informada.
         * @param namedQuery O nome da nameQuery.
         * @return Resultado da namedQuey informada.
         * @throws DaoException
         */
        public List<T> createNamedQuery(String namedQuery) throws DaoException;
        
        /**
         * Obtém todos os objetos persistidos.
         * @return o conjunto de objetos no banco de dados.
         * @throws DaoException 
         */
        public List<T> all() throws DaoException;
        
        /**
         * Obtém um conjunto de objetos pesquisado pela query passada pelo parâmetro.<br />
         * Deverão ser passados os parâmetros para a query através de um {@link Map}.
         * @param query a query para a pesquisa no banco de dados.
         * @param params os parâmetros para a query.
         * @return o conjunto de objetos resultado da pesquisa.
         * @throws DaoException 
         */
        public List<T> listSearchParam(String query, Map<String, Object> params) throws DaoException;
        
        /**
         * Obtém um conjunto de objetos pesquisado pela query passada pelo parâmetro.<br />
         * Deverão ser passados os parâmetros para a query através de um {@link Map}.<br />
         * Neste método, há a possibilidade informar o máximo de resultados e o resultado corrente.
         * @param query a query para a pesquisa no banco de dados.
         * @param params os parâmetros para a query.
         * @param maximum o máximo de resultados.
         * @param current o resultado corrente.
         * @return o conjunto de objetos resultado da pesquisa.
         * @throws DaoException 
         */
        public List<T> listSearchParam(String query, Map<String, Object> params, int maximum, int current) throws DaoException;
        
        /**
         * Obtém um conjunto de objetos pesquisado pela query passada pelo parâmetro.
         * @param query a query para a pesquisa no banco de dados.
         * @return o conjunto de objetos resultado da pesquisa.
         * @throws DaoException 
         */
        public List<T> listSearch(String query) throws DaoException;
        
        /**
         * Obtém o objeto resultante da query passada pelo parâmetro.
         * @param query a query para a pesquisa no banco de dados.
         * @param params os parâmetros para a query.
         * @return o objeto resultado da pesquisa.
         * @throws DaoException 
         */
        public T searchParam(String query, Map<String, Object> params) throws DaoException;

        /**
         * Força para a sessão deste flush. Deve ser chamado no final de uma unidade
         * de trabalho, antes de confirmar a operação e fechar a sessão (dependendo do modo
         * flush, Transaction.commit () chama este método).
         * @throws DaoException 
         */
        void flush() throws DaoException;

        /**
         * Carrega o objeto persistido na base de dados.
         * @param object O objeto que ser carregado.
         * @return O objeto da base de dados.
         * @throws DaoException
         */
        T load(T object) throws DaoException;
}