package Controllers;

import CrawlData.CrawlTrieuDai.TrieuDai;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

import static Application.ReadJSON.readData;

public class SearchSceneController implements Initializable {
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField textField;
    @FXML
    private Button searchButton;
    @FXML
    private TextArea textArea;
    @FXML
    private ListView listView;
    private ObservableList<String> nameSelectedList = FXCollections.observableArrayList();
    private static ObservableList<TrieuDai> trieuDaiList = FXCollections.observableArrayList();
    private static ObservableList<TrieuDai> trieuDaiListName = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trieuDaiList = readData();
        nameSelectedList.clear();
        for(TrieuDai t: trieuDaiList) {
            nameSelectedList.add(t.getTen());
        }
    }
    public void handleRenderListView(ActionEvent event) {
        if(event.getSource() == textField) {
            String searchString = textField.getText();
             textArea.setText(null);
             boolean found = false;
            ObservableList<String> nameList = FXCollections.observableArrayList();
            if(searchString.equals("")) {
                ObservableList nameSelectedList = null;
                listView.setItems(nameSelectedList);
                return;
            }
            for(String str: nameSelectedList) {
                if(str.strip().toLowerCase().startsWith(searchString.strip().toLowerCase()) == true
                    && nameList.contains(str) == false) {
                    nameList.add(str);
                    found = true;
                }
            }
            listView.setItems(null);
            if(found == true) {
                listView.setItems(nameList);
            }
        }
    }
    public void handleRenderTextAre(ActionEvent event) {
        if(event.getSource() == listView) {

        }
    }
//    public void handleChoiceBox(ActionEvent event) {
//        if(event.getSource() == choiceBox) {
//
//        }
//    }
//    public void handleChoiceBox(ActionEvent event) {
//        if(event.getSource() == choiceBox) {
//
//        }
//    }



}
