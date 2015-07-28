import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * @author Pawan Pawar
 * @project URLTesting
 * @package
 * @fileName CheckURLs.java
 * @date 15-Mar-2015
 * @time 1:08:39 pm
 */
public class ReadXMLFile {
	@SuppressWarnings("rawtypes")
	public static Tester[] read(File xmlFile) {
		Tester[] tester = {};
		SAXBuilder builder = new SAXBuilder();		
		try {
			Document document = (Document) builder.build(xmlFile);
			Element testurl = document.getRootElement();
			List server = testurl.getChildren("server");
			Element config = (Element) server.get(0);
			//System.out.println(xmlFile.getName().split("\\.")[0]+"  http://"+config.getChildText("ip")+":"+config.getChildText("port"));
			ServerConfig.config.put(xmlFile.getName().split("\\.")[0],"http://"+config.getChildText("ip")+":"+config.getChildText("port"));
			//System.out.println("KEY: "+xmlFile.getName().split("\\.")[0]+ " value: "+ServerConfig.config.get(xmlFile.getName().split("\\.")[0]));
			List list = testurl.getChildren("test");
			tester = new Tester[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Element test = (Element) list.get(i);
				tester[i] = new Tester();
				tester[i].setUri(test.getChildText("uri"));
				tester[i].setExpOutput(test.getChildText("output"));
				tester[i].setMethod(test.getChildText("method"));

				List params = test.getChildren("params");
				
				String[] name = {};
				String[] value = {};				
				Element prm = (Element) params.get(0);
				List ls = prm.getChildren("param");
				name = new String[ls.size()];
				value = new String[ls.size()];
				for (int j = 0; j < ls.size(); j++) {
					Element param = (Element) ls.get(j);
					name[j] = param.getChildText("name");
					value[j] = param.getChildText("value");
				}
				tester[i].setParam_name(name);
				tester[i].setParam_value(value);
			}
		} catch (IOException io) {
			System.out.println(io.getMessage());
		} catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		}
		return tester;
	}
}
