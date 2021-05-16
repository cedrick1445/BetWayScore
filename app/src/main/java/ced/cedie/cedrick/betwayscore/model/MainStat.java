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
public class MainStat{
	@SerializedName("fouls")
	private Fouls fouls;
	@SerializedName("field_goals")
	private FieldGoals fieldGoals;
	@SerializedName("2_pointers")
	private JsonMember2Pointers jsonMember2Pointers;
	@SerializedName("free_throws")
	private FreeThrows freeThrows;
	@SerializedName("3_pointers")
	private JsonMember3Pointers jsonMember3Pointers;

}