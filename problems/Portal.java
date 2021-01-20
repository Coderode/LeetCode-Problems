import java.util.*;

//User class
class User{
	private String name;
	private String role;  //role of the user e.g. admin or normal user
	private ArrayList<Brick> sent;
	private ArrayList<Brick> received;
	//constructor
	public User(String name,String role){
		this.name = name;
		this.role = role;
		sent = new ArrayList<Brick>();
		received = new ArrayList<Brick>();
	}
	
	//other members
	public String getName(){
		return this.name;
	}
	
	public String getRole(){
		return role;
	}
	
	public int getTotalAttentions(){
		return received.size();
	}
	
	public ArrayList<Brick> getSentDedications(){
		return this.sent;
	}
	
		
	public void addBrickToSend(Brick b,City c){
		sent.add(b);
		c.addBrick(b);
	}
	
	public void addBrickReceived(Brick b){
		received.add(b);
	}
	
	public void showAllSentMessages(){
		int i=1;
		for(Brick b : sent){
			System.out.println(i+"."+b.getMessage());
			i++;
		}
	}
	
	public void showAllReceivedMessages(){
		int i=1;
		for(Brick b : received){
			System.out.println(i+"."+b.getMessage());
			i++;
		}
	}
	
}
//brick class
class Brick{
	private static int noOfBricks = 0;
	private User owner;
	private User dedicatedTo;
	private int flagg=0;
	private boolean visibility;
	private String message;
	private ArrayList<String> comments;
	
	//constructor
	Brick(User owner,String message,User dedicatedTo){
		this.owner = owner;
		this.dedicatedTo = dedicatedTo;
		this.noOfBricks++;
		this.message = message;
		this.visibility = true;
		this.comments = new ArrayList<String>();
	}
	
	//other members
	public static int getTotalBricks(){
		return noOfBricks;
	}
	
	public boolean animate(){
		return true;
	}
	
	public void flagg(){
		this.flagg++;
		if(flagg > 10){
			this.visibility = false;
		}
	}
	
	public int getNoOfFlaggs(){
		return this.flagg;
	}
	
	public void addComment(String comment){
		this.comments.add(comment);
	}
	public String getMessage(){
		return this.message;
	}
	
	public void editMessage(String msg){
		this.message = msg;
	}
}


//class Wall
class Wall{
	static int noOfWalls = 0;
	ArrayList<Brick> bricks;
	int noOfBricks;
	
	//constructor
	public Wall(){
		bricks = new ArrayList<Brick>();
		noOfBricks = 0;
	}
	
	//other members
	public void addBrick(Brick b){
		this.bricks.add(b);
		noOfBricks++;
	}
	public int getNoOfBricks(){
		return noOfBricks;
	}
	
	public static int getTotalWalls(){
		return noOfWalls;
	}
	public ArrayList<Brick> getBricks(){
		return this.bricks;
	}
}

//city class
class City{
	private String name;
	private ArrayList<Wall> walls;
	
	//constructor
	public City(String name){
		this.name = name;
		walls = new ArrayList<Wall>();
	}
	
	//other members
	public int noOfWalls(){
		return walls.size();
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Wall> getWalls(){
		return walls;
	}
	
	//add brick in the city
	public void addBrick(Brick b){
		int size = this.walls.size();
		//if no wall then create one and then add the brick into it
		if(size == 0){
			Wall newWall = new Wall();
			newWall.addBrick(b);
			this.walls.add(newWall);
			return;
		}
		Wall w = walls.get(size-1);
		//if no of bricks is < 90 in last wall
		if(w.getNoOfBricks() < 90){
			w.addBrick(b);
		//> 90 in last wall then create a new wall 
		}else{
			Wall newWall = new Wall();
			newWall.addBrick(b);
			this.walls.add(newWall);
		}
		
	}
	
	public int noOfBricks(){
		int count =0;
		for(Wall w:walls){
			count += w.getNoOfBricks();
		}
		return count;
	}
	
}

//country class
class Country{
	private String name;
	private ArrayList<City> cities;
	
	//constructor
	public Country(String name){
		this.name = name;
		cities = new ArrayList<City>();
	}
	
	//other members
	public int noOfCities(){
		return cities.size();
	}
	public String getName(){
		return name;
	}
	
	public int noOfBricks(){
		int count = 0;
		for(City c : cities){
			count += c.noOfBricks();
		}
		return count;
	}
	
	public void addCity(City c){
		cities.add(c);
	}
	
	public ArrayList<City> getCities(){
		return cities;
	}
	
	public int noOfWalls(){
		int count = 0;
		for(City c : cities){
			count += c.noOfWalls();
		}
		return count;
	}
}


public class Portal{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		ArrayList<User> users = new ArrayList<User>();
		ArrayList<Country> countries = new ArrayList<Country>();
		ArrayList<City> cities = null;
		ArrayList<Brick> bricks = null;
		ArrayList<Wall> walls = null;
		
		//adding a admin for the system
		users.add(new User("master","admin"));
		
		//adding users passed as argument in users 
		for(int i=0; i<args.length; i++){
			users.add(new User(args[i],"user"));
			
		}
		
		//Just considering for one country with two cities
		Country con1 = new Country("India");
		City c1 = new City("Delhi");
		City c2 = new City("Noida");
		
		countries.add(con1);
		//adding cities to con1
		con1.addCity(c1);
		con1.addCity(c2);
		
		
		//----------------------Main :: Visible application to the user------------------
		
		while(true){
			/*--------------------------------maain menu-----------------*/
			System.out.println("--------------Welcome to the App------------------");
			System.out.println("Select : 1.Admin 2.User 3.exit");
			int opt = sc.nextInt();
			//***************************for admin**************************
			if(opt == 1){
				//checking for admin validity
				System.out.println("Enter username : ");
				sc.nextLine();
				String username = sc.nextLine();
				User user = null;
				boolean isValidUser = false;
				
				for(User p:users){
					if(p.getName().equals(username) && p.getRole().equals("admin")){
						isValidUser = true;
						user = p;
						break;
					}		
				}
				//if valid admin user
				if(isValidUser){
					boolean flag = true;
					while(flag){
						//just taking only one options for admin
						System.out.println("1.Watch out a brick");
						System.out.println("2.go back");
						System.out.println("Select option: ");
						int opt2 = sc.nextInt();
						switch(opt2){
							case 1:
								System.out.println("Select Country : ");
								for(int i=0; i<countries.size(); i++){
									System.out.println((i+1)+"."+countries.get(i).getName());
								}
								int opt3 = sc.nextInt(); //assuming selected right option
								opt3--;
								System.out.println("Select City : ");
								cities = countries.get(opt3).getCities();
								for(int i=0; i<cities.size(); i++){
									System.out.println((i+1)+"."+cities.get(i).getName());
								}
								int opt4 = sc.nextInt(); //assuming selected right option
								opt4--;
								
								//show all brick messages for in that city
								walls = cities.get(opt4).getWalls();
								int j=0;
								for(Wall w : walls){
									int s = w.getNoOfBricks();
									bricks = w.getBricks();
									for(int i=0; i<s; i++){
										j++;
										System.out.println(j+"."+bricks.get(i).getMessage());
									}
								}	
								if(j==0){
									System.out.println("No bricks are there in this city!");
								}
								break;
							case 2:
								flag = false;
								break;
							default:
								flag = false;
						}
					}
				}else{
					System.out.println("Not an admin! try again!");
				}
		//******************************for normal user*************************************
			}else if(opt == 2){
				System.out.println("Enter username : ");
				sc.nextLine();
				String username = sc.nextLine();
				User user = null;
				boolean isValidUser = false;
				for(User p:users){
					if(p.getName().equals(username) && p.getRole().equals("user")){
						isValidUser = true;
						user = p;
						break;
					}
						
				}
				//if it is valid user
				if(isValidUser){
					boolean flag = true;
					while(flag){
						System.out.println("--------------Welcome to the app-----------");
						System.out.println("1.Dedicate a brick");
						System.out.println("2.Find Hottest User");
						System.out.println("3.Find who did not get any Attention");
						System.out.println("4.Edit your bricks if exist");
						System.out.println("5.Watch out a brick");
						System.out.println("6.Go to prev manu");
						System.out.println("Select option: ");
						int opt2 = sc.nextInt();
						switch(opt2){
							case 1:
								System.out.println("Select Country : ");
								for(int i=0; i<countries.size(); i++){
									System.out.println((i+1)+"."+countries.get(i).getName());
								}
								int opt3 = sc.nextInt();
								opt3--;
								if(opt3>=0 && opt3<countries.size()){
									System.out.println("Select City : ");
									cities = countries.get(opt3).getCities();
									for(int i=0; i<cities.size(); i++){
										System.out.println((i+1)+"."+cities.get(i).getName());
									}
									int opt4 = sc.nextInt();
									opt4--;
									if(opt4>=0 && opt4<cities.size()){
										System.out.println("Enter the message to write on the brick : ");
										sc.nextLine();
										String msg = sc.nextLine();
										System.out.println("Dedicated to  : ");
										String name = sc.nextLine();  //assuming the user exist here 
										User dedicatedTo = null;
										for(User p:users){
											if(p.getName().equals(name)){
												dedicatedTo = p;
												break;
											}	
										}
										Brick b = new Brick(user,msg,dedicatedTo);
										user.addBrickToSend(b,cities.get(opt4));
										dedicatedTo.addBrickReceived(b);
										System.out.println("Brick added successfully!");
									}
								}
								break;
							case 2:
								User max = users.get(0);
								for(int i=1; i<users.size(); i++){
									if(max.getTotalAttentions() < users.get(i).getTotalAttentions()){
										max = users.get(i);
									}
									
								}
								System.out.println(max.getName());
								break;
							case 3:
								for(int i=0; i<users.size(); i++){
									if(users.get(i).getTotalAttentions() == 0){
										System.out.println(users.get(i).getName());
									}
								}
								break;
							case 4:
								user.showAllSentMessages();
								System.out.println("Select any one to edit : ");
								int opt5 = sc.nextInt();  //assuming selection is right
								bricks = user.getSentDedications();
								if(bricks.size()!=0) {
									System.out.println("Enter the new message : ");
									sc.nextLine();
									String newmsg = sc.nextLine();
									opt5--;
									bricks.get(opt5).editMessage(newmsg);
									System.out.println("Edited successfully!");
								}else{
									System.out.println("No bricks dedicted to any body yet!");
								}
								
								break;
							case 5:
								System.out.println("Select Country : ");
								for(int i=0; i<countries.size(); i++){
									System.out.println((i+1)+"."+countries.get(i).getName());
								}
								int opt6 = sc.nextInt(); //assuming selected right option
								opt6--;
								System.out.println("Select City : ");
								cities = countries.get(opt6).getCities();
								for(int i=0; i<cities.size(); i++){
									System.out.println((i+1)+"."+cities.get(i).getName());
								}
								int opt7 = sc.nextInt(); //assuming selected right option
								opt7--;
								
								//show all brick messages for in that city
								walls = cities.get(opt7).getWalls();
								int j=0;
								for(Wall w : walls){
									int s = w.getNoOfBricks();
									bricks = w.getBricks();
									for(int i=0; i<s; i++){
										j++;
										System.out.println(j+"."+bricks.get(i).getMessage());
									}
								}
								if(j==0){
									System.out.println("No bricks are there in this city!");
								}
								break;
							case 6:
								flag = false;
								break;
							default :
								flag = false;
						}
	
					}
				}else{
					System.out.println("Invalid User!");
				}
			}else{
				break;
			}
		}
	}
}