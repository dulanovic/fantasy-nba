package ajax;

import constants.WebConstants;
import db.DatabaseBroker;
import domen.FantasyIgrac;
import domen.FantasyIgracUcestvovanje;
import domen.FantasyTim;
import domen.FantasyTimUcinak;
import domen.FantasyTimUcinakPK;
import domen.FantasyUtakmica;
import domen.IgracUcinak;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Util;

public class AdminAzuriranjeFantasyLige extends HttpServlet {

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

        int liga = Integer.valueOf(request.getParameter("liga"));
        Date datumZadnjegAzuriranjaLige = DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranjaLige(liga);
        Calendar c = Calendar.getInstance();
        c.setTime(datumZadnjegAzuriranjaLige);
        c.add(Calendar.DAY_OF_MONTH, 1);
        List<FantasyUtakmica> listaUtakmica = DatabaseBroker.getInstance().vratiFantasyUtakmice(liga, c.getTime());

        for (FantasyUtakmica fu : listaUtakmica) {
            Map<String, FantasyTim> mapaTimovi = new HashMap<>();
            double[] nizUcinakDomacin = new double[8];
            double[] nizUcinakGost = new double[8];
            FantasyTim[] ftNiz = new FantasyTim[]{fu.getDomacin(), fu.getGost()};
            for (FantasyTim ft : ftNiz) {
                if (!Util.validanRoster(ft)) {
                    continue;
                }
                int pogodakIzIgre = 0;
                int pokusajIzIgre = 0;
                int pogodak1p = 0;
                int pokusaj1p = 0;
                int pogodak3p = 0;
                int skokovi = 0;
                int asistencije = 0;
                int ukradeneLopte = 0;
                int blokade = 0;
                int poeni = 0;
                for (FantasyIgrac fi : ft.getFantasyIgracList()) {
                    if (fi.getStatus().getStatusIgracaNaziv().equals("Rezerva")) {
                        continue;
                    }
                    IgracUcinak iu = DatabaseBroker.getInstance().vratiUcinakIgracaNaUtakmici(fi.getIgrac(), c.getTime());
                    if (iu == null) {
                        continue;
                    }
                    pogodakIzIgre += iu.getPogodakIzIgre();
                    pokusajIzIgre += iu.getPokusajIzIgre();
                    pogodak1p += iu.getPogodak1p();
                    pokusaj1p += iu.getPokusaj1p();
                    pogodak3p += iu.getPogodak3p();
                    skokovi += iu.getSkokoviUkupno();
                    asistencije += iu.getAsistencije();
                    ukradeneLopte += iu.getUkradeneLopte();
                    blokade += iu.getBlokade();
                    poeni += iu.getPoeni();

                    FantasyIgracUcestvovanje fiu = DatabaseBroker.getInstance().vratiUcestvovanjeFantasyIgraca(fi.getFantasyIgracId(), ft.getFantasyTimId(), fu.getFantasyUtakmicaId());
                    if (fiu == null) {
                        fiu = new FantasyIgracUcestvovanje(fi.getFantasyIgracId(), ft.getFantasyTimId(), fu.getFantasyUtakmicaId());
                        fiu.setDatumOd(c.getTime());
                    }
                    fiu.setDatumDo(c.getTime());
                    DatabaseBroker.getInstance().sacuvajUcestvovanjeFantasyIgraca(fiu);
                }
                FantasyTimUcinak ftu = DatabaseBroker.getInstance().vratiUcinakFantasyTima(fu.getFantasyUtakmicaId(), ft.getFantasyTimId());
                if (ftu == null) {
                    ftu = new FantasyTimUcinak(new FantasyTimUcinakPK(fu.getFantasyUtakmicaId(), ft.getFantasyTimId()));
                }
                ftu.setPogodakIzIgre(ftu.getPogodakIzIgre() + pogodakIzIgre);
                ftu.setPokusajIzIgre(ftu.getPokusajIzIgre() + pokusajIzIgre);
                ftu.setPogodak1p(ftu.getPogodak1p() + pogodak1p);
                ftu.setPokusaj1p(ftu.getPokusaj1p() + pokusaj1p);
                ftu.setPogodak3p(ftu.getPogodak3p() + pogodak3p);
                ftu.setSkokovi(ftu.getSkokovi() + skokovi);
                ftu.setAsistencije(ftu.getAsistencije() + asistencije);
                ftu.setUkradeneLopte(ftu.getUkradeneLopte() + ukradeneLopte);
                ftu.setBlokade(ftu.getBlokade() + blokade);
                ftu.setPoeni(ftu.getPoeni() + poeni);
                DatabaseBroker.getInstance().sacuvajUcinakFantasyTima(ftu);

                if (fu.getDomacin() == ft) {
                    mapaTimovi.put("domacin", ft);
                    nizUcinakDomacin[0] = (double) ftu.getPogodakIzIgre() / (double) ftu.getPokusajIzIgre();
                    nizUcinakDomacin[1] = (double) ftu.getPogodak1p() / (double) ftu.getPokusaj1p();
                    nizUcinakDomacin[2] = ftu.getPogodak3p();
                    nizUcinakDomacin[3] = ftu.getSkokovi();
                    nizUcinakDomacin[4] = ftu.getAsistencije();
                    nizUcinakDomacin[5] = ftu.getUkradeneLopte();
                    nizUcinakDomacin[6] = ftu.getBlokade();
                    nizUcinakDomacin[7] = ftu.getPoeni();
                } else {
                    mapaTimovi.put("gost", ft);
                    nizUcinakGost[0] = (double) ftu.getPogodakIzIgre() / (double) ftu.getPokusajIzIgre();
                    nizUcinakGost[1] = (double) ftu.getPogodak1p() / (double) ftu.getPokusaj1p();
                    nizUcinakGost[2] = ftu.getPogodak3p();
                    nizUcinakGost[3] = ftu.getSkokovi();
                    nizUcinakGost[4] = ftu.getAsistencije();
                    nizUcinakGost[5] = ftu.getUkradeneLopte();
                    nizUcinakGost[6] = ftu.getBlokade();
                    nizUcinakGost[7] = ftu.getPoeni();
                }
            }
            int boljiDomacin = 0;
            int boljiGost = 0;
            int nereseno = 0;
            for (int i = 0; i < nizUcinakDomacin.length; i++) {
                if (nizUcinakDomacin[i] > nizUcinakGost[i]) {
                    boljiDomacin++;
                } else if (nizUcinakDomacin[i] < nizUcinakGost[i]) {
                    boljiGost++;
                } else {
                    nereseno++;
                }
            }
            DatabaseBroker.getInstance().azurirajFantasyUtakmicu(fu.getFantasyUtakmicaId(), boljiDomacin, boljiGost, nereseno);
            if (fu.getDatumKrajDate().equals(c.getTime())) {
                double brojPoenaDomacin = ((double) boljiDomacin / (double) nizUcinakDomacin.length) + ((double) nereseno / 2 * nizUcinakDomacin.length);
                double brojPoenaGost = ((double) boljiGost / (double) nizUcinakGost.length) + ((double) nereseno / 2 * nizUcinakGost.length);
                DatabaseBroker.getInstance().azurirajBrojPoenaTima(fu.getDomacin().getFantasyTimId(), brojPoenaDomacin);
                DatabaseBroker.getInstance().azurirajBrojPoenaTima(fu.getGost().getFantasyTimId(), brojPoenaGost);
            }
        }

        DatabaseBroker.getInstance().azurirajLigu(liga, c.getTime());

        try (PrintWriter out = response.getWriter()) {
            out.write(WebConstants.sdf.format(c.getTime()));
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
