package ru.tinkoff.fintech.service.cashback.rules

import ru.tinkoff.fintech.model.TransactionInfo
import ru.tinkoff.fintech.service.cashback.LOYALTY_PROGRAM_BEER
import ru.tinkoff.fintech.service.cashback.MCC_BEER
import java.time.LocalDate
import java.time.Month


const val SPECIAL_FIRST_NAME = "олег"
const val SPECIAL_LAST_NAME = "олегов"

/**
 * Если программа лояльности "BEER" и mcc код = 5921 и
 * a. Ваше имя "Олег" (без учета регистра) и фамилия Олегов (без учета регистра) то 10% кешбека
 * b. Ваше имя "Олег" (без учета регистра) то начислить 7% кешбека
 * c. Первая буква текущего месяца (на русском, без учета регистра) равна первой букве firstName в
 * транзакции (на русском, без учета регистра) то 5% кешбека
 * d. Первая буква прошлого или следующего месяца (на русском, без учета регистра) равна первой
 * букве firstName в транзакции (на русском, без учета регистра), то 3% кешбека
 * e.иначе 2% кешбека.
 */
class BeerRule : CashbackRule {

    private val monthWithFirstLetter = mapOf(
        Month.JANUARY.value to 'я',
        Month.FEBRUARY.value to 'ф',
        Month.MARCH.value to 'м',
        Month.APRIL.value to 'а',
        Month.MAY.value to 'м',
        Month.JUNE.value to 'и',
        Month.JULY.value to 'и',
        Month.AUGUST.value to 'а',
        Month.SEPTEMBER.value to 'с',
        Month.OCTOBER.value to 'о',
        Month.NOVEMBER.value to 'н',
        Month.DECEMBER.value to 'д'
    )

    override fun calculateCashback(transactionInfo: TransactionInfo): Double {

        if (transactionInfo.loyaltyProgramName == LOYALTY_PROGRAM_BEER
            && transactionInfo.mccCode == MCC_BEER
        ) return when {
            checkFor10(transactionInfo) -> round(transactionInfo.transactionSum * 0.1)
            checkFor7(transactionInfo) -> round(transactionInfo.transactionSum * 0.07)
            checkFor5(transactionInfo) -> round(transactionInfo.transactionSum * 0.05)
            checkFor3(transactionInfo) -> round(transactionInfo.transactionSum * 0.03)
            else -> round(transactionInfo.transactionSum * 0.02)
        }

        return 0.0
    }

    /**
     * Ваше имя "Олег" (без учета регистра) и фамилия Олегов (без учета регистра) то 10% кешбека
     */
    private fun checkFor10(transactionInfo: TransactionInfo): Boolean {
        return transactionInfo.firstName.toLowerCase() == SPECIAL_FIRST_NAME
                && transactionInfo.lastName.toLowerCase() == SPECIAL_LAST_NAME
    }

    /**
     * Ваше имя "Олег" (без учета регистра) то начислить 7% кешбека
     */
    private fun checkFor7(transactionInfo: TransactionInfo): Boolean {
        return transactionInfo.firstName.toLowerCase() == SPECIAL_FIRST_NAME
    }

    /**
     * Первая буква текущего месяца (на русском, без учета регистра) равна первой букве firstName в
    транзакции (на русском, без учета регистра) то 5% кешбека
     */
    private fun checkFor5(transactionInfo: TransactionInfo): Boolean {
        val currentMonth = monthWithFirstLetter[LocalDate.now().month.value].toString()
        return transactionInfo.firstName.toLowerCase().startsWith(currentMonth)
    }

    /**
     * Первая буква прошлого или следующего месяца (на русском, без учета регистра) равна первой
    букве firstName в транзакции (на русском, без учета регистра), то 3% кешбека
     */
    private fun checkFor3(transactionInfo: TransactionInfo): Boolean {
        val previousMonth = monthWithFirstLetter[LocalDate.now().plusMonths(-1).month.value].toString()
        val nextMonth = monthWithFirstLetter[LocalDate.now().plusMonths(1).month.value].toString()
        return transactionInfo.firstName.toLowerCase().startsWith(previousMonth)
                || transactionInfo.firstName.toLowerCase().startsWith(nextMonth)
    }

}