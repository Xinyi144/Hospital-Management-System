import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HMS extends Application {
	
    public static Stage primaryStage;
    private Label dateTimeLabel;
    private VBox dynamicContentVBox;
    private BorderPane layout;
    public static StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final Map<String, String> userCredentials = new HashMap<>();
    public ArrayList<Medicine> medicine= new ArrayList<>(100);
    public ArrayList<Lab> laboratories= new ArrayList<>(20);
    public ArrayList<Facility> facilities= new ArrayList<>(20); 
    public ArrayList<Patient> patients= new ArrayList<>(100); 
    public ArrayList<Staff> staff= new ArrayList<>(100); 
    public ArrayList<Doctor> doctors= new ArrayList<>(25); 
    
    public static void main(String[] args) {
    	
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
        HMS.primaryStage = primaryStage;  // Set the static reference
        showLoginScene();
        
        /*ScrollPane mainScrollPane = new ScrollPane(layout);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setFitToHeight(true);
        
        StringProperty loggedInUsername = new SimpleStringProperty("abc"); // Replace this with actual dynamic value
        // Scene and Stage
        Scene scene = new Scene(mainScrollPane, 1000, 500);
        primaryStage.setTitle("Hospital Management System");
        primaryStage.setScene(createMainScene(loggedInUsername));
        primaryStage.setResizable(false); // Fix the window size and disable resizing
        primaryStage.setWidth(1000);      // Set the fixed width
        primaryStage.setHeight(500); 
        primaryStage.show();*/

    	
    }

    private void showLoginScene() {
        primaryStage.setTitle("Hospital Management System - Login");

        // Create login UI elements
        Label loginTitle = new Label("Login");
        loginTitle.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        ImageView loginImage = new ImageView(new Image("login.png"));
        loginImage.setFitWidth(150);
        loginImage.setFitHeight(150);
        loginImage.setPreserveRatio(true);

        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-size: 25px;-fx-font-family: 'Times New Roman';");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setPrefWidth(200);
        usernameField.textProperty().bindBidirectional(username);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 25px;-fx-font-family: 'Times New Roman';");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setPrefWidth(200);
        passwordField.textProperty().bindBidirectional(password);

        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10; -fx-font-family: 'Times New Roman';");
        addButtonAnimation(loginButton);
        loginButton.setOnAction(e -> handleLogin());

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-padding: 10; -fx-font-family: 'Times New Roman';");
        addButtonAnimation(signUpButton);
        signUpButton.setOnAction(e -> showSignUpScene());

        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));
        loginBox.setAlignment(javafx.geometry.Pos.CENTER);
        loginBox.getChildren().addAll(loginTitle, loginImage, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, signUpButton);

        // Create a frame with light blue background and fixed size
        StackPane frame = new StackPane();
        frame.setPadding(new Insets(20));
        frame.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        frame.setPrefSize(300, 300); // Fixed size for the frame
        frame.getChildren().add(loginBox);

        // Center the frame in the window
        StackPane root = new StackPane();
        root.setPadding(new Insets(50));
        root.getChildren().add(frame);

        Scene loginScene = new Scene(root, 400, 500, javafx.scene.paint.Color.WHITE); // Set the background color to white
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showSignUpScene() {
        primaryStage.setTitle("Hospital Management System - Sign Up");

        // Create sign-up UI elements
        Label signUpTitle = new Label("Sign Up");
        signUpTitle.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-font-family: 'Times New Roman';");

        ImageView signUpImage = new ImageView(new Image("sign.png"));
        signUpImage.setFitWidth(150);
        signUpImage.setFitHeight(150);
        signUpImage.setPreserveRatio(true);

        Label signUpUsernameLabel = new Label("Username:");
        signUpUsernameLabel.setStyle("-fx-font-size: 25px;-fx-font-family: 'Times New Roman';");
        TextField signUpUsernameField = new TextField();
        signUpUsernameField.setPromptText("Enter your username");
        signUpUsernameField.setPrefWidth(200);

        Label signUpPasswordLabel = new Label("Password:");
        signUpPasswordLabel.setStyle("-fx-font-size: 25px;-fx-font-family: 'Times New Roman';");
        PasswordField signUpPasswordField = new PasswordField();
        signUpPasswordField.setPromptText("Enter your password");
        signUpPasswordField.setPrefWidth(200);

        Label confirmPasswordLabel = new Label("Confirm Password:");
        confirmPasswordLabel.setStyle("-fx-font-size: 25px;-fx-font-family: 'Times New Roman';");
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Re-enter your password");
        confirmPasswordField.setPrefWidth(200);

        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-padding: 10; -fx-font-family: 'Times New Roman';");
        addButtonAnimation(signUpButton);
        signUpButton.setOnAction(e -> handleSignUp(signUpUsernameField.getText(), signUpPasswordField.getText(), confirmPasswordField.getText()));

        Button backButton = new Button("Back to Login");
        backButton.setStyle("-fx-background-color: #607D8B; -fx-text-fill: white; -fx-padding: 10; -fx-font-family: 'Times New Roman';");
        addButtonAnimation(backButton);
        backButton.setOnAction(e -> showLoginScene());

        VBox signUpBox = new VBox(10);
        signUpBox.setPadding(new Insets(20));
        signUpBox.setAlignment(javafx.geometry.Pos.CENTER);
        signUpBox.getChildren().addAll(signUpTitle, signUpImage, signUpUsernameLabel, signUpUsernameField, signUpPasswordLabel, signUpPasswordField, confirmPasswordLabel, confirmPasswordField, signUpButton, backButton);

        // Create a frame with light blue background and fixed size
        StackPane frame = new StackPane();
        frame.setPadding(new Insets(20));
        frame.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        frame.setPrefSize(300, 400); // Fixed size for the frame
        frame.getChildren().add(signUpBox);

        // Center the frame in the window
        StackPane root = new StackPane();
        root.setPadding(new Insets(50));
        root.getChildren().add(frame);

        Scene signUpScene = new Scene(root, 400, 500, javafx.scene.paint.Color.WHITE); // Set the background color to white
        primaryStage.setScene(signUpScene);
    }

    private void addButtonAnimation(Button button) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.1);
        scaleTransition.setToY(1.1);

        button.setOnMouseEntered(e -> scaleTransition.playFromStart());

        ScaleTransition reverseScaleTransition = new ScaleTransition(Duration.millis(200), button);
        reverseScaleTransition.setFromX(1.1);
        reverseScaleTransition.setFromY(1.1);
        reverseScaleTransition.setToX(1.0);
        reverseScaleTransition.setToY(1.0);

        button.setOnMouseExited(e -> reverseScaleTransition.playFromStart());
    }

    private void handleLogin() {
        String enteredUsername = username.get();
        String enteredPassword = password.get();

        // Check login credentials against stored credentials
        if (userCredentials.containsKey(enteredUsername) && userCredentials.get(enteredUsername).equals(enteredPassword)) {
            primaryStage.setScene(createMainScene(username));  // Pass the StringProperty
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private void handleSignUp(String username, String password, String confirmPassword) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Sign Up Failed", "Please fill all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Sign Up Failed", "Passwords do not match.");
            return;
        }

        if (userCredentials.containsKey(username)) {
            showAlert(Alert.AlertType.ERROR, "Sign Up Failed", "Username already exists.");
        } else {
            // Store the username and password
            userCredentials.put(username, password);
            showAlert(Alert.AlertType.INFORMATION, "Sign Up Successful", "You can now log in with your new credentials.");
            showLoginScene(); 
       }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private Button createSidebarButton(String text) {
        Button button = new Button(text);
        button.setPrefSize(250, 150);
        button.setPadding(new Insets(15));
        button.setFont(Font.font("Times New Roman", 16));

        return button;
    }

    private void updateDynamicContent(String section) {
        Label contentLabel = new Label("Previous Selection: " + section);
        contentLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
        contentLabel.setPadding(new Insets(10));
        dynamicContentVBox.getChildren().addAll(contentLabel);

       if (section.equals("DOCTOR")) {
            Label infoLabel = new Label("Have visit the Doctor form");
            infoLabel.setFont(new Font("Times New Roman", 14));
            
            VBox doctorContent = new VBox(10, infoLabel);
            doctorContent.setPadding(new Insets(10));
            dynamicContentVBox.getChildren().addAll(contentLabel, doctorContent);
        } else if (section.equals("PATIENTS")) {
            Label infoLabel = new Label("Have visit the Patient form");
            infoLabel.setFont(new Font("Times New Roman", 14));

            VBox patientContent = new VBox(10, infoLabel);
            patientContent.setPadding(new Insets(10));
            dynamicContentVBox.getChildren().addAll(contentLabel, patientContent);
        } else if (section.equals("LABORATORIES")) {
            Label infoLabel = new Label("Have visit the Laboratories form");
            infoLabel.setFont(new Font("Times New Roman", 14));
            
            VBox labContent = new VBox(10, infoLabel);
            labContent.setPadding(new Insets(10));
            dynamicContentVBox.getChildren().addAll(contentLabel, labContent);
        } else if (section.equals("FACILITIES")) {
            Label infoLabel = new Label("Have visit the Facilities form");
            infoLabel.setFont(new Font("Times New Roman", 14));

            VBox facilityContent = new VBox(10, infoLabel);
            facilityContent.setPadding(new Insets(10));
            dynamicContentVBox.getChildren().addAll(contentLabel, facilityContent);
        } else if (section.equals("MEDICINE")) {
            Label infoLabel = new Label("Have visit the Medicine form");
            infoLabel.setFont(new Font("Times New Roman", 14));

            VBox medicineContent = new VBox(10, infoLabel);
            medicineContent.setPadding(new Insets(10));
            dynamicContentVBox.getChildren().addAll(contentLabel, medicineContent);
        } else if (section.equals("STAFF")) {
            Label infoLabel = new Label("Staff Details:");
            infoLabel.setFont(new Font("Times New Roman", 14));

            VBox staffContent = new VBox(10, infoLabel);
            staffContent.setPadding(new Insets(10));
            dynamicContentVBox.getChildren().addAll(contentLabel, staffContent);
        }
    }
    
    private void showWelcomeMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Welcome");
        alert.setHeaderText(null);
        alert.setContentText("Welcome to the Hospital Management System!");
        alert.showAndWait();
    }

    public Scene createMainScene(StringProperty usernameProperty) {
		String username1=usernameProperty.get();
	    addSampleMed();
	    addSampleLab();
	    addSampleFac();
	    addSamplePat();
	    addSampleStaff();
	    addSampleDoctor();
		
        // Title Section
        Label titleLabel = new Label("Hospital Management System");
        titleLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKBLUE);
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setPadding(new Insets(10));
        
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle("-fx-background-color: linear-gradient(to right, #2f6a99, #64B5F6,#7fd2f5);");

        // Top Section with real-time clock and navigation bar
        dateTimeLabel = new Label();
        updateTime();
        dateTimeLabel.setFont(Font.font("Times New Roman",FontWeight.BOLD,20));
        dateTimeLabel.setTextFill(Color.PURPLE);  // Text color
        dateTimeLabel.setPadding(new Insets(5)); 
        dateTimeLabel.setAlignment(Pos.CENTER);
        
        ImageView icon=new ImageView("clock.png");
        icon.setFitWidth(40);
        icon.setFitHeight(40);
        
        dateTimeLabel.setGraphic(icon);
        Timeline clock = new Timeline(new javafx.animation.KeyFrame(Duration.ZERO, e -> updateTime()), 
                                      new javafx.animation.KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
        
        // Menu Item
        MenuBar menuBar = new MenuBar();
        Menu homeMenu = new Menu("Home");
        homeMenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 15px;");

        Menu doctorMenu = new Menu("Doctor");
        doctorMenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 15px;");
        MenuItem doctorMenuItem = new MenuItem("Open Doctor Screen");
        doctorMenu.getItems().add(doctorMenuItem);

        Menu patientMenu = new Menu("Patient");
        MenuItem patientMenuItem = new MenuItem("Open Patient Screen");
        patientMenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 15px;");
        patientMenu.getItems().add(patientMenuItem);

        Menu labMenu = new Menu("Lab");
        labMenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 15px;");
        MenuItem labMenuItem=new MenuItem("Open Lab Screen");
        labMenu.getItems().add(labMenuItem);
        
        Menu facilitiesMenu = new Menu("Facility");
        facilitiesMenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 15px;");
        MenuItem facilitiesMenuItem=new MenuItem("Open Facility Screen");
        facilitiesMenu.getItems().add(facilitiesMenuItem);
        
        Menu medicineMenu = new Menu("Medicine");
        medicineMenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 15px;");
        MenuItem medicineMenuItem=new MenuItem("Open Medicine Screen");
        medicineMenu.getItems().add(medicineMenuItem);
        
        Menu staffMenu = new Menu("Staff");
        staffMenu.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 15px;");
        MenuItem staffMenuItem=new MenuItem("Open Staff Screen");
        staffMenu.getItems().add(staffMenuItem);
        
        menuBar.getMenus().addAll(homeMenu, doctorMenu, patientMenu, labMenu, facilitiesMenu, medicineMenu, staffMenu);

        doctorMenuItem.setOnAction(e -> {
            openDoctorScreen();
            updateDynamicContent("DOCTOR");
        });
        
        labMenuItem.setOnAction(e->{
            openLabScreen();
        	updateDynamicContent("LAB");
        });
        
        patientMenuItem.setOnAction(e->{
        	openPatientScreen();
        	updateDynamicContent("PATIENT");
        });
        
        facilitiesMenuItem.setOnAction(e->{
            openFacilityScreen();
        	updateDynamicContent("FACILITY");
        });
        
        medicineMenuItem.setOnAction(e->{
            openMedScreen();
        	updateDynamicContent("MEDICINE");
        });
        
        staffMenuItem.setOnAction(e->{
        	openStaffScreen();
        	updateDynamicContent("STAFF");
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        HBox topSection = new HBox(10, menuBar);
        topSection.setPadding(new Insets(30));
        topSection.setAlignment(Pos.CENTER);
        
        BorderPane time = new BorderPane();
        time.setTop(dateTimeLabel);
        BorderPane.setAlignment(dateTimeLabel, Pos.CENTER);
        
        // Main Header
        VBox headerSection = new VBox(10, titleBox, time, topSection);
        headerSection.setAlignment(Pos.CENTER);

        // Center 
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setPadding(new Insets(20, 20, 20, 20));
        gp.setAlignment(Pos.CENTER);

        // Create buttons with icons for each option
        Button btnDoctors = createButton("DOCTOR", "doctor.png");
        Button btnPatient = createButton("PATIENT", "patient.png");
        Button btnStaff = createButton("STAFF", "staff.png");
        Button btnMed = createButton("MEDICINE", "medicine.png");
        Button btnLab = createButton("LAB", "lab.png");
        Button btnFac = createButton("FACILITY", "facility.png");

        // Set actions for each button
        btnDoctors.setOnAction(e -> openDoctorScreen());
        btnPatient.setOnAction(e -> openPatientScreen());
        btnStaff.setOnAction(e -> openStaffScreen());
        btnMed.setOnAction(e -> openMedScreen());
        btnLab.setOnAction(e -> openLabScreen());
        btnFac.setOnAction(e -> openFacilityScreen());

        // Add buttons to the grid (column, row)
        gp.add(btnDoctors, 0, 0);
        gp.add(btnPatient, 1, 0);
        gp.add(btnStaff, 0, 1);
        gp.add(btnMed, 1, 1);
        gp.add(btnLab, 0, 2);
        gp.add(btnFac, 1, 2);

     // Set actions for each button
        btnDoctors.setOnAction(e -> {
            openDoctorScreen();
            updateSidebarContent("DOCTOR");
        });

        btnPatient.setOnAction(e -> {
            openPatientScreen();
            updateSidebarContent("PATIENT");
        });

        btnStaff.setOnAction(e -> {
            openStaffScreen();
            updateSidebarContent("STAFF");
        });

      btnMed.setOnAction(e -> {
            openMedScreen();
            updateSidebarContent("MEDICINE");
        });

        
        btnLab.setOnAction(e -> {
            openLabScreen();
            updateSidebarContent("LAB");
        });

        btnFac.setOnAction(e -> {
            openFacilityScreen();
            updateSidebarContent("FACILITY");
        });

		
		
        String[] sections = {"DOCTOR", "PATIENTS", "LAB", "FACILITY", "MEDICINE", "STAFF"};
        
        for (String section : sections) {
            Button button = createSidebarButton(section);

            // Add action to update dynamic content when button is clicked
            button.setOnAction(e -> {
                updateDynamicContent(section);
                if (section.equals("DOCTOR")) {
                    openDoctorScreen(); 
                }
                if(section.equals("PATIENT")) {
                	openPatientScreen();
                }
                if(section.equals("STAFF")) {
                	openStaffScreen();
                }
                if(section.equals("LAB")) {
                	openLabScreen();
                }
                if(section.equals("FACILITY")) {
                	openFacilityScreen();
                }
                if(section.equals("MEDICINE")) {
                	openMedScreen();
                }
            });
        }
        
        
        // Left Sidebar with User Profile and Logout Button
        VBox leftSideBar = new VBox(10);
        leftSideBar.setPadding(new Insets(10));
        leftSideBar.setAlignment(Pos.TOP_CENTER);
        leftSideBar.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: #D0D0D0; -fx-border-width: 1;");

        Label usernameLabel = new Label("User: " + username1);
        usernameLabel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-font-size: 20px;");

        ImageView img1=new ImageView("logout.png");
	    img1.setFitWidth(20);
	    img1.setFitHeight(20);
	    img1.setPreserveRatio(true);
        Button logoutButton = new Button("Logout");
        logoutButton.setGraphic(img1);
        logoutButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        logoutButton.setPadding(new Insets(10));
        logoutButton.setStyle("-fx-background-color: #e07272; -fx-text-fill: white;");
        logoutButton.setOnAction(e -> {
            HMS.primaryStage.close();  
            Platform.exit();
        });
        
	    applyButtonHoverEffect(logoutButton);

        dynamicContentVBox = new VBox();
        dynamicContentVBox.setPadding(new Insets(10));
        dynamicContentVBox.setStyle("-fx-background-color: white; -fx-border-color: #D0D0D0; -fx-border-width: 1;");

        leftSideBar.getChildren().addAll(usernameLabel, logoutButton, dynamicContentVBox);
        
        // Main Layout
        BorderPane layout = new BorderPane();
        layout.setTop(headerSection);
        layout.setCenter(gp);
        layout.setLeft(leftSideBar);
       
        //background img
        Image bgImage = new Image(getClass().getResourceAsStream("hospital.jpg"));
        BackgroundSize bgSize = new BackgroundSize(1000, 650, false, false, false, false);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bgSize);
        layout.setBackground(new Background(bg));
        
        ScrollPane mainScrollPane = new ScrollPane(layout);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setFitToHeight(true);
        
        Button labSectionButton = new Button("Open Lab Section");
        labSectionButton.setOnAction(e -> openLabScreen());
        showWelcomeMessage();        

        return new Scene(mainScrollPane, 800, 600);
    }
    
    private void addSampleMed() {
        medicine.add(new Medicine("Paracetamol", "M001", "PharmaCo", "2024-12-31", 5, 100));
        medicine.add(new Medicine("Ibuprofen", "M002", "MediCorp", "2025-06-30", 7, 150));
        medicine.add(new Medicine("Aspirin", "M003", "HealthInc", "2023-11-15", 3, 200));
    }
    
    private void addSampleLab() {
	    laboratories.add(new Lab("Chemistry Lab","F001", 1200, "Floor1"));
	    laboratories.add(new Lab("Biology Lab","F002", 1500, "Floor2"));
	    laboratories.add(new Lab("Physics Lab","F003", 1800,"Floor3"));
	}
    
    private void addSampleFac() {
    	facilities.add(new Facility("F001","Exercise Map", 15, 30));
    	facilities.add(new Facility("F002","Gard Game", 20, 50));
    	facilities.add(new Facility("F003","Music Therapy Program", 100,20));
  	}

    private void addSamplePat() {
    	patients.add(new Patient("P_001", "John Doe", 30, "Flu", "Male", "Admitted"));
    	patients.add(new Patient("P_002", "Jane Smith", 45, "Headache", "Female", "Discharged"));
    	patients.add(new Patient("P_003", "Alice Johnson", 25, "Cold", "Female", "Admitted"));
    }
    
	private void addSampleStaff() {
        staff.add(new Staff("S_001", "Alice Smith", "Manager", "Female", 5000));
        staff.add(new Staff("S_002", "Bob Johnson", "Assistant", "Male", 3500));
        staff.add(new Nurse("S_003", "Charlie Brown", "Nurse", "Male", 3000, "Pediatrics"));
        staff.add(new Radiologist("S_004", "Diana Ross", "Radiologist", "Female", 4000, "Mammography"));
        staff.add(new Pharmacist("S_005", "Eve Taylor", "Pharmacist", "Female", 4500, "Clinical Pharmacy"));
        staff.add(new LabTechnician("S_006", "Frank Lee", "Lab Technician", "Male", 3200, "Microbiology"));
    }
	
	private void addSampleDoctor() {
		doctors.add(new Doctor("D_038", "Dr. Kavina", "Cardiology", "09:00 AM - 05:00 PM", "MD", 101, 25));
		doctors.add(new Doctor("D_019", "Dr. Xin Yi", "Neurologist", "10:00 AM - 06:00 PM", "PhD", 102, 30));
	    doctors.add(new Doctor("D_180", "Dr. Michael Brown", "General", "08:00 AM - 04:00 PM", "MBBS", 103, 15));
	}
   

    private void updateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTimeLabel.setText(LocalDateTime.now().format(formatter));
    }

    
    private Button createButton(String text, String imagePath) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);

        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        shadow.setOffsetX(5);
        shadow.setOffsetY(5);
        
        Button button = new Button(text, imageView);
        button.setStyle("-fx-background-color: transparent; -fx-border-color:#34849e ; -fx-border-width: 4;");
        button.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
        button.setTextFill(Color.WHITE);
        button.setPrefSize(280, 100);
        button.setEffect(shadow);

        button.setOnMouseEntered(e -> button.setScaleX(1.3));
        button.setOnMouseEntered(e -> button.setScaleY(1.1));
        button.setOnMouseExited(e -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });
        
        return button;
    }
    
    private void updateSidebarContent(String sectionName) {
        dynamicContentVBox.getChildren().clear(); 
        Label selectedLabel = new Label("Previous Selection: " + sectionName);
        selectedLabel.setPadding(new Insets(10));
        selectedLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 14));
        dynamicContentVBox.getChildren().add(selectedLabel); 
    }
    
    private void applyButtonHoverEffect(Button button) {
	      ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), button);
	      scaleTransition.setToX(1.1);  // Scale up by 10%
	      scaleTransition.setToY(1.1);  // Scale up by 10%

	      // ScaleTransition for button returning to normal size when hover ends
	      ScaleTransition scaleBackTransition = new ScaleTransition(Duration.millis(200), button);
	      scaleBackTransition.setToX(1.0);  // Scale back to normal
	      scaleBackTransition.setToY(1.0);  // Scale back to normal

	      // Apply hover effect
	      button.setOnMouseEntered(e -> {
	          button.setStyle("-fx-background-color: #de0d0d; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;-fx-font-family: 'Times New Roman';");
	          scaleTransition.playFromStart();  // Play scaling animation on hover
	      });

	      button.setOnMouseExited(e -> {
	          button.setStyle("-fx-background-color: #e07272; -fx-text-fill: white;-fx-font-family: 'Times New Roman'; -fx-font-weight: bold;-fx-font-family: 'Times New Roman';");
	          scaleBackTransition.playFromStart();  // Play scaling back animation when hover ends
	      });
	  }

    public boolean addDoc(Doctor newDoc) {
        if (doctors.size() < 25) { 
            doctors.add(newDoc);
            return true;
        } else {
            System.out.println("Cannot add more than 25 doctors.");
            return false; 
        }
    }
    public ArrayList<Doctor> getDoctorList() {
        return doctors;
    }

    public void openDoctorScreen() {
    	try {
    		Stage currentStage = (Stage) HMS.primaryStage.getScene().getWindow();
    		currentStage.close();
    		Stage docStage = new Stage();
    		Doctor doc = new Doctor(getDoctorList());
    		doc.start(docStage);
    		primaryStage.close();
    		HMS.primaryStage = docStage;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public boolean addPatient(Patient newPatient) {
        if (patients.size() < 100) {  
            patients.add(newPatient);
            return true; 
        } else {
            System.out.println("Cannot add more than 100 patients.");
            return false; 
        }
    }
    public ArrayList<Patient> getPatientList() {
        return patients;
    }

    public void openPatientScreen() {
    	try {
    		Stage currentStage = (Stage) HMS.primaryStage.getScene().getWindow();
    		currentStage.close();
    		Stage patientStage = new Stage();
    		Patient patientApp = new Patient(getPatientList());
    		patientApp.start(patientStage);
    		primaryStage.close();
    		HMS.primaryStage = patientStage;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public boolean addLab(Lab newLab) {
    	if (laboratories.size() < 20) { 
    		laboratories.add(newLab);
            return true; 
        }else {
            System.out.println("Cannot add more than 20 labs.");
            return false;
        }
    }
    public ArrayList<Lab> getLabList() {
        return laboratories;
    }
    public void openLabScreen() {
        try {
            Stage currentStage = (Stage) HMS.primaryStage.getScene().getWindow();
            currentStage.close(); 
            Stage LabStage = new Stage();
            Lab lab = new Lab(getLabList());
            lab.start(LabStage); 
            HMS.primaryStage = LabStage;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean addFacility(Facility newFacility) {
        if (facilities.size() < 20) { 
        	facilities.add(newFacility);
            return true; 
        } else {
            System.out.println("Cannot add more than 20 facilities.");
            return false;
        }
    }
    public ArrayList<Facility> getFacilityList() {
        return facilities;
    }
    public void openFacilityScreen() {
        try {
            Stage currentStage = (Stage) HMS.primaryStage.getScene().getWindow();
            currentStage.close();  
            Stage FacStage = new Stage();
            Facility facility = new Facility(getFacilityList());
            facility.start(FacStage); 
            HMS.primaryStage = FacStage;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean addMedicine(Medicine newMedicine) {
        if (medicine.size() < 100) {  
            medicine.add(newMedicine);
            return true;
        } else {
            System.out.println("Cannot add more than 100 medicines.");
            return false; 
        }
    }
    public ArrayList<Medicine> getMedicineList() {
        return medicine;
    }
    public void openMedScreen() {
        try {
            Stage currentStage = (Stage) HMS.primaryStage.getScene().getWindow();
            currentStage.close(); 
            Stage medStage = new Stage();
            Medicine med = new Medicine(getMedicineList()); 
            med.start(medStage);
            HMS.primaryStage = medStage;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean addStaff(Staff newStaff) {
        if (staff.size() < 100) { 
            staff.add(newStaff);
            return true;
        } else {
            System.out.println("Cannot add more than 100 staffs.");
            return false; 
        }
    }
    public ArrayList<Staff> getStaffList() {
        return staff;
    }

    public void openStaffScreen() {
    	try {
    		Stage currentStage = (Stage) HMS.primaryStage.getScene().getWindow();
    		currentStage.close();
    		Stage staffStage = new Stage();
    		Staff staff = new Staff(getStaffList());
    		staff.start(staffStage);
    		primaryStage.close();
    		HMS.primaryStage = staffStage;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }

}
    
    