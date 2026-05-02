package ru.yandex.praktikum.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static ru.yandex.praktikum.utils.FinalData.BASE_URL_FOR_API;

public class UserAuthApi {


    public String authAndGetToken(String email, String password) {
        Response response = RestAssured
                .given()
                .baseUri(BASE_URL_FOR_API)
                .contentType("application/json")
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .post("/signin");

        response.then().log().ifError(); // для отладки

        if (response.statusCode() != 200 && response.statusCode() != 201) {
            throw new RuntimeException("Неудачная авторизация. Статус: " + response.statusCode() +
                    ", тело: " + response.body().asString());
        }

        String accessToken = response.jsonPath().getString("access_token");

        if (accessToken == null || accessToken.isEmpty()) {
            accessToken = response.jsonPath().getString("token"); // альтернативный ключ
        }

        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("access_token не найден! Ответ: " + response.body().asString());
        }

        return accessToken;
    }

}
