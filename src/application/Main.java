package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.Map.*;
import java.util.*;
public class Main extends Application implements EventHandler<ActionEvent> {
Button button;
public static void main(String[] args) {
launch(args);
}
@Override
public void start(Stage primaryStage) {
primaryStage.setTitle("Word Occurrences");
button = new Button();
button.setText("Output Words");
//This class will handle the button events
button.setOnAction(this);
StackPane layout = new StackPane();
layout.getChildren().add(button);
Scene scene = new Scene(layout, 300, 250);
primaryStage.setScene(scene);
primaryStage.show();
}
@Override
public void handle(ActionEvent event) {
if (event.getSource() == button) {
//Map<String, Integer> wordMap = buildWordMap("C:/users/David/documents/MacBeth.txt");
Map<String, Integer> wordMap = buildWordMap("poemTEST.txt");
List<Map.Entry<String, Integer>> list = sortByValueInDecreasingOrder(wordMap);
System.out.println("List of repeated words");
for (Map.Entry<String, Integer> entry : list) {
if (entry.getValue() > 100) {
System.out.println(entry.getKey() + " => " + entry.getValue());
}
}
}
}
public static Map<String, Integer> buildWordMap(String fileName) {
Map<String, Integer> wordMap = new HashMap<>();
try (FileInputStream fis = new FileInputStream(fileName);
DataInputStream dis = new DataInputStream(fis);
BufferedReader br = new BufferedReader(new InputStreamReader(dis))) {
Pattern pattern = Pattern.compile("\\s+");
String line = null;
while ((line = br.readLine()) != null) {
line = line.toLowerCase();
String[] words = pattern.split(line);
for (String word : words) {
if (wordMap.containsKey(word)) {
wordMap.put(word, (wordMap.get(word) + 1));
} else {
wordMap.put(word, 1);
}
}
}
} catch (IOException ioex) {
ioex.printStackTrace();
}
return wordMap;
}
public static List<Map.Entry<String, Integer>> sortByValueInDecreasingOrder(Map<String, Integer> wordMap) {
Set<Map.Entry<String, Integer>> entries = wordMap.entrySet();
List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
@Override
public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
return (o2.getValue()).compareTo(o1.getValue());
}
});
return list;
}
}
