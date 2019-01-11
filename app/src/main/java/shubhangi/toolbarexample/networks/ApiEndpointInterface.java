package shubhangi.toolbarexample.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shubhangi.toolbarexample.models.MoviesResponse;

public interface ApiEndpointInterface {
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPayingMovies(@Query("api_key") String apiKey, @Query("page") int page);
}


