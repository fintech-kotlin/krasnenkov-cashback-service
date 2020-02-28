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
     * Кешбек сумируется с другими кешбеками
     */
    fun isAddToTotal(): Boolean


    /**
     * Округление до двух знаков после запятой (копейки).
     */
    fun round(value:Double):Double{
        return (value * 100).roundToInt() / 100.0
    }
}