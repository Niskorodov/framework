package com.example.teamcity.ui.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.element;
@Getter
public class StartUpPage extends Page{
    //private static final String LOGIN_PAGE_URL = "/login.html";
    private SelenideElement acceptLicense = element("input[id='accept']");
    private SelenideElement restoreFromBackupButton = element("input[id='restoreButton']");
    //private SelenideElement backFileUploaded = element("password");
    private SelenideElement proceedButton = element("input[id='proceedButton']");
    private SelenideElement continueButton = element("input[name='Continue']");

    public StartUpPage open(){
        Selenide.open("/");
        return this;
    }

    public StartUpPage setupTeamCityServer(){
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        acceptLicense.shouldBe(Condition.enabled, Duration.ofMinutes(5));
        acceptLicense.scrollTo();
        acceptLicense.click();
        continueButton.click();
        return this;
    }

}

