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

/**
 * This class functions as a text analyzer that reads a file and outputs all words in the file, 
 * sorted by the most frequently used word. The output will show the word and how many times it occurred in the file. 
 * The user will be asked if they want the word displayed in ascending or descending order. 
 * @author Jennifer Berner
 *
 */

public class PoemV2 extends Application {


	String a[] = {"Descending", "Ascending"}; 
	/**
	 * The JavaFX Stage class is the top level JavaFX container. This is where where the user interface will ask the user which order they would like their results to show as. 
	 * @param primaryStage The primary Stage is constructed by the platform.
	 * @throws FileNotFoundException This exception will be thrown when a file (poem.txt)  with the specified pathname does not exit. 
	 * 
	 */
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException { 
	
		HashMap<String, Integer> map = new HashMap<String, Integer>(); //using HashMap

		Scanner txtFile= new Scanner(new File("poem.txt"));  //poem is in a text file

		while (txtFile.hasNext()) {
			String word = txtFile.next().replaceAll("[^a-zA-Z0-9 ]", ""); //removing punctuation etc.
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
    	  /**
    	   * Parameters a and are used for comparing, so it can return the the values in descending order
    	   * @param a parameter a
    	   * @param b parameter b
    	   * @return returns the values in descending order
    	   */
          public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) // allows for comparing 
          {

              return (b.getValue()).compareTo( a.getValue() ); // having it go in descending order 
             // return (a.getValue()).compareTo( b.getValue() ); // having it go in ascending order 
          }
      });

      Collections.sort( sortedList2, new Comparator<Map.Entry<String, Integer>>() // sorting the array list
    	      {
		  /**
    	   * Parameters a and are used for comparing, so it can return the the values in ascending order
    	   * @param a parameter a
    	   * @param b parameter b
    	   * @return returns the values in ascending order
    	   */
    	          public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) // allows for comparing 

    	          {

    	             // return (b.getValue()).compareTo( a.getValue() ); // having it go in descending order 
    	              return (a.getValue()).compareTo( b.getValue() ); // having it go in ascending order 

    	          }
    	      });
		try {

			ObservableList<String> options = FXCollections.observableArrayList(a);
			final ComboBox comboBox = new ComboBox(options);

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
			FlowPane pane = new FlowPane();
			pane.setLayoutX(4);
			pane.setLayoutY(1);
			pane.getChildren().addAll(label, comboBox);
			pane.getChildren().addAll(button, exit);
			
			
			Scene scene = new Scene(pane,240,400);

			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Poem version 2");
			primaryStage.show();
			
		} catch(Exception e) {

			e.printStackTrace();
		}
	}
	/**
	 * The main() method is ignored in correctly deployed JavaFX application. main() serves only as fallback in case the application can not be 
	 * launched through artifacts, in IDEs with limited FX support. 
	 * @param args The arguments that the array of strings store and pass by command line while starting program.
	 * @throws FileNotFoundException This exception will be thrown when a file (The poem)  with the specified pathname does not exit. 
	 */
	public static void main(String[] args) throws FileNotFoundException {

      launch(args);
	}
	
}


