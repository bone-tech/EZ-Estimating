package ezEstimatingPackageFX;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class MainWindow
{
	static Integer selectedRow;
	static BigDecimal totalD = new BigDecimal(0.00);
	static BigDecimal grandTotal = new BigDecimal(0.00);
	static Label total = new Label();
	static BigDecimal bodyHourTotal = new BigDecimal(0.0);
	static BigDecimal paintHourTotal = new BigDecimal(0.0);
	static BigDecimal mechHourTotal = new BigDecimal(0.0);
	static BigDecimal frameHourTotal = new BigDecimal(0.0);
	static BigDecimal structHourTotal = new BigDecimal(0.0);
	static BigDecimal paintAndFinishTotal = new BigDecimal(0.0);
	static BigDecimal bodySuppliesTotal = new BigDecimal(0.0);
	static BigDecimal paintSuppliesTotal = new BigDecimal(0.0);
	static String reportID;
	
	public Stage getStage(Stage mainStage) 
	{
		mainStage.setTitle("EZ-Estimating");
		BorderPane border = new BorderPane();
		Scene mainScene = new Scene(border, 1280, 720);
		
		//================================================
		//Start of toolbar
		//================================================
		HBox toolbar = new HBox();
		Button newBttn = new Button("New");
		newBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		Button openBttn = new Button("Open");
		openBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		Button saveBttn = new Button("Save");
		saveBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		Button printBttn = new Button("Print");
		printBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		Button setupBttn = new Button("Setup");
		setupBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		
		toolbar.getChildren().addAll(newBttn, openBttn, saveBttn, printBttn, setupBttn);
		border.setTop(toolbar);
		
		
		
		printBttn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
					
			}
		});
		
		newBttn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
					Stage aboutStage = new Stage();
					aboutStage.setTitle("EZ-Estimating");
					BorderPane border = new BorderPane();
					Scene aboutScene = new Scene(border, 250, 150);
					Text aboutText = new Text("You will lose any unsaved work!");
					border.setTop(aboutText);
					Button cont = new Button("Continue?");
					Button back = new Button("Back");
					border.setLeft(cont);
					border.setRight(back);
					aboutStage.setScene(aboutScene);
					aboutStage.setAlwaysOnTop(true);
					aboutStage.initModality(Modality.APPLICATION_MODAL);
					aboutStage.show();
					
				cont.setOnAction(new EventHandler<ActionEvent>()
					{
						@Override
						public void handle(ActionEvent e)
						{
							totalD = new BigDecimal(0.00);
							grandTotal = new BigDecimal(0.00);
							bodyHourTotal = new BigDecimal(0.0);
							paintHourTotal = new BigDecimal(0.0);
							mechHourTotal = new BigDecimal(0.0);
							frameHourTotal = new BigDecimal(0.0);
							structHourTotal = new BigDecimal(0.0);
							paintAndFinishTotal = new BigDecimal(0.0);
							bodySuppliesTotal = new BigDecimal(0.0);
							paintSuppliesTotal = new BigDecimal(0.0);
							reportID = "";
							setTotal("");
							MainWindow nextWindow = new MainWindow();
							nextWindow.getStage(mainStage);
							aboutStage.close();
						}
					});
				
				back.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{
						aboutStage.close();
					}
				});
				
			}
		});
		
		openBttn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				Stage aboutStage = new Stage();
				aboutStage.setTitle("EZ-Estimating");
				BorderPane border = new BorderPane();
				Scene aboutScene = new Scene(border, 250, 150);
				Text aboutText = new Text("You will lose any unsaved work!");
				border.setTop(aboutText);
				Button cont = new Button("Continue?");
				Button back = new Button("Back");
				border.setLeft(cont);
				border.setRight(back);
				aboutStage.setScene(aboutScene);
				aboutStage.setAlwaysOnTop(true);
				aboutStage.initModality(Modality.APPLICATION_MODAL);
				aboutStage.show();
				
			cont.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{  
						//MOVE THIS TO After file loads 
						totalD = new BigDecimal(0.00);
						grandTotal = new BigDecimal(0.00);
						bodyHourTotal = new BigDecimal(0.0);
						paintHourTotal = new BigDecimal(0.0);
						mechHourTotal = new BigDecimal(0.0);
						frameHourTotal = new BigDecimal(0.0);
						structHourTotal = new BigDecimal(0.0);
						paintAndFinishTotal = new BigDecimal(0.0);
						bodySuppliesTotal = new BigDecimal(0.0);
						paintSuppliesTotal = new BigDecimal(0.0);
						reportID = "";
						setTotal("");
			
						aboutStage.close();
						//file opening stuff
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Open Estimate");
						String user = System.getProperty("user.home");
						String dirName = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" +  File.separator + "Estimates");
						Path path3 = Paths.get(dirName);
						if(Files.exists(path3))
							fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "EZEstimating" +  File.separator + "Estimates"));
						fileChooser.showOpenDialog(mainStage);
						
					}
				});
			
			back.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent e)
				{
					aboutStage.close();
				}
			});
			
			}
		});
		//================================================
		//Start of main/center grid
		//================================================
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(5, 0, 20, 0));
		grid.setHgap(5);
		grid.setVgap(5);
		
		Label firstLabel = new Label("First");
		TextField firstTextField = new TextField();
		Label lastLabel = new Label("Last");
		TextField lastTextField = new TextField();
		Label totalLabel = new Label("Total");
		
		grid.add(firstLabel, 1, 0);
		GridPane.setMargin(firstLabel, new Insets(0, 0, 0, 100));
		grid.add(firstTextField, 2, 0);
		firstTextField.setMinWidth(300);
		GridPane.setMargin(firstTextField, new Insets(0, 100, 0, 0));
		grid.add(lastLabel, 3, 0);
		grid.add(lastTextField, 4, 0);
		lastTextField.setMinWidth(300);
		GridPane.setMargin(lastTextField, new Insets(0, 100, 0, 0));
		grid.add(totalLabel, 5, 0);
		GridPane.setMargin(totalLabel, new Insets(0, 10, 0, 0));
		
		//================================================
		//Start of tabs
		//================================================
		TabPane mainTabPane = new TabPane();
		mainTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		Tab customerTab = new Tab("Customer");
		customerTab.setContent(new Rectangle(1280, 200, Color.WHITESMOKE));
		GridPane customerGrid = new GridPane();
		customerGrid.setPadding(new Insets(5, 0, 5, 10));
		customerGrid.setHgap(5);
		customerGrid.setVgap(5);
		
		customerGrid.add(new Label("Address"), 0, 0);
		TextField addressTextField = new TextField();
		addressTextField.setMinWidth(1100);
		customerGrid.add(addressTextField, 1, 0, 6, 1);
		customerGrid.add(new Label("City"), 0, 1);
		TextField cityTextField = new TextField();
		customerGrid.add(cityTextField, 1, 1, 6, 1);
		customerGrid.add(new Label("State"), 0, 2);
		ComboBox<String> stateBox = new ComboBox<String>();
		stateBox.setMinWidth(300);
		stateBox.getItems().addAll(
				"AK",
				"AL",
				"AR",
				"AS",
				"AZ",
				"CA",
				"CO",
				"CT",
				"DC",
				"DE",
				"FL",
				"FM",
				"GA",
				"GU",
				"HI",
				"IA",
				"ID",
				"IL",
				"IN",
				"KS",
				"KY",
				"LA",
				"MA",
				"MD",
				"ME",
				"MH",
				"MI",
				"MN",
				"MO",
				"MP",
				"MS",
				"MT",
				"NC",
				"ND",
				"NE",
				"NH",
				"NJ",
				"NM",
				"NV",
				"NY",
				"OH",
				"OK",
				"OR",
				"PA",
				"PR",
				"PW",
				"RI",
				"SC",
				"SD",
				"TN",
				"TT",
				"TX",
				"UT",
				"VA",
				"VI",
				"VT",
				"WA",
				"WI",
				"WV",
				"WY"
				);	
		stateBox.getSelectionModel().select("NY");
		customerGrid.add(stateBox, 1, 2);
		GridPane.setMargin(stateBox, new Insets(0, 50, 0, 0));
		customerGrid.add(new Label("ZIP Code"), 2, 2);
		TextField zipTextField = new TextField();
		zipTextField.setMinWidth(300);
		customerGrid.add(zipTextField, 3, 2);
		GridPane.setMargin(zipTextField, new Insets(0, 50, 0, 0));
		customerGrid.add(new Label("Country"), 4, 2);
		TextField countryTextField = new TextField("USA");
		countryTextField.setMinWidth(300);
		customerGrid.add(countryTextField, 6, 2);
		customerGrid.add(new Label("Home Phone"), 0, 3);
		TextField homePhoneTextField = new TextField();
		homePhoneTextField.setMinWidth(300);
		customerGrid.add(homePhoneTextField, 1, 3);
		GridPane.setMargin(homePhoneTextField, new Insets(0, 50, 0, 0));
		customerGrid.add(new Label("Work Phone"), 2, 3);
		TextField workPhoneTextField = new TextField();
		workPhoneTextField.setMinWidth(300);
		customerGrid.add(workPhoneTextField, 3, 3);
		GridPane.setMargin(workPhoneTextField, new Insets(0, 50, 0, 0));
		customerGrid.add(new Label("Fax #"), 4, 3);
		TextField faxTextField = new TextField();
		faxTextField.setMinWidth(300);
		customerGrid.add(faxTextField, 6, 3);
		customerGrid.add(new Label("Estimator"), 0, 4);
		TextField estimatorTextField = new TextField();
		estimatorTextField.setMinWidth(400);
		customerGrid.add(estimatorTextField, 1, 4,2, 1);
		customerTab.setContent(customerGrid);
		
		Tab vehicleTab = new Tab("Vehicle");
		vehicleTab.setContent(new Rectangle(1280, 200, Color.WHITESMOKE));
		GridPane vehicleGrid = new GridPane();
		vehicleGrid.setPadding(new Insets(5, 0, 5, 10));
		vehicleGrid.setHgap(5);
		vehicleGrid.setVgap(5);
		
		vehicleGrid.add(new Label("Vin"), 0, 0);
		TextField vinTextField = new TextField();
		vinTextField.setMinWidth(1100);
		vehicleGrid.add(vinTextField, 1, 0, 5, 1);
		vehicleGrid.add(new Label("Vehicle Year"), 0, 1);
		TextField vehicleYearTextField = new TextField();
		vehicleYearTextField.setMaxWidth(45);
		vehicleGrid.add(vehicleYearTextField, 1, 1);
		GridPane.setMargin(vehicleYearTextField, new Insets(0, 50, 0, 0));
		vehicleGrid.add(new Label("Vehicle Make"), 2, 1);
		TextField vehicleMakeTextField = new TextField();
		vehicleGrid.add(vehicleMakeTextField, 3, 1);
		vehicleMakeTextField.setMinWidth(300);
		GridPane.setMargin(vehicleMakeTextField, new Insets(0, 50, 0, 0));
		vehicleGrid.add(new Label("Vehicle Model"), 4, 1);
		TextField vehicleModelTextField = new TextField();
		vehicleModelTextField.setMinWidth(300);
		vehicleGrid.add(vehicleModelTextField, 5, 1);
		vehicleGrid.add(new Label("Style"), 0, 2);
		ComboBox<String> vehicleStyleBox = new ComboBox<String>();
		vehicleStyleBox.setMinWidth(300);
		vehicleStyleBox.getItems().addAll(
				"Sedan",
				"Coupe",
				"Convertible",
				"Hatchback",
				"Wagon",
				"Van",
				"Truck"
				);
		vehicleGrid.add(vehicleStyleBox, 1, 2);
		vehicleStyleBox.getSelectionModel().select("Sedan");
		GridPane.setMargin(vehicleStyleBox, new Insets(0, 50, 0, 0));
		vehicleGrid.add(new Label("License"), 2, 2);
		TextField vehicleLicenseTextField = new TextField();
		vehicleLicenseTextField.setMinWidth(300);
		vehicleGrid.add(vehicleLicenseTextField, 3, 2);
		GridPane.setMargin(vehicleLicenseTextField, new Insets(0, 50, 0, 0));
		vehicleGrid.add(new Label("License State"), 4, 2);
		ComboBox<String> vehicleLicenseBox = new ComboBox<String>();
		vehicleLicenseBox.setMinWidth(300);
		vehicleLicenseBox.getItems().addAll(
				"AK",
				"AL",
				"AR",
				"AS",
				"AZ",
				"CA",
				"CO",
				"CT",
				"DC",
				"DE",
				"FL",
				"FM",
				"GA",
				"GU",
				"HI",
				"IA",
				"ID",
				"IL",
				"IN",
				"KS",
				"KY",
				"LA",
				"MA",
				"MD",
				"ME",
				"MH",
				"MI",
				"MN",
				"MO",
				"MP",
				"MS",
				"MT",
				"NC",
				"ND",
				"NE",
				"NH",
				"NJ",
				"NM",
				"NV",
				"NY",
				"OH",
				"OK",
				"OR",
				"PA",
				"PR",
				"PW",
				"RI",
				"SC",
				"SD",
				"TN",
				"TT",
				"TX",
				"UT",
				"VA",
				"VI",
				"VT",
				"WA",
				"WI",
				"WV",
				"WY"
				);	
		vehicleLicenseBox.getSelectionModel().select("NY");
		vehicleGrid.add(vehicleLicenseBox, 5, 2);
		vehicleGrid.add(new Label("Mileage In"), 0,3);
		TextField mileageInTextField = new TextField();
		mileageInTextField.setMinWidth(300);
		vehicleGrid.add(mileageInTextField, 1, 3);
		GridPane.setMargin(mileageInTextField, new Insets(0, 50, 0, 0));
		vehicleGrid.add(new Label("Mileage Out"), 2, 3);
		TextField mileageOutTextField = new TextField();
		mileageOutTextField.setMaxWidth(300);
		vehicleGrid.add(mileageOutTextField, 3, 3);
		vehicleGrid.add(new Label("Finish Type"), 0, 4);
		ComboBox<String> finishTypeBox = new ComboBox<String>();
		finishTypeBox.setMinWidth(300);
		finishTypeBox.getItems().addAll(
				"Clearcoat",
				"3-Stage",
				"Two-Tone with Clearcoat",
				"Two-Tone with 3-Stage",
				"None"
				);
		finishTypeBox.getSelectionModel().select("Clearcoat");
		vehicleGrid.add(finishTypeBox, 1, 4);
		GridPane.setMargin(finishTypeBox, new Insets(0, 50, 0, 0));
		vehicleGrid.add(new Label("Color"), 2, 4);
		ComboBox<String> colorBox = new ComboBox<String>();
		colorBox.setMinWidth(300);
		colorBox.getItems().addAll(
				"Black",
				"Blue",
				"Green",
				"Red",
				"Silver",
				"Tan",
				"White"
				);
		colorBox.getSelectionModel().select("White");
		vehicleGrid.add(colorBox, 3, 4);
		vehicleTab.setContent(vehicleGrid);
		
		mainTabPane.getTabs().add(customerTab);
		mainTabPane.getTabs().add(vehicleTab);
		GridPane.setMargin(mainTabPane, new Insets(0,-100,0,0));
		grid.add(mainTabPane, 0, 1, 6, 1);
		
		Label currentReport = new Label("Current Report");
		GridPane.setMargin(currentReport, new Insets(0, 0, 0, 10));
		grid.add(currentReport, 0, 2);
		TextField currentReportTextField = new TextField();
		grid.add(currentReportTextField, 1, 2, 4, 1);
		
		if(currentReportTextField.getText().equals("")) 
		{
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MM/dd/YY HH:mm:ss");
			LocalDateTime time = LocalDateTime.now();
			String timeString = timeFormatter.format(time);
			currentReportTextField.setText(timeString);
			DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("MM.dd.YY HH.mm.ss");
			String fileString = fileFormatter.format(time);
			reportID = fileString;
		}
		
		//================================================
		//Sidebar
		//================================================
		VBox sidebar = new VBox();
		Button newLineBttn = new Button("New Line");
		newLineBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		newLineBttn.setMinSize(100, 105);
		Button editLineBttn = new Button("Edit Line");
		editLineBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		editLineBttn.setMinSize(100, 105);
		Button deleteLineBttn = new Button("Delete Line");
		deleteLineBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		deleteLineBttn.setMinSize(100, 105);
		Button insertLineBttn = new Button("Insert Line");
		insertLineBttn.setFont(Font.font("Helvetica", FontWeight.NORMAL, 12));
		insertLineBttn.setMinSize(100, 105);
		
		sidebar.getChildren().addAll(newLineBttn, editLineBttn, deleteLineBttn, insertLineBttn);
		grid.add(sidebar, 0, 5);
		sidebar.setPadding(new Insets(-28,0,0,0));

		//================================================
		//Start of console
		//================================================
		HBox descriptionLabels = new HBox();
		Label descriptionLabel = new Label(" Description");
		descriptionLabel.setMinWidth(650);
		descriptionLabel.setStyle("-fx-background-color: GAINSBORO");
		Label priceLabel = new Label("Price");
		priceLabel.setMinWidth(100);
		priceLabel.setStyle("-fx-background-color: GAINSBORO");
		Label laborLabel = new Label("Labor");
		laborLabel.setMinWidth(100);
		laborLabel.setStyle("-fx-background-color: GAINSBORO");
		Label paintLabel = new Label("Paint");
		paintLabel.setMinWidth(100);
		paintLabel.setStyle("-fx-background-color: GAINSBORO");
		Label blankLabel = new Label("Other");
		blankLabel.setMinWidth(197);
		blankLabel.setStyle("-fx-background-color: GAINSBORO");
		
		descriptionLabels.getChildren().addAll(descriptionLabel, priceLabel, laborLabel, paintLabel, blankLabel);
		grid.add(descriptionLabels, 1, 3, 7, 1);
		GridPane.setMargin(descriptionLabels, new Insets(0, 0, 0, -5));
		border.setCenter(grid);
		
		//================================================
		//Setup window
		//================================================
		Stage setupStage = new Stage();
		setupStage.setTitle("Setup");
		BorderPane setupBorder = new BorderPane();
		Scene setupScene = new Scene(setupBorder, 600, 275);
		
		TabPane setupTabPane = new TabPane();
		setupTabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		Tab ratesTab = new Tab("Rates");
		Tab infoTab = new Tab("Company Information");
		Tab printTab = new Tab("Print Settings");
		
		GridPane ratesGrid = new GridPane();
		GridPane infoGrid = new GridPane();
		GridPane printGrid = new GridPane();
		
		ratesGrid.setPadding(new Insets(10, 0, 0, 10));
		ratesGrid.setHgap(5);
		ratesGrid.setVgap(5);
		
		ratesGrid.add(new Label("Body Labor"), 0, 0);
		TextField bodyLaborTextField = new TextField();
		bodyLaborTextField.setMaxWidth(50);
		ratesGrid.add(bodyLaborTextField, 1, 0);
		ratesGrid.add(new Label("/hr"), 2, 0);
		ratesGrid.add(new Label("Paint Labor"), 0, 1);
		TextField paintLaborTextField = new TextField();
		paintLaborTextField.setMaxWidth(50);
		ratesGrid.add(paintLaborTextField, 1, 1);
		ratesGrid.add(new Label("/hr"), 2, 1);
		ratesGrid.add(new Label("Frame Labor"), 0, 2);
		TextField frameLaborTextField = new TextField();
		frameLaborTextField.setMaxWidth(50);
		ratesGrid.add(frameLaborTextField, 1, 2);
		ratesGrid.add(new Label("/hr"), 2, 2);
		ratesGrid.add(new Label("Structural Labor"), 0, 3);
		TextField structLaborTextField = new TextField();
		structLaborTextField.setMaxWidth(50);
		ratesGrid.add(structLaborTextField, 1, 3);
		ratesGrid.add(new Label("/hr"), 2, 3);
		ratesGrid.add(new Label("Mechanical Labor"), 0, 4);
		TextField mechLaborTextField = new TextField();
		mechLaborTextField.setMaxWidth(50);
		ratesGrid.add(mechLaborTextField, 1, 4);
		ratesGrid.add(new Label("/hr"), 2, 4);
		ratesGrid.add(new Label("Body Supplies"), 0, 5);
		TextField bodySuppTextField = new TextField();
		bodySuppTextField.setMaxWidth(50);
		ratesGrid.add(bodySuppTextField, 1, 5);
		ratesGrid.add(new Label("/hr"), 2, 5);
		ratesGrid.add(new Label("Paint Supplies"), 0, 6);
		TextField paintSuppTextField = new TextField();
		paintSuppTextField.setMaxWidth(50);
		ratesGrid.add(paintSuppTextField, 1, 6);
		ratesGrid.add(new Label("/hr"), 2, 6);
		
		infoGrid.setPadding(new Insets(10, 0, 0, 10));
		infoGrid.setHgap(5);
		infoGrid.setVgap(5);
		
		infoGrid.add(new Label("Company"), 0, 0);
		TextField spCompanyTextField = new TextField();
		infoGrid.add(spCompanyTextField, 1, 0, 5, 1);
		infoGrid.add(new Label("Address"), 0, 1);
		TextField spAddressTextField = new TextField();
		infoGrid.add(spAddressTextField, 1, 1, 5, 1);
		infoGrid.add(new Label("City"), 0, 2);
		TextField spCityTextField = new TextField();
		infoGrid.add(spCityTextField, 1, 2, 5, 1);
		infoGrid.add(new Label("State"), 0, 3);
		ComboBox<String> spStateBox = new ComboBox<String>();
		spStateBox.setMaxWidth(75);
		spStateBox.getItems().addAll(
				"AK",
				"AL",
				"AR",
				"AS",
				"AZ",
				"CA",
				"CO",
				"CT",
				"DC",
				"DE",
				"FL",
				"FM",
				"GA",
				"GU",
				"HI",
				"IA",
				"ID",
				"IL",
				"IN",
				"KS",
				"KY",
				"LA",
				"MA",
				"MD",
				"ME",
				"MH",
				"MI",
				"MN",
				"MO",
				"MP",
				"MS",
				"MT",
				"NC",
				"ND",
				"NE",
				"NH",
				"NJ",
				"NM",
				"NV",
				"NY",
				"OH",
				"OK",
				"OR",
				"PA",
				"PR",
				"PW",
				"RI",
				"SC",
				"SD",
				"TN",
				"TT",
				"TX",
				"UT",
				"VA",
				"VI",
				"VT",
				"WA",
				"WI",
				"WV",
				"WY"
				);	
		spStateBox.getSelectionModel().select("NY");
		infoGrid.add(spStateBox, 1, 3);
		infoGrid.add(new Label("ZIP Code"), 2, 3);
		TextField spZIPTextField = new TextField();
		spZIPTextField.setMaxWidth(75);
		infoGrid.add(spZIPTextField, 3, 3);
		infoGrid.add(new Label("Country"), 4, 3);
		TextField spCountryTextField = new TextField("USA");
		spCountryTextField.setMaxWidth(75);
		infoGrid.add(spCountryTextField, 5, 3);
		infoGrid.add(new Label("Phone #"), 0, 4);
		TextField spPhoneTextField = new TextField();
		infoGrid.add(spPhoneTextField, 1, 4);
		infoGrid.add(new Label("Fax #"), 2, 4);
		TextField spFaxTextField = new TextField();
		infoGrid.add(spFaxTextField, 3, 4);
		infoGrid.add(new Label("Email"), 0, 5);
		TextField spEmailTextField = new TextField();
		infoGrid.add(spEmailTextField, 1, 5);
		infoGrid.add(new Label("Estimator"), 2, 5);
		TextField spEstimatorTextField = new TextField();
		infoGrid.add(spEstimatorTextField, 3, 5);
		infoGrid.add(new Label("Registration #"), 0, 6);
		TextField spRegTextField = new TextField();
		infoGrid.add(spRegTextField, 1, 6);
		infoGrid.add(new Label("Position"), 2, 6);
		TextField spPosTextField = new TextField();
		infoGrid.add(spPosTextField, 3, 6);
		
		ratesTab.setContent(ratesGrid);
		infoTab.setContent(infoGrid);
		printTab.setContent(printGrid);
		setupTabPane.getTabs().add(ratesTab);
		setupTabPane.getTabs().add(infoTab);
		setupTabPane.getTabs().add(printTab);
		
		setupBorder.setTop(setupTabPane);
		
		setupStage.setScene(setupScene);
		setupStage.setAlwaysOnTop(true);
		setupStage.setX(340);
		setupStage.setY(222);
		
		setupBttn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				setupStage.show();
			}
		});
		
		//================================================
		//Entry window	
		//================================================
		Stage entryStage = new Stage();
		entryStage.setTitle("Manual Entry");
		BorderPane entryBorder = new BorderPane();
		Scene entryScene = new Scene(entryBorder, 525, 215);
			
		entryStage.setScene(entryScene);
		entryStage.setAlwaysOnTop(true);
		entryStage.setX(378);
		entryStage.setY(253);
		
		GridPane entryGrid = new GridPane();
		entryGrid.setPadding(new Insets(10, 10, 10, 10));
		entryGrid.setHgap(5);
		entryGrid.setVgap(5);
		
		GridPane entryGrid2 = new GridPane();
		entryGrid2.setPadding(new Insets(5, 0, 0, 10));
		entryGrid2.setHgap(5);
		entryGrid2.setVgap(5);
		
		entryGrid.add(new Label("Operation"), 0, 0);
		entryGrid.add(new Label("Description"), 1, 0);
		ComboBox<String> operationBox = new ComboBox<String>();
		operationBox.getItems().addAll(
				"Replace",
				"Repair",
				"Refinish",
				"Pull & Square Frame"
				);
		operationBox.getSelectionModel().select("Replace");
		operationBox.setMinWidth(150);
		TextField descriptionText = new TextField();
		descriptionText.setMinWidth(350);
		entryGrid.add(operationBox, 0, 1);
		entryGrid.add(descriptionText, 1, 1, 5, 1);
		entryGrid.add(new Label("Labor times and prices"), 0, 2);
		entryGrid2.add(new Label("Price"), 0, 0);
		TextField  entryPrice = new TextField();
		entryPrice.setMaxWidth(100);
		entryPrice.setText("$0.00");
		entryGrid2.add(entryPrice, 1, 0);
		ComboBox<String> partType = new ComboBox<String>();
		partType.getItems().addAll(
				"OEM",
				"Aftermarket"
				);
		partType.getSelectionModel().select("OEM");
		partType.setMinWidth(100);
		entryGrid2.add(partType, 2, 0);
		entryGrid2.add(new Label("Labor"), 3, 0);
		TextField laborHours = new TextField();
		laborHours.setMaxWidth(100);
		laborHours.setText("0.0 hrs");
		entryGrid2.add(laborHours, 4, 0);
		ComboBox<String> laborType = new ComboBox<String>();
		laborType.getItems().addAll(
				"body",
				"frame",
				"struct",
				"mech"
				);
		laborType.getSelectionModel().select("body");
		laborType.setMinWidth(100);
		entryGrid2.add(laborType, 5, 0);
		entryGrid2.add(new Label("Paint"), 0, 1);
		TextField paintHours = new TextField();
		paintHours.setMaxWidth(100);
		paintHours.setText("0.0 hrs");
		entryGrid2.add(paintHours, 1, 1);
		entryGrid2.add(new Label("Finish"), 3, 1);
		TextField finishHours = new TextField();
		finishHours.setMaxWidth(100);
		entryGrid2.add(finishHours, 4, 1);
		finishHours.setText("0.0 hrs");
		ComboBox<String> finishType = new ComboBox<String>();
		finishType.setMinWidth(100);
		finishType.getItems().addAll(
				"Clearcoat",
				"3-Stage",
				"None"
				);
		finishType.getSelectionModel().select("Clearcoat");
		entryGrid2.add(finishType, 5, 1);
		entryGrid2.add(new Label("Edging"), 0, 2);
		TextField edgingHours = new TextField();
		edgingHours.setMaxWidth(100);
		edgingHours.setText("0.0 hrs");
		entryGrid2.add(edgingHours, 1, 2);
		Button addButton = new Button("Add");
		Button cancelButton = new Button("Cancel");
		entryGrid2.add(addButton, 5, 3);
		GridPane.setMargin(addButton, new Insets(0,0,0,10));
		GridPane.setMargin(cancelButton, new Insets(0,0,0,50));
		entryGrid2.add(cancelButton, 5, 3);
		entryBorder.setTop(entryGrid);
		entryBorder.setCenter(entryGrid2);
		
		//================================================
		//output window
		//================================================
		GridPane consoleGrid = new GridPane();
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setMinWidth(648);
		ColumnConstraints col2 = new ColumnConstraints();
		col2.setMinWidth(100);
		ColumnConstraints col3 = new ColumnConstraints();
		col3.setMinWidth(100);
		ColumnConstraints col4 = new ColumnConstraints();
		col4.setMinWidth(100);
		consoleGrid.getColumnConstraints().addAll(col1, col2, col3, col4);
		grid.add(consoleGrid, 1, 4, 7, 2);
		ScrollPane consoleScroll = new ScrollPane(consoleGrid);
		consoleScroll.setPadding(new Insets(0, 0, 0, 0));
		grid.add(consoleScroll, 1, 4, 7, 2);
		//consoleGrid.setGridLinesVisible(true);
		
		
		consoleGrid.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent e)
			{
				//more attempts
				
				for(Node node: consoleGrid.getChildren()) 
				{
					if(e.getY() > node.getLayoutY() && node.getLayoutY()>=0 && e.getY()<node.getLayoutY()+17)
						node.setStyle("-fx-background-color: LIGHTBLUE");
					
					else 
						node.setStyle("-fx-background-color: WHITESMOKE");
				}
			
			
		}});

			
		newLineBttn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				entryStage.show();
			}
		});
		
		
		deleteLineBttn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				BigDecimal bodyRateD = new BigDecimal(bodyLaborTextField.getText());
				BigDecimal paintRateD = new BigDecimal(paintLaborTextField.getText());
				BigDecimal frameRateD = new BigDecimal(frameLaborTextField.getText());
				BigDecimal structRateD = new BigDecimal(structLaborTextField.getText());
				BigDecimal mechRateD = new BigDecimal(mechLaborTextField.getText());
				BigDecimal bodySuppRateD = new BigDecimal(bodySuppTextField.getText());
				BigDecimal paintSuppRateD = new BigDecimal(paintSuppTextField.getText());
				
				ArrayList<Node> toRemove = new ArrayList<Node>();
				
					for(Node node: consoleGrid.getChildren()) 
						if(node.getStyle().equals("-fx-background-color: LIGHTBLUE"))
							toRemove.add(node);
					
				consoleGrid.getChildren().removeAll(toRemove);
				//System.out.println(toRemove);	
				if(!((Labeled)toRemove.get(1)).getText().equals("-")) 
				{
				int  priceIndex = (((Labeled) toRemove.get(1)).getText().indexOf("."))+3;
				BigDecimal price =  new BigDecimal(((Labeled) toRemove.get(1)).getText().substring(1,priceIndex));
				totalD = totalD.subtract(price);
				int paintIndex = ((Labeled) toRemove.get(3)).getText().indexOf(" ");
				BigDecimal paintHoursD = new BigDecimal(((Labeled) toRemove.get(3)).getText().substring(0, paintIndex));
				paintHourTotal = paintHourTotal.subtract(paintHoursD);
				paintAndFinishTotal = paintAndFinishTotal.subtract(paintHourTotal);
				paintSuppliesTotal = paintHourTotal;
				totalD = totalD.subtract((paintHoursD.multiply(paintRateD).add(paintHoursD.multiply(paintSuppRateD))));
				}
				
				int typeIndex = ((Labeled) toRemove.get(2)).getText().indexOf("*")+1;
				String typeString = ((Labeled) toRemove.get(2)).getText().substring(typeIndex);
				if(typeString.equalsIgnoreCase("body"))
				{
					BigDecimal bodyHoursD = new BigDecimal(((Labeled) toRemove.get(2)).getText().substring(0, typeIndex-5));
					//System.out.println(bodyHoursD);
					bodyHourTotal = bodyHourTotal.subtract(bodyHoursD);
					totalD = totalD.subtract(bodyHoursD.multiply(bodyRateD));
				}
				
				if(typeString.equalsIgnoreCase("frame"))
				{
					BigDecimal frameHoursD = new BigDecimal(((Labeled) toRemove.get(2)).getText().substring(0, typeIndex-5));
					frameHourTotal = frameHourTotal.subtract(frameHoursD);
					totalD = totalD.subtract(frameHoursD.multiply(frameRateD));
				}
				
				if(typeString.equalsIgnoreCase("struct"))
				{
					BigDecimal structHoursD = new BigDecimal(((Labeled) toRemove.get(2)).getText().substring(0, typeIndex-5));
					structHourTotal = structHourTotal.subtract(structHoursD);
					totalD = totalD.subtract(structHoursD.multiply(structRateD));	
				}
				
				if(typeString.equalsIgnoreCase("mech"))
				{
					BigDecimal mechHoursD = new BigDecimal(((Labeled) toRemove.get(2)).getText().substring(0, typeIndex-5));
					mechHourTotal = mechHourTotal.subtract(mechHoursD);
					totalD = totalD.subtract(mechHoursD.multiply(mechRateD));
				}
				
				if(((Labeled)toRemove.get(1)).getText().equals("-"))
				{
					BigDecimal finishEdgingHours = new BigDecimal(((Labeled) toRemove.get(3)).getText());
					paintAndFinishTotal = paintAndFinishTotal.subtract(finishEdgingHours);
					totalD = totalD.subtract((finishEdgingHours.multiply(paintRateD).add(finishEdgingHours.multiply(paintSuppRateD))));
				}
				
				if(((Labeled) toRemove.get(0)).getText().substring(0, 5).equals("Repai"))
				{
					if(typeString.equalsIgnoreCase("body") || typeString.equalsIgnoreCase("frame") || typeString.equalsIgnoreCase("struct"))
					{
						BigDecimal supplyHoursD = new BigDecimal(((Labeled) toRemove.get(2)).getText().substring(0, typeIndex-5));
						bodySuppliesTotal = bodySuppliesTotal.subtract(supplyHoursD);
						totalD = totalD.subtract(supplyHoursD.multiply(bodySuppRateD));
					}
				}
				
				
				total.setText(totalD.toString().substring(0, totalD.toString().length()-1));
			}
			
		});
		
		entryStage.setOnCloseRequest(event ->
		{
			entryStage.close();
			operationBox.setValue("Replace");
			laborType.setValue("body");
			partType.setValue("OEM");
			finishType.setValue("Clearcoat");
			descriptionText.clear();
			entryPrice.setText("$0.00");
			laborHours.setText("0.0 hrs");
			paintHours.setText("0.0 hrs");
			finishHours.setText("0.0 hrs");
			edgingHours.setText("0.0 hrs");
			
			
		});
		
		
		addButton.setOnAction(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent e)
					{
						BigDecimal bodyRateD = new BigDecimal(bodyLaborTextField.getText());
						BigDecimal paintRateD = new BigDecimal(paintLaborTextField.getText());
						BigDecimal frameRateD = new BigDecimal(frameLaborTextField.getText());
						BigDecimal structRateD = new BigDecimal(structLaborTextField.getText());
						BigDecimal mechRateD = new BigDecimal(mechLaborTextField.getText());
						BigDecimal bodySuppRateD = new BigDecimal(bodySuppTextField.getText());
						BigDecimal paintSuppRateD = new BigDecimal(paintSuppTextField.getText());
						
						consoleGrid.add(new Label(operationBox.getValue() + " " + descriptionText.getText()), 0, consoleGrid.getRowCount());
						consoleGrid.add(new Label(entryPrice.getText()), 1, consoleGrid.getRowCount()-1);
						consoleGrid.add(new Label(laborHours.getText()+ "*" + laborType.getValue()), 2, consoleGrid.getRowCount()-1);
						consoleGrid.add(new Label(paintHours.getText()), 3, consoleGrid.getRowCount()-1);
						
						if(!finishHours.getText().equals("0.0 hrs") && !edgingHours.getText().equals("0.0 hrs")) 
						{
							consoleGrid.add(new Label("  +Edging (" + edgingHours.getText() + ") + " + finishType.getValue() + " (" + finishHours.getText() + ")"), 0, consoleGrid.getRowCount());
							int finishIndex = finishHours.getText().indexOf(" ");
							int edgingIndex = edgingHours.getText().indexOf(" ");
							BigDecimal finishHoursD = new BigDecimal(finishHours.getText().substring(0, finishIndex));
							BigDecimal edgingHoursD = new BigDecimal(edgingHours.getText().substring(0, edgingIndex));
							BigDecimal finishEdgingHours = finishHoursD.add(edgingHoursD);
							consoleGrid.add(new Label("-"), 1, consoleGrid.getRowCount()-1);
							consoleGrid.add(new Label("-"), 2, consoleGrid.getRowCount()-1);
							consoleGrid.add(new Label(finishEdgingHours.toString()), 3, consoleGrid.getRowCount()-1);
							paintAndFinishTotal=paintAndFinishTotal.add(finishEdgingHours);
							totalD = totalD.add((finishEdgingHours.multiply(paintRateD).add(finishEdgingHours.multiply(paintSuppRateD))));
						}
						
						else if(!finishHours.getText().equals("0.0 hrs"))
						{
							consoleGrid.add(new Label("  +" + finishType.getValue() + " (" + finishHours.getText() + ")"), 0, consoleGrid.getRowCount());
							int finishIndex = finishHours.getText().indexOf(" ");
							int edgingIndex = edgingHours.getText().indexOf(" ");
							BigDecimal finishHoursD = new BigDecimal(finishHours.getText().substring(0, finishIndex));
							BigDecimal edgingHoursD = new BigDecimal(edgingHours.getText().substring(0, edgingIndex));
							BigDecimal finishEdgingHours = finishHoursD.add(edgingHoursD);
							consoleGrid.add(new Label("-"), 1, consoleGrid.getRowCount()-1);
							consoleGrid.add(new Label("-"), 2, consoleGrid.getRowCount()-1);
							consoleGrid.add(new Label(finishEdgingHours.toString()), 3, consoleGrid.getRowCount()-1);
							paintAndFinishTotal=paintAndFinishTotal.add(finishEdgingHours);
							totalD = totalD.add((finishEdgingHours.multiply(paintRateD).add(finishEdgingHours.multiply(paintSuppRateD))));
						}
						
						else if(!edgingHours.getText().equals("0.0 hrs"))
						{
							consoleGrid.add(new Label("  +Edging (" + edgingHours.getText() + ")"), 0, consoleGrid.getRowCount());
							int finishIndex = finishHours.getText().indexOf(" ");
							int edgingIndex = edgingHours.getText().indexOf(" ");
							BigDecimal finishHoursD = new BigDecimal(finishHours.getText().substring(0, finishIndex));
							BigDecimal edgingHoursD = new BigDecimal(edgingHours.getText().substring(0, edgingIndex));
							BigDecimal finishEdgingHours = finishHoursD.add(edgingHoursD);
							consoleGrid.add(new Label("-"), 1, consoleGrid.getRowCount()-1);
							consoleGrid.add(new Label("-"), 2, consoleGrid.getRowCount()-1);
							consoleGrid.add(new Label(finishEdgingHours.toString()), 3, consoleGrid.getRowCount()-1);
							paintAndFinishTotal=paintAndFinishTotal.add(finishEdgingHours);
							totalD = totalD.add((finishEdgingHours.multiply(paintRateD).add(finishEdgingHours.multiply(paintSuppRateD))));
						}
						
						int laborIndex = laborHours.getText().indexOf(" ");
						
						if(laborType.getValue().equalsIgnoreCase("body"))
						{
							BigDecimal bodyHoursD = new BigDecimal(laborHours.getText().substring(0, laborIndex));
							bodyHourTotal = bodyHourTotal.add(bodyHoursD);
							totalD = totalD.add(bodyHoursD.multiply(bodyRateD));
						}
						
						if(laborType.getValue().equalsIgnoreCase("frame"))
						{
							BigDecimal frameHoursD = new BigDecimal(laborHours.getText().substring(0, laborIndex));
							frameHourTotal = frameHourTotal.add(frameHoursD);
							totalD = totalD.add(frameHoursD.multiply(frameRateD));
						}
						
						if(laborType.getValue().equalsIgnoreCase("struct"))
						{
							BigDecimal structHoursD = new BigDecimal(laborHours.getText().substring(0, laborIndex));
							structHourTotal = structHourTotal.add(structHoursD);
							totalD = totalD.add(structHoursD.multiply(structRateD));
						}
						
						if(laborType.getValue().equalsIgnoreCase("mech"))
						{
							BigDecimal mechHoursD = new BigDecimal(laborHours.getText().substring(0, laborIndex));
							mechHourTotal = mechHourTotal.add(mechHoursD);
							totalD = totalD.add(mechHoursD.multiply(mechRateD));
						}
						
						int paintIndex = paintHours.getText().indexOf(" ");
						BigDecimal paintHoursD = new BigDecimal(paintHours.getText().substring(0, paintIndex));
						paintHourTotal = paintHourTotal.add(paintHoursD);
						paintAndFinishTotal = paintAndFinishTotal.add(paintHourTotal);
						paintSuppliesTotal = paintHourTotal;
						totalD = totalD.add((paintHoursD.multiply(paintRateD).add(paintHoursD.multiply(paintSuppRateD))));
						
						if(operationBox.getValue().equalsIgnoreCase("Repair"))
						{
							if(laborType.getValue().equalsIgnoreCase("body") || laborType.getValue().equalsIgnoreCase("frame") || laborType.getValue().equalsIgnoreCase("struct"))
							{
								BigDecimal supplyHoursD = new BigDecimal(laborHours.getText().substring(0, laborIndex));
								bodySuppliesTotal = bodySuppliesTotal.add(supplyHoursD);
								totalD = totalD.add(supplyHoursD.multiply(bodySuppRateD));
							}
						}
						
						int  priceIndex = (entryPrice.getText().indexOf("."))+3;
						BigDecimal price =  new BigDecimal(entryPrice.getText().substring(1,priceIndex));
						totalD = totalD.add(price);
						total.setText(totalD.toString().substring(0, totalD.toString().length()-1));
						//System.out.println(totalD);
						entryStage.close();
						operationBox.setValue("Replace");
						laborType.setValue("body");
						partType.setValue("OEM");
						finishType.setValue("Clearcoat");
						descriptionText.clear();
						entryPrice.setText("$0.00");
						laborHours.setText("0.0 hrs");
						paintHours.setText("0.0 hrs");
						finishHours.setText("0.0 hrs");
						edgingHours.setText("0.0 hrs");
						
						
						
					}
		});
		
		cancelButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{				
				entryStage.close();
				operationBox.setValue("Replace");
				laborType.setValue("body");
				partType.setValue("OEM");
				finishType.setValue("Clearcoat");
				descriptionText.clear();
				entryPrice.setText("$0.00");
				laborHours.setText("0.0 hrs");
				paintHours.setText("0.0 hrs");
				finishHours.setText("0.0 hrs");
				edgingHours.setText("0.0 hrs");
				
				
			}
		});
		
		setupStage.setOnCloseRequest(event ->
		{
			String user = System.getProperty("user.home");
			String dirName = new String(user + File.separator + "Documents" + File.separator + "EZEstimating");
			Path path = Paths.get(dirName);
			if(!Files.exists(path)) {
				try {
					Files.createDirectory(path);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			String config = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" + File.separator + "config.txt");
			Path path2 = Paths.get(config);
			if(!Files.exists(path2))
			try {
				Files.createFile(path2);
				BufferedWriter writer = Files.newBufferedWriter(path2);
				writer.write(spCompanyTextField.getText());
				writer.newLine();
				writer.write(spAddressTextField.getText());
				writer.newLine();
				writer.write(spCityTextField.getText());
				writer.newLine();
				writer.write(spStateBox.getValue());
				writer.newLine();
				writer.write(spZIPTextField.getText());
				writer.newLine();
				writer.write(spCountryTextField.getText());
				writer.newLine();
				writer.write(spPhoneTextField.getText());
				writer.newLine();
				writer.write(spFaxTextField.getText());
				writer.newLine();
				writer.write(spEmailTextField.getText());
				writer.newLine();
				writer.write(spEstimatorTextField.getText());
				writer.newLine();
				writer.write(spRegTextField.getText());
				writer.newLine();
				writer.write(spPosTextField.getText());
				writer.newLine();
				
				writer.write(bodyLaborTextField.getText());
				writer.newLine();
				writer.write(paintLaborTextField.getText());
				writer.newLine();
				writer.write(frameLaborTextField.getText());
				writer.newLine();
				writer.write(structLaborTextField.getText());
				writer.newLine();
				writer.write(mechLaborTextField.getText());
				writer.newLine();
				writer.write(bodySuppTextField.getText());
				writer.newLine();
				writer.write(paintSuppTextField.getText());
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			else {
				try {
					BufferedWriter writer = Files.newBufferedWriter(path2);
					writer.write(spCompanyTextField.getText());
					writer.newLine();
					writer.write(spAddressTextField.getText());
					writer.newLine();
					writer.write(spCityTextField.getText());
					writer.newLine();
					writer.write(spStateBox.getValue());
					writer.newLine();
					writer.write(spZIPTextField.getText());
					writer.newLine();
					writer.write(spCountryTextField.getText());
					writer.newLine();
					writer.write(spPhoneTextField.getText());
					writer.newLine();
					writer.write(spFaxTextField.getText());
					writer.newLine();
					writer.write(spEmailTextField.getText());
					writer.newLine();
					writer.write(spEstimatorTextField.getText());
					writer.newLine();
					writer.write(spRegTextField.getText());
					writer.newLine();
					writer.write(spPosTextField.getText());
					writer.newLine();
					
					writer.write(bodyLaborTextField.getText());
					writer.newLine();
					writer.write(paintLaborTextField.getText());
					writer.newLine();
					writer.write(frameLaborTextField.getText());
					writer.newLine();
					writer.write(structLaborTextField.getText());
					writer.newLine();
					writer.write(mechLaborTextField.getText());
					writer.newLine();
					writer.write(bodySuppTextField.getText());
					writer.newLine();
					writer.write(paintSuppTextField.getText());
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		String user = System.getProperty("user.home");
		String config = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" + File.separator + "config.txt");
		Path path2 = Paths.get(config);
		if(Files.exists(path2))
		try {
			BufferedReader reader = Files.newBufferedReader(path2);
			spCompanyTextField.setText(reader.readLine());
			spAddressTextField.setText(reader.readLine());
			spCityTextField.setText(reader.readLine());
			spStateBox.getSelectionModel().select((reader.readLine()));
			spZIPTextField.setText(reader.readLine());
			spCountryTextField.setText(reader.readLine());
			spPhoneTextField.setText(reader.readLine());
			spFaxTextField.setText(reader.readLine());
			spEmailTextField.setText(reader.readLine());
			spEstimatorTextField.setText(reader.readLine());
			spRegTextField.setText(reader.readLine());
			spPosTextField.setText(reader.readLine());
			
			bodyLaborTextField.setText(reader.readLine());
			paintLaborTextField.setText(reader.readLine());
			frameLaborTextField.setText(reader.readLine());
			structLaborTextField.setText(reader.readLine());
			mechLaborTextField.setText(reader.readLine());
			bodySuppTextField.setText(reader.readLine());
			paintSuppTextField.setText(reader.readLine());
			reader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		grid.add(total, 6, 0);
		
		setupStage.initModality(Modality.APPLICATION_MODAL);
		entryStage.initModality(Modality.APPLICATION_MODAL);
		mainStage.setScene(mainScene);
		mainStage.setX(0);
		mainStage.setY(0);
		
		saveBttn.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
			String user = System.getProperty("user.home");
			String dirName = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" +  File.separator + "Estimates");
			Path path3 = Paths.get(dirName);
			if(!Files.exists(path3)) {
				try {
					Files.createDirectory(path3);
					} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
				}
			
			String estimate = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" + File.separator + "Estimates" + File.separator + (reportID + ".txt") );
			Path path4 = Paths.get(estimate);
			if(!Files.exists(path4))
			try {
				Files.createFile(path4);
				BufferedWriter writer = Files.newBufferedWriter(path4);
				writer.write(firstTextField.getText());
				writer.newLine();
				writer.write(lastTextField.getText());
				writer.newLine();
				writer.write(total.getText());
				writer.newLine();
				writer.write(addressTextField.getText());
				writer.newLine();
				writer.write(cityTextField.getText());
				writer.newLine();
				writer.write(stateBox.getValue());
				writer.newLine();
				writer.write(zipTextField.getText());
				writer.newLine();
				writer.write(countryTextField.getText());
				writer.newLine();
				writer.write(homePhoneTextField.getText());
				writer.newLine();
				writer.write(workPhoneTextField.getText());
				writer.newLine();
				writer.write(faxTextField.getText());
				writer.newLine();
				writer.write(estimatorTextField.getText());
				writer.newLine();
				writer.write(currentReportTextField.getText());
				writer.newLine();
				writer.write(vinTextField.getText());
				writer.newLine();
				writer.write(vehicleYearTextField.getText());
				writer.newLine();
				writer.write(vehicleMakeTextField.getText());
				writer.newLine();
				writer.write(vehicleModelTextField.getText());
				writer.newLine();
				writer.write(vehicleStyleBox.getValue());
				writer.newLine();
				writer.write(vehicleLicenseTextField.getText());
				writer.newLine();
				writer.write(vehicleLicenseBox.getValue());
				writer.newLine();
				writer.write(mileageInTextField.getText());
				writer.newLine();
				writer.write(mileageOutTextField.getText());
				writer.newLine();
				writer.write(finishTypeBox.getValue());
				writer.newLine();
				writer.write(colorBox.getValue());
				writer.newLine();
				writer.write(consoleGrid.getChildren().toString());
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			else {
				try {
					BufferedWriter writer = Files.newBufferedWriter(path4);
					writer.write(firstTextField.getText());
					writer.newLine();
					writer.write(lastTextField.getText());
					writer.newLine();
					writer.write(total.getText());
					writer.newLine();
					writer.write(addressTextField.getText());
					writer.newLine();
					writer.write(cityTextField.getText());
					writer.newLine();
					writer.write(stateBox.getValue());
					writer.newLine();
					writer.write(zipTextField.getText());
					writer.newLine();
					writer.write(countryTextField.getText());
					writer.newLine();
					writer.write(homePhoneTextField.getText());
					writer.newLine();
					writer.write(workPhoneTextField.getText());
					writer.newLine();
					writer.write(faxTextField.getText());
					writer.newLine();
					writer.write(estimatorTextField.getText());
					writer.newLine();
					writer.write(currentReportTextField.getText());
					writer.newLine();
					writer.write(vinTextField.getText());
					writer.newLine();
					writer.write(vehicleYearTextField.getText());
					writer.newLine();
					writer.write(vehicleMakeTextField.getText());
					writer.newLine();
					writer.write(vehicleModelTextField.getText());
					writer.newLine();
					writer.write(vehicleStyleBox.getValue());
					writer.newLine();
					writer.write(vehicleLicenseTextField.getText());
					writer.newLine();
					writer.write(vehicleLicenseBox.getValue());
					writer.newLine();
					writer.write(mileageInTextField.getText());
					writer.newLine();
					writer.write(mileageOutTextField.getText());
					writer.newLine();
					writer.write(finishTypeBox.getValue());
					writer.newLine();
					writer.write(colorBox.getValue());
					writer.newLine();
					writer.write(consoleGrid.getChildren().toString());
					writer.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			}
		});
		
		return mainStage;
		
		
	}
	
	public static void setTotal(String totalStr) 
	{
		total = new Label(totalStr);
	}

}
