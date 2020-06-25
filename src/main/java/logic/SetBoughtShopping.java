package logic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.SessionBean;
import beans.ShoppingBean;
import utilities.ErrorCodes;

/**
 * Called when the user wants to set a shopping request to done / bought
 */
public class SetBoughtShopping extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SetBoughtShopping() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingBean shoppingBean = new ShoppingBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String requestId = request.getParameter("requestId");
        String wgId = sessionBean.getWgId();

        ErrorCodes status = shoppingBean.setRequestDone(requestId, wgId);

        switch (status) {
        case SUCCESS:
            //Show success
            response.sendRedirect("/shopping");
            break;
        case FAILURE:
            //Show failure
            request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
            break;
        }
    }
}
