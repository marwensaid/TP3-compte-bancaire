/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import tp3.entities.CompteBancaire;

/**
 *
 * @author marwen
 */
@Stateless
public class CompteBancaireFacade {

    public CompteBancaireFacade() {
    }

    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @return
     */
    protected EntityManager getEntityManager() {
        return em;
    }

    public void creerCompte(CompteBancaire[] compteBancaires) {
        for (CompteBancaire compteBancaire : compteBancaires) {
            createAccount(compteBancaire);
        }
    }

    private void persist(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public void createAccountTest() {
        createAccount(new CompteBancaire("John Lennon", 150000));
        createAccount(new CompteBancaire("Paul McCartney", 950000));
        createAccount(new CompteBancaire("Ringo Starr", 20000));
        createAccount(new CompteBancaire("Georges Harrisson", 100000));
    }

    public void createAccount(CompteBancaire compteBancaire) {
        persist(compteBancaire);
    }

    public List<CompteBancaire> getAll() {
        return em.createNamedQuery("CompteBancaire.getAll").getResultList();
    }

    public List<CompteBancaire> findAllAccount() {
        Query q = em.createQuery("select c from CompteBancaire c");
        return q.getResultList();
    }

    public void updateCompte(CompteBancaire compteBancaire) {
        em.merge(compteBancaire);
    }

    public void deleteAccount(CompteBancaire compteBancaire) {
        em.remove(em.merge(compteBancaire));
    }

    public void transferAccount(int id2, int id1, double montant) {
        debitAccount(id1, montant);
        creditingAccount(id2, montant);
    }

    private void debitAccount(int id, double montant) {
                CompteBancaire compteBancaire = em.find(CompteBancaire.class, id);
                compteBancaire.debit(montant);
                

    }

    private void creditingAccount(int id, double montant) {
        CompteBancaire compteBancaire=em.find(CompteBancaire.class, id);
        compteBancaire.crediting(montant);
    }

}
