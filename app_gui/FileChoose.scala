package app_gui
import javax.swing.JFileChooser

class FileChoose {
  
  private var selectedFile = ""
  
  def file = this.selectedFile
  
  def run() {
    var chooser = new JFileChooser();
    chooser.setCurrentDirectory(new java.io.File("~"));
    chooser.setDialogTitle("Select GPX file");
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    chooser.setAcceptAllFileFilterUsed(false);
    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      this.selectedFile = chooser.getSelectedFile().toString()
    } else {
      System.out.println("No Selection ");
    }
  }
}