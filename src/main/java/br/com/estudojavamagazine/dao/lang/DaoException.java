package br.com.estudojavamagazine.dao.lang;

public class DaoException extends Exception {

    private static final long serialVersionUID = -6081710426736957160L;

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

}
