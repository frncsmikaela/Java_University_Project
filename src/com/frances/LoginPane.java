package com.frances;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class LoginPane extends StackPane {
	TextField tUsername;
	TextField tPassword;
	Button btnLogin;
	
	public LoginPane() {
		this.setStyle("-fx-background-color: #bfe6ff;");
		
		// A VBox for vertical layout of the edit fields
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		StackPane.setMargin(grid, new Insets(0, 0, 0, 0));
		this.getChildren().add(grid);
		
		// For the purpose of testin: include hints for logging in
		TextArea tHint = new TextArea(
				"Cheat sheet to log in, you can use one of these:\n" +
				"STUDENT U: john05@hotmail.com  P: john05\n" + 
				"TEACHER U: james03@hotmail.com P: james03\n" +
				"MANAGER U: peter05@gmail.com   P: peter05");
		tHint.setEditable(false);
		StackPane.setAlignment(tHint, Pos.TOP_CENTER);
		StackPane.setMargin(tHint, new Insets(20, 100, 450, 100));
		this.getChildren().add(tHint);		
		
		// Login fields
		grid.add(new Label("Username"), 0, 0);
		tUsername = new TextField();
		grid.add(tUsername, 1, 0);
		GridPane.setMargin(tUsername, new Insets(10));
		
		grid.add(new Label("Password"), 0, 1);
		tPassword = new TextField();
		grid.add(tPassword, 1, 1);
		GridPane.setMargin(tPassword, new Insets(10));
		
		// Button to confirm login
		btnLogin = new Button("Login");
		this.getChildren().add(btnLogin);
		StackPane.setAlignment(btnLogin, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(btnLogin, new Insets(0,80,20,0));
		
	}
	public void setOnOK(EventHandler<ActionEvent> e) {
		this.btnLogin.setOnAction(e);
	}
	
	public String getUsername() {
		return tUsername.getText().trim();
	}
	public String getPassword() {
		return tPassword.getText().trim();
	}
	
}
