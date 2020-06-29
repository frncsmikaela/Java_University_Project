package com.frances;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

public class ListPane extends StackPane {
	Label lblTitle;
	TableView<UserItem> userList;
	TableColumn<UserItem, String> colGroupOrSalary;
	
	public ListPane() {

		// Create title (label)
		lblTitle = new Label();
		this.getChildren().add(lblTitle);
		StackPane.setAlignment(lblTitle, Pos.TOP_LEFT);
		StackPane.setMargin(lblTitle, new Insets(10,0,0,20));
		
		// Create a table view to display the items (users)
		userList = new TableView<UserItem>();
		StackPane.setMargin(userList,  new Insets(50,20,20,20));
		// Define the columns and types
		TableColumn<UserItem, String> colID = new TableColumn<UserItem, String>("ID");
		colID.setCellValueFactory(new PropertyValueFactory<UserItem,String>("id"));		
		TableColumn<UserItem, String> colFirstName = new TableColumn<UserItem, String>("First Name");
		colFirstName.setCellValueFactory(new PropertyValueFactory<UserItem,String>("firstName"));
		TableColumn<UserItem, String> colLastName = new TableColumn<UserItem, String>("Last Name");
		colLastName.setCellValueFactory(new PropertyValueFactory<UserItem,String>("lastName"));
		TableColumn<UserItem, String> colBirthDate = new TableColumn<UserItem, String>("Birth Date");
		colBirthDate.setCellValueFactory(new PropertyValueFactory<UserItem,String>("birthDate"));
		TableColumn<UserItem, String> colAge = new TableColumn<UserItem, String>("Age");
		colAge.setCellValueFactory(new PropertyValueFactory<UserItem,String>("age"));
		colGroupOrSalary = new TableColumn<UserItem, String>("");
		colGroupOrSalary.setCellValueFactory(new PropertyValueFactory<UserItem,String>("groupOrSalary"));

		userList.getColumns().addAll(colID, colFirstName, colLastName, colBirthDate, colAge,colGroupOrSalary);
		
		this.getChildren().add(userList);
		
	}
	
	public void setItems(ArrayList<UserItem> lst) {
		ObservableList<UserItem> data = FXCollections.observableArrayList(lst);
		userList.setItems(data);
	}
	
	public void setOnUserSelected(InvalidationListener e) {
		userList.getSelectionModel().selectedIndexProperty().addListener(e);
	}
	
	public UserItem getSelectedUser() {
		return userList.getSelectionModel().selectedItemProperty().get();
	}
	
	public void setTitle(String string) {
		// Set the text of the title label to the specified string
		lblTitle.setText(string);
		
	}

	public void setGroupOrSalary(String string) {
		colGroupOrSalary.setText(string);
		
	}
}

