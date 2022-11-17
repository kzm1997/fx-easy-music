package org.kzm.music.jfoenix;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class ListViewTest extends BaseTest {
    @Override
    public Node getTest() {
        ObservableList<String> strings = FXCollections.observableArrayList("红色", "黄色", "绿色");
        ListView<String> listView=new ListView<>(strings);
        listView.setItems(strings);
        listView.setPrefSize(400,200);
        return listView;
    }
}
