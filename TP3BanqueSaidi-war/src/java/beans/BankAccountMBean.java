/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import tp3.entities.BankAccount;
import tp3.session.BankAccountFacade;

/**
 *
 * @author marwen
 */
@Stateless
@LocalBean
public class BankAccountMBean {

    @EJB
    private BankAccountFacade bankAccountFacade;

    private int idCompteACrediter;
    private double montantACrediter;
    private int idCompteADebiter;
    private double montantADebiter;
    private int id1;
    private int id2;
    private double montantTransfert;
    private String message;

    public String getMessage() {
        return message;
    }

    /**
     * Get the value of id2
     *
     * @return the value of id2
     */
    public int getId2() {
        return id2;
    }

    /**
     * Set the value of id2
     *
     * @param id2 new value of id2
     */
    public void setId2(int id2) {
        this.id2 = id2;
    }

    /**
     * Get the value of montantTransfert
     *
     * @return the value of montantTransfert
     */
    public double getMontantTransfert() {
        return montantTransfert;
    }

    /**
     * Set the value of montantTransfert
     *
     * @param montantTransfert new value of montantTransfert
     */
    public void setMontantTransfert(double montantTransfert) {
        this.montantTransfert = montantTransfert;
    }

    /**
     * Get the value of id1
     *
     * @return the value of id1
     */
    public int getId1() {
        return id1;
    }

    /**
     * Set the value of id1
     *
     * @param id1 new value of id1
     */
    public void setId1(int id1) {
        this.id1 = id1;
    }

    /**
     * Get the value of montantADebiter
     *
     * @return the value of montantADebiter
     */
    public double getMontantADebiter() {
        return montantADebiter;
    }

    /**
     * Set the value of montantADebiter
     *
     * @param montantADebiter new value of montantADebiter
     */
    public void setMontantADebiter(double montantADebiter) {
        this.montantADebiter = montantADebiter;
    }

    /**
     * Get the value of idCompteADebiter
     *
     * @return the value of idCompteADebiter
     */
    public int getIdCompteADebiter() {
        return idCompteADebiter;
    }

    /**
     * Set the value of idCompteADebiter
     *
     * @param idCompteADebiter new value of idCompteADebiter
     */
    public void setIdCompteADebiter(int idCompteADebiter) {
        this.idCompteADebiter = idCompteADebiter;
    }

    /**
     * Get the value of montantACrediter
     *
     * @return the value of montantACrediter
     */
    public double getMontantACrediter() {
        return montantACrediter;
    }

    /**
     * Set the value of montantACrediter
     *
     * @param montantACrediter new value of montantACrediter
     */
    public void setMontantACrediter(double montantACrediter) {
        this.montantACrediter = montantACrediter;
    }

    /**
     * Get the value of idCompteACrediter
     *
     * @return the value of idCompteACrediter
     */
    public int getIdCompteACrediter() {
        return idCompteACrediter;
    }

    /**
     * Set the value of idCompteACrediter
     *
     * @param idCompteACrediter new value of idCompteACrediter
     */
    public void setIdCompteACrediter(int idCompteACrediter) {
        this.idCompteACrediter = idCompteACrediter;
    }

    /**
     * Creates a new instance of ComptesBancairesMBean
     */
    public BankAccountMBean() {
    }

    public List<BankAccount> getComptesBancaires() {
        return bankAccountFacade.findAllAccount();
    }

    public void createAccountTest() {
        System.out.println("### ACCOUNT CREATED ###");
        bankAccountFacade.createAccountTest();
    }

    public void creditingAccount() {
        bankAccountFacade.creditingAccount(idCompteACrediter, montantACrediter);
    }

    public void debitAccount() {
        bankAccountFacade.debitAccount(idCompteADebiter, montantADebiter);
    }

    public void transfer() {

        try {
            bankAccountFacade.transferAccount(id1, id2, montantTransfert);
        } catch (Exception e) {
            message = "impossible tranfer";
            System.out.println("### try tomorrow" + e);
        }
    }
}
