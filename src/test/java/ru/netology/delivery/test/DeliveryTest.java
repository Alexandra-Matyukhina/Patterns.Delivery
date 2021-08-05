package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @BeforeEach
    void setup() {

        open("http://localhost:7777");
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        DataGenerator.User user = DataGenerator.Registration.generateDelivery("ru");
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(0));
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement] .checkbox__box").click();
        $("[role=button] .button__content").click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(0)));
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL, "a", Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(1));
        $("[role=button] .button__content").click();
        $("[data-test-id=replan-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("У вас уже запланирована встреча на другую дату. Перепланировать?\n" +
                "\n" +
                "Перепланировать"));
        $("[data-test-id=replan-notification] .button__text").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification] .notification__content").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(1)));
    }
}
