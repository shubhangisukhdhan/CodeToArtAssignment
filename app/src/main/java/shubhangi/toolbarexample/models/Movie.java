package shubhangi.toolbarexample.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {
    public Movie() {
        this.title = "movie title";
    }

    private String title;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private String overview;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("release_date")
    private String releaseDate;

    public String getTitle() {
        return this.title;
    }

    public String getPosterPath() { return this.posterPath; }

    public String getBackdropPath() { return this.backdropPath; }

    public String getOverview() { return this.overview; }

    public float getVoteAverage() { return this.voteAverage; }

    public String getReleaseDate() { return this.releaseDate; }
}
