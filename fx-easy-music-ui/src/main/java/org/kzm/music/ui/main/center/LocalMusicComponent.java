package org.kzm.music.ui.main.center;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.kzm.music.pojo.LocalMusic;
import org.kzm.music.ui.UIObject;

import java.io.File;

/**
 * 本地音乐面板
 */
public class LocalMusicComponent extends UIObject {

    private Integer localMusicNum = 0;

    private Label musicNumLabel;

    private ImageView flder;
    private ImageView fresh;
    private Stage stage;


    private ObservableList<LocalMusic> data = FXCollections.observableArrayList();

    public LocalMusicComponent(Stage stage) {
        initComponent();
        this.stage = stage;
        initEventDefine();
    }


    @Override
    protected void initComponent() {
        BorderPane borderPane = new BorderPane();
        borderPane.setId("borderPane");
        borderPane.getStylesheets().addAll(getClass().getResource("/fxml/main/css/LocalMusicComponent.css").toExternalForm());


        HBox letHbox = new HBox(10);
        HBox.setMargin(letHbox, new Insets(2, 10, 2, 10));
        letHbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(letHbox, Priority.ALWAYS);
        Label label = new Label("本地音乐");
        label.setFont(Font.font(20));

        musicNumLabel = new Label("共" + localMusicNum + "首");

        letHbox.getChildren().addAll(label, musicNumLabel);

        HBox rightBox = new HBox(10);
        HBox.setMargin(rightBox, new Insets(2, 10, 2, 10));
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        flder = new ImageView(new Image("/fxml/main/img/folder.png", 28, 28, true, true));
        flder.setCursor(Cursor.HAND);
        fresh = new ImageView(new Image("/fxml/main/img/fresh.png", 16, 16, true, true));
        fresh.setCursor(Cursor.HAND);

        rightBox.getChildren().addAll(flder, fresh);

        HBox top = new HBox();
        top.getChildren().addAll(letHbox, rightBox);

        borderPane.setTop(top);

        //表格
        TableView tableView = new TableView();

        TableColumn musicName = new TableColumn("音乐标题");
        musicName.setPrefWidth(400);
        TableColumn musicAuthor = new TableColumn("歌手");
        musicAuthor.setPrefWidth(133);
        TableColumn musicTime = new TableColumn("时长");
        musicTime.setPrefWidth(133);
        TableColumn musicSize = new TableColumn("大小");
        musicSize.setPrefWidth(133);
        tableView.setItems(data);
        tableView.getColumns().addAll(musicName, musicAuthor, musicTime, musicSize);
        borderPane.setCenter(tableView);

        setNode(borderPane);

    }

    @Override
    public void initEventDefine() {
        flder.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DirectoryChooser folderChooser = new DirectoryChooser();
                folderChooser.setTitle("选择文件夹");
                folderChooser.setInitialDirectory(new File(System.getProperty("user.home")));
                File selectFolder = folderChooser.showDialog(stage);
                if (selectFolder != null) {
                    System.out.println(selectFolder.getAbsolutePath());
                }

            }
        });

    }


    /**
     * 获取本地文件夹中所有的音乐
     *
     * @param path
     */
    private static void getLocalMusics(String path) {
        File file = new File(path);
        File[] files = file.listFiles((dir, name) -> {
            if (name.endsWith("wav")||name.endsWith("mp3")||name.endsWith("flac")){
                return true;
            }
            return false;
        });
        for (File music : files) {
            
        }
    }
}
