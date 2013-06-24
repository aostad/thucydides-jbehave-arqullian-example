package com.engagepoint.acceptancetest.steps;

import net.thucydides.core.pages.AnyPage;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleJbehaveSteps extends ScenarioSteps {
	
	private AnyPage anyPage;

	public SimpleJbehaveSteps(Pages pages) {
		super(pages);
		anyPage = pages().get(AnyPage.class);
	}

    @When("the user opens the default page")
    public void openLink() {
        anyPage.open();
    }

    @Then("the user is brought to the page with '$title' title")
    public void verifyThatTitleIsPresentOnPage(String title) {
        assertThat(anyPage.getTitle(), is(equalTo(title)));
    }

    @When("the user fills '$id' field with '$value'")
    public void fillField(String id, String value) {
        anyPage.enter(value).intoField(findVisibleElementAndGetSelector(id));
    }

    protected By findVisibleElementAndGetSelector(String id) {
        By[] selectors = { By.id(id), By.xpath("//*[contains(@id, '" + id + "')]"), By.name(id), By.className(id) };
        for (By selector : selectors) {
            if (isElementDisplayed(selector)) {
                return selector;
            }
        }
        return selectors[0];
    }

    private boolean isElementDisplayed(By selector) {
        try {
            return anyPage.element(selector).isCurrentlyVisible();
        } catch (Exception e) {
        }
        return false;
    }

    @When("clicks on element with id/name/className '$id'")
    public void clickBySelector(String id) {
        anyPage.element(findVisibleElementAndGetSelector(id)).click();
    }

    @Then("element id/name/className '$id' has text '$textContent'")
    public void verifyThatElementHasText(String id, String textContent) {
        assertThat(anyPage.element(findVisibleElementAndGetSelector(id)).getText().replaceAll("\n", " "), is(equalTo(textContent)));
    }
	
}