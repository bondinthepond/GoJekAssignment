package OutputWriter;

import Utils.ConfigConstants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class OutputWriter {

    ConfigConstants configConstants = new ConfigConstants();

    public void writeToOutput(String result) {

        String FILENAME = configConstants.LOCATION_OF_OUTPUT_FILE;

        try {

            FileWriter fw = new FileWriter(FILENAME, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String content = result;

            bw.write(content);

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
