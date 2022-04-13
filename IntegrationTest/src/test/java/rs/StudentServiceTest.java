package rs;

import io.restassured.response.Response;
import io.cucumber.java.en.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class StudentServiceTest {

    private static final String STUDENT_SERVICE_URL = "http://localhost:8080";

    private Response response;

    @When("the list of students is requested")
    public void the_list_of_students_is_requested() {
        response = get(STUDENT_SERVICE_URL + "/students");
    }

    @Then("the response contains the student with the id {string}")
    public void the_response_contain_the_student(String id) {
        response.then().statusCode(200).assertThat()
                .body("id[0]", equalTo(2));
    }
}
