package Alllocators;

public class locators {

// Главная страница

// URL главной страницы - https://qa-scooter.praktikum-services.ru/

// Кнопка «Принять cookies» - id = rcc-confirm-button

// Подствердить cookies - class = App_CookieText__1sbqp

// Верхняя кнопка «Заказать» - xpath = (//button[normalize-space(.)='Заказать'])[1]

// Нижняя кнопка «Заказать» - xpath = (//button[normalize-space(.)='Заказать'])[2]

// Вопросы о важном - id = accordion__heading- (0 - 7)

// Ответы о важном - id = accordion__panel- (0 - 7)


// Оформление заказа


// Поле «Имя» - xpath = //input[@placeholder='* Имя']

// Поле «Фамилия» - xpath = //input[@placeholder='* Фамилия']

// Поле «Адрес» - xpath = //input[@placeholder='* Адрес: куда привезти заказ']

// Поле «Станция метро» - css = input.select-search__input

// Поле «Телефон» - xpath = //input[@placeholder='* Телефон: на него позвонит курьер']

// Кнопка «Далее» - xpath = //button[normalize-space(.)='Далее']


// Заголовок «Про аренду» - xpath = //div[normalize-space(.)='Про аренду']

// Поле «Когда привезти самокат» - xpath = //input[contains(@placeholder,'Когда привезти самокат')]

// Выпадашка «Срок аренды» - css = .Dropdown-control

// Срок аренды «сутки» - xpath = //div[contains(@class,'Dropdown-option') and normalize-space(.)='сутки']

// Цвет «чёрный жемчуг» - id = black

// Цвет «серая безысходность» - id = grey

// Поле «Комментарий для курьера» - xpath = //input[@placeholder='Комментарий для курьера']

// Кнопка «Заказать» на шаге «Про аренду» - xpath = //div[contains(@class,'Order_Buttons')]//button[normalize-space(.)='Заказать']


// Подтверждение заказа

// Текст «Хотите оформить заказ?»  - xpath = //*[contains(normalize-space(.),'Хотите оформить заказ')]

// Кнопка «Да» в модалке подтверждения - xpath = //div[contains(@class,'Order_Modal')]//div[contains(@class,'Order_Buttons')]//button[normalize-space(.)='Да']


// Проверка подтверждения заказа


// Текст «Заказ оформлен» - xpath = //*[contains(normalize-space(.),'Заказ оформлен')]

// Кнопка «Посмотреть статус» - xpath = //button[normalize-space(.)='Посмотреть статус']

}