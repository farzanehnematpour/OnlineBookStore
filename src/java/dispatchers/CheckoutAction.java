
package dispatchers;


import javax.servlet.http.*;

/**
 * Action class responsible for displaying the checkout page.
 *
 * @author Ava Nematpour
 */

public class CheckoutAction implements Action {
    
    /**
     * Displays the checkout page.
     *
     * @param request the HTTP request from the client
     * @param response the HTTP response sent to the client
     * @return the checkout JSP page
     * @throws Exception if an error occurs while processing the request
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/jsp/checkout.jsp";
    }
}
