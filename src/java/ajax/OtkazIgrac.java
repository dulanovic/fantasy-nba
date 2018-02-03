package ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import constants.WebConstants;
import db.DatabaseBroker;
import domen.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OtkazIgrac extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        ObjectMapper om = new ObjectMapper();
        ObjectNode on = om.createObjectNode();

        try (PrintWriter out = response.getWriter()) {
            int igrac = Integer.valueOf(request.getParameter("igrac"));
            Korisnik k = (Korisnik) request.getSession().getAttribute(WebConstants.USER_LOGIN);
            if (!DatabaseBroker.getInstance().proveriVlasnistvo(k, igrac, "igrac")) {
                on.put("status", 0);
                on.put("poruka", "Nemate ovlašćenja da menjate sastav tuđeg tima!!!");
                out.write(on.toString());
            } else {
                DatabaseBroker.getInstance().otpustiIgraca(igrac);
                on.put("status", 1);
                on.put("poruka", "Igrač je otpušten.");
                out.write(on.toString());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
