import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.http.HttpMethod;

/**
 * @author Pawan Pawar
 * @project URLTesting
 * @package
 * @fileName CheckURLs.java
 * @date 15-Mar-2015
 * @time 1:08:39 pm
 */
public class CheckURLs implements Runnable {
	Tester[] tester = null;
	HttpClient httpClient = null;
	ContentResponse response = null;
	BufferedWriter bw = null;
	int counter = 0;
	String fileName = "";
	
	/**
	 * @class CheckURLs.java
	 */
	public CheckURLs(Tester[] tester, String fileName) {
		try {
			this.tester = tester;
			httpClient = new HttpClient();
			httpClient.setFollowRedirects(false);
			httpClient.start();
			this.fileName = fileName;
			File file = new File("output/"+fileName+".txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			Request request = null;
			ContentResponse response = null;
			String url = ServerConfig.config.get(this.fileName);
			//System.out.println(this.fileName+" & URL: "+url);
			for (Tester test : tester) {
				request = httpClient.newRequest(url+test.getUri());
				request.method(test.getMethod().toUpperCase());
				request.agent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:17.0) Gecko/20100101 Firefox/17.0");
				bw.write("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
				bw.write("URI: "+url+test.getUri()+"\n");
				bw.write("Method: "+test.getMethod()+"\n");					
				for (int i = 0; i < test.getParam_name().length; i++) {
					request.param(test.getParam_name()[i],
							test.getParam_value()[i]);
					bw.write("Parameter "+(i+1)+": [Name="+test.getParam_name()[i]+", Value="+test.getParam_value()[i]+"]"+"\n");
				}
				response = request.send();
				bw.write("Expected Output: "+ test.getExpOutput()+"\n");				
				bw.write("Output: ["+"\n");
				bw.write("\tStatus Code: "+response.getStatus()+"\n");
				bw.write("\tOutput value: "+response.getContentAsString()+"\n");
				bw.write("]\n");
				if(response.getContentAsString().equals(test.getExpOutput())){
					bw.write("Result: Output of the result and expected out are matched."+"\n");
					bw.write("Result: SUCCESS"+"\n");
					counter++;
				}else{
					bw.write("Result: Output of the result and expected out are not matched."+"\n");
					bw.write("Result: FAILED"+"\n");
				}
			}
			bw.write("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
			bw.write("Total Number of Test: "+tester.length+"\n");
			bw.write("Number of SUCCESS Test: "+counter+"\n");
			bw.write("Number of FAILED Test: "+(tester.length - counter)+"\n");					
			bw.write("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
			bw.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
