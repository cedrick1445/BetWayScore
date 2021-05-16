package ced.cedie.cedrick.betwayscore.model;

import com.google.gson.annotations.SerializedName;

public class JsonMember3Pointers{
	@SerializedName("away")
	private Integer away;

	@SerializedName("home")
	private Integer home;

	public Integer getAway(){
		return away;
	}

	public Integer getHome(){
		return home;
	}
}