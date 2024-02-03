package com.example.teamcity.ui;

import com.codeborne.selenide.Condition;
import com.example.teamcity.api.generators.RandomData;
import com.example.teamcity.api.models.BuildType;
import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.checked.CheckedProject;
import com.example.teamcity.api.requests.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import com.example.teamcity.api.spec.Specifications;
import com.example.teamcity.ui.pages.favourites.ProjectsPage;
import com.example.teamcity.ui.pages.admin.CreateNewProject;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.example.teamcity.api.errors.ErrorMessages.BUILD_CONFIG_ERROR_ID;
import static com.example.teamcity.api.errors.ErrorMessages.PROJECT_CREATION_WITHOUT_NAME;
import static com.example.teamcity.api.errors.ErrorMessages.PROJECT_CREATION_WITHOUT_URL;
import static org.hamcrest.Matchers.containsString;

public class CreateNewProjectTest extends BaseUiTest {
    @Test
    public void authorizedUserShouldBeAbleCreateNewProject() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/ArtemActum/myc-cypress";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        new ProjectsPage().open()
                .getSubprojects()
                .stream().reduce((first, second) -> second).get()
                .getHeader().shouldHave(Condition.text(testData.getProject().getName()));
    }

    @Test
    public void authorizedUserShouldNotBeAbleCreateNewProjectWithoutURL() {
        var testData = testDataStorage.addTestData();

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .clearURLInput()
                .createProjectWithoutUrl()
                .getUrlError().shouldHave(Condition.text(PROJECT_CREATION_WITHOUT_URL));

        new UncheckedProject(Specifications.getSpec().authSpec(testData.getUser()))
                .get(testData.getProject().getName())
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void authorizedUserShouldNotBeAbleCreateNewProjectWithoutProjectName() {
        var testData = testDataStorage.addTestData();
        var url = "https://github.com/ArtemActum/myc-cypress";

        loginAsUser(testData.getUser());

        new CreateNewProject()
                .open(testData.getProject().getParentProject().getLocator())
                .createProjectByUrl(url)
                .clearNameInput()
                .setupProjectWithoutName(testData.getBuildType().getName())
                .getProjectNameInput().shouldHave(Condition.text(PROJECT_CREATION_WITHOUT_NAME));

        new UncheckedProject(Specifications.getSpec().authSpec(testData.getUser()))
                .get(testData.getProject().getName())
                .then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
