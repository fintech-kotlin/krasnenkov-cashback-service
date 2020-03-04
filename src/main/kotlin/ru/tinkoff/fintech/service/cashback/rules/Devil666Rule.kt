package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo

/**
 * Если сумма транзакции 666 или кратна 666, то дополнительно начислить 6.66
 */
class Devil666Rule : CashbackRule {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.transactionSum.rem(666) == 0.0) {
            return 6.66
        }
        return 0.0
    }

    override val isAddToTotal = true
}