package hr_remover

import scala.xml.XML
import scala.xml.transform._
import scala.xml._

class HR_Remover {

  private var file = "File not chosen yet"
  
  def getFileName = file.reverse.takeWhile(_ != '/').reverse
  
  def setSource(source: String) = {
    this.file = source
  }

  def remove(): String = {
    var successMessage = ""
    try {
      val xml = XML.loadFile(file)

      val removeHR = new RewriteRule {
        override def transform(n: Node): NodeSeq = n match {
          case e: Elem if (e \\ "hr").text != "" => NodeSeq.Empty
          case n                                 => n
        }
      }

      val output = new RuleTransformer(removeHR)(xml)

      XML.save(file, output, "UTF-8", true, null)
      successMessage = "Your file has been converted"
    } catch {
      case e: Exception => successMessage = "Please choose a valid file"
    }
    successMessage
  }
}
