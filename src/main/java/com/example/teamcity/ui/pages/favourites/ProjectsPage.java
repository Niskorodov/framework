package com.example.teamcity.ui.pages.favourites;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.selector.ByAttribute;
import com.example.teamcity.ui.Selectors;
import com.example.teamcity.ui.elements.PageElement;
import com.example.teamcity.ui.elements.ProjectElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.elements;

public class ProjectsPage extends FavoritesPage {
    private static final String FAVORITE_PROJECTS_URL = "/favorite/projects";
    private ElementsCollection subprojects = elements(Selectors.byClass("Subproject__container--WE"));
    public ProjectsPage open(){
        Selenide.open(FAVORITE_PROJECTS_URL);
        waitUntilFavoritePageIsLoaded();
        return this;
    }
    public List<ProjectElement> getSubprojects(){
        return generatePageElements(subprojects, ProjectElement::new);
    }

}
