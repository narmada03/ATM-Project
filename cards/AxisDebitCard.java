package com.codegnan.cards;
 
import java.util.ArrayList;
import java.util.Collections;
 
import com.codegnan.customExceptions.InsufficientBalanceException;
import com.codegnan.customExceptions.InsufficientMachineBalanceException;
import com.codegnan.customExceptions.InvalidAmountException;
import com.codegnan.customExceptions.NotAOperatorException;
import interfaces.IATMService;
 
public class AxisDebitCard implements IATMService {
 	String name;
 	long debitCardNumber;
 	double accountBalance;
 	int pinNumber;
 	ArrayList<String> statement;
 	final String type = "user";
 	int chances;
 
 	public AxisDebitCard(long debitCardNumber, String name, double accountBalance, int pinNumber) {
      	chances = 3;
      	this.name = name;
      	this.accountBalance = accountBalance;
      	this.pinNumber = pinNumber;
      	statement = new ArrayList<>();
 	}
 
 	@Override
 	public String getUserType() throws NotAOperatorException {
      	// TODO Auto-generated method stub
      	return type;
 	}
 
 	@Override
 	public double withdrawAmount(double wthAmount)
          	throws InvalidAmountException, InsufficientBalanceException, InsufficientMachineBalanceException {
      	if (wthAmount <= 0) {
          	throw new InvalidAmountException("You can Enter Zero(0) amount to withdraw. please enter valid amount");
      	} else if (wthAmount % 10 != 0) {
          	throw new InsufficientBalanceException("Please Withdraw Multiples of 100");
      	} else if (wthAmount < 500) {
          	throw new InsufficientBalanceException("Please Withdraw More Than 500");
 
      	} else if (wthAmount > accountBalance) {
          	throw new InsufficientBalanceException(
                    	"you don:t have sufficient balance to withdraw.. Please check your balanace");
 
      	} else {
          	accountBalance = accountBalance - wthAmount;
          	statement.add("Debited : " + wthAmount);
          	return wthAmount;
      	}
 	}
 
 	@Override
 	public void depositAmount(double dptAmount) throws InvalidAmountException {
      	if (dptAmount <= 0 || dptAmount % 10 != 0) {
          	throw new InvalidAmountException("Please Deposit Multiples of 10");
      	} else {
          	accountBalance = accountBalance + dptAmount;
          	statement.add("Creadited : " + dptAmount);
      	}
 
 	}
 
 	@Override
 	public double checkAccountBalance() {
      	// TODO Auto-generated method stub
      	return accountBalance;
 	}
 
 	@Override
 	public void changePinNumber(int pinNumber) {
      	this.pinNumber = pinNumber;
 
 	}
 
 	@Override
 	public int getPinNumber() {
 
      	return pinNumber;
 	}
 
 	@Override
 	public String getUserName() {
 
      	return name;
 	}
 
 	@Override
 	public void decreaseChances() {
      	--chances;
 	}
 
 	@Override
 	public int getChances() {
 
      	return chances;
 	}
 
 	@Override
 	public void resetPinChances() {
      	chances = 3;
 
 	}
 
 	@Override
 	public void generateMiniStatement() {
      	int count = 5;
      	if (statement.size() == 0) {
          	System.out.println("There are no Transactions Happend");
          	return;
      	}
 	 	System.out.println("================List 5 Transactions ========================");
      	Collections.reverse(statement);
      	for (String trans : statement) {
          	if (count == 0) {
               	break;
          	}
          	System.out.println(trans);
          	count--;
      	}
      	Collections.reverse(statement);
 	}
 
}
