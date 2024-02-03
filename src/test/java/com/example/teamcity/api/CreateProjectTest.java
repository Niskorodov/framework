package com.example.teamcity.api;

import com.example.teamcity.api.generators.RandomData;
import com.example.teamcity.api.models.Project;
import com.example.teamcity.api.requests.checked.CheckedProject;
import com.example.teamcity.api.requests.unchecked.UncheckedProject;
import com.example.teamcity.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseApiTest{
    @Test
    public void successProjectCreation() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());
    }

    @Test
    public void successProjectCreationWithID224Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        String longID224 = RandomData.getStringChars(219);

        var projectType = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(Project.builder()
                        .id(longID224)
                        .name(RandomData.getString())
                        .build());

        softy.assertThat(projectType.getId()).isEqualTo(longID224);

    }

    @Test
    public void successProjectCreationWithID225Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        String longID225 = RandomData.getStringChars(220);

        var projectType = new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(Project.builder()
                        .id(longID225)
                        .name(RandomData.getString())
                        .build());

        softy.assertThat(projectType.getId()).isEqualTo(longID225);

    }

    @Test
    public void unSuccessProjectCreationWithID226Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        String longID226 = RandomData.getStringChars(221);

        new UncheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(Project.builder()
                        .id(longID226)
                        .name(RandomData.getString())
                        .build())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void unSuccessProjectCreationWithoutName() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        new UncheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(Project.builder()
                        .id(RandomData.getString())
                        .name(null)
                        .build())
                .then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void successProjectCreationWithoutID() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());

        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(Project.builder()
                        .id(null)
                        .name(RandomData.getString())
                        .build());
    }
}
