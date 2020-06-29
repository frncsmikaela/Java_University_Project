package com.frances;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;



public class Main extends Application {
	LoginPane login;
	ListPane listScreen;
	AddStudentPane addStudentScreen;
	ReportPane editReportScreen;
	Alert alertReport;
	UserDB userDB;
	User currentUser;
	Alert alertNoAccess;
	// These menu parts need to be accessible for enable/disable
	Menu generateMenu;
	Menu addMenu;
	MenuItem reportListMenu;
	Menu displayListMenu;
	MenuItem logout;
	Alert alertCloseWindow;
	Label labelAlert;
	Button btnClose;
	Button btnCancel;
	ScreenMode screenMode;
	
	@Override
	public void start(Stage primaryStage) {
		//Create an instance of database
		userDB = new UserDB();
		
		VBox vb = new VBox();
		
		//The main menu for my application
		final Menu fileMenu = new Menu("File");
		displayListMenu = new Menu("Display List");
		addMenu = new Menu("Add");
		generateMenu = new Menu("Generate");
		
		logout = new MenuItem("LogOut");
		MenuItem exit = new MenuItem("Exit");
		MenuItem studentsListMenu = new MenuItem("Students");
		MenuItem teachersListMenu = new MenuItem("Teachers");
		reportListMenu = new MenuItem("Reports");
		MenuItem addStudentsMenu = new MenuItem("Students");
		MenuItem saveReports = new MenuItem("Student Reports");
		
		MenuBar mb = new MenuBar();
		mb.getMenus().add(fileMenu);
		mb.getMenus().add(displayListMenu);
		mb.getMenus().add(addMenu);
		mb.getMenus().add(generateMenu);
		
		fileMenu.getItems().add(logout);
		fileMenu.getItems().add(exit);
		displayListMenu.getItems().add(studentsListMenu);
		displayListMenu.getItems().add(teachersListMenu);
		displayListMenu.getItems().add(reportListMenu);
		addMenu.getItems().add(addStudentsMenu);
		generateMenu.getItems().add(saveReports);
		
		vb.getChildren().add(mb);
		
		//TO DO: Continue setting the actions per menu item 
		addStudentsMenu.setOnAction(e -> { selectScreen(ScreenMode.AddStudent); });
		studentsListMenu.setOnAction(e -> { selectScreen(ScreenMode.StudentList); });
		teachersListMenu.setOnAction(e -> { selectScreen(ScreenMode.TeacherList); });
		reportListMenu.setOnAction(e -> { selectScreen(ScreenMode.StudentReportList); });
		saveReports.setOnAction(e -> {
			generateReports();
			alertReport.show();
			});
		logout.setOnAction(e -> { selectScreen(ScreenMode.Login); });
		exit.setOnAction(e -> { System.exit(0); });
		
		//POP-UP WINDOW
		Stage popupWindow = new Stage();
		popupWindow.initModality(Modality.APPLICATION_MODAL);
		popupWindow.setTitle("INFO");
		      
		labelAlert= new Label("Close the window?");
		btnClose= new Button("OK");
		btnCancel = new Button("Cancel");
		
		VBox layout= new VBox(10);
	     
		layout.getChildren().addAll(labelAlert, btnClose, btnCancel);      
		layout.setAlignment(Pos.CENTER);    
		Scene scenePopUp= new Scene(layout, 300, 250);     
		popupWindow.setScene(scenePopUp);
		
		primaryStage.setOnCloseRequest(e -> {
			popupWindow.showAndWait();
		});
		
		btnClose.setOnAction(e -> { System.exit(0); });
		btnCancel.setOnAction(e -> { popupWindow.hide();});
		
		//Main content panel is added here
		StackPane contentArea = new StackPane();
		vb.getChildren().add(contentArea);
		contentArea.setStyle("-fx-background-color: #dddddd;");
		contentArea.setPrefHeight(600);
		
		//Log-in screen
		login = new LoginPane();
		//alertNoAccess = new Alert(AlertType.ERROR, "Invalid Login", ButtonType.OK);
		contentArea.getChildren().add(login);
		login.setOnOK(m -> {
			String uname = login.getUsername();
			String pwd = login.getPassword();
			currentUser = userDB.checkUserNamePwd(uname, pwd);
			if(currentUser != null) {
				// Enable/disable correct options in the menu bar
				updateMenuState();
				selectScreen(ScreenMode.StudentList);
			}
			
			else {
				alertNoAccess.show();
			}
			
		});
		
		//List screen 
		listScreen = new ListPane();
		contentArea.getChildren().add(listScreen);
		//Testing hiding the component
		//listScreen.setVisible(false);
		// Add event handler for detecting user click on row
		listScreen.setOnUserSelected((num) -> {
			// List should pull up the EditRepor only if screen mode is StudentReportList
			if (screenMode == ScreenMode.StudentReportList) {
				UserItem user = listScreen.getSelectedUser(); 
				if (user != null) {
					//System.out.println("Selected " + user.getId());
					// Show the form to edit the report
					// Find student that matches this user
					int id = Integer.parseInt(user.getId());
					Student s = (Student)(userDB.findUser(id));
					editReportScreen.setStudent(s);
					selectScreen(ScreenMode.EditReport);									
				}
			}
		});
		
		//Add student screen
		addStudentScreen = new AddStudentPane();
		contentArea.getChildren().add(addStudentScreen);
		addStudentScreen.setOnOK(m -> {
			Student s = addStudentScreen.getStudent();
			userDB.insertStudent(s);
			selectScreen(ScreenMode.StudentList);
		});
		addStudentScreen.setOnCancel(m -> {
			selectScreen(ScreenMode.StudentList);
		});
		
		//Add Display report screen
		editReportScreen = new ReportPane();
		contentArea.getChildren().add(editReportScreen);
		editReportScreen.setOnOK(m -> {
			Student s = editReportScreen.getStudent();
			userDB.updateStudent(s);
			selectScreen(ScreenMode.StudentReportList);
		});
		editReportScreen.setOnCancel(m -> {
			selectScreen(ScreenMode.StudentReportList);
		});
		
		alertReport = new Alert(AlertType.INFORMATION, "Reports have been generated", ButtonType.OK);
			
		Scene scene = new Scene(vb, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		selectScreen(ScreenMode.Login);
	}
	
	private void generateReports() {
		ArrayList<Student> students = userDB.getStudents();
		try {
			for (Student s : students) {
				FileWriter writer = new FileWriter(
						Integer.toString(s.getId()) + " " + s.getFirstname() + " " + s.getLastname() + ".txt", false);
				writer.write("Report of student: " + s.getFirstname() + " " + s.getLastname() + "\r\n\r\n");
				writer.write("Student Id: " + s.getId() + "\r\n");
				writer.write("First name: " + s.getFirstname() + "\r\n");
				writer.write("Last name: " + s.getLastname() + "\r\n");
				writer.write("Birth date: " + s.getBirthdate() + "\r\n");
				writer.write("Age: " + s.getAge() + "\r\n");
				writer.write("Group: " + s.getGroup() + "\r\n\r\n");
				writer.write("COURSES:\r\n\r\n");
				writer.write("Java:   " + s.getGrade(CourseEnum.JAVA) + "\r\n");
				writer.write("CSharp: " + s.getGrade(CourseEnum.CSHARP) + "\r\n");
				writer.write("Python: " + s.getGrade(CourseEnum.PYTHON) + "\r\n");
				writer.write("PHP:    " + s.getGrade(CourseEnum.PHP) + "\r\n\r\n");
				writer.write("RESULTS:\r\n\r\n");
				writer.write("Result: ");
				if (s.isPassed()) {
					writer.write("Passed\r\n");
				} else {
					writer.write("Failed\r\n");
				}
				writer.write("Retakes: " + s.getNumberOfRetakes() + "\r\n");
				writer.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void updateMenuState() {
		generateMenu.setVisible((currentUser != null) && (currentUser.getAccesslvl() == AccessLevel.ADMIN)); 
		addMenu.setVisible((currentUser != null) && (currentUser.getAccesslvl() != AccessLevel.BASIC));
		reportListMenu.setVisible((currentUser != null) && (currentUser.getAccesslvl() != AccessLevel.BASIC));
		displayListMenu.setVisible(currentUser != null);
		logout.setVisible(currentUser != null);
		//System.out.println(currentUser.getAccesslvl());
	}
	
	//Select the top-level screen 
	//Log-in, list screen, add student screen etc...
	public void selectScreen(ScreenMode m) {
		screenMode = m;
		//Show the correct screen for the speficied mode
		login.setVisible(m == ScreenMode.Login);
		listScreen.setVisible((m == ScreenMode.StudentList) || (m == ScreenMode.TeacherList) || (m == ScreenMode.StudentReportList));
		addStudentScreen.setVisible(m == ScreenMode.AddStudent);
		editReportScreen.setVisible(m == ScreenMode.EditReport);
		//TO DO: If necessarry set content for selected screen
		switch(m) {
		case StudentList:
			listScreen.setTitle("Student List");
			listScreen.setGroupOrSalary("Group");
			ArrayList<UserItem> studentItems = convertStudentsToUserItems(userDB.getStudents());
			listScreen.setItems(studentItems);
			break;
		case TeacherList:
			listScreen.setTitle("Teacher List");
			listScreen.setGroupOrSalary("Salary");
			ArrayList<UserItem> teacherItems = convertTeachersToUserItems(userDB.getTeachers());
			listScreen.setItems(teacherItems);
			break;
		case StudentReportList:
			listScreen.setTitle("Student Report List      Select one of the items to display or edit the grades ");
			listScreen.setGroupOrSalary("Group");
			ArrayList<UserItem> studentItems2 = convertStudentsToUserItems(userDB.getStudents());
			listScreen.setItems(studentItems2);
			break;
		case Login:
			currentUser = null;
			updateMenuState();
			break;
		}
		
	}

	private ArrayList<UserItem> convertStudentsToUserItems(ArrayList<Student> students) {
		ArrayList<UserItem> res = new ArrayList<UserItem>();
		for (Student s: students) {
			res.add(new UserItem(Integer.toString(s.getId()), s.getFirstname(), s.getLastname(), s.getBirthdate().toString(), Integer.toString(s.getAge()), s.getGroup()));
		}
		return res;
	}

	private ArrayList<UserItem> convertTeachersToUserItems(ArrayList<Teacher> teachers) {
		ArrayList<UserItem> res = new ArrayList<UserItem>();
		for (Teacher t: teachers) {
			res.add(new UserItem(Integer.toString(t.getId()), t.getFirstname(), t.getLastname(), t.getBirthdate().toString(), Integer.toString(t.getAge()), Double.toString(t.getSalary())));
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        launch();
	}
	
}
