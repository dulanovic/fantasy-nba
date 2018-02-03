package ajax;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.WebConstants;
import db.DatabaseBroker;
import domen.Tim;
import domen.Utakmica;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class AdminUnosRasporeda extends HttpServlet {

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

//        File json = new File("C:\\Users\\Korisnik\\Desktop\\2017-02-25.json");
//        Client c = ClientBuilder.newClient();
//        WebTarget t = c.target("https://jsonplaceholder.typicode.com/posts");
//        String json = t.request(MediaType.APPLICATION_JSON).get(String.class);
//        System.out.println("Datum odigravanja utakmica ---> " + node.get("date") + "\nLigaID ---> " + node.get("league").get("id") + "\nLigaNaziv ---> " + node.get("league").get("name") + "\nLigaAlias ---> " + node.get("league").get("alias") + "\nUkupan broj utakmica ---> " + node.get("games").size());
//        System.out.println("Broj atributa jedne utakmice ---> " + node.get("games").get(2).size());
//        System.out.println("Broj atributa jedne arene ---> " + node.get("games").get(3).get("venue").size());
//        System.out.println("" + node.get("games").get(0).get("away").get("name"));

        ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Calendar datumPocetkaSezone = Calendar.getInstance();
        Calendar datumKrajaSezone = Calendar.getInstance();
        datumPocetkaSezone.set(2017, 1, 23);
        datumKrajaSezone.set(2017, 3, 13);
        boolean odigrana = false;

//        while (datumPocetkaSezone.before(datumKrajaSezone)) {
//            String godina = String.valueOf(datumPocetkaSezone.get(Calendar.YEAR));
//            String mesec = ((datumPocetkaSezone.get(Calendar.MONTH) + 1) < 10) ? "0" + String.valueOf((datumPocetkaSezone.get(Calendar.MONTH) + 1)) : String.valueOf((datumPocetkaSezone.get(Calendar.MONTH) + 1));
//            String dan = (datumPocetkaSezone.get(Calendar.DAY_OF_MONTH) < 10) ? "0" + String.valueOf(datumPocetkaSezone.get(Calendar.DAY_OF_MONTH)) : String.valueOf(datumPocetkaSezone.get(Calendar.DAY_OF_MONTH));
//
//            Client c = ClientBuilder.newClient();
//            WebTarget t = c.target("https://api.sportradar.us/nba-t3/games/" + godina + "/" + mesec + "/" + dan + "/schedule.json?api_key=nkzkct779r6dddwtqjvj76ez");
//            String json = t.request(MediaType.APPLICATION_JSON).get(String.class);
//            JsonNode node = om.readTree(json);
//            JsonNode utakmice = node.get("games");
//            List<Utakmica> lista = new ArrayList<>();
//            Date datumOdigravanja = datumPocetkaSezone.getTime();
//
//            for (int i = 0; i < utakmice.size(); i++) {
//                Tim domacin = DatabaseBroker.getInstance().vratiTim(utakmice.get(i).get("home").get("id").asText());
//                Tim gost = DatabaseBroker.getInstance().vratiTim(utakmice.get(i).get("away").get("id").asText());
//                String utakmicaApiId = utakmice.get(i).get("id").asText();
//                Utakmica u = new Utakmica(odigrana, datumOdigravanja, utakmicaApiId, domacin, gost);
//                lista.add(u);
//            }
//            DatabaseBroker.getInstance().sacuvajUtakmice(lista);
//
//            datumPocetkaSezone.add(Calendar.DAY_OF_MONTH, 1);
//        }
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
