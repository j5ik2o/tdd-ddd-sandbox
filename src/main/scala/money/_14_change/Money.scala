package money._14_change

object Money {
  def dollar(amount: Int): Money = new Money(amount, "USD")
  def franc(amount: Int): Money  = new Money(amount, "CHF")
}

trait Expression {
  def reduce(bank: Bank, to: String): Money
}

class Money(amt: Int, cur: String) extends Expression {

  def amount: Int      = amt
  def currency: String = cur

  override def reduce(bank: Bank, to: String): Money = {
    val rate = bank.rate(currency, to)
    new Money(amt / rate, to)
  }

  def plus(addend: Expression): Expression = Sum(this, addend)

  def times(mul: Int): Money = new Money(amount * mul, currency)

  override def equals(other: Any): Boolean = {
    val money = other.asInstanceOf[Money]
    amount == money.amount && currency == money.currency
  }

  override def toString = s"$amount $currency"
}
