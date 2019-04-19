package operation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProjectConstants {
	Properties p = new Properties();
	public Properties getProjectConstants() throws IOException{
		InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\constants\\projectConstant.properties"));
		p.load(stream);
		 return p;
	}
	
	
}
