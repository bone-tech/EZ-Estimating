package ezEstimatingPackageFX;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class StartWindow extends Application
{

	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage startStage) throws Exception 
	{
		startStage.setTitle("Welcome to EZ-Estimating");
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setHgap(10);
		grid.setVgap(25);
		grid.setPadding(new Insets(5, 5, 5, 5));
		
		Label newEstimate = new Label("Begin writing a new estimate\nfor a customer");
		newEstimate.setFont(Font.font("Helvetica", FontWeight.NORMAL, 14));
		grid.add(newEstimate, 3, 1);
		
		Label loadEstimate = new Label("Open a previous estimate\nfor editing");
		loadEstimate.setFont(Font.font("Helvetica", FontWeight.NORMAL, 14));
		grid.add(loadEstimate, 3, 2);
		
		Label about = new Label("About EZ-Estimating");
		about.setFont(Font.font("Helvetica", FontWeight.NORMAL, 14));
		grid.add(about, 3, 3);
		
		Button newButton = new Button("New");
		newButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
		newButton.setMaxWidth(110);
		grid.add(newButton, 2, 1);
		
		Button openButton = new Button("Open");
		openButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
		openButton.setMaxWidth(110);
		grid.add(openButton, 2, 2);
		
		Button aboutButton = new Button("About");
		aboutButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
		aboutButton.setMaxWidth(110);
		grid.add(aboutButton, 2, 3);
		
		newButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				MainWindow nextWindow = new MainWindow();
				nextWindow.getStage(startStage);
			}
		});
		
		openButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent e)
			{
				
				//LoadWindow nextWindow = new LoadWindow();
				//nextWindow.getStage(startStage);
				//file opening stuff
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Estimate");
				String user = System.getProperty("user.home");
				String dirName = new String(user + File.separator + "Documents" + File.separator + "EZEstimating" +  File.separator + "Estimates");
				Path path3 = Paths.get(dirName);
				if(Files.exists(path3))
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + "EZEstimating" +  File.separator + "Estimates"));
				fileChooser.showOpenDialog(startStage);
			}
		});
		
		Stage aboutStage = new Stage();
		aboutStage.setTitle("EZ-Estimating");
		BorderPane border = new BorderPane();
		Scene aboutScene = new Scene(border, 250, 150);
		
		Text aboutText = new Text("Version: 0.7\nAnthony Bonifacio\nAdvisor: Dr. Mary Courtney");
		aboutText.setFont(Font.font("Helvetica", FontWeight.NORMAL, 10));
		border.setCenter(aboutText);
		aboutStage.setScene(aboutScene);
		aboutStage.setAlwaysOnTop(true);
		
		aboutButton.setOnAction(new EventHandler<ActionEvent>()
		{	
			@Override
			public void handle(ActionEvent e)
			{
				aboutStage.show();
			}
			
		});
		
		aboutStage.initModality(Modality.APPLICATION_MODAL);
		Scene startScene = new Scene(grid, 400, 250);
		startStage.setScene(startScene);
		startStage.show();
	}

}
