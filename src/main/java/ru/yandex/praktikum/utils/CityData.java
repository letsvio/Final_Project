package ru.yandex.praktikum.utils;

public enum CityData {
    MOSCOW("Москва"),
    SAINT_PETERSBURG("Санкт-Петербург"),
    NOVOSIBIRSK("Новосибирск"),
    EKATERINBURG("Екатеринбург"),
    NIZHNY_NOVGOROD("Нижний Новгород"),
    KAZAN("Казань");

    private final String value;

    CityData(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
