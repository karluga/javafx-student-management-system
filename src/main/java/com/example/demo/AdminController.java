package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;

public class AdminController {

    @FXML
    private TableView<Student> studentTable;

    @FXML
    private TableColumn<Student, String> idColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, String> courseColumn;

    @FXML
    private TableColumn<Student, Double> gradeColumn;

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private ComboBox<String> courseDropdown;  // Add dropdown for course selection

    private StudentManager studentManager;
    private CourseManager courseManager;

    @FXML
    public void initialize() {
        studentManager = new StudentManager();
        courseManager = new CourseManager();

        // Populate ComboBox with courses
        courseDropdown.getItems().addAll(courseManager.getCourses());

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("averageGrade"));

        studentTable.getItems().addAll(studentManager.getStudents());
    }

    @FXML
    protected void onAddStudentClick() {
        String name = nameField.getText();
        String id = idField.getText();
        String course = courseDropdown.getSelectionModel().getSelectedItem();  // Get selected course
        if (name.isEmpty() || id.isEmpty() || course == null) {
            // Ensure all fields are filled
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Information");
            alert.setHeaderText("All fields must be filled.");
            alert.showAndWait();
            return;
        }

        // Create and add student
        Student student = new Student(name, id, course, new HashMap<>());
        studentManager.addStudent(student);
        studentTable.getItems().add(student);

        // Clear fields after adding
        nameField.clear();
        idField.clear();
        courseDropdown.getSelectionModel().clearSelection();
    }

    @FXML
    protected void onDeleteStudentClick() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentManager.deleteStudent(selectedStudent.getId());
            studentTable.getItems().remove(selectedStudent);
        }
    }
}
