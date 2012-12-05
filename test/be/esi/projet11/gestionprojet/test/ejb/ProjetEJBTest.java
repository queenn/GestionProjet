/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.ejb;

import be.esi.projet11.gestionprojet.ejb.MembreEJB;
import be.esi.projet11.gestionprojet.ejb.ProjetEJB;
import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.entity.ParticipeProjet;
import be.esi.projet11.gestionprojet.entity.Projet;
import java.util.HashMap;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author g34771
 */
public class ProjetEJBTest {

    private static EJBContainer container;
    private static ProjetEJB projetEJB;
    private static MembreEJB membreEJB;
    private static HashMap<Object,Object> properties;

    public ProjetEJBTest() {
    }

    @BeforeClass
    public static void setUpClass() throws NamingException {
        properties = new HashMap<Object, Object>();
        properties.put(EJBContainer.APP_NAME, "GestionProjet");
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer(properties);
        projetEJB = (ProjetEJB) container.getContext().lookup("java:global/GestionProjet/classes/ProjetEJB");
        membreEJB = (MembreEJB) container.getContext().lookup("java:global/GestionProjet/classes/MembreEJB");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of removeParticipeProjet method, of class ProjetEJB.
     */
    @Test
    public void testRemoveParticipeProjet() throws Exception {
        Projet projet = projetEJB.creerProjet();
        Membre mbr = membreEJB.addUser("null", "null", "null@null.null", "null", "null");
        membreEJB.ajoutMembreProjet(mbr.getMail(), projet);
        projetEJB.removeParticipeProjet(projet, mbr);
        Projet projetEnDB = projetEJB.getProjetById(projet.getId());
        assertEquals(0, projetEnDB.getAllParticipant().size());
    }

    /**
     * Test of accepterParticipant method, of class ProjetEJB.
     */
    @Test
    public void testAccepterParticipant() throws Exception {
        Projet projet = projetEJB.creerProjet();
        Membre mbr = membreEJB.addUser("null", "null", "null@null.null", "null", "null");
        membreEJB.ajoutMembreProjet(mbr.getMail(), projet);
        projetEJB.accepterParticipant(projet, mbr);
        Projet projetEnDB = projetEJB.getProjetById(projet.getId());
        ParticipeProjet participationDeCeMembreAuProjet = projetEnDB.getParticipants().get(0);
        assertTrue(participationDeCeMembreAuProjet.isAccepter() );
    }
}
