import java.io.File;
import java.io.IOException;


/**
 * Developed By Pawan Pawar
 * 
 * @project StartCasino
 * @package
 * @fileName SocketServer.java
 * @date 11-Mar-2015
 * @time 12:12:47 pm
 */
public class Main{

	/**
	 * @method main
	 * @description Socket Server Main program for starting the server.
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException,
			IOException {
		File folder = new File("xml/");
		File[] listOfFiles = folder.listFiles();
		System.out.println("==========================================================");		
		for (File file : listOfFiles) {
			if (file.isFile()) {
				System.out.println("Start Reading: "+file.getName());
				ReadXMLFile rmf = new ReadXMLFile();
				Tester[] tester = rmf.read(file);				
				new Thread(new CheckURLs(tester,file.getName().split("\\.")[0])).start();
				System.out.println("Start Processing: "+file.getName());
			}
		}
		System.out.println("==========================================================");		
	}
}
