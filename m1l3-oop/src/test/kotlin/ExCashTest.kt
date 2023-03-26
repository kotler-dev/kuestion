import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*
import kotlin.test.Test

class ExCashTest(
    val amount: BigDecimal,
    val currency: Currency
) {
    constructor(
        amount: String,
        currency: Currency
    ) : this(BigDecimal(amount), currency)

    fun format(locale: Locale) : String {
        val formatter = NumberFormat.getCurrencyInstance(locale)
        formatter.currency = currency
        return formatter.format(amount)
    }

    operator fun minus(other: ExCashTest) : ExCashTest {
        require(amount >= BigDecimal.ZERO) {
            "Amount should be non negative"
        }
        return ExCashTest(amount - other.amount, currency)
    }

    override fun toString(): String {
        return "ExCashTest(amount=$amount, currency=$currency)"
    }
}

class CashTest {
    @Test
    fun test() {
        val a = ExCashTest("10", Currency.getInstance("USD"))
        val b = ExCashTest("20", Currency.getInstance("USD"))
        val c = b - a

        println(c.amount)
        println(a)
        println(c.format(Locale.ENGLISH))
    }
}