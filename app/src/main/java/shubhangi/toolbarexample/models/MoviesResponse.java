package shubhangi.toolbarexample.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public List<Movie> getResults() {
        return results;
    }

    public int getTotalPages() { return totalPages; }
}
