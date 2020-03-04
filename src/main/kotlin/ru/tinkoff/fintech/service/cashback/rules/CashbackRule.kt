package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo
import kotlin.math.roundToInt

/**
 * Правило подсчета кешбека.
 */
interface CashbackRule {

    /**
     * Получает на вход модель платежа и возвращает
     */
    fun calculateCashback(transactionInfo: TransactionInfo): Double

    /**
     * Свойство определяющее, что кешбек правила суммируется с кешбеком от других правил.
     */
    val isAddToTotal: Boolean
        get() = false

    /**
     * Округление до двух знаков после запятой (копейки).
     */
    fun round(value:Double):Double{
        return (value * 100).roundToInt() / 100.0
    }
}