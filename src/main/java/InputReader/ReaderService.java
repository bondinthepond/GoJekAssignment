package InputReader;

import DataStores.InputDataStore;
import Utils.ConfigConstants;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class ReaderService {

    ConfigConstants constants;

    public void initiateRead(InputDataStore inputDataStore) throws InterruptedException {

        InputReader inputReader1 = new InputReader(constants.LOCATION_OF_INPUT_FILE_1, inputDataStore);

        InputReader inputReader2 = new InputReader(constants.LOCATION_OF_INPUT_FILE_2, inputDataStore);


        Thread t1 = new Thread(inputReader1);
        Thread t2 = new Thread(inputReader2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

//        inputDataStore.printData();

    }
}
