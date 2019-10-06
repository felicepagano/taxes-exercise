package it.fpagano.salestaxes

case class InvoiceItem(description: String, quantity: Int, totalPrice: BigDecimal, taxesAmount: BigDecimal, imported: Boolean) {

    def toPrintableString = s"$quantity ${if(imported) "imported " else ""}$description: $totalPrice"
    
}