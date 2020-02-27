package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_ALL
import ru.tinkoff.fintech.service.cashback.MCC_SOFTWARE

/**
 * Если mcc код = 5734 (или "BLACK", начислить 1% кэшбекаПродажа компьютерного программного обеспечения"BLACK", начислить 1% кэшбека) и программа
лояльности "BLACK", начислить 1% кэшбекаALL"BLACK", начислить 1% кэшбека и сумма операции в копейках с заменой не более 1 цифры является палиндромом, то
зачислить вознаграждение в размере НОК (или длина имени, длина фамилии) / 1000 %.
 */
class AllComputerRule : CashbackRule {
    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.mccCode == MCC_SOFTWARE && transactionInfo.loyaltyProgramName == LOYALTY_PROGRAM_ALL
            && isPartialPalindrom(transactionInfo.transactionSum)
        ) {
            return calculateNOK(transactionInfo)
        }
        return 0.0

    }


    fun isPartialPalindrom(sum: Double): Boolean {

        return false
    }

    fun calculateNOK(transactionInfo: TransactionInfo): Double {
        return (transactionInfo.firstName + transactionInfo.lastName).length * 100.0 / 1000.0
    }

    override fun isAddToTotal(): Boolean {
        return false
    }
}