package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private ListView<String> courseListView;
    @FXML
    private MenuBar menuBar;

    private StudentManager studentManager;

    @FXML
    public void initialize() {
        studentManager = new StudentManager();
        for (Student student : studentManager.getStudents()) {
            if (!courseListView.getItems().contains(student.getCourse())) {
                courseListView.getItems().add(student.getCourse());
            }
        }
    }

    @FXML
    protected void onLoginMenuClick() throws IOException {
        // Load login view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
        Scene loginScene = new Scene(fxmlLoader.load(), 400, 300);
        Stage stage = (Stage) menuBar.getScene().getWindow();
        stage.setScene(loginScene);
    }
}
