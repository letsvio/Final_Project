package ru.yandex.praktikum.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static ru.yandex.praktikum.utils.FinalData.BASE_URL_FOR_API;

public class UserRegisterApi {

    public Response register(String email, String password) {
        return RestAssured
                .given()
                .baseUri(BASE_URL_FOR_API)
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\"," +
                        "\"password\":\"" + password + "\"," +
                        "\"submitPassword\":\"" + password + "\"}")
                .post("/signup");
    }
}

