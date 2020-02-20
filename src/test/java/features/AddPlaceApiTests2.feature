Feature: Validating Place API's

Scenario: Verify if Place is Being sucessfully Added using AddPlaceAPI
	Given Add Place PayLoad
	When user calls "AddPlaceAPI" with Post Http Request 
	Then The API Call got success with Status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
