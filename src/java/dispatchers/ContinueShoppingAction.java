
package dispatchers;

import javax.servlet.http.*;
/**
 * Action class responsible for returning the user to the book titles page.
 *
 * @author Ava Nematpour
 */

public class ContinueShoppingAction implements Action {
    
     /**
     * Returns the user to the book titles page.
     *
     * @param request the HTTP request from the client
     * @param response the HTTP response sent to the client
     * @return the JSP page that displays the available books
     * @throws Exception if an error occurs while processing the request
     */

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "/jsp/titles.jsp";
    }
}
