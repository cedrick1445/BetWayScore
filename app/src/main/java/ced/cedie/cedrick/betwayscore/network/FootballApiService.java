package ced.cedie.cedrick.betwayscore.network;

import ced.cedie.cedrick.betwayscore.model.FootballModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FootballApiService {
    @GET("live.json?")
    Observable<FootballModel> getFootScore(@Query("key") String key, @Query("secret") String secret);
}
