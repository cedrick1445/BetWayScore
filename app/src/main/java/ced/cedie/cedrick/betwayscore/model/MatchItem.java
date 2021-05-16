package ced.cedie.cedrick.betwayscore.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MatchItem{
	@SerializedName("status")
	private final String status;

	@SerializedName("score")
	private final String score;

	@SerializedName("away_name")
	private final String awayName;

	@SerializedName("added")
	private final String added;

	@SerializedName("home_name")
	private final String homeName;

	@SerializedName("location")
	private final String location;

}


