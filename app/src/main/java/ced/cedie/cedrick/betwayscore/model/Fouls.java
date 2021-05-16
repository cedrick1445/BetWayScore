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
public class Fouls{
	@SerializedName("away")
	private Integer away;
	@SerializedName("home")
	private Integer home;
}