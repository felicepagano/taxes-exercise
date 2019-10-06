package it.fpagano.salestaxes

import java.math.{MathContext, RoundingMode}

sealed trait Tax {
    def apply(product: Product): BigDecimal
    def round(value: BigDecimal): BigDecimal = ((value * 20).round(new MathContext(2, RoundingMode.UP))  / 20).setScale(2)
    protected def applyPercentage(value: BigDecimal, factor: Int): BigDecimal = if(value == 0) 0 else round((value / 100) * factor)
}

case class BaseTax(excludedCategories: List[String]) extends Tax {
    override def apply(product: Product): BigDecimal = if(excludedCategories.contains(product.kind.group)) 0 else applyPercentage(product.amount, factor = 10)
}

case object ImportTax extends Tax {
    override def apply(product: Product): BigDecimal = if(product.imported) applyPercentage(product.amount, 5) else 0
}