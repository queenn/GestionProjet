/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.ejb;

import be.esi.projet11.gestionprojet.entity.Membre;
import be.esi.projet11.gestionprojet.exception.BusinessException;
import be.esi.projet11.gestionprojet.exception.DBException;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author g35001
 */
@Stateful
public class FacadeMembreEJB {

    @EJB
    private MembreEJB userEJB;
    private Membre authenticatedUser;

    public Membre createUser(String login, String password, String mail,
            String nom, String prenom) throws BusinessException {
        try {
            return userEJB.addUser(login, password, mail, nom, prenom);
        } catch (Exception e) {
            System.out.println("FacadeException : " + e);
            throw new BusinessException(e.getMessage());
        }
    }

    public Membre authenticateUser(String login, String password) throws BusinessException {
        try {
            authenticatedUser = userEJB.getUserByAuthentification(login, password);
            return authenticatedUser;
        } catch (DBException e) {
            authenticatedUser = null;
            throw new BusinessException(e.getMessage());
        }
    }
    
    public boolean isAuthenticated() {
        return authenticatedUser != null;
    }
}