import java.util.*;
//Amount class 
class Amount{
	private int amount;
	public Amount(int amount){
		this.amount = amount;
	}
	public int getAmount(){
		return this.amount;
	}
}

//Chain interface
interface WithdrawChain{
	//for setting the next of chain
	void setNextChain(WithdrawChain nextChain);
	//method for processing of withdrawal amount 
	void withdraw(Amount amt,HashMap<Integer,Integer> notes,HashMap<Integer,Integer> counter);
}

//class for withdrawal of Rs.2000 notes
class Withdraw2000 implements WithdrawChain{
	private WithdrawChain chain;
	
	@Override 
	public void setNextChain(WithdrawChain nextChain){
		this.chain=nextChain;
	}
	//processing for 2000 
	@Override
	public void withdraw(Amount amt,HashMap<Integer,Integer> notes,HashMap<Integer,Integer> counter){
		if(amt.getAmount() >= 2000){
			
			int n = amt.getAmount()/2000;
			int remainder =0;
			
			if(n <= notes.get(2000)){
				remainder = amt.getAmount() - n*2000;
				counter.put(2000,n);
				
				
			}else{
				counter.put(notes.get(2000),notes.get(2000));
				remainder = amt.getAmount() - notes.get(2000)*2000;
			}
		
			//pass remainig amount to next note
			if(remainder != 0){
				this.chain.withdraw(new Amount(remainder),notes,counter);
			}
			
		}else{
			this.chain.withdraw(amt,notes,counter);
		}
	}
}
//class for withdrawal of Rs.500 notes
class Withdraw500 implements WithdrawChain{
	private WithdrawChain chain;
	
	@Override 
	public void setNextChain(WithdrawChain nextChain){
		this.chain=nextChain;
	}
	
	@Override
	public void withdraw(Amount amt,HashMap<Integer,Integer> notes,HashMap<Integer,Integer> counter){
		if(amt.getAmount() >= 500){
			
			int n = amt.getAmount()/500;
			int remainder =0;
			
			if(n <= notes.get(500)){
				remainder = amt.getAmount() - n*500;
				counter.put(500,n);
				
				
			}else{
				remainder = amt.getAmount() - notes.get(500)*500;
				counter.put(500,notes.get(500));
			}
		
			//pass remainig amount to next note
			if(remainder != 0){
				this.chain.withdraw(new Amount(remainder),notes,counter);
			}
			
		}else{
			this.chain.withdraw(amt,notes,counter);
		}
	}
}
//class for withdrawal of Rs.200 notes
class Withdraw200 implements WithdrawChain{
	private WithdrawChain chain;
	
	@Override 
	public void setNextChain(WithdrawChain nextChain){
		this.chain=nextChain;
	}
	
	@Override
	public void withdraw(Amount amt,HashMap<Integer,Integer> notes,HashMap<Integer,Integer> counter){
		if(amt.getAmount() >= 200){
			
			int n = amt.getAmount()/200;
			int remainder =0;
			
			if(n <= notes.get(200)){
				remainder = amt.getAmount() - n*200;
				counter.put(200,n);
				
			}else{
				counter.put(200,notes.get(200));
				remainder = amt.getAmount() - notes.get(200)*200;
			}
		
			//pass remainig amount to next note
			if(remainder != 0){
				this.chain.withdraw(new Amount(remainder),notes,counter);
			}
			
		}else{
			this.chain.withdraw(amt,notes,counter);
		}
	}
}

//class for withdrawal of Rs.100 notes
class Withdraw100 implements WithdrawChain{
	private WithdrawChain chain;
	
	@Override 
	public void setNextChain(WithdrawChain nextChain){
		this.chain=nextChain;
	}
	
	@Override
	public void withdraw(Amount amt,HashMap<Integer,Integer> notes,HashMap<Integer,Integer> counter){
		
			
		int n = amt.getAmount()/100;
		int remainder =0;
		
		if(n <= notes.get(100)){
			remainder = amt.getAmount() - n*100;
			counter.put(100,n);				
		}else{
			remainder = amt.getAmount() - notes.get(100)*100;
			counter.put(100,notes.get(100));
		}
		
	
		//pass remainig amount to next note
		if(remainder != 0){
			this.chain.withdraw(new Amount(remainder),notes,counter);
		}else{
			//withdrawing
			if(counter.get(2000)>0)
				System.out.println("Dispensing "+notes.get(2000)+" notes of 2000");
			if(counter.get(500)>0)
				System.out.println("Dispensing "+notes.get(500)+" notes of 500");
			if(counter.get(200)>0)
				System.out.println("Dispensing "+notes.get(200)+" notes of 200");
			if(counter.get(100)>0)
				System.out.println("Dispensing "+notes.get(100)+" notes of 100");
			
			notes.put(2000,notes.get(2000)-counter.get(2000));
			notes.put(500,notes.get(500)-counter.get(500));
			notes.put(200,notes.get(200)-counter.get(200));
			notes.put(100,notes.get(100)-counter.get(100));
			
		}
			
	
	}
}


//Main ATM class with functionalities

class ATM{
	//Head of chain 
	private WithdrawChain chain1;
	
	//available notes in ATM
	private HashMap<Integer,Integer> notes;
	final private int pin = 1234;
	
	//constructor
	public ATM(){
		
		//creating chain to check for 2000->500->200->100
		this.chain1 = new Withdraw2000();
		WithdrawChain chain2 = new Withdraw500();
		WithdrawChain chain3 = new Withdraw200();
		WithdrawChain chain4 = new Withdraw100();
		
		chain1.setNextChain(chain2);
		chain2.setNextChain(chain3);
		chain3.setNextChain(chain4);
		notes = new HashMap<>();
		notes.put(2000,0);
		notes.put(500,0);
		notes.put(200,0);
		notes.put(100,0);
		
	}
	
	public int getPin(){
		return this.pin;
	}
	
	//top up atm with the notes
	public void topUpAtm(int notes2000,int notes500, int notes200,int notes100){
		notes.put(2000,notes.get(2000)+notes2000);
		notes.put(500,notes.get(500)+notes500);
		notes.put(200,notes.get(200)+notes200);
		notes.put(100,notes.get(100)+notes100);
	}
	
	public int getTotalAmount(){
		return (2000*notes.get(2000)+500*notes.get(500)+200*notes.get(200)+100*notes.get(100));
	}
	public int getCount2000(){
		return this.notes.get(2000);
	}
	
	public int getCount500(){
		return this.notes.get(500);
	}
	
	public int getCount200(){
		return this.notes.get(200);
	}
	public int getCount100(){
		return this.notes.get(100);
	}
	
	public HashMap<Integer,Integer> getNotes(){
		return this.notes;
	}
	
	
	//main method for processing of the atm functionalities
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		HashMap<Integer,Integer> counter = new HashMap<>();
		counter.put(2000,0);
		counter.put(500,0);
		counter.put(200,0);
		counter.put(100,0);
		ATM atm = new ATM();
		//window
		while(true){
			System.out.println("****Welcome to ATM****");
			System.out.println("---->1.Withdraw Amount");
			System.out.println("---->2.TopUp (System Use)");
			System.out.println("---->3.exit");
			System.out.println("Select option : ");
			int opt = sc.nextInt();
			
			switch(opt){
				case 1:
					System.out.println("Enter Amount to withdraw  :");
					int withAmount = sc.nextInt();
					if(withAmount > atm.getTotalAmount()){
						System.out.println("Insufficient Balance! :-(");
							
					}else{
						if(withAmount%100 != 0){
							System.out.println("Amount should be in multiple of 100");
							
						}else{
							atm.chain1.withdraw(new Amount(withAmount),atm.getNotes(),counter);
							System.out.println("Total amount = "+atm.getTotalAmount());
						}
					}
					
					break;
				case 2:
					System.out.println("Provide the System pin to topup ATM :");
					int pin = sc.nextInt();
					if(pin == atm.getPin()){
						System.out.println("Enter number of notes of Rs.2000 : ");
						int n2000 = sc.nextInt();
						System.out.println("Enter number of notes of Rs.500 : ");
						int n500 = sc.nextInt();
						System.out.println("Enter number of notes of Rs.200 : ");
						int n200 = sc.nextInt();
						System.out.println("Enter number of notes of Rs.100 : ");
						int n100 = sc.nextInt();
						
						System.out.println("Total Amount to top up : Rs."+(2000*n2000+500*n500+200*n200+100*n100));
						System.out.print("\nEnter 1 to confirm : ");
						char c = sc.next().charAt(0);
						if(c=='1'){
							atm.topUpAtm(n2000,n500,n200,n100);
							System.out.println("Amount added successfully!");
						}else{
							System.out.println("Error Ocuured! Amount not added to ATM");
						}
						
					}else{
						System.out.println("Wrong pin! Alert!");
					}
					break;
				case 3:
					return;
				default :
					System.out.println("try again please!");
					
			}
			
			
			
		}
	}
}


