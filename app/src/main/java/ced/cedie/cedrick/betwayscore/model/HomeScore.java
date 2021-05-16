package ced.cedie.cedrick.betwayscore.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
@Data
public class HomeScore{
	@SerializedName("display")
	private final String display;
	@SerializedName("period_1")
	private final String period1;
	@SerializedName("period_2")
	private final String period2;
	@SerializedName("period_3")
	private final String period3;
	@SerializedName("period_4")
	private final String period4;
}