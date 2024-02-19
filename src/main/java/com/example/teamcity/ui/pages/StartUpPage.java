package com.example.teamcity.ui.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import lombok.Getter;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.element;
@Getter
public class StartUpPage extends Page {
    private SelenideElement createAdminAccountHeader = element(Selectors.byId("header"));
    private SelenideElement header = element(Selectors.byId("header"));
    private SelenideElement acceptLicense = element(Selectors.byId("accept"));
    //private SelenideElement restoreFromBackupButton = element("input[id='restoreButton']");
    //private SelenideElement backFileUploaded = element("password");
    private SelenideElement proceedButton = element(Selectors.byId("proceedButton"));
    //private SelenideElement continueButton = element("input[name='Continue']");
    private SelenideElement submitButton = element(Selectors.byType("submit"));

    public StartUpPage open(){
        Selenide.open("/mnt");
        return this;
    }

    public SelenideElement getHeader() {
        return createAdminAccountHeader.shouldBe(Condition.visible, Duration.ofSeconds(35));
    }

    public StartUpPage setupTeamCityServer(){
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        proceedButton.click();
        waitUntilPageIsLoaded();
        header.shouldBe(Condition.visible, Duration.ofMinutes(5));
        acceptLicense.shouldBe(Condition.enabled, Duration.ofMinutes(5));
        acceptLicense.scrollTo();
        acceptLicense.click();
        submitButton.click();
        return this;
    }

}