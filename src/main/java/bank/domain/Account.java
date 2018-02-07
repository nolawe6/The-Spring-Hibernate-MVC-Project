package bank.domain;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;



//@XmlRootElement
@Entity
public class Account {
	@Id
	private long accountNumber;
	
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<AccountEntry> entryList = new ArrayList<AccountEntry>();
	
	@OneToOne(cascade=CascadeType.ALL)
	private Customer customer;
	
	public Account(){}
	
	public Account (long accountnr){
		this.accountNumber = accountnr;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		double balance=0;
		for (AccountEntry entry : entryList) {
			balance+=entry.getAmount();
		}
		return balance;
	}
	public void deposit(double amount){
		AccountEntry entry = new AccountEntry(new Date(), amount, "deposit", "", "");
		entryList.add(entry);
	}
	public void withdraw(double amount){
		AccountEntry entry = new AccountEntry(new Date(), -amount, "withdraw", "", "");
		entryList.add(entry);	
	}

	private void addEntry(AccountEntry entry){
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description){
		AccountEntry fromEntry = new AccountEntry(new Date(), -amount, description, ""+toAccount.getAccountNumber(), toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(new Date(), amount, description, ""+toAccount.getAccountNumber(), toAccount.getCustomer().getName());
		entryList.add(fromEntry);	
		toAccount.addEntry(toEntry);
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

//	@Override
//	public String toString() {
//		return "Account [customer=" + customer + "]";
//	}

}
