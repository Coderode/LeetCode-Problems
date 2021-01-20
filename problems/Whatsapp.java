import java.util.*;

//user
class User{
	//attributes
	private String name;
	private ArrayList<User> contacts;
	private HashMap<User,ArrayList<Chat>> chats;
	private ArrayList<Status> status;
	
	//creating a user
	public User(String name){
		this.name = name;
		this.constacts = new ArrayList<Contact>();
		this.chats = new HashMap<User,ArrayList<Chat>>();
		this.status = new ArrayList<Message>();
	}
	
	//add user to contact list
	public void addToContact(User u){
		this.contacts.add(u);
	}
	
	//get user name
	public String getName(){
		return this.name;
	}
	
	//show own status
	public void showStatus(){
		for(Status s : status){
			System.out.println("---------->"+s.getContent());
		}
	}
	
	//show chat with a particular user
	public void showChats(User withContact){
		ArrayList<Chat> messages = chats.get(withContact);
		System.out.println("with contact name : "+withContact.getName());
		for(Chat c : messages){
			c.showChat();
		}
	}
	
	//show status of list of contacts
	public void showContactsStatus(){
		int i=1;
		for(User u: contacts){
			System.out.print("\n"+i+".Username : "+u.getName());
			System.out.print("\nstatus : ");
			u.showStatus();
			i++;
		}
	}
	//add chats with a particular user
	public void addChat(User to,ArrayList<Chat> chats){
		this.chats.put(to,chats);
	}
	
	public void addStatus(Status m){
		status.add(m);
	}
	
	public void deleteContact(User contact){
		contacts.remove(contact);
		chats.remove(contact);
	}
	
	public void deleteChat(User contact){
		chats.remove(contact);
	}
	
}

//message
abstract Message{
	private int id;
	public Message(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public getMessage();
}
//message types
class Image extends Message{
	public Image(int id){
		super(id);
	}
	@Override
	public String getMessage(){
		return "Image";
	}
}
class Text extends Message{
	public Text(int id){
		super(id);
	}
	private String text;
	public Text(String text){
		this.text = text;
	}
	@Override
	public String getMessage(){
		return this.text;
	}
}

class Video extends Message {
	public Video(int id){
		super(id);
	}
	@Override
	public String getMessage(){
		return "Video";
	}
}

class Audio extends Message {
	public Audio(int id){
		super(id);
	}
	@Override
	public String getMessage(){
		return "Audio";
	}
}

//status class
abstract class Status{
	public String getContent();
}
//status class
class TextStatus extends Status{
	String st;
	public TextStatus(String msg){
		this.st = msg;
	}
	@Override
	public String getContent(){
		return this.st;
	}
}

class ImageStatus extends Status{

	@Override
	public String getContent(){
		return "Image";
	}
}

//chat box
class Chat{
	private Message receivedMessage;
	private Message sentMessage;
	
	public Chat(Message receivedMessage , Message sentMessage){
		this.receivedMessage = receivedMessage;
		this.sentMessage = sentMessage;
	}
	
	public void showChat(){
		System.out.println("Sent : "+sentMessage.getMessage());
		System.out.println("Received : "+receivedMessage.getMessage());
	}
}

//main portal
class Whatsapp{
	public static void main(String args[]){
		
	}
}