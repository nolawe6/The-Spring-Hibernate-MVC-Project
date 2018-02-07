package bank.DAO;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bank.domain.Account;

@Repository("accountDAO")
@Transactional(propagation=Propagation.MANDATORY)
public class AccountDAO implements IAccountDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void saveAccount(Account account) {
		sessionFactory.getCurrentSession().save(account);
	}

	public void updateAccount(Account account) {
		sessionFactory.getCurrentSession().merge(account);
		
	}

	public Account loadAccount(long accountnumber) {
		Collection<Account> accountList = new ArrayList();

		accountList = sessionFactory.getCurrentSession().createQuery("from Account").list();
		for(Account acc: accountList){
			if(acc.getAccountNumber()==accountnumber){
				return acc;
			}
		}
		return null;
	}

	public Collection<Account> getAccounts() {
		Collection<Account> acc = sessionFactory.getCurrentSession().createQuery("from Account").list();
		return acc;
	}

}
