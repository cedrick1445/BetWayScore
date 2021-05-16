package ced.cedie.cedrick.betwayscore.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ced.cedie.cedrick.betwayscore.model.DataItem;
import ced.cedie.cedrick.betwayscore.model.MatchItem;
import ced.cedie.cedrick.betwayscore.model.TeamsItem;
import ced.cedie.cedrick.betwayscore.network.Repository;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ViewModels extends ViewModel {

    private static final String TAG = "ViewModel";
    private final MutableLiveData<List<DataItem>> basketlist = new MutableLiveData<>();
    private final MutableLiveData<List<MatchItem>> footList = new MutableLiveData<>();
    private final MutableLiveData<List<TeamsItem>> teamList = new MutableLiveData<>();

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final Repository repository;

    @ViewModelInject
    public ViewModels(Repository repository){this.repository = repository; }

    public LiveData<List<DataItem>> getBasketList(){return  basketlist; }

    public LiveData<List<MatchItem>> getFootList(){return footList; }

    public LiveData<List<TeamsItem>> getTeamList(){return teamList;}

    public void getBasketScore(){
        disposable.add(repository.getBasket()
        .subscribeOn(Schedulers.newThread())
        .map(basketResponse -> {
            List<DataItem> list = basketResponse.getData();
            return list;
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(result -> basketlist.setValue(result),
                error -> Log.e(TAG, "walang basketball"+error.getMessage())));
    }

    public void getFootScore(){
        disposable.add(repository.getFoot()
        .subscribeOn(Schedulers.io())
        .map(footResponse ->{
            List<MatchItem> fList = footResponse.getData().getMatch();
            return fList;
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(footList::setValue,
                        error -> Log.e(TAG, "walang football"+error.getMessage())));
    }

   public void getFootTeam(){
        disposable.add(repository.getFTeams()
        .subscribeOn(Schedulers.io())
        .map(fteamResponse -> {
            List<TeamsItem> tList = fteamResponse.getTeams();
            return tList;
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(teamList::setValue, error -> Log.e(TAG, "walang lamaaaan"+error.getMessage())));
   }

   public void getBTeams(){
        disposable.add(repository.getBTeams()
        .subscribeOn(Schedulers.io())
        .map(bteamResponse ->{
            List<TeamsItem> tList = bteamResponse.getTeams();
            return tList;
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(teamList::setValue, error -> Log.e(TAG,"Wala talaga lamaaan"+error.getMessage())));
   }
}
