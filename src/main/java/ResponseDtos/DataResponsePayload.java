package ResponseDtos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class DataResponsePayload {

    public DataResponseDto getDataResponseDto() {
        return dataResponseDto;
    }

    public void setDataResponseDto(DataResponseDto dataResponseDto) {
        this.dataResponseDto = dataResponseDto;
    }

    @JsonProperty("data")
        DataResponseDto dataResponseDto;
}
