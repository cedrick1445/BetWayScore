package ced.cedie.cedrick.betwayscore.network;
import javax.inject.Inject;
import ced.cedie.cedrick.betwayscore.model.BasketballModel;
import ced.cedie.cedrick.betwayscore.model.FootballModel;
import ced.cedie.cedrick.betwayscore.model.TeamModel;
import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private final BasketballApiService basketApiService;
    private final FootballApiService footballApiService;
    private final TeamApiService teamApiService;
    @Inject
    public Repository(BasketballApiService basketApiService, FootballApiService footballApiService, TeamApiService teamApiService){
        this.basketApiService = basketApiService;
        this.footballApiService = footballApiService;
        this.teamApiService = teamApiService;
    }

    public Observable<BasketballModel> getBasket(){
        return basketApiService.getBasketScore();
    }

    public Observable<FootballModel> getFoot(){
        return footballApiService.getFootScore("XzMh2Iy6vfULiaY6","Jriz0LkpL6OLF8DDHpVK8tQEJKp1Vo7E");
    }

    public Observable<TeamModel> getFTeams(){
        return teamApiService.getFootTeams();
    }

    public Observable<TeamModel> getBTeams(){
        return teamApiService.getBasketTeams();
    }
}
