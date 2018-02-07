package bank.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bank.domain.Account;
import bank.service.AccountService;

@Controller
public class BankController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getAllAccountInfo(Model model){
		Collection<Account> accountList = accountService.getAllAccounts();
		model.addAttribute("accountList", accountList);
		return "accounts";
	}
	
	@RequestMapping(value="/createAccount", method=RequestMethod.GET)
	public String createAccount(){
		return "createAccount";
	}
	@RequestMapping(value="/postAccountInfo", method=RequestMethod.POST)
	public String postAccountInfo(String name){
		
		ArrayList<Account> accList = (ArrayList<Account>) accountService.getAllAccounts();
		long accountNumber= (long)accList.get(0).getAccountNumber()+(long)(100*Math.random());
		accountService.createAccount(accountNumber, name);
		
		return "redirect:/";
	}
	@RequestMapping(value="/deposit/{accountNumber}", method=RequestMethod.GET)
	public String deposit(Model model, @PathVariable long accountNumber){
		model.addAttribute("accountNumber", accountNumber);
		return "deposit";
	}
	@RequestMapping(value="/depositPost", method=RequestMethod.POST)
	public String depositPost(String accountNumber, String depositAmount){
		accountService.deposit((long)Integer.parseInt(accountNumber), Double.parseDouble(depositAmount));
		return "redirect:/";
	}
	@RequestMapping(value="/depositEuros/{accountNumber}", method=RequestMethod.GET)
	public String depositEuros(Model model, @PathVariable long accountNumber){
		model.addAttribute("accountNumber", accountNumber);
		return "depositEuros";
	}
	@RequestMapping(value="/depositEurosPost", method=RequestMethod.POST)
	public String depositEurosPost(String accountNumber, String depositAmount){
		accountService.depositEuros((long)Integer.parseInt(accountNumber), Double.parseDouble(depositAmount));
		return "redirect:/";
	}
	@RequestMapping(value="/withdraw/{accountNumber}", method=RequestMethod.GET)
	public String withdraw(Model model, @PathVariable int accountNumber){
		model.addAttribute("accountNumber", accountNumber);
		return "withdraw";
	}
	
	@RequestMapping(value="/withdrawPost", method=RequestMethod.POST)
	public String withdrawPost(String accountNumber, String withdrawAmount){
		accountService.withdraw((long)Integer.parseInt(accountNumber), Double.parseDouble(withdrawAmount));
		return "redirect:/";
	}
	@RequestMapping(value="/withdrawEuros/{accountNumber}", method=RequestMethod.GET)
	public String withdrawEuros(Model model, @PathVariable int accountNumber){
		model.addAttribute("accountNumber", accountNumber);
		return "withdrawEuros";
	}
	
	@RequestMapping(value="/withdrawEurosPost", method=RequestMethod.POST)
	public String withdrawEurosPost(String accountNumber, String withdrawAmount){
		accountService.withdrawEuros((long)Integer.parseInt(accountNumber), Double.parseDouble(withdrawAmount));
		return "redirect:/";
	}
	@RequestMapping(value="/transferFund/fromAccountNumber/{fromAccNo}", method=RequestMethod.GET)
	public String transferFunds(Model model, @PathVariable String fromAccNo){
		String fromAccountNumber = "fromAccountNumber";
		Map<String, String> transferInfo = new HashMap();
		transferInfo.put(fromAccountNumber, fromAccNo);
		model.addAllAttributes(transferInfo);
		return "transferFund";
	}
	@RequestMapping(value="/transferFundPost", method=RequestMethod.POST)
	public String transferFundsPost(String fromAccountNumber, String toAccountNumber, String transferAmount, String description){
		accountService.transferFunds((long)Integer.parseInt(fromAccountNumber), (long)Integer.parseInt(toAccountNumber), Double.parseDouble(transferAmount), description);
		return "redirect:/";
	}
}
