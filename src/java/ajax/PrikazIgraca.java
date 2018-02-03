package ajax;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyTim;
import domen.Igrac;
import domen.Korisnik;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Util;

public class PrikazIgraca extends HttpServlet {

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

        try (PrintWriter out = response.getWriter()) {
            Korisnik k = (Korisnik) request.getSession().getAttribute(WebConstants.USER_LOGIN);
            String tip = request.getParameter("tip");
            int liga = (!tip.equals("prikaz")) ? Integer.valueOf(request.getParameter("liga")) : 0;
            String pozicija = request.getParameter("pozicija");
            String tim = request.getParameter("tim");
            String unos = request.getParameter("unos");
            boolean draft = tip.equals("draft");
            List<Igrac> lista = new ArrayList<>();
            FantasyTim ft = DatabaseBroker.getInstance().vratiTimKorisnikaIzLige(liga, k);

            if (unos == null) {
                if (tip.equals("prikaz")) {
                    lista = DatabaseBroker.getInstance().vratiSveIgrace(pozicija, tim);
                } else {
                    lista = DatabaseBroker.getInstance().vratiDostupneIgrace(liga, pozicija, tim, draft);
                }
            } else {
                if (tip.equals("prikaz")) {
                    lista = DatabaseBroker.getInstance().vratiSveIgrace(unos);
                } else {
                    lista = DatabaseBroker.getInstance().vratiDostupneIgrace(liga, unos, draft);
                }
            }

            String tabela = "";
            switch (tip) {
                case "draft":
                    tabela = Util.generisiTabeluDraft(lista);
                    break;
                case "trziste":
                    tabela = Util.generisiTabeluTrziste(ft, lista);
                    break;
                case "prikaz":
                    tabela = Util.generisiTabeluPrikaz(lista);
                    break;
            }

            out.write(tabela);
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
