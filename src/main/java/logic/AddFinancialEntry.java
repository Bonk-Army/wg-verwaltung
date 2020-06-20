package logic;

import beans.FinancialBean;
import beans.SessionBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFinancialEntry extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddFinancialEntry() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * Called when the user wants to create a new financial entry
     *
     * @param request  The http POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FinancialBean financialBean = new FinancialBean();
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String userId = sessionBean.getUserId();
        String wgId = sessionBean.getWgId();

        String title = request.getParameter("title");
        String reason = request.getParameter("reason");
        String value = request.getParameter("value");
        String dateString = request.getParameter("date");
        String sign = request.getParameter("sign"); //Vorzeichen
        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String valueWithSign = (sign + value);

        ErrorCodes status = financialBean.addFinancialEntry(reason, valueWithSign, userId, wgId, date);

        switch (status) {
            case SUCCESS:
                //Show success
                response.sendRedirect("/financial");
                break;
            case FAILURE:
                //Show failure
                request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                break;
            case WRONGENTRY:
                request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
                break;
        }
    }
}
