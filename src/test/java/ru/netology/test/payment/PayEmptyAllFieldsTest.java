package ru.netology.test.payment;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.MainPage;
import ru.netology.page.PaymentPage;
import ru.netology.test.TestBase;

import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.DataHelper.getInvalidCardDataIfEmptyAllFields;

public class PayEmptyAllFieldsTest extends TestBase {
    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCard() {
        mainPage.payWithCard();
    }

    @Test
    public void shouldFailurePaymentIfEmptyAllFields() {
        val cardData = getInvalidCardDataIfEmptyAllFields();
        paymentPage.fillCardData(cardData);
        final ElementsCollection fieldSub = $$(".input__sub");
        final SelenideElement cardNumberFieldSub = fieldSub.get(1);
        final SelenideElement monthFieldSub = fieldSub.get(2);
        final SelenideElement yearFieldSub = fieldSub.get(3);
        final SelenideElement cardholderFieldSub = fieldSub.get(4);
        final SelenideElement cvvFieldSub = fieldSub.get(5);
        cardNumberFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
        monthFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
        yearFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
        cardholderFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
        cvvFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
}