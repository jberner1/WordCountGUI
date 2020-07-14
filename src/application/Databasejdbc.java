package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
//Author: Jennifer Berner
//Date: 7/6/20
//
public class Databasejdbc{
	public static void main(String[] args) throws FileNotFoundException {
		HashMap<String, Integer> map = new HashMap<String, Integer>(); //using HashMap
		
		Scanner txtFile= new Scanner(new File("poem.txt"));  //poem is in a text file
		while (txtFile.hasNext()) {
			String word = txtFile.next().replaceAll("[^a-zA-Z0-9 ]", ""); //removing punctuations etc.  
			
			if (map.containsKey(word)) {   // counting how many times a word occurs
				int count = map.get(word) + 1; // count goes up 1 if word already exist
				map.put(word,  count);	
			}
			else{
				map.put(word, 1);         // if the word count is one
			}
		}
		txtFile.close();  // closing the text file
		Set<Map.Entry<String, Integer>> set = map.entrySet(); // grabbing the map contents
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>(set); // making the array list 
        Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>() // sorting the array list
        {
            public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) // allows for comparing 
            {
                return (b.getValue()).compareTo( a.getValue() ); // having it go in descending order 
            }
        } );

        for(Map.Entry<String, Integer> i:sortedList){  // printing the list
            System.out.println(i.getKey()+"="+i.getValue());
        }
      int input;
		do{
			Scanner in = new Scanner(System.in); //using scanner for input word that the user puts
			try
			{
				System.out.println("Enter a word to add:"); //ask user for a word to add
				String word = in.next();
				Class.forName("com.mysql.jdbc.Driver");//jdbc driver.
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/wordoccurences","root","1122334455");// Connection.
				PreparedStatement ps = con.prepareStatement("insert into word (words) values(?)");
				ps.setString(1,word);//putting the word in the query.
				ps.executeUpdate();
				System.out.println("Database with the word added");// displaying the database table after adding the word
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from word");
				while(rs.next()){
					System.out.println(rs.getString(1));
				}
				con.close();
			}
			catch(ClassNotFoundException |SQLException c){
				System.out.println(c.getMessage());
			}
			System.out.println("Do you want to enter more word? type 1 for yes");
			input = in.nextInt();
		}while(input==1);
		System.out.println("Frequency of each word present in the database is:");//getting the frequency
		//Map<String,Integer> sortedList = new LinkedHashMap<>();//map to store the word and frequency
		//Map<String,Integer> frequency = new LinkedHashMap<>();//map to store the word and frequency
		try{//getting the resultset.
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/wordoccurences","root","1122334455");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from word");
			while(rs.next()){
				String s = rs.getString(1);//Getting the word from the database.
				if(map.get(s)==null){	//Checking if the word is already there, and if not adding a frequency of 1.
					map.put(s,1);
				}
				else{// else increase the frequency by 1.
					map.put(s,map.get(s)+1);
				}
			}
			con.close();
		}
		catch(ClassNotFoundException | SQLException s){
			System.out.println(s.getMessage());
		}
		Set<String> key = map.keySet();//printing the word along with the frequency 
		for(String k:key){
			System.out.println("Word: "+k+" frequency: "+map.get(k));
		}
	}
}
