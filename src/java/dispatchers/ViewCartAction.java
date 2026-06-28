
package dispatchers;


import java.util.Map;
import javax.servlet.http.*;
import model.CartItem;

/**
 * Action class responsible for displaying the shopping cart.
 *
 * @author Ava Nematpour
 */

public class ViewCartAction implements Action {
    
     /**
     * Displays the shopping cart if it contains items; otherwise returns
     * the user to the book titles page.
     *
     * @param request the HTTP request from the client
     * @param response the HTTP response sent to the client
     * @return the shopping cart page or the book titles page
     * @throws Exception if an error occurs while processing the request
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        Map<String, CartItem> cart =
                (Map<String, CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "/jsp/titles.jsp";
        }

        return "/jsp/cart.jsp";
    }
}