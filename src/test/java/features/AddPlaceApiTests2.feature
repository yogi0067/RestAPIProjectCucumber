Feature: Validating Place API's

Scenario Outline:: Verify if Place is Being sucessfully Added using AddPlaceAPI
	Given Add Place PayLoad "<name>","<language>","<address>"
	When user calls "AddPlaceAPI" with "GET" Http Request 
	Then The API Call got success with Status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And Verify the place_id of maps that were created using and return same name "<name>" using "GetPlaceAPI"

	
Examples:

	|name|language|address|
	|yogesh|Punjabi|Bathinda|
	|Gupta|Hindi|Nabha|