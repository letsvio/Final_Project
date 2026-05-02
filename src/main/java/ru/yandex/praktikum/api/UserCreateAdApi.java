package ru.yandex.praktikum.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static ru.yandex.praktikum.utils.FinalData.BASE_URL_FOR_API;

public class UserCreateAdApi {

    public Response createAd(String accessToken, String name, String category,
                             String condition, String city, String description, String price) {

        return RestAssured
                .given()
                .baseUri(BASE_URL_FOR_API)
                .header("Authorization", "Bearer " + accessToken)
                .contentType("multipart/form-data")
                .multiPart("name", name)
                .multiPart("category", category)
                .multiPart("condition", condition)
                .multiPart("city", city)
                .multiPart("description", description)
                .multiPart("price", price)
                .post("/create-listing");
    }

    public Integer getAdId(Response response) {
        response.then().statusCode(201);

        return response.jsonPath().getInt("id");
    }
}
