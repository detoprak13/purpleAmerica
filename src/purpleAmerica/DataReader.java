package purpleAmerica;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
	private String fileName = "";
	ArrayList<String> list = new ArrayList<String>();
	
	public DataReader(String arg) {
		fileName = Test.path + arg+".txt";
		String line = null;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(
					fileName)));
	         
	         while ((line = br.readLine()) != null) {
	            list.add(line);
	         }
	         br.close();
	         
		}catch (IOException e){
			e.printStackTrace();
			System.out.println("okuyamadým");
			
		}
	}
	
	public ArrayList<String> getData(){
		return list;
	}
	
	public String toString() {
		System.out.println(list);
		return null;		
	}

}
