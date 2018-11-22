package PostcodesIO_RestAssured;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SinglePostcodeTests {

    @Before
    public void setup(){
        baseURI = "https://api.postcodes.io";
        basePath = "/postcodes/";
//        port = 443;
    }

    @Test
    public void postCodeRequestIsSuccessful(){
        get("EN80LW")
                .then()
                .statusCode(200)
                .extract();
    }
}
