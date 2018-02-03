package db;

import constants.WebConstants;
import domen.FantasyIgrac;
import domen.FantasyIgracUcestvovanje;
import domen.FantasyIgracUcestvovanjePK;
import domen.FantasyLiga;
import domen.FantasyTim;
import domen.FantasyTimUcinak;
import domen.FantasyTimUcinakPK;
import domen.FantasyUtakmica;
import domen.Igrac;
import domen.IgracUcinak;
import domen.Konferencija;
import domen.Korisnik;
import domen.StatusIgraca;
import domen.StatusLige;
import domen.Tim;
import domen.TimUcinak;
import domen.TimUcinakPK;
import domen.TipKorisnika;
import domen.TipTransakcije;
import domen.Transakcija;
import domen.Utakmica;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.mindrot.jbcrypt.BCrypt;
import util.PozicijaValidator;
import util.UtakmicaSkracenIspis;

public class DatabaseBroker {

    private static DatabaseBroker instance;

    public DatabaseBroker() {
    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public Korisnik login(String korisnickoIme, String korisnickaSifra) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            Korisnik k = (Korisnik) em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", korisnickoIme).getSingleResult();
            if (k != null) {
                if (BCrypt.checkpw(korisnickaSifra, k.getKorisnickaSifra())) {
                    return k;
                }
            }
            return null;
        } catch (NoResultException nrex) {
            nrex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public List<Tim> vratiSveTimove() {
        EntityManager em = EMFactory.createEntityManager();
        List<Tim> lista = em.createNamedQuery("Tim.findAll").getResultList();
        em.close();
        return lista;
    }

    public List<Konferencija> vratiKonferencije() {
        EntityManager em = EMFactory.createEntityManager();
        List<Konferencija> lista = em.createNamedQuery("Konferencija.findAll").getResultList();
        em.close();
        return lista;
    }

    public List<Igrac> vratiIgraceTima(int timId) throws Exception {
        EntityManager em = EMFactory.createEntityManager();
        Tim t = em.find(Tim.class, timId);
        List<Igrac> lista = em.createQuery("SELECT i FROM Igrac i WHERE i.tim =:tim").setParameter("tim", t).getResultList();
        em.close();
        if (!lista.isEmpty()) {
            return lista;
        }
        throw new Exception("Nema nijednog igraca u timu!!!");
    }

    public Map<String, List<Tim>> generisiTabelu() {
        EntityManager em = EMFactory.createEntityManager();
        Map<String, List<Tim>> mapaTabele = new HashMap<>();
        String[] parametri = new String[]{"WHERE kk.konferencija_id = 1", "WHERE kk.konferencija_id = 2", "WHERE dd.divizija_id = 1", "WHERE dd.divizija_id = 2", "WHERE dd.divizija_id = 3", "WHERE dd.divizija_id = 4", "WHERE dd.divizija_id = 5", "WHERE dd.divizija_id = 6"};
        String[] nazivi = new String[]{"Istok", "Zapad", "Atlantik", "Central", "Jugoistok", "Pacifik", "Jugozapad", "Severozapad"};
        for (int i = 0; i < parametri.length; i++) {
            List<Tim> lista = em.createNativeQuery("SELECT tim0, logo, naziv, pobede, porazi, CONCAT(TRUNCATE((pobede/(pobede+porazi)), 3)) AS uspesnost, skor_konf, skor_div, skor_domacin, skor_gost, prosek_datih_poena, prosek_primljenih_poena, TRUNCATE(prosek_datih_poena-prosek_primljenih_poena, 2) AS prosecna_razlika \n"
                    + "FROM (SELECT t0.tim_id AS tim0, t0.tim_naziv AS naziv, t0.tim_logo AS logo, t0.divizija AS tim_divizija, \n"
                    + "SUM(IF(t0.tim_id = u0.gost AND u0.poeni_gost > u0.poeni_domacin, 1, 0)) + SUM(IF(t0.tim_id = u0.domacin AND u0.poeni_domacin > u0.poeni_gost, 1, 0)) AS pobede, \n"
                    + "SUM(IF(t0.tim_id = u0.gost AND u0.poeni_gost < u0.poeni_domacin, 1, 0)) + SUM(IF(t0.tim_id = u0.domacin AND u0.poeni_domacin < u0.poeni_gost, 1, 0)) AS porazi, \n"
                    + "CONCAT(CAST(SUM(IF(t0.tim_id = u0.domacin AND u0.poeni_gost < u0.poeni_domacin, 1, 0)) AS CHAR), '-', CAST(SUM(IF(t0.tim_id = u0.domacin AND u0.poeni_gost > u0.poeni_domacin, 1, 0)) AS CHAR)) AS skor_domacin, \n"
                    + "CONCAT(CAST(SUM(IF(t0.tim_id = u0.gost AND u0.poeni_gost > u0.poeni_domacin, 1, 0)) AS CHAR), '-', CAST(SUM(IF(t0.tim_id = u0.gost AND u0.poeni_gost < u0.poeni_domacin, 1, 0)) AS CHAR)) AS skor_gost \n"
                    + "FROM tim t0 INNER JOIN utakmica u0 ON t0.tim_id = u0.domacin OR t0.tim_id = u0.gost GROUP BY t0.tim_id ORDER BY pobede DESC) AS Tab1 \n"
                    + "INNER JOIN (SELECT t1.tim_id AS tim1, CONCAT(SUM(IF(t1.tim_id = u1.domacin AND t2.tim_id = u1.gost AND t1.divizija = t2.divizija AND u1.poeni_domacin > u1.poeni_gost, 1, 0)) + SUM(IF(t1.tim_id = u1.gost AND t2.tim_id = u1.domacin AND t1.divizija = t2.divizija AND u1.poeni_domacin < u1.poeni_gost, 1, 0)), '-', SUM(IF(t1.tim_id = u1.domacin AND t2.tim_id = u1.gost AND t1.divizija = t2.divizija AND u1.poeni_domacin < u1.poeni_gost, 1, 0)) + SUM(IF(t1.tim_id = u1.gost AND t2.tim_id = u1.domacin AND t1.divizija = t2.divizija AND u1.poeni_domacin > u1.poeni_gost, 1, 0))) AS skor_div \n"
                    + "FROM utakmica u1 INNER JOIN tim t1 ON (t1.tim_id = u1.domacin OR t1.tim_id = u1.gost) INNER JOIN tim t2 ON (t2.tim_id = u1.domacin OR t2.tim_id = u1.gost) GROUP BY t1.tim_id) AS Tab2 ON (tim0 = tim1) \n"
                    + "INNER JOIN (SELECT tim3.tim_id AS id3, CONCAT(SUM(IF(tim3.tim_id = u2.domacin AND tim4.tim_id = u2.gost AND u2.poeni_domacin > u2.poeni_gost, 1, 0)) + SUM(IF(tim3.tim_id = u2.gost AND tim4.tim_id = u2.domacin AND u2.poeni_domacin < u2.poeni_gost, 1, 0)), '-', SUM(IF(tim3.tim_id = u2.domacin AND tim4.tim_id = u2.gost AND u2.poeni_domacin < u2.poeni_gost, 1, 0)) + SUM(IF(tim3.tim_id = u2.gost AND tim4.tim_id = u2.domacin AND u2.poeni_domacin > u2.poeni_gost, 1, 0))) AS skor_konf \n"
                    + "FROM utakmica u2 INNER JOIN (SELECT * FROM tim t3 INNER JOIN divizija d0 ON (t3.divizija = d0.divizija_id) INNER JOIN konferencija k0 ON (d0.konferencija = k0.konferencija_id)) AS tim3 ON (u2.domacin = tim3.tim_id OR tim3.tim_id = u2.gost) \n"
                    + "INNER JOIN (SELECT * FROM tim t4 INNER JOIN divizija d1 ON (t4.divizija = d1.divizija_id) INNER JOIN konferencija k1 ON (d1.konferencija = k1.konferencija_id)) AS tim4 ON (u2.gost = tim4.tim_id OR u2.domacin = tim4.tim_id) WHERE tim3.konferencija_id = tim4.konferencija_id GROUP BY tim3.tim_id) AS Tab3 ON (tim0 = id3) \n"
                    + "INNER JOIN (SELECT id4, TRUNCATE((dati_poeni/utakmica_ukupno), 2) AS prosek_datih_poena, TRUNCATE((primljeni_poeni/utakmica_ukupno), 2) AS prosek_primljenih_poena \n"
                    + "FROM (SELECT t5.tim_id AS id4, SUM(IF(t5.tim_id = u3.domacin, u3.poeni_domacin, 0)) + SUM(IF(t5.tim_id = u3.gost, u3.poeni_gost, 0)) AS dati_poeni, SUM(IF(t5.tim_id = u3.domacin, u3.poeni_gost, 0)) + SUM(IF(t5.tim_id = u3.gost, u3.poeni_domacin, 0)) AS primljeni_poeni, SUM(IF(t5.tim_id = u3.domacin, 1, 0)) + SUM(IF(t5.tim_id = u3.gost, 1,0)) AS utakmica_ukupno \n"
                    + "FROM tim t5 INNER JOIN utakmica u3 ON (t5.tim_id = u3.domacin OR t5.tim_id = u3.gost) WHERE u3.odigrana = TRUE GROUP BY t5.tim_id) AS poeni) AS Tab4 ON (tim0 = id4) INNER JOIN divizija dd ON (tim_divizija = dd.divizija_id) INNER JOIN konferencija kk ON (dd.konferencija = kk.konferencija_id) " + parametri[i] + " ORDER BY Uspesnost DESC, Pobede DESC, Porazi ASC", "standingsMapping").getResultList();
            int brojac = 1;
            double brojPobedaPrvi = lista.get(0).getBrojPobeda();
            double brojPorazaPrvi = lista.get(0).getBrojPoraza();

            for (Tim t : lista) {
                t.setPozicija(brojac);
                brojac++;
                t.setZaostatak((Math.abs(brojPobedaPrvi - t.getBrojPobeda()) + Math.abs(brojPorazaPrvi - t.getBrojPoraza())) / 2.00);
            }
            mapaTabele.put(nazivi[i], lista);
        }
        em.close();
        return mapaTabele;
    }

    public boolean zauzetoKorisnickoIme(String korisnickoIme) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            Korisnik k = (Korisnik) em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", korisnickoIme).getSingleResult();
            return false;
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
            return true;
        } finally {
            em.close();
        }
    }

    public boolean zauzetoKorisnickoIme(Korisnik k) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            Korisnik k1 = (Korisnik) em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", k.getKorisnickoIme()).getSingleResult();
            return !k1.equals(k);
        } catch (NoResultException nrex) {
            nrex.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public TipKorisnika vratiTipKorisnika(int tip) {
        EntityManager em = EMFactory.createEntityManager();
        TipKorisnika tk = (TipKorisnika) em.createNamedQuery("TipKorisnika.findByTipKorisnikaId").setParameter("tipKorisnikaId", tip).getSingleResult();
        em.close();
        return tk;
    }

    public void sacuvajKorisnika(Korisnik k) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        TipKorisnika tk = (TipKorisnika) em.createNamedQuery("TipKorisnika.findByTipNaziv").setParameter("tipNaziv", "Registrovani korisnik").getSingleResult();
        k.setTip(tk);
        k.setKorisnickaSifra(BCrypt.hashpw(k.getKorisnickaSifra(), BCrypt.gensalt(12)));
        em.persist(k);
        em.getTransaction().commit();
        em.close();
    }

    public void izmeniKorisnika(Korisnik korisnik) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        Korisnik k = em.find(Korisnik.class, korisnik.getKorisnikId());
        k.setIme(korisnik.getIme());
        k.setPrezime(korisnik.getPrezime());
        k.setDatumRodjenja(korisnik.getDatumRodjenja());
        k.setEmail(korisnik.getEmail());
        k.setKorisnickoIme(korisnik.getKorisnickoIme());
        k.setKorisnickaSifra(BCrypt.hashpw(korisnik.getKorisnickaSifra(), BCrypt.gensalt(12)));
        em.getTransaction().commit();
        em.close();
    }

    public List<FantasyLiga> vratiSveLige() {
        EntityManager em = EMFactory.createEntityManager();
        List<FantasyLiga> lista = em.createNamedQuery("FantasyLiga.findAll").getResultList();
        em.close();
        return lista;
    }

    public long vratiBrojTimovaLige(int liga) {
        EntityManager em = EMFactory.createEntityManager();
        long broj = (long) em.createNativeQuery("SELECT COUNT(*) FROM fantasy_tim WHERE liga = ?liga").setParameter("liga", liga).getSingleResult();
        em.close();
        return broj;
    }

    public StatusLige vratiStatusLige(int statusLige) {
        EntityManager em = EMFactory.createEntityManager();
        StatusLige sl = em.find(StatusLige.class, statusLige);
        em.close();
        return sl;
    }

    public boolean zauzetNazivLige(String nazivLige) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            FantasyLiga fl = (FantasyLiga) em.createNamedQuery("FantasyLiga.findByLigaNaziv").setParameter("ligaNaziv", nazivLige).getSingleResult();
            return false;
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
            return true;
        } finally {
            em.close();
        }
    }

    public void sacuvajLigu(String ligaNaziv, Korisnik k) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        StatusLige sl = (StatusLige) em.createNamedQuery("StatusLige.findByStatusLigeNaziv").setParameter("statusLigeNaziv", "Pre-draft").getSingleResult();
        Calendar c = Calendar.getInstance();
        c.set(2017, 1, 22);
        FantasyLiga fl = new FantasyLiga(ligaNaziv, c.getTime(), sl, k);
        em.persist(fl);
        Korisnik korisnik = fl.getAdministrator();
        korisnik.getFantasyLigaList().add(fl);
        em.getTransaction().commit();
        em.close();
    }

    public FantasyLiga vratiLigu(int ligaId) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyLiga fl = em.find(FantasyLiga.class, ligaId);
        em.close();
        return fl;
    }

    public boolean zauzetNazivTima(String nazivTima) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            FantasyTim ft = (FantasyTim) em.createNamedQuery("FantasyTim.findByTimNaziv").setParameter("timNaziv", nazivTima).getSingleResult();
            return false;
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
            return true;
        } finally {
            em.close();
        }
    }

    public void sacuvajTim(FantasyTim tim) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(tim);
        Korisnik k = tim.getVlasnik();
        k.getFantasyTimList().add(tim);
        FantasyLiga fl = tim.getLiga();
        fl.getFantasyTimList().add(tim);
        FantasyLiga fl_0 = em.find(FantasyLiga.class, fl.getLigaId());
        em.getTransaction().commit();
        em.close();
    }

    public FantasyTim vratiTimKorisnikaIzLige(int liga, Korisnik k) {
        try {
            EntityManager em = EMFactory.createEntityManager();
            FantasyLiga fl = em.find(FantasyLiga.class, liga);
            FantasyTim ft = (FantasyTim) em.createQuery("SELECT ft FROM FantasyTim ft WHERE ft.liga = :liga AND ft.vlasnik = :vlasnik").setParameter("liga", fl).setParameter("vlasnik", k).getSingleResult();
            ft.setValidator(new PozicijaValidator(ft));
            em.close();
            return ft;
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
            return null;
        }
    }

    public List<FantasyTim> vratiRedosledDraft(String ligaId) {
        EntityManager em = EMFactory.createEntityManager();
        List<FantasyTim> lista = em.createNativeQuery("SELECT * FROM fantasy_tim WHERE liga = ?ligaId ORDER BY RAND()", FantasyTim.class).setParameter("ligaId", ligaId).getResultList();
        List<FantasyTim> novaLista = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (i % 2 != 0) {
                List<FantasyTim> pomocnaLista = new ArrayList<>(lista);
                Collections.reverse(pomocnaLista);
                novaLista.addAll(pomocnaLista);
            } else {
                novaLista.addAll(lista);
            }
        }

        em.close();
        return novaLista;
    }

    public List<FantasyTim> vratiSveTimoveLige(int ligaId) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyLiga liga = (FantasyLiga) em.createNamedQuery("FantasyLiga.findByLigaId").setParameter("ligaId", ligaId).getSingleResult();
        List<FantasyTim> lista = em.createQuery("SELECT ft FROM FantasyTim ft WHERE ft.liga = :liga").setParameter("liga", liga).getResultList();
        em.close();
        return lista;
    }

    public String vratiAdministratoraLige(int liga) {
        EntityManager em = EMFactory.createEntityManager();
        String admin = (String) em.createNativeQuery("SELECT ft.tim_naziv FROM fantasy_tim ft INNER JOIN fantasy_liga fl ON (ft.liga = fl.liga_id) WHERE fl.administrator = ft.vlasnik AND ft.liga = ?liga").setParameter("liga", liga).getSingleResult();
        em.close();
        return admin;
    }

    public List<Igrac> vratiDostupneIgrace(int liga, String pozicija, String tim, boolean draft) {
        EntityManager em = EMFactory.createEntityManager();
        List<Igrac> lista = new ArrayList<>();
        String upit = "SELECT * FROM igrac i WHERE i.igrac_id NOT IN (SELECT fi.igrac FROM fantasy_igrac fi WHERE fi.liga = ?liga)";
        if (!pozicija.equals("svi")) {
            upit += " AND pozicija = ?pozicija";
        }
        if (!tim.equals("svi")) {
            upit += " AND tim = ?tim";
        }
        if (!draft) {
            upit += " UNION SELECT ig.* FROM igrac ig INNER JOIN fantasy_igrac fi ON (ig.igrac_id = fi.igrac) WHERE fi.status = 3 AND fi.liga = ?liga";
            if (!pozicija.equals("svi")) {
                upit += " AND ig.pozicija = ?pozicija";
            }
            if (!tim.equals("svi")) {
                upit += " AND ig.tim = ?tim";
            }
        }
        upit += " ORDER BY poeni_projekcija DESC, skokovi_projekcija DESC, asistencije_projekcija DESC";
        lista = em.createNativeQuery(upit, Igrac.class).setParameter("liga", liga).setParameter("pozicija", pozicija).setParameter("tim", tim).getResultList();
        em.close();
        return lista;
    }

    public List<Igrac> vratiDostupneIgrace(int liga, String unos, boolean draft) {
        EntityManager em = EMFactory.createEntityManager();
        String upit = "SELECT * FROM igrac WHERE igrac_id NOT IN (SELECT fi.igrac FROM fantasy_igrac fi INNER JOIN fantasy_tim ft ON (fi.tim = ft.fantasy_tim_id) WHERE ft.liga = ?liga)  AND (ime LIKE '%" + unos + "%' OR prezime LIKE '%" + unos + "%' OR CONCAT(ime, ' ', prezime) LIKE '%" + unos + "%')";
        if (!draft) {
            upit += " UNION SELECT ig.* FROM igrac ig INNER JOIN fantasy_igrac fi ON (ig.igrac_id = fi.igrac) WHERE fi.status = 3 AND fi.liga = ?liga";
        }
        List<Igrac> lista = em.createNativeQuery(upit, Igrac.class).setParameter("liga", liga).getResultList();
        em.close();
        return lista;
    }

    public List<Igrac> vratiSveIgrace(String pozicija, String tim) {
        EntityManager em = EMFactory.createEntityManager();
        String upit = "SELECT i FROM Igrac i";
        if (!pozicija.equals("svi") || !tim.equals("svi")) {
            upit += " WHERE";
        }
        if (!pozicija.equals("svi")) {
            upit += " i.pozicija = :pozicija";
        }
        if (!pozicija.equals("svi") && !tim.equals("svi")) {
            upit += " AND";
        }
        if (!tim.equals("svi")) {
            upit += " i.tim.timId = :tim";
        }
        upit += " ORDER BY i.prezime ASC";
        Query query = em.createQuery(upit);
        if (!pozicija.equals("svi")) {
            query.setParameter("pozicija", pozicija);
        }
        if (!tim.equals("svi")) {
            query.setParameter("tim", Integer.valueOf(tim));
        }
        List<Igrac> lista = query.getResultList();
        em.close();
        return lista;
    }

    public List<Igrac> vratiSveIgrace(String unos) {
        EntityManager em = EMFactory.createEntityManager();
        String upit = "SELECT * FROM igrac WHERE ime LIKE '%" + unos + "%' OR prezime LIKE '%" + unos + "%' OR CONCAT(ime, ' ', prezime) LIKE '%" + unos + "%'";
        List<Igrac> lista = em.createNativeQuery(upit, Igrac.class).getResultList();
        em.close();
        return lista;
    }

    public boolean validirajTim(int liga, String tim, int igrac) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            FantasyLiga fl = em.find(FantasyLiga.class, liga);
            Igrac i = em.find(Igrac.class, igrac);
            FantasyTim ft = (FantasyTim) em.createNamedQuery("FantasyTim.findByTimNazivAndLiga").setParameter("timNaziv", tim).setParameter("liga", fl).getSingleResult();
            long broj = (long) em.createNativeQuery("SELECT COUNT(*) FROM fantasy_igrac fi INNER JOIN igrac i ON (fi.igrac = i.igrac_id) WHERE fi.tim = " + ft.getFantasyTimId() + " AND i.pozicija = '" + i.getPozicijaKrace() + "' GROUP BY fi.tim").getSingleResult();
            return broj < 3;
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
            return true;
        } finally {
            em.close();
        }
    }

    public List<FantasyIgrac> vratiDosadIzabrane(int liga, String tim) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        FantasyTim ft = (FantasyTim) em.createNamedQuery("FantasyTim.findByTimNazivAndLiga").setParameter("timNaziv", tim).setParameter("liga", fl).getSingleResult();
        em.close();
        return ft.getFantasyIgracList();
    }

    public Igrac sacuvajIzbor(String ligaId, String nazivTima, int igracId) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyLiga fl = em.find(FantasyLiga.class, Integer.valueOf(ligaId));
        FantasyTim ft = (FantasyTim) em.createNamedQuery("FantasyTim.findByTimNaziv").setParameter("timNaziv", nazivTima).getSingleResult();
        Igrac i = em.find(Igrac.class, igracId);
        StatusIgraca si = (StatusIgraca) em.createNamedQuery("StatusIgraca.findByStatusIgracaNaziv").setParameter("statusIgracaNaziv", "Rezerva").getSingleResult();
        FantasyIgrac fi = new FantasyIgrac(fl, ft, i, si);
        em.persist(fi);
        em.getTransaction().commit();
        em.close();
        return i;
    }

    public void aktivirajLigu(int liga, List<FantasyUtakmica> lista) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        StatusLige sl = (StatusLige) em.createNamedQuery("StatusLige.findByStatusLigeNaziv").setParameter("statusLigeNaziv", "Post-draft").getSingleResult();
        fl.setStatus(sl);
        em.merge(fl);
        for (FantasyUtakmica fu : lista) {
            em.persist(fu);
        }
        em.getTransaction().commit();
        em.close();
    }

    public int vratiIdTima(int liga, String tim) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        FantasyTim ft = (FantasyTim) em.createQuery("SELECT ft FROM FantasyTim ft WHERE ft.liga = :liga AND ft.timNaziv = :tim").setParameter("liga", fl).setParameter("tim", tim).getSingleResult();
        em.close();
        return ft.getFantasyTimId();
    }

    public void ponistiDraft(int liga) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        List<FantasyIgrac> listaIgraca = em.createQuery("SELECT fi FROM FantasyIgrac fi JOIN fi.tim ft WHERE ft.liga = :liga").setParameter("liga", fl).getResultList();
        for (FantasyIgrac fi : listaIgraca) {
            fi = em.merge(fi);
            em.remove(fi);
        }
        em.getTransaction().commit();
        em.close();
    }

    public Tim vratiTim(String timApiId) {
        EntityManager em = EMFactory.createEntityManager();
        Tim t = (Tim) em.createNamedQuery("Tim.findByTimApiId").setParameter("timApiId", timApiId).getSingleResult();
        em.close();
        return t;
    }

    public void sacuvajUtakmice(List<Utakmica> lista) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        for (Utakmica u : lista) {
            em.persist(u);
        }
        em.getTransaction().commit();
        em.close();
    }

    public FantasyTim vratiFantasyTim(int fantasyTim, Korisnik k) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyTim ft = em.find(FantasyTim.class, fantasyTim);
        if (ft == null) {
            return null;
        }
        for (FantasyIgrac fi : ft.getFantasyIgracList()) {
            Igrac i = fi.getIgrac();
            Utakmica u = (Utakmica) em.createQuery("SELECT u FROM Utakmica u WHERE (u.gost = :tim OR u.domacin = :tim) AND u.odigrana = FALSE ORDER BY u.datum ASC").setParameter("tim", i.getTim()).setMaxResults(1).getSingleResult();
            i.setSledecaUtakmica(u);
            i.setUsi(new UtakmicaSkracenIspis());
        }
        List<FantasyUtakmica> lista = em.createNamedQuery("FantasyUtakmica.findByFantasyTim").setParameter("fantasyTim", ft).getResultList();
        ft.setListaSvihUtakmica(lista);
        if (k != null && k.equals(ft.getVlasnik())) {
            ft.setValidator(new PozicijaValidator(ft));
        }
        em.close();
        return ft;
    }

    public boolean postojiLiFantasyTim(int fantasyTim) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyTim ft = em.find(FantasyTim.class, fantasyTim);
        em.close();
        return (ft != null);
    }

    public void aktivirajIgraca(int fantasyIgrac) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyIgrac fi = em.find(FantasyIgrac.class, fantasyIgrac);
        StatusIgraca si = (StatusIgraca) em.createNamedQuery("StatusIgraca.findByStatusIgracaNaziv").setParameter("statusIgracaNaziv", "Aktivan").getSingleResult();
        fi.setStatus(si);
        em.getTransaction().commit();
        em.close();
    }

    public void staviNaKlupu(int fantasyIgrac) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyIgrac fi = em.find(FantasyIgrac.class, fantasyIgrac);
        StatusIgraca si = (StatusIgraca) em.createNamedQuery("StatusIgraca.findByStatusIgracaNaziv").setParameter("statusIgracaNaziv", "Rezerva").getSingleResult();
        fi.setStatus(si);
        em.getTransaction().commit();
        em.close();
    }

    public void sacuvajRoster(int fantasyTim, Map<Integer, String> mapaStatusi) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        for (Map.Entry par : mapaStatusi.entrySet()) {
            FantasyIgrac fi = em.find(FantasyIgrac.class, par.getKey());
            StatusIgraca si = (StatusIgraca) em.createNamedQuery("StatusIgraca.findByStatusIgracaNaziv").setParameter("statusIgracaNaziv", par.getValue()).getSingleResult();
            fi.setStatus(si);
            em.merge(fi);
        }
        em.getTransaction().commit();
        em.close();
    }

    public boolean proveriVlasnistvo(Korisnik k, int id, String tip) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            if (tip.equals("igrac")) {
                FantasyIgrac fi = em.find(FantasyIgrac.class, id);
                return (fi.getTim().getVlasnik().equals(k));
            } else if (tip.equals("tim")) {
                FantasyTim ft = em.find(FantasyTim.class, id);
                return (ft.getVlasnik().equals(k));
            }
            return false;
        } finally {
            em.close();
        }
    }

    public void otpustiIgraca(int igrac) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyIgrac fi = em.find(FantasyIgrac.class, igrac);
        StatusIgraca si = (StatusIgraca) em.createNamedQuery("StatusIgraca.findByStatusIgracaNaziv").setParameter("statusIgracaNaziv", "Neaktivan").getSingleResult();
        em.getTransaction().begin();
        fi.setStatus(si);
        FantasyTim ft = fi.getTim();
        fi.setTim(null);
        TipTransakcije tipT = (TipTransakcije) em.createNamedQuery("TipTransakcije.findByNazivTransakcije").setParameter("nazivTransakcije", "Otkaz").getSingleResult();
        Transakcija t = new Transakcija(DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranja(), ft, fi, tipT);
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    public Date vratiDatumZadnjegAzuriranja() {
        EntityManager em = EMFactory.createEntityManager();
        Calendar c = Calendar.getInstance();
        c.set(2017, 1, 22);
        try {
            Date datumPoslednjeUtakmice = (Date) em.createNativeQuery("SELECT datum FROM utakmica WHERE odigrana = true ORDER BY datum DESC").setMaxResults(1).getSingleResult();
            return datumPoslednjeUtakmice;
        } catch (NoResultException nrex) {
            return c.getTime();
        } finally {
            em.close();
        }
    }

    public Date vratiDatumZadnjegAzuriranjaLige(int liga) {
        EntityManager em = EMFactory.createEntityManager();
        Date datumZadnjegAzuriranjaLige = (Date) em.createQuery("SELECT fl.datumAzuriranja FROM FantasyLiga fl WHERE fl.ligaId = :liga").setParameter("liga", liga).getSingleResult();
        em.close();
        return datumZadnjegAzuriranjaLige;
    }

    public void odobriLigu(int liga) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        StatusLige sl = (StatusLige) em.createNamedQuery("StatusLige.findByStatusLigeNaziv").setParameter("statusLigeNaziv", "Sezona").getSingleResult();
        fl.setStatus(sl);
        Calendar c = Calendar.getInstance();
        c.set(2017, 1, 22);
        fl.setDatumAzuriranja(c.getTime());
        em.merge(fl);
        em.getTransaction().commit();
        em.close();
    }

    public List<String> vratiApiIdUtakmica(Date datum) {
        EntityManager em = EMFactory.createEntityManager();
        List<String> lista = em.createQuery("SELECT u.utakmicaApiId FROM Utakmica u WHERE u.datum = :datum").setParameter("datum", datum).getResultList();
        em.close();
        return lista;
    }

    public TimUcinakPK vratiTimUcinakPK(String utakmicaApiId, String timApiId) {
        EntityManager em = EMFactory.createEntityManager();
        int utakmica = (int) em.createQuery("SELECT u.utakmicaId FROM Utakmica u WHERE u.utakmicaApiId = :utakmicaApiId").setParameter("utakmicaApiId", utakmicaApiId).getSingleResult();
        int tim = (int) em.createQuery("SELECT t.timId FROM Tim t WHERE t.timApiId = :timApiId").setParameter("timApiId", timApiId).getSingleResult();
        em.close();
        return new TimUcinakPK(utakmica, tim);
    }

    public void sacuvajUcinakTima(TimUcinak timUcinak) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(timUcinak);
        em.getTransaction().commit();
        em.close();
    }

    public int vratiIgracId(String igracApiId) {
        EntityManager em = EMFactory.createEntityManager();
        int igracId = 0;
        try {
            igracId = (int) em.createQuery("SELECT i.igracId FROM Igrac i WHERE i.igracApiId = :igracApiId").setParameter("igracApiId", igracApiId).getSingleResult();
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
        }
        em.close();
        return igracId;
    }

    public void sacuvajUcinakIgraca(IgracUcinak igracUcinak) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(igracUcinak);
        em.getTransaction().commit();
        em.close();
    }

    public void azurirajUtakmicu(String utakmicaApiId, boolean odigrana, int brojGledalaca, String trajanje, int poeniDomacin, int poeniGost) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        Utakmica u = (Utakmica) em.createNamedQuery("Utakmica.findByUtakmicaApiId").setParameter("utakmicaApiId", utakmicaApiId).getSingleResult();
        u.setOdigrana(odigrana);
        u.setBrojGledalaca(brojGledalaca);
        u.setTrajanje(trajanje);
        u.setPoeniDomacin(poeniDomacin);
        u.setPoeniGost(poeniGost);
        em.getTransaction().commit();
        em.close();
    }

    public List<FantasyUtakmica> vratiFantasyUtakmice(int liga, Date datum) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyLiga fl = em.find(FantasyLiga.class,
                liga);
        List<FantasyUtakmica> lista = em.createNamedQuery("FantasyUtakmica.findByDatumAndLiga").setParameter("datum", datum).setParameter("liga", fl).getResultList();
        em.close();
        return lista;
    }

    public IgracUcinak vratiUcinakIgracaNaUtakmici(Igrac i, Date datum) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            Utakmica u = (Utakmica) em.createQuery("SELECT u FROM Utakmica u WHERE u.datum = :datum AND (u.domacin = :tim OR u.gost = :tim)").setParameter("datum", datum).setParameter("tim", i.getTim()).getSingleResult();
            IgracUcinak iu = (IgracUcinak) em.createQuery("SELECT iu FROM IgracUcinak iu WHERE iu.igracUcinakPK.utakmicaId = :utakmicaId AND iu.igracUcinakPK.igracId = :igracId").setParameter("utakmicaId", u.getUtakmicaId()).setParameter("igracId", i.getIgracId()).getSingleResult();
            return iu;
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public FantasyIgracUcestvovanje vratiUcestvovanjeFantasyIgraca(int igrac, int tim, int utakmica) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyIgracUcestvovanjePK fiuPK = new FantasyIgracUcestvovanjePK(igrac, tim, utakmica);
        FantasyIgracUcestvovanje fiu = em.find(FantasyIgracUcestvovanje.class,
                fiuPK);
        em.close();
        return fiu;
    }

    public FantasyTimUcinak vratiUcinakFantasyTima(int utakmica, int tim) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyTimUcinakPK ftuPK = new FantasyTimUcinakPK(utakmica, tim);
        FantasyTimUcinak ftu = em.find(FantasyTimUcinak.class,
                ftuPK);
        em.close();
        return ftu;
    }

    public void sacuvajUcinakFantasyTima(FantasyTimUcinak ftu) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyTimUcinak fantasyTU = em.find(FantasyTimUcinak.class, ftu.getFantasyTimUcinakPK());
        if (fantasyTU != null) {
            fantasyTU.postaviNoveVrednosti(ftu);
        } else {
            em.persist(ftu);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void sacuvajUcestvovanjeFantasyIgraca(FantasyIgracUcestvovanje fiu) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyIgracUcestvovanje fantasyIU = em.find(FantasyIgracUcestvovanje.class, fiu.getFantasyIgracUcestvovanjePK());
        if (fantasyIU != null) {
            fantasyIU.postaviNoveVrednosti(fiu);
        } else {
            em.persist(fiu);
        }
        em.getTransaction().commit();
        em.close();
    }

    public void azurirajLigu(int liga, Date datumAzuriranja) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        fl.setDatumAzuriranja(datumAzuriranja);
        em.getTransaction().commit();
        em.close();
    }

    public long vratiBrojAngazmana(FantasyTim ft) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyUtakmica fu = (FantasyUtakmica) em.createQuery("SELECT fu FROM FantasyUtakmica fu WHERE (fu.datumPocetak <= :datum AND fu.datumKraj >= :datum) AND (fu.domacin = :tim OR fu.gost = :tim)").setParameter("datum", DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranja()).setParameter("tim", ft).getSingleResult();
        long brojac = 0;
        try {
            brojac = (long) em.createNativeQuery("SELECT COUNT(*) FROM transakcija t INNER JOIN tip_transakcije tt ON (t.tip = tt.tip_transakcije_id) WHERE (t.datum_transakcije BETWEEN ?datumPocetak AND ?datumKraj) AND (tt.naziv_transakcije = 'Angažman') AND (t.tim = ?tim)").setParameter("datumPocetak", fu.getDatumPocetak()).setParameter("datumKraj", fu.getDatumKraj()).setParameter("tim", ft.getFantasyTimId()).getSingleResult();
        } catch (NoResultException nrex) {
            //nrex.printStackTrace();
        } finally {
            em.close();
        }
        return brojac;
    }

    public void sacuvajAngazman(int tim, int igrac) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyTim ft = em.find(FantasyTim.class, tim);
        Igrac i = em.find(Igrac.class, igrac);
        StatusIgraca si = (StatusIgraca) em.createNamedQuery("StatusIgraca.findByStatusIgracaNaziv").setParameter("statusIgracaNaziv", "Rezerva").getSingleResult();
        FantasyIgrac fi = null;
        try {
            fi = (FantasyIgrac) em.createQuery("SELECT fi FROM FantasyIgrac fi WHERE fi.liga = :liga AND fi.igrac = :igrac").setParameter("liga", ft.getLiga()).setParameter("igrac", i).getSingleResult();
            fi.setStatus(si);
            fi.setTim(ft);
        } catch (NoResultException nrex) {
            fi = new FantasyIgrac(ft.getLiga(), ft, i, si);
            em.persist(fi);
        } finally {
            TipTransakcije tipT = (TipTransakcije) em.createNamedQuery("TipTransakcije.findByNazivTransakcije").setParameter("nazivTransakcije", "Angažman").getSingleResult();
            Transakcija t = new Transakcija(DatabaseBroker.getInstance().vratiDatumZadnjegAzuriranja(), ft, fi, tipT);
            em.persist(t);
            em.getTransaction().commit();
            em.close();
        }
    }

    public void azurirajFantasyUtakmicu(int utakmica, int brojPoenaDomacin, int brojPoenaGost, int nereseno) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyUtakmica fu = em.find(FantasyUtakmica.class, utakmica);
        fu.setPoeniDomacin(brojPoenaDomacin);
        fu.setPoeniGost(brojPoenaGost);
        fu.setNereseno(nereseno);
        em.getTransaction().commit();
        em.close();
    }

    public void azurirajBrojPoenaTima(int tim, double brojPoena) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyTim ft = em.find(FantasyTim.class, tim);
        ft.setBrojPoena(ft.getBrojPoena() + brojPoena);
        em.getTransaction().commit();
        em.close();
    }

    public Igrac vratiIgraca(int igrac) {
        EntityManager em = EMFactory.createEntityManager();
        Igrac i = em.find(Igrac.class, igrac);
        if (i == null) {
            return null;
        }
        i.setUsi(new UtakmicaSkracenIspis());
        em.close();
        return i;
    }

    public Igrac vratiStatistikuIgraca(int igrac) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            Igrac i = (Igrac) em.createNativeQuery("SELECT igrac_id, \n"
                    + "COUNT(*) AS ukupno_igrao, \n"
                    + "SUM(IF(starter = TRUE, 1, 0)) AS ukupno_startovao, \n"
                    + "CONCAT(SUBSTRING(AVG(SUBSTRING(minuti, 1, 2)*60 + SUBSTRING(minuti, 4, 2))/60, 1, 2), '.', TRUNCATE(SUBSTRING(AVG(SUBSTRING(minuti, 1, 2)*60 + SUBSTRING(minuti, 4, 2))/60, 4, 2)*0.6, 0)) AS prosek_minuti, \n"
                    + "TRUNCATE(AVG(pogodak_iz_igre), 1) AS prosek_pogodak_iz_igre, \n"
                    + "TRUNCATE(AVG(pokusaj_iz_igre), 1) AS prosek_pokusaj_iz_igre, \n"
                    + "TRUNCATE((SUM(pogodak_iz_igre)/SUM(pokusaj_iz_igre))*100, 1) AS prosek_procenat_iz_igre, \n"
                    + "TRUNCATE(AVG(pogodak_3p), 1) AS prosek_pogodak_3p, \n"
                    + "TRUNCATE(AVG(pokusaj_3p), 1) AS prosek_pokusaj_3p, \n"
                    + "TRUNCATE((SUM(pogodak_3p)/SUM(pokusaj_3p))*100, 1) AS prosek_procenat_3p, \n"
                    + "TRUNCATE(AVG(pogodak_2p), 1) AS prosek_pogodak_2p, \n"
                    + "TRUNCATE(AVG(pokusaj_2p), 1) AS prosek_pokusaj_2p, \n"
                    + "TRUNCATE((SUM(pogodak_2p)/SUM(pokusaj_2p))*100, 1) AS prosek_procenat_2p, \n"
                    + "TRUNCATE(AVG(blokirani_sutevi), 1) AS prosek_blokirani_sutevi, \n"
                    + "TRUNCATE(AVG(pogodak_1p), 1) AS prosek_pogodak_1p, \n"
                    + "TRUNCATE(AVG(pokusaj_1p), 1) AS prosek_pokusaj_1p, \n"
                    + "TRUNCATE((SUM(pogodak_1p)/SUM(pokusaj_1p))*100, 1) AS prosek_procenat_1p, \n"
                    + "TRUNCATE(AVG(skokovi_napad), 1) AS prosek_skokovi_napad, \n"
                    + "TRUNCATE(AVG(skokovi_odbrana), 1) AS prosek_skokovi_odbrana, \n"
                    + "TRUNCATE(AVG(skokovi_ukupno), 1) AS prosek_skokovi_ukupno, \n"
                    + "TRUNCATE(AVG(asistencije), 1) AS prosek_asistencije, \n"
                    + "TRUNCATE(AVG(izgubljene_lopte), 1) AS prosek_izgubljene_lopte, \n"
                    + "TRUNCATE(AVG(ukradene_lopte), 1) AS prosek_ukradene_lopte, \n"
                    + "TRUNCATE(AVG(blokade), 1) AS prosek_blokade, \n"
                    + "TRUNCATE(AVG(licne_greske), 1) AS prosek_licne_greske, \n"
                    + "TRUNCATE(AVG(poeni), 1) AS prosek_poeni\n"
                    + "FROM igrac_ucinak WHERE igrac_id = ?id", "igracStatistikaMapping").setParameter("id", igrac).getSingleResult();
            //System.out.println("Statistika ---> " + i.getUkupnoIgrao() + "   " + i.getUkupnoStartovao() + "   " + i.getProsekMinuti() + "   " + i.getProsekPogodakIzIgre() + "   " + i.getProsekPokusajIzIgre() + "   " + i.getProsekProcenatIzIgre() + "   " + i.getProsekPogodak3p() + "   " + i.getProsekPokusaj3p() + "   " + i.getProsekProcenat3p() + "   " + i.getProsekPogodak2p() + "   " + i.getProsekPokusaj2p() + "   " + i.getProsekProcenat2p() + "   " + i.getProsekBlokiraniSutevi() + "   " + i.getProsekPogodak1p() + "   " + i.getProsekPokusaj1p() + "   " + i.getProsekProcenat1p() + "   " + i.getProsekSkokoviNapad() + "   " + i.getProsekSkokoviOdbrana() + "   " + i.getProsekSkokoviUkupno() + "   " + i.getProsekAsistencije() + "   " + i.getProsekIzgubljeneLopte() + "   " + i.getProsekUkradeneLopte() + "   " + i.getProsekBlokade() + "   " + i.getProsekLicneGreske() + "   " + i.getProsekPoeni());
            return i;
        } catch (PersistenceException pex) {
            //pex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public Tim vratiTim(int tim) {
        EntityManager em = EMFactory.createEntityManager();
        Tim t = em.find(Tim.class, tim);
        List<Utakmica> lista = em.createQuery("SELECT u FROM Utakmica u WHERE (u.domacin = :tim OR u.gost = :tim) AND u.odigrana = TRUE ORDER BY u.datum ASC").setParameter("tim", t).getResultList();
        if (t == null) {
            return null;
        }
        t.setListaSvihUtakmica(lista);
        String trenutniSkor = (String) em.createNativeQuery("SELECT CONCAT(SUM(IF(poeni_domacin > poeni_gost AND domacin = ?tim, 1, 0)) + SUM(IF(poeni_domacin < poeni_gost AND gost = ?tim, 1, 0)), '-', \n"
                + "SUM(IF(poeni_domacin > poeni_gost AND gost = ?tim, 1, 0)) + SUM(IF(poeni_domacin < poeni_gost AND domacin = ?tim, 1, 0))) AS trenutni_skor \n"
                + "FROM utakmica").setParameter("tim", tim).getSingleResult();
        t.setTrenutniSkor(trenutniSkor);
        t.setUsi(new UtakmicaSkracenIspis());
        em.close();
        return t;
    }

    public Tim vratiStatistikuTima(int tim) {
        EntityManager em = EMFactory.createEntityManager();
        try {
            Tim t = (Tim) em.createNativeQuery("SELECT tim, \n"
                    + "COUNT(*) AS ukupno_utakmica, \n"
                    + "TRUNCATE(AVG(poeni), 1) AS prosek_poeni, \n"
                    + "TRUNCATE(AVG(pogodak_iz_igre), 1) AS prosek_pogodak_iz_igre, \n"
                    + "TRUNCATE(AVG(pokusaj_iz_igre), 1) AS prosek_pokusaj_iz_igre, \n"
                    + "TRUNCATE((SUM(pogodak_iz_igre)/SUM(pokusaj_iz_igre))*100, 1) AS prosek_procenat_iz_igre, \n"
                    + "TRUNCATE(AVG(pogodak_3p), 1) AS prosek_pogodak_3p, \n"
                    + "TRUNCATE(AVG(pokusaj_3p), 1) AS prosek_pokusaj_3p, \n"
                    + "TRUNCATE((SUM(pogodak_3p)/SUM(pokusaj_3p))*100, 1) AS prosek_procenat_3p, \n"
                    + "TRUNCATE(AVG(pogodak_2p), 1) AS prosek_pogodak_2p, \n"
                    + "TRUNCATE(AVG(pokusaj_2p), 1) AS prosek_pokusaj_2p, \n"
                    + "TRUNCATE((SUM(pogodak_2p)/SUM(pokusaj_2p))*100, 1) AS prosek_procenat_2p, \n"
                    + "TRUNCATE(AVG(blokirani_sutevi), 1) AS prosek_blokirani_sutevi, \n"
                    + "TRUNCATE(AVG(pogodak_1p), 1) AS prosek_pogodak_1p, \n"
                    + "TRUNCATE(AVG(pokusaj_1p), 1) AS prosek_pokusaj_1p, \n"
                    + "TRUNCATE((SUM(pogodak_1p)/SUM(pokusaj_1p))*100, 1) AS prosek_procenat_1p, \n"
                    + "TRUNCATE(AVG(skokovi_napad), 1) AS prosek_skokovi_napad, \n"
                    + "TRUNCATE(AVG(skokovi_odbrana), 1) AS prosek_skokovi_odbrana, \n"
                    + "TRUNCATE(AVG(skokovi_ukupno), 1) AS prosek_skokovi_ukupno, \n"
                    + "TRUNCATE(AVG(asistencije), 1) AS prosek_asistencije, \n"
                    + "TRUNCATE(AVG(izgubljene_lopte), 1) AS prosek_izgubljene_lopte, \n"
                    + "TRUNCATE(AVG(ukradene_lopte), 1) AS prosek_ukradene_lopte, \n"
                    + "TRUNCATE(AVG(blokade), 1) AS prosek_blokade, \n"
                    + "TRUNCATE(AVG(licne_greske), 1) AS prosek_licne_greske, \n"
                    + "TRUNCATE(AVG(poeni_iz_kontre), 1) AS prosek_poeni_iz_kontre, \n"
                    + "TRUNCATE(AVG(poeni_iz_reketa), 1) AS prosek_poeni_iz_reketa, \n"
                    + "TRUNCATE(AVG(poeni_iz_izgubljenih_lopti), 1) AS prosek_poeni_iz_izgubljenih_lopti \n"
                    + "FROM tim_ucinak WHERE tim = ?tim", "timStatistikaMapping").setParameter("tim", tim).getSingleResult();
            return t;
        } catch (PersistenceException pex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Utakmica> vratiUtakmiceOdredjenogDatuma(String datumUtakmice) throws ParseException {
        EntityManager em = EMFactory.createEntityManager();
        Date datum = WebConstants.sdf.parse(datumUtakmice);
        List<Utakmica> lista = em.createNamedQuery("Utakmica.findByDatum").setParameter("datum", datum).getResultList();
        em.close();
        return lista;
    }

    public List<Utakmica> vratiUtakmiceTima(String tim) {
        EntityManager em = EMFactory.createEntityManager();
        Tim t = em.find(Tim.class, Integer.valueOf(tim));
        List<Utakmica> lista = em.createNamedQuery("Utakmica.findByTim").setParameter("tim", t).getResultList();
        em.close();
        return lista;
    }

    public Map<String, Object> vratiStatistikuUtakmice(int utakmica) {
        EntityManager em = EMFactory.createEntityManager();
        Map<String, Object> mapaStatistika = new HashMap<>();
        Utakmica u = em.find(Utakmica.class, utakmica);
        TimUcinakPK tuDomacinPK = new TimUcinakPK(utakmica, u.getDomacin().getTimId());
        TimUcinak tuDomacin = (TimUcinak) em.createNamedQuery("TimUcinak.findByTimUcinakPK").setParameter("timUcinakPK", tuDomacinPK).getSingleResult();
        TimUcinakPK tuGostPK = new TimUcinakPK(utakmica, u.getGost().getTimId());
        TimUcinak tuGost = (TimUcinak) em.createNamedQuery("TimUcinak.findByTimUcinakPK").setParameter("timUcinakPK", tuGostPK).getSingleResult();
        List<IgracUcinak> listaIgracaDomacin = em.createQuery("SELECT iu FROM IgracUcinak iu INNER JOIN iu.igrac.tim t WHERE iu.igracUcinakPK.utakmicaId = :utakmica AND t.timId = :tim ORDER BY iu.starter DESC, iu.igrac.pozicija").setParameter("utakmica", utakmica).setParameter("tim", u.getDomacin().getTimId()).getResultList();
        List<IgracUcinak> listaIgracaGost = em.createQuery("SELECT iu FROM IgracUcinak iu INNER JOIN iu.igrac.tim t WHERE iu.igracUcinakPK.utakmicaId = :utakmica AND t.timId = :tim ORDER BY iu.starter DESC, iu.igrac.pozicija").setParameter("utakmica", utakmica).setParameter("tim", u.getGost().getTimId()).getResultList();
        mapaStatistika.put("utakmica", u);
        mapaStatistika.put("ucinakDomacin", tuDomacin);
        mapaStatistika.put("ucinakGost", tuGost);
        mapaStatistika.put("ucinakIgracaDomacin", listaIgracaDomacin);
        mapaStatistika.put("ucinakIgracaGost", listaIgracaGost);
        em.close();
        return mapaStatistika;
    }

    public List<FantasyUtakmica> vratiUtakmiceLige(int liga) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        List<FantasyUtakmica> listaUtakmica = em.createQuery("SELECT fu FROM FantasyUtakmica fu WHERE fu.domacin.liga = :liga AND fu.gost.liga = :liga ORDER BY fu.kolo ASC").setParameter("liga", fl).getResultList();
        for (FantasyUtakmica fu : listaUtakmica) {
            FantasyTimUcinak ftuDomacin = em.find(FantasyTimUcinak.class, new FantasyTimUcinakPK(fu.getFantasyUtakmicaId(), fu.getDomacin().getFantasyTimId()));
            if (ftuDomacin == null) {
                ftuDomacin = new FantasyTimUcinak(new FantasyTimUcinakPK(fu.getFantasyUtakmicaId(), fu.getDomacin().getFantasyTimId()), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            }
            fu.setFtuDomacin(ftuDomacin);
            FantasyTimUcinak ftuGost = em.find(FantasyTimUcinak.class, new FantasyTimUcinakPK(fu.getFantasyUtakmicaId(), fu.getGost().getFantasyTimId()));
            if (ftuGost == null) {
                ftuGost = new FantasyTimUcinak(new FantasyTimUcinakPK(fu.getFantasyUtakmicaId(), fu.getGost().getFantasyTimId()), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            }
            fu.setFtuGost(ftuGost);
        }
        em.close();
        return listaUtakmica;
    }

    public void obrisiLigu(int liga) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyLiga fl = em.find(FantasyLiga.class, liga);
        em.remove(fl);
        em.getTransaction().commit();
        em.close();
    }

    public void obrisiTim(int tim) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        FantasyTim ft = em.find(FantasyTim.class, tim);
        em.remove(ft);
        em.getTransaction().commit();
        em.close();
    }

    public void obrisiKorisnika(int korisnik) {
        EntityManager em = EMFactory.createEntityManager();
        em.getTransaction().begin();
        Korisnik k = em.find(Korisnik.class, korisnik);
        em.remove(k);
        em.getTransaction().commit();
        em.close();
    }

    public List<FantasyIgrac> vratiSastavFantasyTima(int tim) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyTim ft = em.find(FantasyTim.class, tim);
        List<FantasyIgrac> lista = em.createNamedQuery("FantasyIgrac.findByFantasyTim").setParameter("tim", ft).getResultList();
        em.close();
        return lista;
    }

    public FantasyIgrac vratiFantasyIgraca(int fantasyIgrac) {
        EntityManager em = EMFactory.createEntityManager();
        FantasyIgrac fi = em.find(FantasyIgrac.class, fantasyIgrac);
        em.close();
        return fi;
    }

    public List<Korisnik> vratiSveKorisnike() {
        EntityManager em = EMFactory.createEntityManager();
        List<Korisnik> lista = em.createNamedQuery("Korisnik.findAll").getResultList();
        em.close();
        return lista;
    }

    public Korisnik vratiKorisnika(int korisnik) {
        EntityManager em = EMFactory.createEntityManager();
        Korisnik k = em.find(Korisnik.class, korisnik);
        em.close();
        return k;
    }

    public boolean postojiLiKorisnik(int korisnik) {
        EntityManager em = EMFactory.createEntityManager();
        Korisnik k = em.find(Korisnik.class, korisnik);
        em.close();
        return (k != null);
    }

}
