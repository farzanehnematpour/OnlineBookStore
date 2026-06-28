
package dispatchers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Defines the common action interface used by all dispatcher action classes.
 *
 * @author Ava Nematpour
 */


public interface Action {
    /**
    * Executes the requested action.
    *
    * @param request the HTTP request from the client
    * @param response the HTTP response sent to the client
    * @return the path of the JSP page to display
    * @throws Exception if an error occurs while executing the action
    */
    
      String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
    
}
