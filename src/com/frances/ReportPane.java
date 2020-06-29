package com.frances;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ReportPane extends StackPane {
	private Student student;
	Button btnUpdate;
	Button btnCancel;
	TextField tId;
	TextField tFirstname;
	TextField tLastname;
	TextField tAge;
	TextField tJava; 
	TextField tCSharp;
	TextField tPython;
	TextField tPhp;
	TextField tResult;
	TextField tRetakes;
	
	public ReportPane() {
		this.setStyle("-fx-background-color: #FFBBBB;");
		
		Label labTitle = new Label("Edit student report");
		labTitle.setAlignment(Pos.TOP_CENTER);
		StackPane.setMargin(labTitle, new Insets(20, 300, 500, 300));
		this.getChildren().add(labTitle);
		
		// A VBox for vertical layout of the edit fields
		GridPane gridInfo = new GridPane();
		gridInfo.setAlignment(Pos.TOP_LEFT);
		StackPane.setMargin(gridInfo, new Insets(70, 410, 300, 10));
		this.getChildren().add(gridInfo);
		
		GridPane gridCourses = new GridPane();
		gridCourses.setAlignment(Pos.TOP_RIGHT);
		StackPane.setMargin(gridCourses, new Insets(70, 0, 300, 410));
		this.getChildren().add(gridCourses);
		
		GridPane gridResults = new GridPane();
		gridResults.setAlignment(Pos.BOTTOM_CENTER);
		StackPane.setMargin(gridResults, new Insets(300, 200, 100, 200));
		this.getChildren().add(gridResults);
		
		// Edit fields
		gridInfo.add(new Label("Student ID"), 0, 0);
		tId = new TextField();
		tId.setEditable(false);
		gridInfo.add(tId, 1, 0);
		GridPane.setMargin(tId, new Insets(10));
		
		gridInfo.add(new Label("First name"), 0, 1);
		tFirstname = new TextField();
		tFirstname.setEditable(false);
		gridInfo.add(tFirstname, 1, 1);
		GridPane.setMargin(tFirstname, new Insets(10));
		
		gridInfo.add(new Label("Last name"), 0, 2);
		tLastname = new TextField();
		tLastname.setEditable(false);
		gridInfo.add(tLastname, 1, 2);
		GridPane.setMargin(tLastname, new Insets(10));
		
		gridInfo.add(new Label("Age"), 0, 3);
		tAge = new TextField();
		tAge.setEditable(false);
		gridInfo.add(tAge, 1, 3);
		GridPane.setMargin(tAge, new Insets(10));
		
		gridCourses.add(new Label("Java"), 0, 0);
		tJava = new TextField();
		gridCourses.add(tJava, 1, 0);
		GridPane.setMargin(tJava, new Insets(10));
		
		gridCourses.add(new Label("C#"), 0, 1);
		tCSharp = new TextField();
		gridCourses.add(tCSharp, 1, 1);
		GridPane.setMargin(tCSharp, new Insets(10));
		
		gridCourses.add(new Label("Phython"), 0, 2);
		tPython = new TextField();
		gridCourses.add(tPython, 1, 2);
		GridPane.setMargin(tPython, new Insets(10));
		
		gridCourses.add(new Label("PHP"), 0, 3);
		tPhp = new TextField();
		gridCourses.add(tPhp, 1, 3);
		GridPane.setMargin(tPhp, new Insets(10));
		
		gridResults.add(new Label("Result"), 0, 0);
		tResult= new TextField();
		gridResults.add(tResult, 1, 0);
		GridPane.setMargin(tResult, new Insets(10));
		
		gridResults.add(new Label("Retakes"), 0, 1);
		tRetakes= new TextField();
		gridResults.add(tRetakes, 1, 1);
		GridPane.setMargin(tRetakes, new Insets(10));
		
		// Button to confirm add
		btnUpdate = new Button("Update Student");
		this.getChildren().add(btnUpdate);
		StackPane.setAlignment(btnUpdate, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(btnUpdate, new Insets(0,20,20,0));
				
		// Button to cancel the add
		btnCancel = new Button("Cancel");
		this.getChildren().add(btnCancel);
		StackPane.setAlignment(btnCancel, Pos.BOTTOM_LEFT);
		StackPane.setMargin(btnCancel, new Insets(0,0,20,20));
		
		
	}

	public void setStudent(Student student) {
		this.student = student;
		System.out.println("Student set in ReportPane: " + student.getId());
		// Student info
		tId.setText(Integer.toString(student.getId()));
		tFirstname.setText(student.getFirstname());
		tLastname.setText(student.getLastname());
		tAge.setText(Integer.toString(student.getAge()));
		// Grades
		tJava.setText(Integer.toString(student.getGrade(CourseEnum.JAVA)));
		tCSharp.setText(Integer.toString(student.getGrade(CourseEnum.CSHARP)));
		tPython.setText(Integer.toString(student.getGrade(CourseEnum.PYTHON)));
		tPhp.setText(Integer.toString(student.getGrade(CourseEnum.PHP)));
		// Results
		if (student.isPassed()) {
			tResult.setText("Passed");			
		}
		else {
			tResult.setText("Failed");
		}
		tRetakes.setText(Integer.toString(student.getNumberOfRetakes()));
	}
	
	public Student getStudent() {
		student.setGrade(CourseEnum.JAVA, Integer.parseInt(tJava.getText()));
		student.setGrade(CourseEnum.CSHARP, Integer.parseInt(tCSharp.getText()));
		student.setGrade(CourseEnum.PYTHON, Integer.parseInt(tPython.getText()));
		student.setGrade(CourseEnum.PHP, Integer.parseInt(tPhp.getText()));
		return student;
	}
	
	public void setOnOK(EventHandler<ActionEvent> e) {
		this.btnUpdate.setOnAction(e);
	}
	
	public void setOnCancel(EventHandler<ActionEvent> e) {
		this.btnCancel.setOnAction(e);
	}
}
