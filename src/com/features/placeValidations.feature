 Feature: Validating Place API's
 
 @AddPlace
 Scenario Outline: Verify if Place is being successfully added using AddPlace API
 
 	Given AddPlace Payload with "<name>" "<language>" and "<address>"
 	When user calls "AddPlaceAPI" with "POST" HTTP request
 	Then the API call got success with status code 200
 	And "status" in response body is "OK"
 	And "scope" in response body is "APP" 
 	And verify the place details are returned using "GetPlaceAPI" with "<name>" HTTP Request
 	
 	Examples:
 	
 	|name		|language	|address			  |
 	| ABC house |  Spanish  |  XYZ palace, Spain  |
 	
 @DeletePlace
 Scenario: Delete the place added using DeletePlaceAPI
 
 	Given DeletePlace Payload 
 	When user calls "deletePlaceAPI" with "POST" HTTP request
 	Then the API call got success with status code 200
 	And "status" in response body is "OK"