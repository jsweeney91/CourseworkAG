package pack1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
	public static ArrayList<Node> coordinates = new ArrayList<>(); 
	private String fileName;
	
	Reader(String fileName){
		this.fileName = fileName;
	}
	
	public String readFile(){
		int counter = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(this.fileName));

		    String line = br.readLine();
		    while (line != null) {
		    	String pattern = "^\\d+ (.*) (.*)";
		        Pattern r = Pattern.compile(pattern);
		        Matcher m = r.matcher(line);
		        if (m.find( )) {
		        	double x = Double.parseDouble(m.group(1));
		        	double y = Double.parseDouble(m.group(2));
		            Node newNode = new Node(counter,x,y);		            
		            coordinates.add(newNode);
		            counter++;
		         }
		        line = br.readLine();
		    }
		    br.close();
		    return "read successful";
		    
		}catch(Exception e){
			return e.toString();
		}
	}

	
}
