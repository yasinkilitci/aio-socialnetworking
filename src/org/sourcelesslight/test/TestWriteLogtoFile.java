


package org.sourcelesslight.test;
import java.util.Locale;
import org.sourcelesslight.model.User;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.springframework.context.MessageSource;
public class TestWriteLogtoFile {
	
	private MessageSource messageSource;
	
	public void write(User user) throws IOException {
		
		//System.err.println(messageSource.getMessage("L002",new Object[]{user.getUsername(),user.getPassword()},Locale.US));
			
			//String content = "This is the content to write into file";
			String content = messageSource.getMessage("L002",new Object[]{user.getUsername()},Locale.US);
			
					File file = new File("./logs/log.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
 
			System.out.println("Done");
 
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
}