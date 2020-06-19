//Author:Jennifer Berner
//Date: 4/1/2020

package application;
	

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class PoemV2 extends Application {
	//array array = new array;
	String a[] = {"Descending", "Ascending"};

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {

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
      List<Map.Entry<String, Integer>> sortedList2 = new ArrayList<Map.Entry<String, Integer>>(set); // making the array list 
      Collections.sort( sortedList, new Comparator<Map.Entry<String, Integer>>() // sorting the array list
      {
          public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) // allows for comparing 
          {

              return (b.getValue()).compareTo( a.getValue() ); // having it go in descending order 
             // return (a.getValue()).compareTo( b.getValue() ); // having it go in ascending order 
          }
      });
      Collections.sort( sortedList2, new Comparator<Map.Entry<String, Integer>>() // sorting the array list
    	      {
    	          public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) // allows for comparing 
    	          {

    	             // return (b.getValue()).compareTo( a.getValue() ); // having it go in descending order 
    	              return (a.getValue()).compareTo( b.getValue() ); // having it go in ascending order 
    	          }
    	      });

     // for(Map.Entry<String, Integer> i:sortedList){  // printing the list
     //     System.out.println(i.getKey()+"="+i.getValue());
	//}
      
		try {
		
			ObservableList<String> options = FXCollections.observableArrayList(a);
			final ComboBox comboBox = new ComboBox(options);
			//ObservableList<String> options2 = FXCollections.observableArrayList("20", "All");
			//final ComboBox comboBox2 = new ComboBox(options2);
			
			Button button = new Button("Click to generate results");
			Button exit = new Button("Exit");
			exit.setOnAction(e -> System.exit(0));

			
			button.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {

					if(comboBox.getValue() == "Descending") {
						for(Map.Entry<String, Integer> i:sortedList){  // printing the list
					          System.out.println(i.getKey()+"="+i.getValue());
						}
					}
					if(comboBox.getValue() == "Ascending") {
						for(Map.Entry<String, Integer> i:sortedList2){  // printing the list
					          System.out.println(i.getKey()+"="+i.getValue());
						}
					}
				}
			});
			
			Label label = new Label("Descending or Ascending Order: ");
			//Label label2 = new Label("How many words:  ");
			FlowPane pane = new FlowPane();
			pane.setLayoutX(4);
			pane.setLayoutY(1);
			pane.getChildren().addAll(label, comboBox);
			//pane.getChildren().addAll(label2, comboBox2);
			pane.getChildren().addAll(button, exit);
			
			
			Scene scene = new Scene(pane,240,400);

			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Poem version 2");
			primaryStage.show();
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {

      launch(args);
	}
}


