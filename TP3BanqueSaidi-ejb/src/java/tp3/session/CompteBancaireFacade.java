/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp3.session;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tp3.entities.CompteBancaire;

/**
 *
 * @author marwen
 */
@Stateless
public class CompteBancaireFacade {

    @PersistenceContext(unitName = "TP3BanqueSaidi-ejbPU")
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
            creerCompte(compteBancaire);
        }
    }

    private void persist(CompteBancaire compteBancaire) {
        em.persist(compteBancaire);
    }

    public void creerComptesTest() {
        creerCompte(new CompteBancaire("John Lennon", 150000));
        creerCompte(new CompteBancaire("Paul McCartney", 950000));
        creerCompte(new CompteBancaire("Ringo Starr", 20000));
        creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }

    public void creerCompte(CompteBancaire compteBancaire) {
        persist(compteBancaire);
    }

    public List<CompteBancaire> getAll() {
        return em.createNamedQuery("CompteBancaire.getAll").getResultList();
    }

    public void updateCompte(CompteBancaire compteBancaire) {
        em.merge(compteBancaire);
    }

    public void supprimerCompte(CompteBancaire compteBancaire) {
        em.remove(em.merge(compteBancaire));
    }

    public void transfererCompte(CompteBancaire source, CompteBancaire destination,
            int montant) {
        int val = source.retirer(montant);

        if (val == 0) {
            LOGGER.warning("vous étes dans la merde");
        }

        destination.deposer(montant);
        updateCompte(source);
        updateCompte(destination);
    }

}
