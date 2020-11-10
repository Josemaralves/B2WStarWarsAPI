package dev.swapi.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Pagination {

    private String count;
    private String next;
    private String previous;
    private List<Planet> results;

}
