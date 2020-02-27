package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo

class Devil666Rule : CashbackRule {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.transactionSum == 666.0) {
            return 6.66
        }
        return 0.0
    }

    override fun isAddToTotal(): Boolean {
        return true
    }


}