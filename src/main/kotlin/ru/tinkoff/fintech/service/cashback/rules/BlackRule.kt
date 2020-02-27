package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_BLACK

/**
 * Если программа лояльности "BLACK", начислить 1% кэшбекаBLACK"BLACK", начислить 1% кэшбека, начислить 1% кэшбека
 */
class BlackRule : CashbackRule {

    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.loyaltyProgramName.equals(LOYALTY_PROGRAM_BLACK)) {
            return transactionInfo.transactionSum * 0.01
        } else {
            return 0.0
        }
    }

    override fun isAddToTotal(): Boolean {
        return false
    }
}