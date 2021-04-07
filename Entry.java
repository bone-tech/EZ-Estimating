package ezEstimatingPackageFX;

import java.text.DecimalFormat;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;


public class Entry {
	
	DecimalFormat phoneFormat = new DecimalFormat("(000)-000-0000");
	
	public void setPhoneFormat(TextField f) 
	{
		f.getTextFormatter();
	}
}
