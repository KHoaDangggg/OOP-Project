package Controllers;

import CrawlData.CrawlTrieuDai.TrieuDai;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        trieuDaiList = readData();
        nameSelectedList.clear();
        for(TrieuDai t: trieuDaiList) {
            nameSelectedList.add(t.getTen());
        }
        textField.textProperty().addListener((obs, oldText, newText) -> {
            handleRenderListView();
        });
        handleRenderTextArea();
    }
    public void handleRenderListView() {
        String searchString = textField.getText();
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
    public void handleRenderTextArea() {
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TrieuDai selectedTrieuDai = null;
                String tenTrieuDai = (String) listView.getSelectionModel().getSelectedItem();
                if(tenTrieuDai != null) {
                    for(TrieuDai t: trieuDaiList) {
                        if(tenTrieuDai.equals(t.getTen())) {
                            selectedTrieuDai = t;
                        }
                    }
                    String vua = "";
                    if (selectedTrieuDai.getKings().size() == 0 ) {

                    }
                    textArea.setText("Tên: " + selectedTrieuDai.getTen() + "\n" +
                            "Năm bắt đầu - kết thúc: " + selectedTrieuDai.getNamBatDau() + " - " + selectedTrieuDai.getNamKetThuc() + "\n" +
                            "Quốc hiệu: : " + selectedTrieuDai.getKinhDo() + "\n" +
                            "Kinh đô: " + selectedTrieuDai.getKinhDo() + "\n" +
                            "Vua: " + selectedTrieuDai.getKings() + "\n" +
                            "Mô tả: : " + selectedTrieuDai.getMoTa() + "\n" 
                    );
                }
            }
        });
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
