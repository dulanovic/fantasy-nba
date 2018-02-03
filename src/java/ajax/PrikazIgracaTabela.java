package ajax;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import db.DatabaseBroker;
import domen.Igrac;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrikazIgracaTabela extends HttpServlet {

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

        List<Igrac> listaIgraca = DatabaseBroker.getInstance().vratiSveIgrace("svi", "svi");
        ObjectMapper om = new ObjectMapper();
        ArrayNode jsonNiz = om.createArrayNode();

        for (Igrac i : listaIgraca) {
            ArrayNode nizIgrac = om.createArrayNode();
            nizIgrac.add("<a href='./fantasy?action=igrac&igrac=" + i.getIgracId() + "'>" + i.getPrezime() + ", " + i.getIme() + " (#" + i.getBrojDres() + ")" + "</a>");
            nizIgrac.add("<a href='./fantasy?action=tim&tim=" + i.getTim().getTimId() + "' target='_blank'><img src='" + i.getTim().getTimLogo() + "' style='width: 30px;' /></a>");
            nizIgrac.add(i.getVisina() + "cm");
            nizIgrac.add(i.getTezina() + "kg");
            nizIgrac.add(i.getPozicija());
            nizIgrac.add(i.getIskustvo());
            nizIgrac.add(i.getKoledz());
            nizIgrac.add(i.getDatumRodjenjaString() + "<br />" + i.getMestoRodjenja());
            nizIgrac.add(i.getDraftPik() + "<br />(" + i.getDraftGodina() + ")");
            jsonNiz.add(nizIgrac);
        }
        ObjectNode jsonIzlaz = om.createObjectNode();
        jsonIzlaz.putPOJO("data", jsonNiz);

        try (PrintWriter out = response.getWriter()) {
            out.write(jsonIzlaz.toString());
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
