package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	public static String readPropertiesFile(String key) {
		
		String Path = System.getProperty("user.dir") + "//application.properties";
		
		String value = null ;
		
		try {
			Properties prop = new Properties();
			FileInputStream fis = new FileInputStream(Path);
			prop.load(fis);
			return value= prop.getProperty(key);
		} catch (Exception e) {
		
		}
		return value;
	}
}
