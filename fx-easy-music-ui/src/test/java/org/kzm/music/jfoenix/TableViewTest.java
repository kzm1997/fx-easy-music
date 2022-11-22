package org.kzm.music.jfoenix;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableViewTest extends BaseTest {
    @Override
    public Node getTest() {
        TableView table=new TableView();
        table.setEditable(false);
        TableColumn firstNameCol = new TableColumn("First Name");
        TableColumn lastNameCol = new TableColumn("Last Name");
        TableColumn addressCol = new TableColumn("Email");


        table.getColumns().addAll(firstNameCol, lastNameCol, addressCol);
        return table;
    }
}
