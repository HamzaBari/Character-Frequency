
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class ApplicationRunner {

    static Scanner inputTotalLetters;
    static Scanner inputFrequency;
    static PrintWriter output;
    static HashMap<String, Integer> letterFreq = new HashMap<>();
    static String letterKey;
    static int freqVal;

    public static void main(String[] args) {

        String fromPath = System.getProperty("user.dir") + File.separator + "play.txt";
        String toPath = System.getProperty("user.dir") + File.separator + "results.txt";

        File myInputFile = new File(fromPath);
        File myOutputFile = new File(toPath);

        try {

            inputTotalLetters = new Scanner(myInputFile);
            inputFrequency = new Scanner(myInputFile);
            output = new PrintWriter(myOutputFile);

        } catch (FileNotFoundException ex) {

            System.out.println(ex.getMessage() + " File is not found, please try again.");
            System.exit(0);

        }

        int numberOfLetters = 0;
        int letters = 0;

        while (inputTotalLetters.hasNextLine()) {
            String word = inputTotalLetters.next();
            word = word.replaceAll("[^A-Za-z]+", "");
            letters = word.length();
            numberOfLetters = numberOfLetters + letters;
        }
        System.out.println("Total number of letters = " + numberOfLetters);
        output.println("Total number of letters = " + numberOfLetters);
        output.flush();
        inputTotalLetters.close();

        String fileContent = "";
        while (inputFrequency.hasNextLine()) {
            fileContent = fileContent.concat(inputFrequency.nextLine());
            fileContent = fileContent.replaceAll("[^A-Za-z]+", "").toLowerCase();
        }

        char characters;
        int totalLettersInFile = fileContent.length();

        for (char character = 'A'; character <= 'z'; character++) {
            freqVal = 0;
            for (int j = 0; j < totalLettersInFile; j++) {
                characters = fileContent.charAt(j);
                if (character == characters) {
                    freqVal++;
                }
            }
            if (freqVal > 0) {
                letterKey = Character.toString(character);
                letterFreq.put(letterKey, freqVal);
            }
        }

        ArrayList<HashMap.Entry<String, Integer>> listFreq = new ArrayList<>(letterFreq.entrySet());

        Collections.sort(listFreq, (Entry<String, Integer> characters1, Entry<String, Integer> letters2) -> characters1.getValue().compareTo(letters2.getValue()));

        Collections.reverse(listFreq);

        for (Entry<String, Integer> getFreq : listFreq) {
            System.out.println(getFreq.getKey() + " --> " + getFreq.getValue());
            output.println(getFreq.getKey() + " --> " + getFreq.getValue());
            output.flush();
        }
        
        output.close();
        inputFrequency.close();
        
    }

}
