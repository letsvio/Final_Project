package ru.yandex.praktikum.runners;

import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = "cucumber.glue", value = "ru.yandex.praktikum.steps")
@ConfigurationParameter(key = "cucumber.plugin", value =
        "pretty, html:target/cucumber-reports/cucumber.html, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
@ConfigurationParameter(key = "cucumber.glue", value = "ru.yandex.praktikum.steps, ru.yandex.praktikum.hooks")
@Cucumber
public class CucumberTestRunner {
}