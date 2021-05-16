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
public class FootballModel{
	@SerializedName("data")
	private Data data;
	@SerializedName("success")
	private boolean success;
}