package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	static Properties prop;

	public static String getProperty(String key) {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
				prop.load(fis);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return prop.getProperty(key);

	}

}
