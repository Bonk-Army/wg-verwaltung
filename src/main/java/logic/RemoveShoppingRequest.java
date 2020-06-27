package logic;

import beans.SessionBean;
import beans.ShoppingBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Remove shopping request servlet that is called when a shopping request is set to done
 */
public class RemoveShoppingRequest extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveShoppingRequest() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingBean shoppingBean = new ShoppingBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String requestId = request.getParameter("requestId");
        String wgId = sessionBean.getWgId();

        ErrorCodes status = shoppingBean.setRequestInactive(requestId, wgId);

        switch (status) {
            case SUCCESS:
                //Show success
                response.sendRedirect("/shopping");
                break;
            default:
                //Show failure
                request.setAttribute("isSadLlama", true);
                request.setAttribute("header", status.getHeader());
                request.setAttribute("message", status.getMessage());
                request.getServletContext().getRequestDispatcher("/status").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
