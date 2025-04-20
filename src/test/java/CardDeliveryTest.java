import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "http://localhost:9999/";
        Configuration.browserSize = "1920x1080";
        // Удалено глобальное изменение таймаута
    }

    @Test
    void shouldSubmitFormSuccessfully() {
        open("/");

        String validDate = DataGenerator.generateDate(3);

        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").doubleClick().sendKeys(validDate);
        $("[data-test-id=name] input").setValue("Иван-Петров Иван");
        $("[data-test-id=phone] input").setValue("+79001234567");
        $("[data-test-id=agreement]").click();
        $$("button").findBy(text("Забронировать")).click();

        $("[data-test-id=notification]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно! Встреча успешно забронирована на " + validDate), Duration.ofSeconds(15));
    }
}
