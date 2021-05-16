package ced.cedie.cedrick.betwayscore.network;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkApi {
    @Provides
    @Singleton
    public static BasketballApiService provideBasketService(){
        return new Retrofit.Builder()
                .baseUrl("https://rapidapi.p.rapidapi.com/sports/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(BasketballApiService.class);
    }
    @Provides
    @Singleton
    public static FootballApiService providesFootService(){
        return new Retrofit.Builder()
                .baseUrl("https://livescore-api.com/api-client/scores/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(FootballApiService.class);
    }

    @Provides
    @Singleton
    public static TeamApiService providesTeamService(){
        return new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(TeamApiService.class);
    }

}
