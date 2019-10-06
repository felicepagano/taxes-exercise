package it.fpagano.salestaxes

import org.scalatest.FunSuite

class InvoiceTest extends FunSuite {

  test("invoice must sum the total amount and the total taxes for the products in invoice item list") {

    val invoiceItems = List(InvoiceItem("Test", 1, 100, 10, imported = false), InvoiceItem("Test", 2, 200, 20, imported = false))
    val invoice: Invoice = Invoice(invoiceItems)
    val totalAmountWithTaxes = invoice.calculateTotal._1
    val totalTaxesAmount = invoice.calculateTotal._2

    assert(totalAmountWithTaxes === BigDecimal(300))
    assert(totalTaxesAmount === BigDecimal(30))
  }
}