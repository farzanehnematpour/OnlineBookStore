package controller;

import dispatchers.Action;
import dispatchers.AddToCartAction;
import dispatchers.CheckoutAction;
import dispatchers.ContinueShoppingAction;
import dispatchers.UpdateCartAction;
import dispatchers.ViewBooksAction;
import dispatchers.ViewCartAction;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * FrontController class to handle HTTP requests and responses.
 */
public class FrontController extends HttpServlet {
    
    private final Map<String, Action> actions =
        new HashMap<String, Action>();
    private String defaultAction;
    
    //private final HashMap actions = new HashMap();

    /**
     * Initialize global variables.
     * @param config ServletConfig object
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        defaultAction = config.getInitParameter("defaultAction");
        // Additional initialization code can be added here
        actions.put("view_books", new ViewBooksAction());
        actions.put("add_to_cart", new AddToCartAction());
        actions.put("view_cart", new ViewCartAction());
        actions.put("update_cart", new UpdateCartAction());
        actions.put("checkout", new CheckoutAction());
        actions.put("continue", new ContinueShoppingAction());
    }

    /**
     * Process the HTTP GET request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.err.println("doGet()");
        // Forward GET requests to doPost method
        doPost(request, response);
    }

    /**
     * Process the HTTP POST request.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String requestedAction = request.getParameter("action");
        System.err.println("REQUESTED ACTION = " + requestedAction);

        if (requestedAction == null || requestedAction.trim().isEmpty()) {
            requestedAction = defaultAction;
        }

        Action action = (Action) actions.get(requestedAction);

        if (action == null) {
            action = (Action) actions.get(defaultAction);
        }

        String nextPage;

        try {
            nextPage = action.execute(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();

            request.setAttribute("result",
                   ex.getClass().getName() + " : " + ex.toString());

            nextPage = "/jsp/error.jsp";
        }

        dispatch(request, response, nextPage);
    }

    /**
     * Forward the request to the specified page.
     * @param request HttpServletRequest object
     * @param response HttpServletResponse object
     * @param page Page to forward to
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    /**
     * Get Servlet information.
     * @return Servlet information
     */
    @Override
    public String getServletInfo() {
        return "controller.FrontController Information";
    }
}

