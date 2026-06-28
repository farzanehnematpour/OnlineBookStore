
package dispatchers;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CartItem;

/**
 * Action class responsible for updating the shopping cart.
 *
 * @author Ava Nematpour
 */

public class UpdateCartAction implements Action {
    
     /**
     * Updates the quantities of books in the shopping cart and removes
     * any selected items.
     *
     * @param request the HTTP request containing updated cart information
     * @param response the HTTP response sent to the client
     * @return the shopping cart JSP page
     * @throws Exception if an error occurs while updating the cart
     */

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response)
                          throws Exception {

        HttpSession session = request.getSession();

        Map<String, CartItem> cart =
                (Map<String, CartItem>) session.getAttribute("cart");

        String[] booksToRemove =
                request.getParameterValues("remove");

        if (booksToRemove != null) {

            for (String bookToRemove : booksToRemove) {
                cart.remove(bookToRemove);
            }
        }

        Set<Map.Entry<String, CartItem>> entries =
                cart.entrySet();

        Iterator<Map.Entry<String, CartItem>> iter =
                entries.iterator();

        while (iter.hasNext()) {

            Map.Entry<String, CartItem> entry =
                    iter.next();

            String isbn = entry.getKey();

            CartItem item = entry.getValue();

            int quantity =
                    Integer.parseInt(request.getParameter(isbn));

            item.updateQuantity(quantity);
        }

        return "/jsp/cart.jsp";
    }
}