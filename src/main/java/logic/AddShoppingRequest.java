package logic;

import beans.LoginBean;
import beans.SessionBean;
import beans.ShoppingBean;
import utilities.ErrorCodes;
import utilities.RegexHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Add shopping request logic that is called when the user creates a new shopping request
 */
public class AddShoppingRequest extends HttpServlet {
    public AddShoppingRequest() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingBean shoppingBean = new ShoppingBean();
        request.setCharacterEncoding("UTF-8");

        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");

        String userId = sessionBean.getUserId();

        String article = request.getParameter("article");
        String amount = request.getParameter("amount");
        String requestedBy = request.getParameter("requestedBy");
        String dateDueString = request.getParameter("dateDue");
        Date dateDue = null;

        String wgId = sessionBean.getWgId();

        ErrorCodes status = ErrorCodes.FAILURE;

        // If the user has no wg, show an error page. Otherwise, try to add the shopping request
        if (wgId.equals("")) {
            status = ErrorCodes.NOWGFOUND;
        } else {
            try {
                dateDue = new SimpleDateFormat("yyyy-MM-dd").parse(dateDueString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String requestedById = new LoginBean().getUserId(requestedBy);

            status = shoppingBean.createRequest(article, amount, userId, requestedById, dateDue);
        }

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
}
