import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Proxy implements HTLInternetAccess {

	private HTLInternetAccess internetAccess = new Real();
	private List<String> bannedServerhosts = new ArrayList<String>();
	private String client;

	public Proxy(String string) {
		client = string;
		bannedServerhosts.add("tetris.com");
	}
	
	public void audit(String serverhost) {
		File file = new File("Audit.txt"); 

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
		Date date = new Date();  

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file,true)); 
			writer.write(formatter.format(date)+": "+client+" tried to connect to "+serverhost);
			writer.newLine();
			writer.close();
		}
		catch(IOException ioe) {
			System.err.println(ioe);
		}	
	}

	@Override
	public void connect(String serverhost) {
		audit(serverhost);
		if(bannedServerhosts.contains(serverhost.toLowerCase())) {
			System.out.println("Access Denied: Forbidden Serverhost");
		}
		else {
			internetAccess.connect(serverhost);
		}
	}
}
