package ru.tinkoff.fintech.service.notification

import ru.tinkoff.fintech.model.NotificationMessageInfo

class NotificationMessageGeneratorImpl(
    private val cardNumberMasker: CardNumberMasker
) : NotificationMessageGenerator {

    /**
     * Уважаемый, Сергей!
     * Спешим Вам сообщить, что на карту ############4567
     * начислен cashback в размере 700.00
     * за категорию BLACK.
     * Спасибо за покупку 2020-11-20T01:00
     */
    override fun generateMessage(notificationMessageInfo: NotificationMessageInfo): String {
        return "Уважаемый, ${notificationMessageInfo.name}!\n" +
                "Спешим Вам сообщить, что на карту ${cardNumberMasker.mask(notificationMessageInfo.cardNumber)}\n" +
                "начислен cashback в размере ${notificationMessageInfo.cashback}\n" +
                "за категорию ${notificationMessageInfo.category}.\n" +
                "Спасибо за покупку ${notificationMessageInfo.transactionDate}"
    }
}