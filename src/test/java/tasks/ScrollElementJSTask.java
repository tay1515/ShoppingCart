package tasks;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

public class ScrollElementJSTask implements Task {

    private String locator;

    public ScrollElementJSTask(String locator) {
        this.locator = locator;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) Serenity.getDriver();
        WebElementFacade scroll = BrowseTheWeb.as(actor).find(By.xpath(locator));
        js.executeScript("arguments[0].scrollIntoView();", scroll);
        scroll.click();
    }

    public static ScrollElementJSTask ScrollElementJS(String locator) {
        return instrumented(ScrollElementJSTask.class, locator);
    }
}
