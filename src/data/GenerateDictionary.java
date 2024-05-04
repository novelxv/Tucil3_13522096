package data;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GenerateDictionary {
    public static void main(String[] args){
        GenerateDictionary generator = new GenerateDictionary();
        generator.saveWordsByLength("src/data/dictionary.txt");
    }

    /* Save words according to their length to a file */
    public void saveWordsByLength(String filename){
        Map<Integer, PrintWriter> writers = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null){
                int length = line.length();
                PrintWriter writer = writers.get(length);
                if (writer == null){
                    String outputFileName = System.getProperty("user.dir") + "/src/data/dictionary_" + length + ".txt";
                    writer = new PrintWriter(new FileWriter(outputFileName, true));
                    writers.put(length, writer);
                }
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            for (PrintWriter writer : writers.values()) {
                writer.close();
            }
        }
    }
}