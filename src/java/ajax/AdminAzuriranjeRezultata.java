package ajax;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.WebConstants;
import db.DatabaseBroker;
import domen.IgracUcinak;
import domen.IgracUcinakPK;
import domen.TimUcinak;
import domen.TimUcinakPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class AdminAzuriranjeRezultata extends HttpServlet {

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

        Date datumZadnjegAzuriranja = DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranja();
        Calendar c = Calendar.getInstance();
        c.setTime(datumZadnjegAzuriranja);
        c.add(Calendar.DAY_OF_MONTH, 1);

        List<String> listaApiId = DatabaseBroker.getInstance().vratiApiIdUtakmica(c.getTime());

        for (String apiId : listaApiId) {

            Client cl = ClientBuilder.newClient();
            WebTarget t = cl.target("https://api.sportradar.us/nba-t3/games/" + apiId + "/summary.json?api_key=nkzkct779r6dddwtqjvj76ez");
            String json = t.request(MediaType.APPLICATION_JSON).get(String.class);
            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readTree(json);
            String utakmicaApiId = node.get("id").asText();

            JsonNode domacin = node.get("home");
            JsonNode gost = node.get("away");
            JsonNode[] nizTimovi = new JsonNode[]{domacin, gost};

            boolean odigrana = true;
            int brojGledalaca = node.get("attendance").asInt();
            String trajanje = node.get("duration").asText();
            int poeniDomacin = domacin.get("points").asInt();
            int poeniGost = gost.get("points").asInt();

            DatabaseBroker.getInstance().azurirajUtakmicu(utakmicaApiId, odigrana, brojGledalaca, trajanje, poeniDomacin, poeniGost);

            for (JsonNode cvor : nizTimovi) {

                TimUcinakPK timUcinakPK = DatabaseBroker.getInstance().vratiTimUcinakPK(utakmicaApiId, cvor.get("id").asText());

                int poeni = cvor.get("points").asInt();
                int poeniCetvrtina1 = cvor.get("scoring").get(0).get("points").asInt();
                int poeniCetvrtina2 = cvor.get("scoring").get(1).get("points").asInt();
                int poeniCetvrtina3 = cvor.get("scoring").get(2).get("points").asInt();
                int poeniCetvrtina4 = cvor.get("scoring").get(3).get("points").asInt();
                String minuti = cvor.get("statistics").get("minutes").asText();
                int pogodakIzIgre = cvor.get("statistics").get("field_goals_made").asInt();
                int pokusajIzIgre = cvor.get("statistics").get("field_goals_att").asInt();
                double procenatIzIgre = cvor.get("statistics").get("field_goals_pct").asDouble();
                int pogodak3p = cvor.get("statistics").get("three_points_made").asInt();
                int pokusaj3p = cvor.get("statistics").get("three_points_att").asInt();
                double procenat3p = cvor.get("statistics").get("three_points_pct").asDouble();
                int pogodak2p = cvor.get("statistics").get("two_points_made").asInt();
                int pokusaj2p = cvor.get("statistics").get("two_points_att").asInt();
                double procenat2p = cvor.get("statistics").get("two_points_pct").asDouble();
                int blokiraniSutevi = cvor.get("statistics").get("blocked_att").asInt();
                int pogodak1p = cvor.get("statistics").get("free_throws_made").asInt();
                int pokusaj1p = cvor.get("statistics").get("free_throws_att").asInt();
                double procenat1p = cvor.get("statistics").get("free_throws_pct").asDouble();
                int skokoviNapad = cvor.get("statistics").get("offensive_rebounds").asInt();
                int skokoviOdbrana = cvor.get("statistics").get("defensive_rebounds").asInt();
                int skokoviUkupno = cvor.get("statistics").get("rebounds").asInt();
                int asistencije = cvor.get("statistics").get("assists").asInt();
                int izgubljeneLopte = cvor.get("statistics").get("turnovers").asInt();
                int ukradeneLopte = cvor.get("statistics").get("steals").asInt();
                int blokade = cvor.get("statistics").get("blocks").asInt();
                double odnosAsistIzglop = cvor.get("statistics").get("assists_turnover_ratio").asDouble();
                int licneGreske = cvor.get("statistics").get("personal_fouls").asInt();
                int poeniIzKontre = cvor.get("statistics").get("fast_break_pts").asInt();
                int poeniIzReketa = cvor.get("statistics").get("paint_pts").asInt();
                int poeniIzIzgubljenihLopti = cvor.get("statistics").get("points_off_turnovers").asInt();

                TimUcinak timUcinak = new TimUcinak(timUcinakPK, poeni, poeniCetvrtina1, poeniCetvrtina2, poeniCetvrtina3, poeniCetvrtina4, minuti, pogodakIzIgre, pokusajIzIgre, procenatIzIgre, pogodak3p, pokusaj3p, procenat3p, pogodak2p, pokusaj2p, procenat2p, blokiraniSutevi, pogodak1p, pokusaj1p, procenat1p, skokoviNapad, skokoviOdbrana, skokoviUkupno, asistencije, izgubljeneLopte, ukradeneLopte, blokade, odnosAsistIzglop, licneGreske, poeniIzKontre, poeniIzReketa, poeniIzIzgubljenihLopti);
                DatabaseBroker.getInstance().sacuvajUcinakTima(timUcinak);

                for (JsonNode cvorIgrac : cvor.get("players")) {

                    int igracId = DatabaseBroker.getInstance().vratiIgracId(cvorIgrac.get("id").asText());
                    if (igracId == 0 || !cvorIgrac.has("played")) {
                        continue;
                    }

                    boolean starter = false;
                    if (cvorIgrac.has("starter")) {
                        starter = cvorIgrac.get("starter").asBoolean();
                    }
                    String minutiIgrac = cvorIgrac.get("statistics").get("minutes").asText();
                    int pogodakIzIgreIgrac = cvorIgrac.get("statistics").get("field_goals_made").asInt();
                    int pokusajIzIgreIgrac = cvorIgrac.get("statistics").get("field_goals_att").asInt();
                    double procenatIzIgreIgrac = cvorIgrac.get("statistics").get("field_goals_pct").asDouble();
                    int pogodak3pIgrac = cvorIgrac.get("statistics").get("three_points_made").asInt();
                    int pokusaj3pIgrac = cvorIgrac.get("statistics").get("three_points_att").asInt();
                    double procenat3pIgrac = cvorIgrac.get("statistics").get("three_points_pct").asDouble();
                    int pogodak2pIgrac = cvorIgrac.get("statistics").get("two_points_made").asInt();
                    int pokusaj2pIgrac = cvorIgrac.get("statistics").get("two_points_att").asInt();
                    double procenat2pIgrac = cvorIgrac.get("statistics").get("two_points_pct").asDouble();
                    int blokiraniSuteviIgrac = cvorIgrac.get("statistics").get("blocked_att").asInt();
                    int pogodak1pIgrac = cvorIgrac.get("statistics").get("free_throws_made").asInt();
                    int pokusaj1pIgrac = cvorIgrac.get("statistics").get("free_throws_att").asInt();
                    double procenat1pIgrac = cvorIgrac.get("statistics").get("free_throws_pct").asDouble();
                    int skokoviNapadIgrac = cvorIgrac.get("statistics").get("offensive_rebounds").asInt();
                    int skokoviOdbranaIgrac = cvorIgrac.get("statistics").get("defensive_rebounds").asInt();
                    int skokoviUkupnoIgrac = cvorIgrac.get("statistics").get("rebounds").asInt();
                    int asistencijeIgrac = cvorIgrac.get("statistics").get("assists").asInt();
                    int izgubljeneLopteIgrac = cvorIgrac.get("statistics").get("turnovers").asInt();
                    int ukradeneLopteIgrac = cvorIgrac.get("statistics").get("steals").asInt();
                    int blokadeIgrac = cvorIgrac.get("statistics").get("blocks").asInt();
                    double odnosAsistIzglopIgrac = cvorIgrac.get("statistics").get("assists_turnover_ratio").asDouble();
                    int licneGreskeIgrac = cvorIgrac.get("statistics").get("personal_fouls").asInt();
                    int plusMinusIgrac = cvorIgrac.get("statistics").get("pls_min").asInt();
                    int poeniIgrac = cvorIgrac.get("statistics").get("points").asInt();

                    IgracUcinakPK igracUcinakPK = new IgracUcinakPK(timUcinakPK.getUtakmica(), igracId);
                    IgracUcinak igracUcinak = new IgracUcinak(igracUcinakPK, starter, minutiIgrac, pogodakIzIgreIgrac, pokusajIzIgreIgrac, procenatIzIgreIgrac, pogodak3pIgrac, pokusaj3pIgrac, procenat3pIgrac, pogodak2pIgrac, pokusaj2pIgrac, procenat2pIgrac, blokiraniSuteviIgrac, pogodak1pIgrac, pokusaj1pIgrac, procenat1pIgrac, skokoviNapadIgrac, skokoviOdbranaIgrac, skokoviUkupnoIgrac, asistencijeIgrac, izgubljeneLopteIgrac, ukradeneLopteIgrac, blokadeIgrac, odnosAsistIzglopIgrac, licneGreskeIgrac, plusMinusIgrac, poeniIgrac);
                    DatabaseBroker.getInstance().sacuvajUcinakIgraca(igracUcinak);

                }
            }
        }

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
