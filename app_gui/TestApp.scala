package app_gui

import scala.swing.SimpleSwingApplication
import scala.swing._
import scala.swing.FileChooser
import scala.swing.event.ButtonClicked
import hr_remover.HR_Remover

object TestApp extends SimpleSwingApplication {
  
  
  
  val remover = new HR_Remover

  val commentary = new Label
  commentary.text = "Choose file to remove hr from"

  val chooseFileButton = new Button("Choose file")
  val fileChosen = new Label
  fileChosen.text = remover.getFileName
  val removeButton = new Button("Convert file")

  val filePicker = new FileChoose()


  this.listenTo(chooseFileButton)
  this.listenTo(removeButton)
  
  this.reactions += {
    case clickEvent: ButtonClicked => 
      if (clickEvent.source == chooseFileButton) {
        filePicker.run()
        remover.setSource(filePicker.file)
        fileChosen.text = remover.getFileName
      }
      if (clickEvent.source == removeButton) {
        val removalMessage = remover.remove()
        Dialog.showMessage(appWindow, removalMessage, "Done")
      }
  }

  val verticalPanel = new BoxPanel(Orientation.Vertical)
  verticalPanel.contents += commentary
  verticalPanel.contents += chooseFileButton
  verticalPanel.contents += fileChosen
  verticalPanel.contents += removeButton

  val appWindow = new MainFrame
  appWindow.centerOnScreen()
  appWindow.preferredSize = new Dimension(400, 200)
  appWindow.title = "HR Remover"
  appWindow.contents = verticalPanel

  def top = this.appWindow
  
}
