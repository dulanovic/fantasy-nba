package db;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EMFactory implements ServletContextListener {

    private static EntityManagerFactory emf;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        emf = Persistence.createEntityManagerFactory("FantasyNBAPU");
        try {
            new ProcessBuilder("cmd.exe", "/c", "cd \"C:\\wamp64\" && wampmanager.exe").redirectErrorStream(true).start();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        emf.close();
    }

    public static EntityManager createEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("Aplikacija nije inicijalizovana!!!");
        }
        return emf.createEntityManager();
    }

}
