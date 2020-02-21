package resources;

import java.util.ArrayList;

import pojo.AddPlacePojo;
import pojo.Location;

public class PayLoadDataForTestBuild {
	
	public AddPlacePojo getPlacePayload()
	{
		AddPlacePojo inputPlace = new AddPlacePojo();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		inputPlace.setAccuracy(50);
		inputPlace.setAddress("29, side layout, cohen 09");
		inputPlace.setLanguage("French-IN");
		inputPlace.setName("Frontline house");
		inputPlace.setLocation(location);
		ArrayList<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		inputPlace.setTypes(types);
		inputPlace.setWebsite("http://google.com");
		inputPlace.setPhone_number("(+91) 983 893 3937");
		return inputPlace;
	}
}
