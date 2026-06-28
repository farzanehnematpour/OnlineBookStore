
package dispatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.CartItem;

/**
 * Action class responsible for adding selected books to the shopping cart.
 *
 * @author Ava Nematpour
 */

public class AddToCartAction implements Action {
    
     /**
     * Adds the selected books and quantities from the request to the cart.
     *
     * @param request the HTTP request containing selected book details
     * @param response the HTTP response sent to the client
     * @return the JSP page used to display the book titles
     * @throws Exception if an error occurs while processing the cart
     */

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
                          throws Exception {

        HttpSession session = request.getSession();

        Map<String, CartItem> cart =
                (Map<String, CartItem>) session.getAttribute("cart");

        String[] selectedBooks =
                request.getParameterValues("add");

        String nextPage = "/jsp/titles.jsp";

        if (selectedBooks == null || selectedBooks.length == 0) {
            return nextPage;
        }

        if (cart == null) {
            cart = new HashMap<String, CartItem>();
        }

        for (String isbn : selectedBooks) {

            int quantity =
                    Integer.parseInt(request.getParameter(isbn));

            Book book = getBookFromList(isbn, session);
            if (book == null) {
                continue;
            }

            if (cart.containsKey(isbn)) {

                CartItem item = cart.get(isbn);
                item.setQuantity(quantity);

            } else {

                CartItem item = new CartItem(book);
                item.setQuantity(quantity);
                cart.put(isbn, item);
            }
        }

        session.setAttribute("cart", cart);

        return nextPage;
    }
    
    /**
     * Finds a book in the session book list by its ISBN.
     *
     * @param isbn the ISBN of the book to find
     * @param session the current user session containing the book list
     * @return the matching book, or null if no book is found
     */

    private Book getBookFromList(String isbn,
                                 HttpSession session) {

        List<Book> list =
                (List<Book>) session.getAttribute("books");
        
         if (list == null) {
        return null;
        }

        Book aBook = null;

        for (Book book : list) {

            if (isbn.equals(book.getIsbn())) {
                aBook = book;
                break;
            }
        }

        return aBook;
    }
}