package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.Bank;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FDIC_API {
    private long bankId;

    @BeforeMethod
    public void getInfo(){
        // get bank class
        //bankInfo = TestDataGenerator.getBank();
        Response response =
                given()
                        .baseUri("site/url")
                        .basePath("/basesiteurl")
                        .header("Accept","application/json")
                        .header("Content-type","application/json")
                        .body("object/class for testing/bank etc.")
                        .when()
                        .post()
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();

        JsonPath json = response.jsonPath();
        bankId = json.getLong("id");
        System.out.println(bankId);

    }
    @AfterMethod
    public void deleteBank(){
        given()
                .baseUri("end point url for api")
                .basePath("/failure-bank/"+bankId)
                .when()
                .delete();

    }
    @Test
    public void getPetById(){
        Response response =
                given()
                        .baseUri("end point url for api")
                        .basePath("/failure-bank/"+bankId)
                .when()
                        .get()
                .then()
                        .statusCode(200)
                        .extract()
                        .response();

        JsonPath json = response.jsonPath();
        // get bank from json response
        Bank bank = json.getObject("$", Bank.class);

        String name = json.getString("name");

        //Asset if bank name match
        Assert.assertEquals(bank.getName(),bank.getName());

    }
}
