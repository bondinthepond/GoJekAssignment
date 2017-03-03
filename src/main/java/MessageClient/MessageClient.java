package MessageClient;

import DataStores.OutputDataStore;
import DataStores.UrlAndPayload;
import OutputWriter.OutputWriter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

/**x`
 * Created by aditya.mullela on 03/03/17.
 */
public class MessageClient implements Runnable {

    private String key1;
    private String key2;
    private String url1;
    private String url2;
    OutputDataStore outputDataStore;


    public MessageClient(String key1, String url1, String key2, String url2, OutputDataStore outputDataStore){
        this.key1 = key1;
        this.key2 = key2;
        this.url1 = url1;
        this.url2 = url2;
        this.outputDataStore = outputDataStore;

    }

    public void run(){
        System.out.println(Thread.currentThread().getName() + "Start");
        makeCall(key1, url1, outputDataStore);
        makeCall(key2, url2, outputDataStore);
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    public void makeCall(String key, String url, OutputDataStore outputDataStore) {

        try {

            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(url);

            HttpResponse response = httpClient.execute(get);
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " +
                    response.getStatusLine().getStatusCode());

            String type = response.getEntity().getContentType().toString();

            System.out.println("type:" + type);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            ObjectMapper mapper = new ObjectMapper();
            UrlAndPayload actualObj = new UrlAndPayload(url, mapper.readValue(result.toString(), JsonNode.class));
            outputDataStore.addData(key, actualObj);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
