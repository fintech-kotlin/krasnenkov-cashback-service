package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_BLACK

/**
 * Если программа лояльности "BLACK", начислить 1% кэшбека
 */
class BlackRule : CashbackRule {

    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        return if (transactionInfo.loyaltyProgramName == LOYALTY_PROGRAM_BLACK)
            (transactionInfo.transactionSum * 0.01)
        else 0.0
    }

    override fun isAddToTotal(): Boolean {
        return false
    }
}