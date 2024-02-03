package com.example.teamcity.ui.pages.admin;

import com.codeborne.selenide.SelenideElement;
import com.example.teamcity.ui.Selectors;
import com.example.teamcity.ui.pages.Page;

import static com.codeborne.selenide.Selenide.element;

public class CreateNewBuildStep extends Page {
    private SelenideElement buildStepsBtn = element(Selectors.byId("runType_Tab"));
    private SelenideElement addBuildStepBtn = element(Selectors.byText("Add build step"));
    private SelenideElement commandLineBtn = element(Selectors.byText("Command Line"));
    private SelenideElement buildStepNameInput = element(Selectors.byId("buildStepName"));
    private SelenideElement customScriptInput = element(Selectors.byId("buildStepName"));
    private SelenideElement runBuildButton = element(Selectors.byId("buildStepName"));


    public CreateNewBuildStep navigateToBuildStepsSection() {
        buildStepsBtn.click();
        return this;
    }

    public CreateNewBuildStep selectAddBuildStep() {
        addBuildStepBtn.click();
        return this;
    }

    public CreateNewBuildStep selectCommandLineBtn() {
        commandLineBtn.click();
        return this;
    }
    public CreateNewBuildStep setupBuildStepWithoutScript(String projectName) {
        buildStepNameInput.clear();
        buildStepNameInput.sendKeys(projectName);
        submit();
        return this;
    }

    public void setupBuildStep(String projectName, String customScript) {
        buildStepNameInput.clear();
        buildStepNameInput.sendKeys(projectName);
        customScriptInput.clear();
        customScriptInput.sendKeys(customScript);
        submit();
    }

    public CreateNewBuildStep runBuild() {
        runBuildButton.click();
        return this;
    }


}
