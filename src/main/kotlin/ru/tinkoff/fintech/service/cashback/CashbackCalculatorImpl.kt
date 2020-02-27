package ru.tinkoff.fintech.service.cashback

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.rules.*

internal const val LOYALTY_PROGRAM_BLACK = "BLACK"
internal const val LOYALTY_PROGRAM_ALL = "ALL"
internal const val LOYALTY_PROGRAM_BEER = "BEER"
internal const val MAX_CASH_BACK = 3000.0
internal const val MCC_SOFTWARE = 5734
internal const val MCC_BEER = 5921

class CashbackCalculatorImpl : CashbackCalculator {


    /**
     * Логика расчета кешбека.
     */
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        var maxRuleCashback = 0.0
        var addToTotalCashback = 0.0

        for (rule in buildRules()) {
            if (rule.isAddToTotal()) {
                addToTotalCashback += rule.calculateCashback(transactionInfo)
            } else {
                val cashback = rule.calculateCashback(transactionInfo)
                if (cashback > maxRuleCashback) {
                    maxRuleCashback = cashback
                }
            }

        }

        maxRuleCashback += addToTotalCashback

        return if (maxRuleCashback > MAX_CASH_BACK) MAX_CASH_BACK else maxRuleCashback
    }

    fun buildRules(): List<CashbackRule> {
        val r1 = BlackRule()
        val r2 = Devil666Rule()
        val r3 = AllComputerRule()
        val r4 = BeerRule()

        return listOf(r1, r2, r3, r4)
    }


}