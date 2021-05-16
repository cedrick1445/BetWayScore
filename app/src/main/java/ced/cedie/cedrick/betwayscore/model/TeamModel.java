package ced.cedie.cedrick.betwayscore.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class TeamModel{
	@SerializedName("teams")
	private List<TeamsItem> teams;
}