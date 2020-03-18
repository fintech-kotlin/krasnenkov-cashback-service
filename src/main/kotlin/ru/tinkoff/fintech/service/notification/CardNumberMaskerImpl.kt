package ru.tinkoff.fintech.service.notification

import org.springframework.stereotype.Service

@Service
class CardNumberMaskerImpl : CardNumberMasker {

    /**
     * маскирует цифры в номере карты(1112223335554567 -> ############4567)
     */
    override fun mask(cardNumber: String, maskChar: Char, start: Int, end: Int): String {
        //проверка условий валидных граничных значений
        if (start > end) {
            throw Exception("Start index cannot be greater than end index")
        }

        //проверка пустоты номера карты
        if (cardNumber.isBlank() || start == end) {
            return cardNumber
        }

        //Проверки на выход за границы массивы
        val startReal = if (start < 0) {
            0
        } else {
            start
        }
        val endReal = if (end > cardNumber.length) {
            cardNumber.length;
        } else {
            end
        }

        return cardNumber.replaceRange(startReal, endReal, maskChar.toString().repeat(endReal - startReal))
    }
}
