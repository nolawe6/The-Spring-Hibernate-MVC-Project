package bank.service;

import java.util.Collection;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bank.DAO.AccountDAO;
import bank.DAO.IAccountDAO;
import bank.domain.Account;
import bank.domain.Customer;

@Service("accountService")
@Transactional
public class AccountService implements IAccountService {
	
	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private CurrencyConverter currencyConverter;
		
	public AccountService(){
	}

	public void setAccountDAOHibernate(AccountDAO accountDAOHibernate) {
		this.accountDAO = accountDAOHibernate;
	}



	public void setCurrencyConverter(CurrencyConverter currencyConverter) {
		this.currencyConverter = currencyConverter;
	}

	
	public void createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
	}
	
	public Account getAccount(long accountNumber) {
		
		Account acc = accountDAO.loadAccount(accountNumber);
		
		return acc;
	}
	
	public Collection<Account> getAllAccounts() {
		
		Collection<Account> accountList = accountDAO.getAccounts();
	
		return accountList;
	}
	
	public void deposit(long accountNumber, double amount) {
		
		Account acc = accountDAO.loadAccount(accountNumber);
		acc.deposit(amount);
		accountDAO.updateAccount(acc);
		
		
	}
	
	public void withdraw(long accountNumber, double amount) {
		
		Account acc = accountDAO.loadAccount(accountNumber);
		acc.withdraw(amount);
		accountDAO.updateAccount(acc);
		
		
	}
	
	public void depositEuros(long accountNumber, double amount) {
		Account acc = accountDAO.loadAccount(accountNumber);
		double amt = currencyConverter.euroToDollars(amount);
		acc.deposit(amt);
		
		accountDAO.updateAccount(acc);
		
		
		
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account acc = accountDAO.loadAccount(accountNumber);
		double amt = currencyConverter.euroToDollars(amount);
		acc.withdraw(amt);
		accountDAO.updateAccount(acc);
		
	
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		
	}
	
}
