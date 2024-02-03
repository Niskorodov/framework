package com.example.teamcity.api;

import com.example.teamcity.api.generators.RandomData;
import com.example.teamcity.api.models.BuildType;
import com.example.teamcity.api.requests.checked.CheckedBuildConfig;
import com.example.teamcity.api.requests.checked.CheckedProject;
import com.example.teamcity.api.requests.unchecked.UncheckedBuildConfig;
import com.example.teamcity.api.spec.Specifications;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.example.teamcity.api.errors.ErrorMessages.BUILD_CONFIG_ERROR_ID;
import static com.example.teamcity.api.errors.ErrorMessages.BUILD_CONFIG_INTERNAL_SERVER_ERROR;
import static org.hamcrest.Matchers.containsString;

public class CreateBuildConfigTest extends BaseApiTest {
    @Test
    public void successBuildConfigCreation() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());


        new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getBuildType());
    }

    @Test
    public void successBuildConfigCreationWithID224Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String longNumber224 = RandomData.getStringChars(219);

        var buildType = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(longNumber224)
                        .name(RandomData.getString())
                        .project(testData.getProject())
                        .build());

        softy.assertThat(buildType.getId()).isEqualTo(longNumber224);
    }

    @Test
    public void successBuildConfigCreationWithID225Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String longNumber225 = RandomData.getStringChars(220);

        var buildType = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(longNumber225)
                        .name(RandomData.getString())
                        .project(testData.getProject())
                        .build());

        softy.assertThat(buildType.getId()).isEqualTo(longNumber225);
    }

    @Test
    public void unSuccessBuildConfigCreationWithID226Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String longID226 = RandomData.getStringChars(221);

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(longID226)
                        .name(RandomData.getString())
                        .project(testData.getProject())
                        .build())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(containsString(BUILD_CONFIG_ERROR_ID));


    }

    @Test
    public void unSuccessBuildConfigCreationWithIDSpecialChars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String specialChars = RandomData.getStringSpecialChars();

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(specialChars)
                        .name(RandomData.getString())
                        .project(testData.getProject())
                        .build())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(containsString(BUILD_CONFIG_INTERNAL_SERVER_ERROR));
    }

    @Test
    public void unSuccessBuildConfigCreationWithIDStartsSpecialChars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String startsWithSpecialChars = RandomData.getStringStartsSpecialChars();

        new UncheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(startsWithSpecialChars)
                        .name(RandomData.getString())
                        .project(testData.getProject())
                        .build())
                .then().assertThat().statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void successBuildConfigCreationWithName224Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String longName224 = RandomData.getStringChars(219);

        var buildType = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(RandomData.getString())
                        .name(longName224)
                        .project(testData.getProject())
                        .build());

        softy.assertThat(buildType.getName()).isEqualTo(longName224);
    }

    @Test
    public void successBuildConfigCreationWithName225Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String longName225 = RandomData.getStringChars(220);

        var buildType = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(RandomData.getString())
                        .name(longName225)
                        .project(testData.getProject())
                        .build());

        softy.assertThat(buildType.getName()).isEqualTo(longName225);
    }

    @Test
    public void successBuildConfigCreationWithName1000Chars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String longName1000 = RandomData.getStringChars(995);

        var buildType = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(RandomData.getString())
                        .name(longName1000)
                        .project(testData.getProject())
                        .build());

        softy.assertThat(buildType.getName()).isEqualTo(longName1000);
    }

    @Test
    public void successBuildConfigCreationWithNameSpecialChars() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        String specialChars = RandomData.getStringSpecialChars();

        var buildType = new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(RandomData.getString())
                        .name(specialChars)
                        .project(testData.getProject())
                        .build());

        softy.assertThat(buildType.getName()).isEqualTo(specialChars);
    }

    @Test
    public void successBuildConfigCreationWithoutID() {
        var testData = testDataStorage.addTestData();

        checkedWithSuperUser.getUserRequest()
                .create(testData.getUser());


        new CheckedProject(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(testData.getProject());

        new CheckedBuildConfig(Specifications.getSpec()
                .authSpec(testData.getUser()))
                .create(BuildType.builder()
                        .id(null)
                        .name(RandomData.getString())
                        .project(testData.getProject())
                        .build());
    }

}
