package ru.yandex.praktikum.utils;

public class TestContext {

    private String email;
    private String password;
    private String token;

    private Integer adId;
    private String lastAdTitle;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public Integer getAdId() { return adId; }
    public void setAdId(Integer adId) { this.adId = adId; }

    public String getLastAdTitle() { return lastAdTitle; }
    public void setLastAdTitle(String lastAdTitle) { this.lastAdTitle = lastAdTitle; }
    public void clear() {
        this.email = null;
        this.password = null;
        this.token = null;
        this.adId = null;
        this.lastAdTitle = null;
    }
}