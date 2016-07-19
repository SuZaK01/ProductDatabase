package controllers

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.inject.Inject

import org.krysalis.barcode4j.impl.upcean.EAN13Bean
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}

/**
  * Created by lukasz on 19.07.16.
  */
class Barcode @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {
  val ImageResolution = 144

  def id13BarCode(id: Long, MimeType: String): Array[Byte] = {
    val output = new ByteArrayOutputStream
    val canvas: BitmapCanvasProvider = new BitmapCanvasProvider(
      output, MimeType, ImageResolution, BufferedImage.TYPE_BYTE_BINARY, true, 0
    )
    val barcode = new EAN13Bean
    barcode.generateBarcode(canvas, String.valueOf(id))
    canvas.finish()

    output.toByteArray
  }

  def barcode(id: Long) = Action {
    val MimeType = "image/png"
    try {
      val imageData = id13BarCode(id, MimeType)
      Ok(imageData).as(MimeType)
    } catch {
      case e: IllegalArgumentException =>
        BadRequest("Couldn't generate barcode. Error " + e.getMessage)
    }
  }
}
