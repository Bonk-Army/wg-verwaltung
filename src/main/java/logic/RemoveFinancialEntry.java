package logic;

import beans.FinancialBean;
import beans.SessionBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Called when the user wants to delete a financial entry (expense / credit)
 */
public class RemoveFinancialEntry extends HttpServlet {
    public RemoveFinancialEntry() {
        super();
    }

    /**
     * Called when the users wants to remove a financial entry
     *
     * @param request  The http POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinancialBean financialBean = new FinancialBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String userId = sessionBean.getUserId();
        String wgId = sessionBean.getWgId();

        String entryId = request.getParameter("entryId");

        ErrorCodes status = financialBean.removeFinancialEntry(entryId, wgId);

        switch (status) {
            case SUCCESS:
                //Show success
                response.sendRedirect("/financial");
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
