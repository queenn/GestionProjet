/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.test.recette;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author g33252
 */
public class TestAjoutTache extends TestDeBase {

    private String bouton_ajout_tache = "id=formAfficherTaches:creerTacheBtn";
    private String lienProjet1 = "link=Projet 1";
    private String bouton_cree_tache = "id=formCreerTache:btnCreer";
    private String bouton_annuler_tache = "id=formCreerTache:btnAnnuler";
    private String champ_tache_nom = "id=formCreerTache:Nom";
    private String combo_priorite = "id=formCreerTache:priorite";
    private String champ_description = "id=formCreerTache:description";
    private String prioTresHaute = "Très important";
    private String prioNormale = "Normale";
    private String prioImportante = "Important";

    public TestAjoutTache() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        creerUser();
        seConnecter();
        selenium.open("/GestionProjet/pages/accueil.xhtml");
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        //supprimerUser();
    }

    @Test
    public void ajoutTachePrioEtNomDifferents() {
        ajouterUneTacheEtLaRetrouver("tache1", prioNormale);
        ajouterUneTacheEtLaRetrouver("tache2", prioImportante);
        ajouterUneTacheEtLaRetrouver("tache3", prioTresHaute);
    }

    @Test
    public void ajoutDeuxTachesMemeNoms() {
        ajouterUneTacheEtLaRetrouver("tache5", prioNormale);
        ajouterUneTacheEtLaRetrouver("tache5", prioImportante);
    }

    @Test
    public void ajoutTachePuisAnnuler() {
        selenium.open("/GestionProjet/pages/accueil.xhtml");
        attendre();
        selenium.click(lienProjet1);
        selenium.click(bouton_ajout_tache);
        attendre();
        selenium.type(champ_tache_nom, "tache annulee");
        selenium.select(combo_priorite, "label=" + prioNormale);
        selenium.click(bouton_annuler_tache);
        attendre();
        Assert.assertFalse(selenium.isTextPresent("tache annulee"));
    }

    public void ajouterUneTacheEtLaRetrouver(String nomTache, String priorite) {
        selenium.open("/GestionProjet/pages/accueil.xhtml");
        selenium.click(lienProjet1);
        attendre();
        selenium.click(bouton_ajout_tache);
        attendre();
        selenium.type(champ_tache_nom, nomTache);
        selenium.select(combo_priorite, "label=" + priorite);
        selenium.type(champ_description, "Commentaire");
        selenium.click(bouton_cree_tache);
        attendre();
        Assert.assertTrue(selenium.isTextPresent(nomTache));
    }
}
