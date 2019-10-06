package it.fpagano.salestaxes

case class Cashier(basket: Basket, taxes: List[Tax]) {

  def generateInvoice: Invoice = Invoice(basket.items.map(item => {
    val productTaxes = calculateTaxes(item.product)
    InvoiceItem(item.product.description, item.quantity, (item.product.amount + productTaxes) * item.quantity, productTaxes, item.product.imported)
  }))

  private def calculateTaxes(product: Product): BigDecimal = taxes.map(tax => tax(product)).sum
}
