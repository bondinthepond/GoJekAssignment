package ResponseDtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by aditya.mullela on 03/03/17.
 */
public class PageResponsePayload {

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<DataResponseDto> getListOfData() {
        return listOfData;
    }

    public void setListOfData(ArrayList<DataResponseDto> listOfData) {
        this.listOfData = listOfData;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }


    @JsonProperty("page")
    String pageNumber;

    @JsonProperty("per_page")
    int perPage;

    @JsonProperty("total")
    String total;

    @JsonProperty("total_pages")
    String totalPages;

    @JsonProperty("data")
    ArrayList<DataResponseDto> listOfData;
}
