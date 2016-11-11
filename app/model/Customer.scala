package model

/**
  * Created by lukasz on 09.11.16.
  */
case class Customer(id: Long = 0L,
                    firstName: String,
                    lastName: String,
                    properties1: Option[String] = None,
                    properties2: Option[String] = None,
                    properties3: Option[String] = None,
                    properties4: Option[String] = None,
                    properties5: Option[String] = None,
                    properties6: Option[String] = None,
                    properties7: Option[String] = None,
                    properties8: Option[String] = None,
                    properties9: Option[String] = None,
                    properties10: Option[String] = None,
                    properties11: Option[String] = None,
                    properties12: Option[String] = None,
                    properties13: Option[String] = None,
                    properties14: Option[String] = None,
                    properties15: Option[String] = None,
                    properties16: Option[String] = None,
                    properties17: Option[String] = None,
                    properties18: Option[String] = None,
                    properties19: Option[String] = None
//                    properties20: Option[String] = None,
//                    properties21: Option[String] = None,
//                    properties22: Option[String] = None,
//                    properties23: Option[String] = None,
//                    properties24: Option[String] = None,
//                    properties25: Option[String] = None,
//                    properties26: Option[String] = None
                   ) {

}

object Customer {
  def createForm(cos: (String, String,  Option[String])): Customer =
    Customer(1, cos._1, cos._2, cos._3)

//
//  def extractForm(customer: Customer): Option[(String, String, Option[String])] = customer match {
//    case _ => Some((customer.firstName, customer.lastName, customer.properties1))
//  }

}


case class Address(id: Long = 0L,
                   customerId: Long,
                   street: String = "",
                   houseNr: String = "",
                   aprtmntNr: Option[String],
                   zipCode: String = "",
                   city: String = "",
                   country: Option[String]
                  )



