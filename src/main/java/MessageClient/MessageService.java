package MessageClient;

import DataStores.InputDataStore;
import DataStores.OutputDataStore;
import InputReader.InputReader;

import java.util.concurrent.*;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class MessageService {


    public void initiate(InputDataStore inputDataStore, OutputDataStore outputDataStore) throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

//        int size = inputDataStore.getSize()/2;
        int i = 1;

        while(!inputDataStore.isEmpty()){

            String key1 = "1."+i;

            String key2 = "2."+i;

            Runnable messageClient = new MessageClient(key1, inputDataStore.readData(key1), key2, inputDataStore.readData(key2), outputDataStore);

            Future future = executorService.submit(messageClient);

            future.get();

            inputDataStore.deleteData(key1);
            inputDataStore.deleteData(key2);

            i++;
        }
        executorService.shutdown();
    }
}
