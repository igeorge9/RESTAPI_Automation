package cucumber.steps;

import java.io.IOException;

import io.cucumber.core.gherkin.Step;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		stepDefinition step= new stepDefinition();
		if(stepDefinition.place_id==null)
		{

			step.add_place_payload_with_and("Ritu Varma", "Malayalam", "India");
			step.user_calls_with_http_request("AddPlaceAPI", "POST");
			step.verify_the_place_details_are_returned_using_with_http_request("GetPlaceAPI", "Ritu Varma");
		}
	}
}
