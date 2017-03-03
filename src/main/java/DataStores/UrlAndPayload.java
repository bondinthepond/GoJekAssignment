package DataStores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by aditya.mullela on 04/03/17.
 */
public class UrlAndPayload {

    ObjectMapper mapper = new ObjectMapper();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JsonNode getPayload() {
        return payload;
    }

    public void setPayload(JsonNode payload) {
        this.payload = payload;
    }

    public String getStringRepresentationOfPayload(){
        try {
            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    String url;
    JsonNode payload;

    public UrlAndPayload(String url, JsonNode payload){
        this.url = url;
        this.payload = payload;
    }

}
