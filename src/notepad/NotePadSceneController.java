/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Muhammad
 */
public class NotePadSceneController implements Initializable {

    @FXML
    private Menu file;
    @FXML
    private MenuItem New;
    @FXML
    private MenuItem open;
    @FXML
    private MenuItem save;
    @FXML
    private MenuItem close;
    @FXML
    private Menu edit;
    @FXML
    private MenuItem cut;
    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem paste;
    @FXML
    private MenuItem delete;
    @FXML
    private Menu help;
    @FXML
    private MenuItem about;
    @FXML
    private TextArea textArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(NotePad.getStage());
        //check you've selected file to open 
        if(file != null){
            try {
                FileInputStream fis = new FileInputStream(file.getPath());
                //create array of bytes that holds the available text
                int availableTxt = fis.available();
                byte[] myTxtBytes = new byte[availableTxt];
                //read text from fis & put it in byte[]
                fis.read(myTxtBytes);
                textArea.setText(new String(myTxtBytes));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NotePadSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NotePadSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }

    }
    @FXML
    private void handleNew(ActionEvent event) {
        textArea.clear();
    }

    @FXML
    private void handleSave(ActionEvent event) {
        //create file chooser to save your file
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(NotePad.getStage());
        //check you set name to file
        if(file != null){
            //obtain output stream
            FileOutputStream fos = null;
            try {
                //throw your data in fos & write it on the path from chooser
                fos = new FileOutputStream(file.getPath());
                byte[] myTxtBytes = textArea.getText().getBytes();
                fos.write(myTxtBytes);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NotePadSceneController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NotePadSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(NotePadSceneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }

    @FXML
    private void handleCut(ActionEvent event) {
        textArea.cut();
    }

    @FXML
    private void handleCopy(ActionEvent event) {
        String txt = textArea.getSelectedText();
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(txt);
        clipboard.setContent(content);

    }

    @FXML
    private void handlePaste(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        String content = clipboard.getContent(DataFormat.PLAIN_TEXT).toString();
        int caretPosition = textArea.getCaretPosition();
        textArea.insertText(caretPosition, content);
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        IndexRange indexRange = textArea.getSelection();
        textArea.deleteText(indexRange);
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setContentText("My Own NotePad");
        al.show();

    }
    @FXML
    void handleClose(ActionEvent event) {

        NotePad.getStage().close();
    }
    @FXML
    void handleHelp(ActionEvent event) {

    }

    
}
