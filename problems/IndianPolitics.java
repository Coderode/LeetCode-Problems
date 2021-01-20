import java.util.*;
// person class 
class Person{
	protected String name;
	
	public Person(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}

//MP is a Person of a constituency
class MP extends Person{
	protected Constituency cons;
	protected int spendingLimit;
	public MP(String name,Constituency cons){
		super(name);
		this.cons = cons;
		this.spendingLimit = 50000;
	}
	//getters
	public Constituency getConstituency(){
		return this.cons;
	}
	
	public int getSpendingLimit(){
		return this.spendingLimit;
	}
	
	//to display the person object
	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append("MP [Name:"+super.name+",Spending Limit : "+this.spendingLimit+",Constituency : "+this.cons+"]");
		return sb.toString();
	}
}

//Minister is MP with a car and a driver
class Minister extends MP{
	private Car car;
	protected Driver driver;
	public Minister(String name, Constituency cons,Car car,Driver driver){
		super(name,cons);
		super.spendingLimit=70000;
		this.car = car;
		this.driver = driver;
	}
	
	public Car getCar(){
		return car;
	}
	public Driver getDriver(){
		return driver;
	}
	
	//to display the minster object
	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append("Minister [Name:"+super.name+",Spending Limit : "+super.spendingLimit+",Car :");
		sb.append(this.car+", ");
		sb.append(",Driver :"+this.driver+",Constituency : "+super.cons+"]");
		
		return sb.toString();
	}
	
}

//PM is a minister with atmost 5 cars
class PM extends Minister{
	
	private int noOfCars;
	private Car cars[];
	
	public PM(String name,Constituency cons,int n,Car []cars,Driver driver) throws Exception{
		super(name,cons,cars[0],driver);
		if(n<1 || n>5){
			throw new Exception("Invalid no of cars provided to PM");
		}
		
		this.noOfCars = n;
		this.cars= new Car[n];
		for(int i=0; i<n; i++){
			this.cars[i]=cars[i];
		}
		super.spendingLimit= 170000;
	}
	
	//to display the PM details
	@Override
	public String toString(){
		StringBuilder sb= new StringBuilder();
		sb.append("PM [Name:"+super.name+",Spending Limit : "+super.spendingLimit+",Cars :[");
		for(int i=0; i<noOfCars; i++){
			sb.append(this.cars[i]+",");
		}
		sb.append("],Constituency : "+super.cons+"]");
		
		return sb.toString();
	}
}

//Car with name
class Car{
	private String name;
	public Car(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	//to display the car detail
	@Override
	public String toString(){
		return "Car [Name:"+this.name+"]";
	}
}

//Driver is person
class Driver extends Person{
	public Driver(String name){
		super(name);
	}
	
	//to display the driver detail
	@Override
	public String toString(){
		return "Driver [Name:"+this.name+"]";
	}
}

//constituency with name and area
class Constituency{
	private String name;
	private double area;
	public Constituency(String name,double area){
		this.name = name;
		this.area = area;
	}
	//to display the constituency detail
	@Override
	public String toString(){
		return "Constituency [ Name:"+this.name+",Aera:"+this.area+"]";
	}
	
}

class IndianPolitics{
	public static void main(String args[]) throws Exception{
		//creating objects
		Constituency cons1 = new Constituency("Banaras",2000);
		Constituency cons2 = new Constituency("Mathura",3000);
		Driver driver1 = new Driver("Dhanraj");
		Driver driver2 = new Driver("Panday");
		Car car1 = new Car("Suzuki");
		Car car2 = new Car("Farari");
		Car car3 = new Car("Wagnar");
		
		//mp
		Minister xyz = new Minister("PS Singh",cons2,car3,driver2);
		System.out.println(xyz);
		
		//PM
		PM modi = new PM("N modi",cons1,2,new Car[]{car1,car2},driver1);
		System.out.println(modi);
		
	}
}