package ResponseComparator;

import DataStores.OutputDataStore;
import OutputWriter.OutputWriter;
import ResponseDtos.DataResponseDto;
import ResponseDtos.DataResponsePayload;
import ResponseDtos.PageResponsePayload;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class CompareService {

    OutputWriter outputWriter = new OutputWriter();

    String key1;
    String key2;
    String url1;
    String url2;

    JsonNode response1;
    JsonNode response2;


    static int initialSize;

    public void initiateCompare(OutputDataStore outputDataStore) throws IOException {

        initialSize = outputDataStore.getSize();
        int i = 1;

        while(!outputDataStore.isEmpty()){

            key1 = "1."+i;
            key2 = "2."+i;

            url1 = outputDataStore.readData(key1).getUrl();
            url2 = outputDataStore.readData(key2).getUrl();

            response1 = outputDataStore.readData(key1).getPayload();
            response2 = outputDataStore.readData(key2).getPayload();

            outputDataStore.deleteData(key1);
            outputDataStore.deleteData(key2);

            compareResponses(response1, response2);
            i++;
        }
    }

    public void compareResponses(JsonNode response1, JsonNode response2) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        if(response1.get("page") != null && response2.get("page") != null) {
                PageResponsePayload pageResponsePayload1 = mapper.readValue(mapper.writeValueAsString(response1), PageResponsePayload.class);
                PageResponsePayload pageResponsePayload2 = mapper.readValue(mapper.writeValueAsString(response2), PageResponsePayload.class);
                comparePageResponses(pageResponsePayload1, pageResponsePayload2);
            } else if(response1.get("page") == null && response2.get("page") != null) {
                outputWriter.writeToOutput(url1 + " not equals " + url2 + "\n");
            } else if(response1.get("page") != null && response2.get("page") == null) {
                outputWriter.writeToOutput(url1 + " not equals " + url2 + "\n");
            } else if(response1.get("page") == null && response1.get("data") != null && response2.get("page") == null && response2.get("data") != null) {
                DataResponsePayload dataResponsePayload1 = mapper.readValue(mapper.writeValueAsString(response1), DataResponsePayload.class);
                DataResponsePayload dataResponsePayload2 = mapper.readValue(mapper.writeValueAsString(response2), DataResponsePayload.class);
                compareDataResponses(dataResponsePayload1, dataResponsePayload2);
            } else outputWriter.writeToOutput(url1 + " not equals " + url2 + "\n");
        }


    public void comparePageResponses(PageResponsePayload pageResponsePayload1, PageResponsePayload pageResponsePayload2){

        ArrayList<Boolean> listOfBooleans = new ArrayList<>();
        if(pageResponsePayload1.getListOfData().size() == pageResponsePayload2.getListOfData().size()){
            for (int i = 0; i < pageResponsePayload1.getListOfData().size() ; i++) {
                listOfBooleans.add(compareDataResponseDtos(pageResponsePayload1.getListOfData().get(i), pageResponsePayload2.getListOfData().get(i)));
            }
            if(listOfBooleans.contains(false)){
                outputWriter.writeToOutput(url1 + " not equals " + url2 + "\n");
            } else {
                outputWriter.writeToOutput(url1 + " equals " + url2 + "\n");
            }
        } else {
            outputWriter.writeToOutput(url1 + " not equals " + url2 + "\n");
        }
    }

    public void compareDataResponses(DataResponsePayload dataResponsePayload1, DataResponsePayload dataResponsePayload2){

        if(compareDataResponseDtos(dataResponsePayload1.getDataResponseDto(), dataResponsePayload2.getDataResponseDto()))
            outputWriter.writeToOutput(url1 + " equals " + url2 + "\n");
        else outputWriter.writeToOutput(url1 + " not equals " + url2 + "\n");
    }

    public Boolean compareDataResponseDtos(DataResponseDto dataResponseDto1, DataResponseDto dataResponseDto2){

        if(dataResponseDto1.getFirstName().equals(dataResponseDto2.getFirstName()) &&
                dataResponseDto1.getLastName().equals(dataResponseDto2.getLastName())){
            return true;
        }
        return false;
    }
}
