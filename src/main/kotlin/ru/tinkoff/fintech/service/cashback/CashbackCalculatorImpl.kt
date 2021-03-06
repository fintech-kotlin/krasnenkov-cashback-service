package ru.tinkoff.fintech.service.cashback

import org.springframework.stereotype.Component
import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.rules.*
import org.springframework.stereotype.Service

internal const val LOYALTY_PROGRAM_BLACK = "BLACK"
internal const val LOYALTY_PROGRAM_ALL = "ALL"
internal const val LOYALTY_PROGRAM_BEER = "BEER"
internal const val MAX_CASH_BACK = 3000.0
internal const val MCC_SOFTWARE = 5734
internal const val MCC_BEER = 5921

@Service
class CashbackCalculatorImpl : CashbackCalculator {

    /**
     * Список правил начисления кешбека
     */
    private val rules = mutableListOf(
        BlackRule(),
        Devil666Rule(),
        AllComputerRule(),
        AllComputerRule(),
        BeerRule()
    )


    /**
     * Логика расчета кешбека.
     * Считается максиальный кешбек среди всех правил.
     * Если правило возвращает кешбек, который суммируется с максимальным, то суммируем.
     */
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        var addToTotalCashback = 0.0
        var totalCashback = 0.0

        for (rule in rules) {
            val cashback = rule.calculateCashback(transactionInfo)

            if (rule.isAddToTotal) {
                addToTotalCashback += cashback
            } else if (cashback > totalCashback) {
                totalCashback = cashback
            }

        }
        totalCashback += addToTotalCashback

        //Расчитанный кешбек не должен превышать максимальный за период
        if (totalCashback + transactionInfo.cashbackTotalValue > MAX_CASH_BACK) {
            totalCashback = MAX_CASH_BACK - transactionInfo.cashbackTotalValue
        }

        return totalCashback
    }
}
