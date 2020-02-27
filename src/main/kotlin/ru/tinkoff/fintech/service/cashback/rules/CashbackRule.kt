package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo

/**
 * Правило подсчета кешбека.
 */
interface CashbackRule {

    /**
     * Получает на вход модель платежа и возвращает
     */
    fun calculateCashback(transactionInfo: TransactionInfo): Double

    /**
     * Кешбек ссумируется с другими кешбеками
     */
    fun isAddToTotal(): Boolean
}