package model


/**
  * Created by lukasz on 19.07.16.
  */
case class Product(ean: Long=0L,
                   name: String,
                   desc: String) {

}


object Product {
/*  def findByEan(ean: Long) = products.find(_.ean == ean)

  def add(product: Product): Unit = {
    products = products + product
  }

  var products = Set(
    Product(5010255079763L, "Paperclips Large",
      "Large Plain Pack of 1000"),
    Product(5018206244666L, "Giant Paperclips",
      "Giant Plain 51mm 100 pack"),
    Product(5018306332812L, "Paperclip Giant Plain",
      "Giant Plain Pack of 10000"),
    Product(5018306312913L, "No Tear Paper Clip",
      "No Tear Extra Large Pack of 1000"),
    Product(5018206244611L, "Zebra Paperclips",
      "Zebra Length 28mm Assorted 150 Pack")
  )

  def findAll = products.toList.sortBy(_.ean)*/

  def tupled = (Product.apply _).tupled
}


case class Warehouse(id: Long, name: String)

object Warehouse {}


case class StockItem(id: Long, productId: Long, warehouseId: Long, quantity: Long)

