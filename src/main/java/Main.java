import DataStores.OutputDataStore;
import InputReader.*;
import MessageClient.*;
import ResponseComparator.CompareService;
import Utils.ConfigConstants;
import DataStores.InputDataStore;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {


        ReaderService readerService = new ReaderService();
        MessageService messageService = new MessageService();
        InputDataStore inputDataStore = new InputDataStore();
        OutputDataStore outputDataStore = new OutputDataStore();
        CompareService compareService = new CompareService();

        readerService.initiateRead(inputDataStore);

        messageService.initiate(inputDataStore, outputDataStore);

        compareService.initiateCompare(outputDataStore);

        System.out.println("End of Program. \nCheck output file.");



    }
}
