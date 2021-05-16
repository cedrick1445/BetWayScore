package ced.cedie.cedrick.betwayscore.network;

import ced.cedie.cedrick.betwayscore.model.TeamModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface TeamApiService {

    @GET("search_all_teams.php?l=English%20Premier%20League")
    Observable<TeamModel> getFootTeams();

    @GET("search_all_teams.php?l=NBA")
    Observable<TeamModel> getBasketTeams();
}
