package objectplus;
import java.util.Scanner;
public class ObjectplusA03Loan {
	public static void main(String[]args){
		loan a=new loan(25,10,100000);
		System.out.println(a.getmpayment());
	}
}
class loan{
	double annInt;
	int nofy;
	double amount;
	java.util.Date loandate;
	
	public java.util.Date getdat(){
		return this.loandate;
	}
	public loan(){
		this.nofy=1;
		this.amount=10000;
		this.annInt=2.5;
	}
	public loan(double interestoftheloan,int numberofyears,double totalofloan){
		this.amount=totalofloan;
		this.nofy=numberofyears;
		this.annInt=interestoftheloan;
		this.loandate=new java.util.Date();
	}
	public double getannint(){
		return this.annInt;
	}
	public int getyears(){
		return this.nofy;
	}
	public java.util.Date getdate(){
		return this.loandate;
	}
	public void setInt(double interest){
		this.annInt=interest;
	}
	public void setamount(double amountoftheloan){
		this.amount=amountoftheloan;
	}
	public double getmpayment(){
		return this.amount*Math.pow((1+(this.annInt/100)),this.nofy);
	}
}