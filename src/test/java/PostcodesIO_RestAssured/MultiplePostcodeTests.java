package PostcodesIO_RestAssured;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MultiplePostcodeTests {

    JsonPath multiplePostcodesResponce;

    @Before
    public void setup(){
        baseURI = "https://api.postcodes.io";
        basePath = "/postcodes/";
//        port = 443;

        JSONObject postcodes = new JSONObject();
        JSONArray multiplePostcodes = new JSONArray();

        multiplePostcodes.add("en80lw");
        multiplePostcodes.add("se96rj");

        postcodes.put("postcodes", multiplePostcodes);

        multiplePostcodesResponce =
        given()
                .contentType(ContentType.JSON)
                .body(postcodes)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .jsonPath();
    }

    @Test
    public void MultiplePostcodesResponds(){
        Assert.assertEquals(200, multiplePostcodesResponce.get("status"));
    }

    @Test
    public void checkMultiplePostcodesRespondsWithCorrectPostcodes(){
        JSONObject postcodes = new JSONObject();
        JSONArray multiplePostcodes = new JSONArray();

        multiplePostcodes.add("en80lw");
        multiplePostcodes.add("se96rj");

        postcodes.put("postcodes", multiplePostcodes);

        given()
                .contentType(ContentType.JSON)
                .body(postcodes)
                .when()
                .post()
                .then()
                .statusCode(200)
                .and()
                .body("result[0].result.postcode", equalTo("EN8 0LW"))
                .and()
                .body("result[1].result.postcode", equalTo("SE9 6RJ"));
    }
}
