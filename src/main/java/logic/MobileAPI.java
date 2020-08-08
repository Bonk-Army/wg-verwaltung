package logic;

import beans.APIBean;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;

public class MobileAPI extends HttpServlet {
    public MobileAPI() {
        super();
    }

    /**
     * Called by the mobile app
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        APIBean apiBean = new APIBean();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String jsonData = request.getParameter("jsondata");
        Enumeration<String> params = request.getParameterNames();
        String body = getBody(request);

        JSONObject jsonObj = new JSONObject(body);
        String reqType = jsonObj.getString("type");

        PrintWriter out = response.getWriter();

        switch (reqType) {
            case "AUTHREQUEST":
                String username = jsonObj.getString("username");
                String password = jsonObj.getString("password");

                out.print(apiBean.performLogin(username, password));
                out.flush();
                response.setStatus(200);
                break;
            case "CHECKSESSION":
                String userId = jsonObj.getString("userId");
                String sessionId = jsonObj.getString("sessionId");

                out.print(apiBean.checkSessionToken(userId, sessionId));
                out.flush();
                response.setStatus(200);
                break;
            default:
                response.setStatus(400);
                break;
        }
    }

    private static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
