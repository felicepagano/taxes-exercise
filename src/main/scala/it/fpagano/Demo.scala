package it.fpagano

import it.fpagano.salestaxes._

object Demo extends App {
    val freeTaxKindGroup = List("Book", "Medicine", "Food")
    val applicableTaxes = List(BaseTax(freeTaxKindGroup), ImportTax)

    val importedPerfume = Product("Imported perfume", 27.99, ProductKind("Perfume", "Beauty"), imported = true)
    val perfume = Product("perfume", 18.99, ProductKind("Perfume", "Beauty"), imported = false)
    val aspirina = Product("Aspirina", 9.75, ProductKind("Pills", "Medicine"), imported = false)
    val food = Product("KitKat", 11.25, ProductKind("Chocolate", "Food"), imported = true)

    val basket = Basket(List(BasketItem(importedPerfume, 1), BasketItem(perfume, 1), BasketItem(aspirina, 1), BasketItem(food, 1)))

    val cashier = Cashier(basket, applicableTaxes)
    val invoice = cashier.generateInvoice

    println(invoice.toPrintableString)
}