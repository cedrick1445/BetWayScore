package ced.cedie.cedrick.betwayscore.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class DataItem{

	@SerializedName("away_team")
	private AwayTeam awayTeam;
	@SerializedName("main_stat")
	private MainStat mainStat;
	@SerializedName("id")
	private int id;
	@SerializedName("home_score")
	private HomeScore homeScore;
	@SerializedName("away_score")
	private HomeScore awayScore;
	@SerializedName("home_team")
	private HomeTeam homeTeam;

}