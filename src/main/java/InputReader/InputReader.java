package InputReader;

import Utils.ConfigConstants;
import DataStores.InputDataStore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class InputReader implements Runnable {

    ConfigConstants constants;
    String id;
    InputDataStore inputDataStore;
    String fileName;

    public InputReader(String fileName, InputDataStore inputDataStore){
            this.fileName = fileName;
            this.inputDataStore = inputDataStore;
    }

    public void run(){

        System.out.println(Thread.currentThread().getName() + "Start");
        readInputFile(this.fileName);
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    public void readInputFile(String fileName) {

        constants = new ConfigConstants();

            try {

                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

                String line = bufferedReader.readLine();

                int lineNum = 1;

                while (line != null && !line.equals("EOF")) {

                    id = calculateId(fileName, lineNum);
                    inputDataStore.addData(id, line);
                    line = bufferedReader.readLine();
                    lineNum++;
                }
                bufferedReader.close();

            } catch (FileNotFoundException fileNotFound) {
                System.out.println("One of the input Files, not Present");
                fileNotFound.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
    }

    public String calculateId(String fileName, int lineNumber) {

        StringBuilder builder = new StringBuilder();

        char c = fileName.charAt(fileName.length() - 1);
        builder.append(c);
        builder.append('.');
        builder.append(lineNumber);

        return builder.toString();
    }
}
