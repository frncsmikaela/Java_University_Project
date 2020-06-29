package com.frances;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class AddStudentPane extends StackPane {
	Button btnAdd;
	Button btnCancel;
	TextField tId;
	TextField tUsername;
	TextField tPassword;
	TextField tFirstname;
	TextField tLastname; 
	TextField tDateOfBirth;
	TextField tGroup;
	
	public AddStudentPane() {
		setStyle("-fx-background-color: #FFBBCC;");
		
		Label labTitle = new Label("Add student");
		labTitle.setAlignment(Pos.TOP_CENTER);
		StackPane.setMargin(labTitle, new Insets(20, 300, 500, 300));
		this.getChildren().add(labTitle);
		
		// GridPane for vertical layout of the edit fields
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		StackPane.setMargin(grid, new Insets(100, 0, 0, 20));
		this.getChildren().add(grid);
		
		// Edit fields
		grid.add(new Label("ID"), 0, 0);
		tId = new TextField();
		grid.add(tId, 1, 0);
		GridPane.setMargin(tId, new Insets(10));

		grid.add(new Label("User name"), 0, 1);
		tUsername = new TextField();
		grid.add(tUsername, 1, 1);		
		GridPane.setMargin(tUsername, new Insets(10));

		grid.add(new Label("Password"), 0, 2);
		tPassword = new TextField();
		grid.add(tPassword, 1, 2);
		GridPane.setMargin(tPassword, new Insets(10));
		
		grid.add(new Label("First name"), 0, 3);
		tFirstname = new TextField();
		grid.add(tFirstname, 1, 3);
		GridPane.setMargin(tFirstname, new Insets(10));
		
		grid.add(new Label("Last name"), 0, 4);
		tLastname = new TextField();
		grid.add(tLastname, 1, 4);
		GridPane.setMargin(tLastname, new Insets(10));
		
		grid.add(new Label("Date of Birth"), 0, 5);
		tDateOfBirth = new TextField();
		grid.add(tDateOfBirth, 1, 5);
		GridPane.setMargin(tDateOfBirth, new Insets(10));
		
		grid.add(new Label("Group"), 0, 6);
		tGroup = new TextField();
		grid.add(tGroup, 1, 6);
		GridPane.setMargin(tGroup, new Insets(10));
		

		
		// Button to confirm add
		btnAdd = new Button("Add Student");
		this.getChildren().add(btnAdd);
		StackPane.setAlignment(btnAdd, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(btnAdd, new Insets(0,20,20,0));
		
		// Button to cancel the add
		btnCancel = new Button("Cancel");
		this.getChildren().add(btnCancel);
		StackPane.setAlignment(btnCancel, Pos.BOTTOM_LEFT);
		StackPane.setMargin(btnCancel, new Insets(0,0,20,20));
	}
	
	public void setOnOK(EventHandler<ActionEvent> e) {
		this.btnAdd.setOnAction(e);
	}
	
	public void setOnCancel(EventHandler<ActionEvent> e) {
		this.btnCancel.setOnAction(e);
	}

	public Student getStudent() {
		Student res = new Student(
				tUsername.getText(),
				tPassword.getText(),
				Integer.parseInt(tId.getText()),
				tFirstname.getText(),
				tLastname.getText(),
				LocalDate.parse(tDateOfBirth.getText()),
				tGroup.getText()
				);
		return res;
	}
}
