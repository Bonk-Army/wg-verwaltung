package logic;

import beans.CleanBean;
import beans.LoginBean;
import beans.SessionBean;
import beans.SettingsBean;
import utilities.ErrorCodes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Called when the user wants to update the assignees for the cleaning tasks
 */
public class UpdateCleaningAssignees extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateCleaningAssignees() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * Called when the user updates the assignees of the cleaning tasks
     *
     * @param request  The http POST request
     * @param response The http response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CleanBean cleanBean = new CleanBean();
        boolean thereAreErrors = false; // No errors in any loop iteration
        SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
        request.setCharacterEncoding("UTF-8");

        String wgId = sessionBean.getWgId();

        List<String> taskIds = cleanBean.getTaskIdsForWg(wgId);

        // Loop over every task and get the assignees, then save them to sql
        for (String taskId : taskIds) {
            String mon = request.getParameter((taskId + "-monday"));
            String tue = request.getParameter((taskId + "-tuesday"));
            String wed = request.getParameter((taskId + "-wednesday"));
            String thu = request.getParameter((taskId + "-thursday"));
            String fri = request.getParameter((taskId + "-friday"));
            String sat = request.getParameter((taskId + "-saturday"));
            String sun = request.getParameter((taskId + "-sunday"));

            ErrorCodes status = cleanBean.updateUsersForTask(wgId, taskId, mon, tue, wed, thu, fri, sat, sun);

            boolean hasError = false;

            // If there was an error at any point in the iteration, abort the rest of the loop and directly forward
            // the user to the respective error page
            switch (status) {
                case FAILURE:
                    //Show failure
                    request.getServletContext().getRequestDispatcher("/responseFailure").forward(request, response);
                    hasError = true;
                    break;
                case WRONGENTRY:
                    //Show wrongentry
                    request.getServletContext().getRequestDispatcher("/responseWrongEntry").forward(request, response);
                    hasError = true;
                    break;
                default:
                    break;
            }

            if (hasError) {
                thereAreErrors = true;
                break;
            }
        }

        if (!thereAreErrors) {
            // If no errors have occured, send user back to cleaning page
            response.sendRedirect("/clean");
        }
    }
}
