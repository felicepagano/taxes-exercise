package it.fpagano.salestaxes

import org.scalatest.FunSuite

class CashierTest extends FunSuite {

  test("example 1: cashier must generate an invoice with the the right taxes applied") {
    val freeTaxKindGroup = List("Book", "Medicine", "Food")
    val applicableTaxes = List(BaseTax(freeTaxKindGroup), ImportTax)

    val book = Product("Effective Java", 12.49, ProductKind("IT", "Book"), imported = false)
    val cd = Product("Scenes from a memory", 14.99, ProductKind("CD", "Music"), imported = false)
    val food = Product("KitKat", 0.85, ProductKind("Chocolate", "Food"), imported = false)

    val basket = Basket(List(BasketItem(book, 1), BasketItem(cd, 1), BasketItem(food, 1)))

    val cashier = Cashier(basket, applicableTaxes)
    val invoice = cashier.generateInvoice

    val totalAmounts = invoice.calculateTotal
    val total = totalAmounts._1
    val salesTaxes = totalAmounts._2

    assert(total === BigDecimal(29.83))
    assert(salesTaxes === BigDecimal(1.50))
  }

  test("example 2 cashier must generate an invoice with the the right taxes applied") {
    val freeTaxKindGroup = List("Book", "Medicine", "Food")
    val applicableTaxes = List(BaseTax(freeTaxKindGroup), ImportTax)

    val food = Product("Ferrero rocher", 10.00, ProductKind("Chocolate", "Food"), imported = true)
    val perfume = Product("Acqua di gio", 47.50, ProductKind("Perfume", "Beauty"), imported = true)

    val basket = Basket(List(BasketItem(food, 1), BasketItem(perfume, 1)))

    val cashier = Cashier(basket, applicableTaxes)
    val invoice = cashier.generateInvoice

    val totalAmounts = invoice.calculateTotal
    val total = totalAmounts._1
    val salesTaxes = totalAmounts._2

    assert(total === BigDecimal(65.15))
    assert(salesTaxes === BigDecimal(7.65))
  }

  test("example 3 cashier must generate an invoice with the the right taxes applied") {
    val freeTaxKindGroup = List("Book", "Medicine", "Food")
    val applicableTaxes = List(BaseTax(freeTaxKindGroup), ImportTax)

    val importedPerfume = Product("Imported perfume", 27.99, ProductKind("Perfume", "Beauty"), imported = true)
    val perfume = Product("perfume", 18.99, ProductKind("Perfume", "Beauty"), imported = false)
    val aspirina = Product("Aspirina", 9.75, ProductKind("Pills", "Medicine"), imported = false)
    val food = Product("KitKat", 11.25, ProductKind("Chocolate", "Food"), imported = true)

    val basket = Basket(List(BasketItem(importedPerfume, 1), BasketItem(perfume, 1), BasketItem(aspirina, 1), BasketItem(food, 1)))

    val cashier = Cashier(basket, applicableTaxes)
    val invoice = cashier.generateInvoice

    val totalAmounts = invoice.calculateTotal
    val total = totalAmounts._1
    val salesTaxes = totalAmounts._2

    assert(total === BigDecimal(74.68))
    assert(salesTaxes === BigDecimal(6.70))
  }
}
