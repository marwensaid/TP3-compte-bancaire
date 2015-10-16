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
import tp3.entities.BankAccount;

/**
 *
 * @author marwen
 */
@Stateless
public class BankAccountFacade {

    public BankAccountFacade() {
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

    public void createAccount(BankAccount[] bankAccount) {
        for (BankAccount account : bankAccount) {
            BankAccountFacade.this.createAccount(account);
        }
    }

    private void persist(BankAccount bankAccount) {
        em.persist(bankAccount);
    }

    public void createAccountTest() {
        BankAccountFacade.this.createAccount(new BankAccount("John Lennon", 150000));
        BankAccountFacade.this.createAccount(new BankAccount("Paul McCartney", 950000));
        BankAccountFacade.this.createAccount(new BankAccount("Ringo Starr", 20000));
        BankAccountFacade.this.createAccount(new BankAccount("Georges Harrisson", 100000));
    }

    public void createAccount(BankAccount bankAccount) {
        persist(bankAccount);
    }

    public List<BankAccount> getAll() {
        return em.createNamedQuery("BankAccount.getAll").getResultList();
    }

    public List<BankAccount> findAllAccount() {
        Query q = em.createQuery("select c from BankAccount c");
        return q.getResultList();
    }

    public void updateAccount(BankAccount bankAccount) {
        em.merge(bankAccount);
    }

    public void deleteAccount(BankAccount bankAccount) {
        em.remove(em.merge(bankAccount));
    }

    public void transferAccount(int id2, int id1, double montant) {
        debitAccount(id1, montant);
        creditingAccount(id2, montant);
    }

    public void debitAccount(int id, double montant) {
        BankAccount bankAccount = em.find(BankAccount.class, id);
        bankAccount.debit(montant);

    }

    public void creditingAccount(int id, double montant) {
        BankAccount bankAccount = em.find(BankAccount.class, id);
        bankAccount.crediting(montant);
    }

    public void persist(Object Object) {
        em.persist(Object);
    }

}
