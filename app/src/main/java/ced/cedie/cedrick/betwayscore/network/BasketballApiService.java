package ced.cedie.cedrick.betwayscore.network;

import ced.cedie.cedrick.betwayscore.model.BasketballModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface BasketballApiService {

    @Headers({"x-rapidapi-host: sportscore1.p.rapidapi.com",
            "x-rapidapi-key: 07e55202eemshd454005e3a79774p103cccjsn4b32f05d3a2f",
            "useQueryString: true"})
    @GET("events")
    Observable<BasketballModel> getBasketScore();
}
