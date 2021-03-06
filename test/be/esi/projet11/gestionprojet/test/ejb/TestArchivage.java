package be.esi.projet11.gestionprojet.test.ejb;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.ejb.TacheEJB;
import be.esi.projet11.gestionprojet.entity.Tache;
import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import be.esi.projet11.gestionprojet.exception.TacheException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.ejb.embeddable.EJBContainer;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g32460
 */
public class TestArchivage {

    static EJBContainer container;
    static TacheEJB instanceTacheEJB;
    static Long id;
    static Tache tache;
    private static HashMap<Object, Object> properties;
    static Collection<Tache> collection;
    private static ProjetEJB instanceProjetEJB;

    public TestArchivage() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        instanceTacheEJB = (TacheEJB) container.getContext().lookup("java:global/GestionProjet/classes/TacheEJB");
        instanceProjetEJB = (ProjetEJB) container.getContext().lookup("java:global/GestionProjet/classes/ProjetEJB");
        collection = new ArrayList<Tache>();
        collection.add(instanceTacheEJB.creerTache("4","", ImportanceEnum.IMPORTANT,instanceProjetEJB.creerProjet("Projet 6","description")));    
        id = instanceTacheEJB.getTache("4").getId();
        tache = instanceTacheEJB.getTache("4");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        container.close();
    }  

    @Test
    public void testArchivageRéussi() throws TacheException {
        instanceTacheEJB.archiverTache(tache);
        assertTrue(instanceTacheEJB.getTache(id).isArchive());
    }

    @Test
    public void testDesarchivageRéussi() throws TacheException {
        instanceTacheEJB.desarchiverTache(tache);
        assertFalse(instanceTacheEJB.getTache(id).isArchive());
    }
   
}
