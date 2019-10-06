package it.fpagano.salestaxes

import org.scalatest.FunSuite

class TaxTest extends FunSuite {

  test("A product not in tax free list must be extra charged by 10% rounded") {
    val freeTaxKindGroup = List("Book", "Medicine", "Food")
    val product = Product("bottle of perfume", 27.99, ProductKind("Perfume", "Cosmetics"), imported = true)
    val taxAmount = BaseTax(freeTaxKindGroup)(product)

    assert(taxAmount === BigDecimal(2.8))
  }

  test("A product in tax free list must not be extra charged") {
    val freeTaxKindGroup = List("Book", "Medicine", "Food")
    val product = Product("Effective java", 27.99, ProductKind("IT", "Book"), imported = true)
    val taxAmount = BaseTax(freeTaxKindGroup)(product)

    assert(taxAmount === BigDecimal(0))
  }

  test("Imported product must be extra charged by 5%") {
    val product = Product("Effective java", 27.99, ProductKind("IT", "Book"), imported = true)

    val taxAmount = ImportTax(product)

    assert(taxAmount === BigDecimal(1.4))
  }

  test("Not imported product must not be extra charged by 5%") {
    val product = Product("Effective java", 27.99, ProductKind("IT", "Book"), imported = false)

    val taxAmount = ImportTax(product)

    assert(taxAmount === BigDecimal(0))
  }
}
