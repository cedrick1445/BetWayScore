package ced.cedie.cedrick.betwayscore.model;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamsItem{
	@SerializedName("intStadiumCapacity")
	private String intStadiumCapacity;
	@SerializedName("strTeamJersey")
	private String strTeamJersey;
	@SerializedName("strTeamFanart1")
	private String strTeamFanart1;
	@SerializedName("strTeamFanart2")
	private String strTeamFanart2;
	@SerializedName("strTeamFanart3")
	private String strTeamFanart3;
	@SerializedName("strTeamFanart4")
	private String strTeamFanart4;
	@SerializedName("strStadiumDescription")
	private String strStadiumDescription;
	@SerializedName("strTeamLogo")
	private String strTeamLogo;
	@SerializedName("strStadiumLocation")
	private String strStadiumLocation;
	@SerializedName("idTeam")
	private String idTeam;
	@SerializedName("strDescriptionEN")
	private String strDescriptionEN;
	@SerializedName("strAlternate")
	private String strAlternate;
	@SerializedName("strTeamBadge")
	private String strTeamBadge;
	@SerializedName("strStadiumThumb")
	private String strStadiumThumb;
}