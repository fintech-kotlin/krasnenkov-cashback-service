package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_ALL
import ru.tinkoff.fintech.service.cashback.MCC_SOFTWARE

/**
 * Если mcc код = 5734 ("Продажа компьютерного программного обеспечения") и программа
 * лояльности "ALL" и сумма операции в копейках с заменой не более 1 цифры является палиндромом, то
 * зачислить вознаграждение в размере НОК ( длина имени, длина фамилии) / 1000 %.
 */
class AllComputerRule : CashbackRule {

    override fun calculateCashback(transactionInfo: TransactionInfo): Double {
        if (transactionInfo.mccCode == MCC_SOFTWARE && transactionInfo.loyaltyProgramName == LOYALTY_PROGRAM_ALL
            && isPartialPalindrom(transactionInfo.transactionSum)
        ) {
            return calculateNOKReward(transactionInfo)
        }
        return 0.0
    }


    /**
     * сумма операции в копейках с заменой не более 1 цифры является палиндромом
     */
    private fun isPartialPalindrom(sum: Double): Boolean {

        val penny = (sum * 100).toString()
        val penny2 = penny.substring(0, penny.indexOf("."))

        var i = 0
        var numOfEqualChars = 0
        val t = penny2.length / 2

        while (i < t) {
            if (penny2[i] == penny2[penny2.length - i - 1]) {
                numOfEqualChars++
            }
            i++
        }
        return numOfEqualChars >= t - 1
    }

    /**
     * НОК( длина имени, длина фамилии) / 1000 %
     */
    private fun calculateNOKReward(transactionInfo: TransactionInfo): Double {
        val percent = nok(transactionInfo.firstName.length, transactionInfo.lastName.length) / 1000.0
        return round(transactionInfo.transactionSum * percent / 100.0)
    }


    //НОД
    private fun nod(a: Int, b: Int): Int {
        return if (b == 0) a else nod(b, a % b)
    }

    //НОК
    private fun nok(a: Int, b: Int): Int {
        return a / nod(a, b) * b
    }

    override fun isAddToTotal(): Boolean {
        return false
    }
}