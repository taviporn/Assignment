import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CusInfo {

	private String date;
	private String timestr;
	private String timeend;
	private String phonenum;
	private String promotion;

	public CusInfo (String date, String timestr, String timeend, String phonenum, String promotion){
		this.date = date;
		this.timestr = timestr;
		this.timeend = timeend;
		this.phonenum = phonenum;
		this.promotion = promotion;
	}

	public int calculate() throws ParseException{		    
			
		//Calculate Time Difference
		DateFormat timeDiff = new SimpleDateFormat("HH:mm:ss");
		Date start = timeDiff.parse(timestr);
		Date end = timeDiff.parse(timeend);
					
		long diff = 0;
		int hourDiff = 0;
		int minuteDiff = 0;
		int secondDiff = 0;
		int fee = 0;
		diff = end.getTime() - start.getTime();	
		hourDiff = (int) (diff / (60 * 60 * 1000) % 60); 
		minuteDiff = (int) (diff / (60 * 1000) % 60);
		secondDiff = (int) (diff / (1000) % 60);

			
		//Case1: 1 minute
		if(hourDiff == 0 && minuteDiff == 1 && secondDiff == 0) fee = 3;
		//Case2: less than 1 minute
		else if(hourDiff == 0 && minuteDiff == 0 && secondDiff < 60) fee = 3;
		//Case3: more than 1 minute but less than 2 minutes
		else if(hourDiff == 0 && minuteDiff == 1 && secondDiff > 0) fee = 4;
		//Case4: without any second
		else if(hourDiff >= 0 && minuteDiff >= 0 && secondDiff == 0) fee = 3+(((minuteDiff+(hourDiff*60))-1)*1);
		//Case5: with more than 1 second
		else if(hourDiff >= 0 && minuteDiff >= 0 && secondDiff > 0) fee = 4+(((minuteDiff+(hourDiff*60))-1)*1);
					
		return fee;
	}

	public String toString(){
		return 	/*"Date: " + date + "\n" + 
				"TimeStart: " + timestr + "\n" +
				"TimeEnd: " + timeend + "\n" +
				"Phone: " + phonenum + "\n" +
				"Promotion: " + promotion;*/
				"Phone: " + phonenum + "\n";
	}
	
}
