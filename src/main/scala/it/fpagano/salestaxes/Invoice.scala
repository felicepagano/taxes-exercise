package it.fpagano.salestaxes

case class Invoice(invoiceItems: List[InvoiceItem]) {

    def calculateTotal: (BigDecimal, BigDecimal) = invoiceItems
      .map(item => (item.totalPrice, item.taxesAmount))
      .foldLeft((BigDecimal(0), BigDecimal(0))) { case ((accA, accB), (a, b)) => (accA + a, accB + b) }

    def toPrintableString: String = {
        val totals = calculateTotal
        s"${invoiceItems.map(_.toPrintableString).mkString("\n")}\nSales Taxes: ${totals._2}\nTotal: ${totals._1}"
    }
}