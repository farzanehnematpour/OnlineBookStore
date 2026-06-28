package utility;

import java.util.*;
import java.sql.*;
import model.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * The {@code AdmitBookStoreDAO} class provides data access methods for retrieving book information from a database.
 */
public class AdmitBookStoreDAO {

    private EntityManagerFactory emf;

    public AdmitBookStoreDAO() {
        emf = Persistence.createEntityManagerFactory("BookShopPU");
    }

    public List<Book> getAllBooks() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT b FROM Book b", Book.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

}