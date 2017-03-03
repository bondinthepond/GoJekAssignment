package DataStores;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class InputDataStore {


     Map<String, String> mapOfUrls = new ConcurrentHashMap<String, String>();


    public void addData(String id, String url){
        mapOfUrls.put(id, url);
    }

    public void printData(){

        for(Map.Entry<String, String> entry : mapOfUrls.entrySet()){
            System.out.println(entry.getKey()+" : " + entry.getValue());
        }
    }

    public void deleteData(String id){

        System.out.println("Deleting key : " + id + " and its value : " + mapOfUrls.get(id));
        mapOfUrls.remove(id);

    }

    public String readData(String id){

        return mapOfUrls.get(id);

    }

    public int getSize(){

        return mapOfUrls.size();

    }

    public boolean isEmpty(){
        return mapOfUrls.isEmpty();
    }

}
