
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Calculation {
	
	public static void main( String [] args ) throws ParseException, IOException{
		
		//Create array to store CusInfo 
		List<CusInfo> cusInfo = new ArrayList<>();
		try{
			BufferedReader br = new BufferedReader(new FileReader("promotion1.log"));
			String fileRead = br.readLine();

			//Loop for read all lines
			while (fileRead != null)
			{
				//In file, using | as the delimiter
				String[] token = fileRead.split(Pattern.quote("|"));

				//Create variables for each types of data
				String tempDate = token[0];
				String tempTimeStr = token[1];
				String tempTimeEnd = token[2];
				String tempPhoneNum = token[3];
				String tempPromotion = token[4];
				
				//Create object and store data values
				CusInfo tempObject = new CusInfo(tempDate, tempTimeStr, tempTimeEnd, tempPhoneNum, tempPromotion);
				cusInfo.add(tempObject);

				fileRead = br.readLine();
			}
			br.close();
		}		
		catch (FileNotFoundException fnfe){
			System.out.println("file not found");
		}
		catch (IOException ioe){
			ioe.printStackTrace();
		}
		
		
		//Create file and save output
		PrintStream file = new PrintStream("totalFee.json");
		System.setOut(file);
		
		for (CusInfo each : cusInfo){	
			System.out.println("{" + each + "Total Fee: " + each.calculate() + "}");

		}
	}
}

