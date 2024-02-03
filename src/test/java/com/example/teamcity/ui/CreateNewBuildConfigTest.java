package com.example.teamcity.ui;

import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.spec.Specifications;
import com.example.teamcity.ui.pages.admin.CreateNewProject;
import com.example.teamcity.ui.pages.admin.CreateNewBuildStep;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class CreateNewBuildConfigTest extends BaseUiTest{
    @Test
    public void authorizedUserShouldBeAbleCreateNewBuildConfig() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/ArtemActum/myc-cypress";
        var customScript = "echo 'Hello World'";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new CreateNewBuildStep()
                .navigateToBuildStepsSection()
                .selectAddBuildStep()
                .selectCommandLineBtn()
                .setupBuildStep(testData.getProject().getName(), customScript);

        var buildConfig = new CheckedBuildConfig(Specifications.getSpec().authSpec(testData.getUser()))
                .get(testData.getBuildType().getName());
        softy.assertThat(buildConfig.getId()).isEqualTo(testData.getBuildType().getId());
        softy.assertThat(buildConfig.getName()).isEqualTo(testData.getBuildType().getName());
        softy.assertThat(buildConfig.getProject().getName()).isEqualTo(testData.getBuildType().getProject().getName());
        }

    @Test
    public void authorizedUserShouldNotBeAbleCreateNewBuildConfigWithoutScript() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/ArtemActum/myc-cypress";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new CreateNewBuildStep()
                .navigateToBuildStepsSection()
                .selectAddBuildStep()
                .selectCommandLineBtn()
                .setupBuildStepWithoutScript(testData.getProject().getName());

        new UncheckedBuildConfig(Specifications.getSpec().authSpec(testData.getUser()))
                .get(testData.getBuildType().getName()).then().statusCode(HttpStatus.SC_NOT_FOUND);

    }
}
