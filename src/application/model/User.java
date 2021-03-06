package application.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;



/** 
* This class represensts User name inputs 
*
* @author Brynna watring, Christina Zhang, Zain Momin, Steven Luong, Chris Rademacher 
* UUTSA CS3443-001
* Fall 2020
*/

public class User {
	
	private String username;
	private String password;
	private String name;
	private String email;
	
	/**
	* Sets the username password name and email to the user input 
	* @param username password name and email (String) 
	*
	*/
	public User (final String username, final String password, final String name, final String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	/** 
	* Returns username inputted by user 
	* @return String username
	*/
	public String getUsername() {
		return username;
	}
	/**
	* Sets the name of username 
	*@param one parameter username to set on this account (String) 
	*
	*/
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	*
	* Returns the password entered by the user 
	*@return password that is a string that is read in from user (String) 
	*
	*/
	public String getPassword() {
		return password;
	}
	/**
	* Sets the name of the password entered by the user
	*
	*@param string password sets userinputs password to password (String)
	*
	*/
	
	
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	* Returns the name entered by the user
	* @return String of the name of the account entered (String) 
	*
	*/
	public String getName() {
		return name;
	}
	/**
	*
	* Sets the name of the user entered by the user 
	* @param name to se the name enterd by the user(String) 
	*/
	
	public void setName(String name) {
		this.name = name;
	}
	/*
	*
	* Returns the email entered by the user 
	* @return string email read in from user input (String) 
	*/
	
	public String getEmail() {
		return email;
	}
	
	/*
	* Sets the name of the email of the account entered by the email 
	* @param string email sets email to the input email from user input (String) 
	*
	*/
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method reads the file of users and validates the entered credentials.
	 * @author Brynna watring, Christina Zhang, Zain Momin, Steven Luong, Chris Rademacher 
	 * @param inpUsername	String of inputted username. 
	 * @param inpPassword	String of inputted password.
	 * @param user			User object to be updated (name/email) if validated - needed for profile view.
	 * @return foundUser	boolean value.
	 * @throws IOException
	 */
	public boolean validate(String inpUsername, String inpPassword, User user)throws IOException {
		boolean foundUser = false;	//boolean to check for correct/ incorrect password and username 	
		try {
	    	Scanner scan = new Scanner(new File("users.csv") ); //creates new  csv file for user 

	    	while( scan.hasNextLine() ) {
	    		String line = scan.nextLine();
	    		String[] data = line.split(",");
	            if(data[0].equals(inpUsername))
	            {
	            	//if the password is correct
	            	if(data[1].equals(inpPassword))
	            	{
	            		//Permission to move to next view
	            		foundUser = true;
	            		user.setName(data[2]);
	            		user.setEmail(data[3]);
	            	}
	            	//if the password is not correct
	            	else
	            	{
	            		//Error message for incorrect password
	            	}
	            }
	        }
	        //if the username is not correct
	        if(!foundUser)
	        {
	        	//Error message for incorrect username
	        }
	    	scan.close();
	    } catch(IOException e) {
	    e.printStackTrace();
	    }
		return foundUser;
	}
	
	/*
	 * This method reads the file of users and checks if the username of the user attempting to signup already exists.
	 * @author Brynna watring, Christina Zhang, Zain Momin, Steven Luong, Chris Rademacher 
	 * @param inpUsername	String of inputted username. 
	 * @return validUsername	boolean value.
	 * @throws IOException
	 */
	public boolean checkForExistingUser(String inpUsername)throws IOException {
		boolean validUsername = true;		//boolean checking if username exists 
		try {
	    	Scanner scan = new Scanner(new File("users.csv") ); //new csv file 

	    	while( scan.hasNextLine() ) {
	    		String line = scan.nextLine();
	    		String[] data = line.split(",");
	            if(data[0].equals(inpUsername))
	            {
	            	validUsername = false; //if username is invalid or non-existent 
	            }
	        }
	    	scan.close();
	    } catch(IOException e) {
	    e.printStackTrace();
	    }	
		return validUsername; //returns username back to user 
	}
	
	/**
	 * This method adds a new user to the user database.
	 * @author Brynna watring, Christina Zhang, Zain Momin, Steven Luong, Chris Rademacher 
	 * @param newUser	User object to be added.
	 * @throws IOException
	 */
	public void addUserToCSV(User newUser) throws IOException{
		java.io.File file = new java.io.File("users.csv");
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);		
		
	    try {
	    	bw.write(newUser.getUsername());
			bw.write(",");
			bw.write(newUser.getPassword());
			bw.write(",");
			bw.write(newUser.getName());
			bw.write(",");
			bw.write(newUser.getEmail());
			bw.newLine();
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    System.out.println("User CSV has been added to!");	// success message to let user know CSV file has been added to 
		bw.close();
		fw.close();
	}
	
	/**
	 * This method edits the profile of the user, updated the CSV database.
	 * @author Brynna watring, Christina Zhang, Zain Momin, Steven Luong, Chris Rademacher 
	 * @param username	String of username to find and match in database to locate correct user.
	 * @param newName	String of new name for user.
	 * @param newEmail	String of new email for user.
	 * @throws IOException
	 */
	public void editProfile(String username, String newName, String newEmail) throws IOException{
		java.io.File file = new java.io.File("../usersTemp.csv");
		file.createNewFile();
		FileWriter fw = new FileWriter(file, false);
		BufferedWriter bw = new BufferedWriter(fw);		
		
		try {
	    	Scanner scan = new Scanner(new File("users.csv") ); //create new csv file 

	    	while( scan.hasNextLine()) {
	    		String line = scan.nextLine();
	    		String[] data = line.split(",");
	    		if(data.length == 4){
	            if(!data[0].equals(username))
	            {
	            	bw.write(data[0]);
	            	System.out.println(data[1]);
	            	System.out.println(data[2]);
	    			bw.write(",");
	    			bw.write(data[1]);
	    			bw.write(",");
	    			bw.write(data[2]);
	    			bw.write(",");
	    			bw.write(data[3]);
	    			bw.newLine();
	            }
	            else
	            {
	            	bw.write(data[0]);
	    			bw.write(",");
	    			bw.write(data[1]);
	    			bw.write(",");
	    			bw.write(newName);
	    			bw.write(",");
	    			bw.write(newEmail);
	    			bw.newLine();
	            }
	        }
	    	}
			//makes new csv file 
	    	scan.close();
	    	File f = new File("users.csv"); 
	    	f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			} 
	    System.out.println("User CSV has been updated!");	//lets user know if CSV file was successfully updated or not 
		bw.close();
		fw.close();
		file.renameTo(new File("users.csv")); //renames csv file and closes bw and fw  
	}
}
	
