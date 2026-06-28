
package dispatchers;


import java.util.List;
import javax.servlet.http.*;
import model.Book;
import utility.AdmitBookStoreDAO;

/**
 * Action class responsible for retrieving all books from the database.
 *
 * @author Ava Nematpour
 */

public class ViewBooksAction implements Action {
    
     /**
     * Retrieves all books from the database and stores them in the user's session.
     *
     * @param request the HTTP request from the client
     * @param response the HTTP response sent to the client
     * @return the JSP page that displays the available books
     * @throws Exception if an error occurs while retrieving the books
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        

        AdmitBookStoreDAO dao = new AdmitBookStoreDAO();
        List<Book> books = dao.getAllBooks();
        System.err.println("BOOKS SIZE = " + books.size());
        HttpSession session = request.getSession();
        session.setAttribute("books", books);

        return "/jsp/titles.jsp";
    }
}
