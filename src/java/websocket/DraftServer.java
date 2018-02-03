package websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import db.DatabaseBroker;
import domen.FantasyIgrac;
import domen.FantasyTim;
import domen.FantasyUtakmica;
import domen.Igrac;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import util.Util;

@ServerEndpoint(value = "/fantasy/draft/{ligaId}/{nazivTima}")
public class DraftServer {

    private static Map<String, Map<String, Session>> sveSesije = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, Map<String, Boolean>> prisustvo = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, String> listaAdmin = Collections.synchronizedMap(new HashMap<>());
    private static List<String> inicijalizovaneLige = Collections.synchronizedList(new ArrayList<>());
    private static Map<String, Boolean> draftUToku = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, List<FantasyTim>> redosled = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, Map<String, Integer>> brojOdabranih = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, Integer> trenutniTim = Collections.synchronizedMap(new HashMap<>());
    private static Map<String, String> logovi = Collections.synchronizedMap(new HashMap<>());
    private static ObjectMapper om = new ObjectMapper();

    @OnOpen
    public void open(@PathParam("ligaId") String ligaId, @PathParam("nazivTima") String nazivTima, Session session) throws IOException {
        if (!inicijalizovaneLige.contains(ligaId)) {
            inicijalizujLigu(ligaId, nazivTima, session);
        }

        if (daLiJeUSesiji(ligaId, nazivTima)) {
            ObjectNode on = om.createObjectNode();
            on.put("tip_poruke", "onemoguci_pristup");
            session.getBasicRemote().sendText(on.toString());
            return;
        }

        session.getUserProperties().put("nazivTima", nazivTima);
        sveSesije.get(ligaId).put(nazivTima, session);
        prisustvo.get(ligaId).put(nazivTima, true);

        if (daLiJeAdmin(ligaId, nazivTima) && (vratiUkupanBrojOdabranih(ligaId)) == 4 && draftUToku.get(ligaId)) {
            ObjectNode on1 = om.createObjectNode();
            on1.put("tip_poruke", "kraj_drafta");
            session.getBasicRemote().sendText(on1.toString());
        }

        if (!logovi.get(ligaId).equals("")) {
            ObjectNode on = om.createObjectNode();
            on.put("tip_poruke", "azuriraj_log");
            on.put("log", logovi.get(ligaId).replace("\"", ""));
            if (logovi.get(ligaId).contains(" je izabrao igrača")) {
                List<FantasyIgrac> listaDosadIzabranih = DatabaseBroker.getInstance().vratiDosadIzabrane(Integer.valueOf(ligaId), nazivTima);
                String dosadIzabraniIgraci = "[";
                for (int i = 0; i < listaDosadIzabranih.size(); i++) {
                    dosadIzabraniIgraci += "{\"igrac_slika\": \"" + listaDosadIzabranih.get(i).getIgrac().getIgracSlika() + "\", \"igrac_broj\": \"" + listaDosadIzabranih.get(i).getIgrac().getBrojDres() + "\", \"igrac_ime\": \"" + listaDosadIzabranih.get(i).getIgrac().getIme() + "\", \"igrac_prezime\": \"" + listaDosadIzabranih.get(i).getIgrac().getPrezime() + "\", \"igrac_pozicija\": \"" + listaDosadIzabranih.get(i).getIgrac().getPozicijaKrace() + "\"}";
                    if (i < (listaDosadIzabranih.size() - 1)) {
                        dosadIzabraniIgraci += ",";
                    }
                }
                dosadIzabraniIgraci += "]";
                on.put("dosad_izabrani_igraci", dosadIzabraniIgraci);
            }
            session.getBasicRemote().sendText(on.toString());
        }

        ObjectNode on1 = om.createObjectNode();
        on1.put("tip_poruke", "obavestenje");
        on1.put("tekst_poruke", "Tim ---> " + nazivTima + " se pridružio draft sesiji.");
        posaljiSvima(ligaId, on1.toString());

        if (sviPrisutni(ligaId) && !draftUToku.get(ligaId)) {
            ObjectNode on = om.createObjectNode();
            on.put("tip_poruke", "pokreni_draft");
            Session adminSession = sveSesije.get(ligaId).get(listaAdmin.get(ligaId));
            adminSession.getBasicRemote().sendText(on.toString());
        }
    }

    @OnMessage
    public void message(@PathParam("ligaId") String ligaId, @PathParam("nazivTima") String nazivTima, String poruka, Session session) throws IOException, ParseException {
        JsonNode porukaJson = om.readValue(poruka, JsonNode.class);
        String tipPoruke = porukaJson.get("tip_poruke").asText();
        ObjectNode on = om.createObjectNode();

        switch (tipPoruke) {
            case "pokreni_draft": {
                draftUToku.put(ligaId, true);
                azurirajPokazivac(ligaId);

                on.put("tip_poruke", "obavestenje");
                on.put("tekst_poruke", "Draft je upravo počeo!");
                posaljiSvima(ligaId, on.toString());
                break;
            }
            case "izabran_igrac": {
                int igrac = porukaJson.get("igrac_id").asInt();
                String tim = porukaJson.get("naziv_tima").asText();

                brojOdabranih.get(ligaId).put(tim, brojOdabranih.get(ligaId).get(tim) + 1);

                Igrac i = DatabaseBroker.getInstance().sacuvajIzbor(ligaId, tim, igrac);

                on.put("tip_poruke", "izvrsen_izbor");
                on.put("tim_izabrao", tim);
                on.put("igrac_id", i.getIgracId());
                on.put("izabran_igrac", i.getIme() + " " + i.getPrezime());
                on.put("pozicija", i.getPozicijaKrace());
                posaljiSvima(ligaId, on.toString());
                azurirajPokazivac(ligaId);
                break;
            }
            case "isteklo_vreme": {
                on.put("tip_poruke", "obavestenje");
                on.put("tekst_poruke", "Tim ---> " + nazivTima + " nije izabrao igrača, isteklo je vreme.");
                posaljiSvima(ligaId, on.toString());
                azurirajRedosled(ligaId);
                break;
            }
            case "potvrdi_draft": {
                List<FantasyTim> listaTimova = DatabaseBroker.getInstance().vratiSveTimoveLige(Integer.valueOf(ligaId));
                Collections.shuffle(listaTimova);
                List<FantasyUtakmica> listaUtakmica = Util.generisiRaspored(listaTimova);
                DatabaseBroker.getInstance().aktivirajLigu(Integer.valueOf(ligaId), listaUtakmica);
                on.put("tip_poruke", "obavestenje");
                on.put("tekst_poruke", "Draft je završen!");
                posaljiSvima(ligaId, on.toString());
                posajiTimId(ligaId);
                break;
            }
            case "ponisti_draft": {
                DatabaseBroker.getInstance().ponistiDraft(Integer.valueOf(ligaId));
                napustiDraft(ligaId);
                break;
            }
        }
    }

    @OnClose
    public void close(@PathParam("ligaId") String ligaId,
            @PathParam("nazivTima") String nazivTima, Session session) throws IOException {
        if (daLiJePrijavljen(ligaId, nazivTima, session)) {
            sveSesije.get(ligaId).remove(nazivTima);
            prisustvo.get(ligaId).put(nazivTima, false);

            ObjectNode on1 = om.createObjectNode();
            on1.put("tip_poruke", "obavestenje");
            on1.put("tekst_poruke", "Tim ---> " + nazivTima + " je napustio draft sesiju.");
            posaljiSvima(ligaId, on1.toString());
        }

        if (daLiJeBioNaRedu(ligaId, nazivTima)) {
            int brojac = 0;
            for (Map.Entry par : prisustvo.get(ligaId).entrySet()) {
                if ((boolean) par.getValue() == true) {
                    brojac++;
                }
            }
            if (brojac < 2) {
                DatabaseBroker.getInstance().ponistiDraft(Integer.valueOf(ligaId));
                ObjectNode on = om.createObjectNode();
                on.put("tip_poruke", "napusti_draft");
                posaljiSvima(ligaId, on.toString());
            } else {
                azurirajRedosled(ligaId);
            }
        }

        if (!sviPrisutni(ligaId)) {
            Session adminSession = sveSesije.get(ligaId).get(listaAdmin.get(ligaId));
            ObjectNode on = om.createObjectNode();
            on.put("tip_poruke", "zaustavi_draft");
            if (adminSession != null) {
                adminSession.getBasicRemote().sendText(on.toString());
            }
        }

        if (sviOtisli(ligaId)) {
            obrisiAtributeLige(ligaId);
        }
    }

    private void inicijalizujLigu(@PathParam("ligaId") String ligaId, @PathParam("nazivTima") String nazivTima, Session session) throws IOException {
        sveSesije.put(ligaId, Collections.synchronizedMap(new HashMap<>()));
        prisustvo.put(ligaId, Collections.synchronizedMap(new HashMap<>()));
        brojOdabranih.put(ligaId, Collections.synchronizedMap(new HashMap<>()));
        String admin = DatabaseBroker.getInstance().vratiAdministratoraLige(Integer.valueOf(ligaId));
        listaAdmin.put(ligaId, admin);
        List<FantasyTim> lista = DatabaseBroker.getInstance().vratiSveTimoveLige(Integer.valueOf(ligaId));
        for (FantasyTim ft : lista) {
            prisustvo.get(ligaId).put(ft.getTimNaziv(), false);
            brojOdabranih.get(ligaId).put(ft.getTimNaziv(), 0);
        }
        inicijalizovaneLige.add(ligaId);
        draftUToku.put(ligaId, false);
        List<FantasyTim> listaRedosled = DatabaseBroker.getInstance().vratiRedosledDraft(ligaId);
        redosled.put(ligaId, listaRedosled);
        trenutniTim.put(ligaId, 0);
        logovi.put(ligaId, "");

        int brojac = 1;
        for (FantasyTim ft1 : redosled.get(ligaId)) {
            System.out.println(brojac + " ---> " + ft1.getTimNaziv());
            brojac++;
        }
    }

    private boolean sviPrisutni(String ligaId) {
        Map<String, Boolean> mapa = prisustvo.get(ligaId);
        Iterator i = mapa.entrySet().iterator();
        int brojac = 0;
        while (i.hasNext()) {
            Map.Entry par = (Map.Entry) i.next();
            if ((boolean) par.getValue() == true) {
                brojac++;
            }
        }
        System.out.println("Trenutno prisutno igrača u sesiji ---> " + brojac);
        if (brojac == 4) {
            return true;
        } else {
            return false;
        }
    }

    private boolean sviOtisli(String ligaId) {
        Map<String, Boolean> mapa = prisustvo.get(ligaId);
        for (Map.Entry par : mapa.entrySet()) {
            if ((boolean) par.getValue() == true) {
                return false;
            }
        }
        return true;
    }

    private void azurirajPokazivac(String ligaId) throws IOException {
        ObjectNode on = om.createObjectNode();
        int indeksTrenutnog = trenutniTim.get(ligaId);
        String trenutni = "";
        String sledeci = "";
        try {
            trenutni = redosled.get(ligaId).get(indeksTrenutnog).getTimNaziv();
            sledeci = redosled.get(ligaId).get(indeksTrenutnog + 1).getTimNaziv();
        } catch (IndexOutOfBoundsException ioobex) {
            //ioobex.printStackTrace();
        }

        on.put("tip_poruke", "azuriranje_stanja");
        on.put("trenutno_bira", trenutni);
        on.put("sledeci_bira", sledeci);
        posaljiSvima(ligaId, on.toString());

        if (trenutni.equals("")) {
            Session adminSession = sveSesije.get(ligaId).get(listaAdmin.get(ligaId));
            ObjectNode on1 = om.createObjectNode();
            on1.put("tip_poruke", "kraj_drafta");
            if (adminSession != null) {
                adminSession.getBasicRemote().sendText(on1.toString());
            }
            trenutniTim.put(ligaId, redosled.get(ligaId).size() + 1);
        } else {
            trenutniTim.put(ligaId, indeksTrenutnog + 1);

            Session sessionSledeci = sveSesije.get(ligaId).get(trenutni);
            ObjectNode on1 = om.createObjectNode();
            if (sessionSledeci != null) {
                on1.put("tip_poruke", "omoguci_izbor");
                if (brojOdabranih.get(ligaId).get(String.valueOf(sessionSledeci.getUserProperties().get("nazivTima"))) == 9) {
                    on1.put("napomena", "poslednji_izbor");
                }
                sessionSledeci.getBasicRemote().sendText(on1.toString());
            } else {
                on1.put("tip_poruke", "obavestenje");
                on1.put("tekst_poruke", "Tim ---> " + trenutni + " trenutno nije u sesiji, draft se nastavlja.");
                posaljiSvima(ligaId, on1.toString());
                azurirajRedosled(ligaId);
            }
        }
    }

    private void azurirajRedosled(String ligaId) throws IOException {
        FantasyTim ft = redosled.get(ligaId).get(trenutniTim.get(ligaId) - 1);
        redosled.get(ligaId).add(ft);
        azurirajPokazivac(ligaId);

        int brojac = 1;
        for (FantasyTim ft1 : redosled.get(ligaId)) {
            System.out.println(brojac + " ---> " + ft1.getTimNaziv());
            brojac++;
        }
    }

    private boolean daLiJeUSesiji(String ligaId, String nazivTima) {
        return sveSesije.get(ligaId).containsKey(nazivTima);
    }

    private boolean daLiJePrijavljen(String ligaId, String nazivTima, Session session) {
        if (sveSesije.get(ligaId).containsKey(nazivTima)) {
            if (sveSesije.get(ligaId).get(nazivTima).getId().equals(session.getId())) {
                return true;
            }
        }
        return false;
    }

    private void posaljiSvima(String ligaId, String poruka) throws IOException {
        for (Map.Entry par : sveSesije.get(ligaId).entrySet()) {
            Session s = (Session) par.getValue();
            s.getBasicRemote().sendText(poruka);
        }
        JsonNode porukaJson = om.readValue(poruka, JsonNode.class);
        if (porukaJson.get("tip_poruke").asText().equals("obavestenje")) {
            String log = logovi.get(ligaId);
            log += porukaJson.get("tekst_poruke") + "\n";
            logovi.put(ligaId, log);
        } else if (porukaJson.get("tip_poruke").asText().equals("izvrsen_izbor")) {
            String log = logovi.get(ligaId);
            log += "Tim ---> " + porukaJson.get("tim_izabrao").asText() + " je izabrao igrača ---> " + porukaJson.get("izabran_igrac").asText() + "\n";
            logovi.put(ligaId, log);
        }
    }

    private void obrisiAtributeLige(String ligaId) {
        sveSesije.remove(ligaId);
        prisustvo.remove(ligaId);
        listaAdmin.remove(ligaId);
        inicijalizovaneLige.remove(ligaId);
        draftUToku.remove(ligaId);
        redosled.remove(ligaId);
        trenutniTim.remove(ligaId);
        logovi.remove(ligaId);
    }

    private void napustiDraft(String ligaId) throws IOException {
        ObjectNode on = om.createObjectNode();
        on.put("tip_poruke", "napusti_draft");
        posaljiSvima(ligaId, on.toString());
    }

    private void posajiTimId(String ligaId) throws IOException {
        for (int i = 0; i < 4; i++) {
            String timNaziv = redosled.get(ligaId).get(i).getTimNaziv();
            int timId = DatabaseBroker.getInstance().vratiIdTima(Integer.valueOf(ligaId), timNaziv);
            ObjectNode on = om.createObjectNode();
            on.put("tip_poruke", "idi_na_stranicu");
            on.put("tim_id", timId);
            Session s = sveSesije.get(ligaId).get(timNaziv);
            s.getBasicRemote().sendText(on.toString());
        }
    }

    private boolean daLiJeBioNaRedu(String ligaId, String nazivTima) {
        try {
            return redosled.get(ligaId).get(trenutniTim.get(ligaId) - 1).getTimNaziv().equals(nazivTima);
        } catch (IndexOutOfBoundsException ioobex) {
            //ioobex.printStackTrace();
            return false;
        }
    }

    private boolean daLiJeAdmin(String ligaId, String nazivTima) {
        return listaAdmin.get(ligaId).equals(nazivTima);
    }

    private int vratiUkupanBrojOdabranih(String ligaId) {
        int brojac = 0;
        for (Map.Entry par : brojOdabranih.get(ligaId).entrySet()) {
            brojac += (int) par.getValue();
        }
        return brojac;
    }
}
