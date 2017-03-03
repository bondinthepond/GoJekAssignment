package DataStores;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class OutputDataStore {

    Map<String, UrlAndPayload> mapOfUrlsAndPayloads = new ConcurrentHashMap<>();

    public void addData(String id, UrlAndPayload urlAndPayload){
        mapOfUrlsAndPayloads.put(id, urlAndPayload);
    }

    public void printPayloads(){

        for(Map.Entry<String, UrlAndPayload> entry : mapOfUrlsAndPayloads.entrySet()){
            System.out.println(entry.getKey()+" : " + entry.getValue().getStringRepresentationOfPayload());
        }
    }

    public void printUrlandPayloads(){

        for(Map.Entry<String, UrlAndPayload> entry : mapOfUrlsAndPayloads.entrySet()){
            System.out.println(entry.getValue().getUrl()+" : " + entry.getValue().getStringRepresentationOfPayload());
        }
    }

    public void deleteData(String id){

        System.out.println("Deleting key : " + id + " and its value : " + mapOfUrlsAndPayloads.get(id));
        mapOfUrlsAndPayloads.remove(id);

    }

    public UrlAndPayload readData(String id){

        return mapOfUrlsAndPayloads.get(id);

    }

    public int getSize(){

        return mapOfUrlsAndPayloads.size();

    }

    public boolean isEmpty(){

        return mapOfUrlsAndPayloads.isEmpty();
    }
}
