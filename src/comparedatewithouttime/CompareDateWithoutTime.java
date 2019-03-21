package comparedatewithouttime;

import java.util.Calendar;
import java.util.Date;

public class CompareDateWithoutTime {

	public static int CompareTwoDates(Date startDate, Date endDate) 
	{
		Date sDate = GetZeroTimeDate(startDate);
		Date eDate = GetZeroTimeDate(endDate);
		if (sDate.before(eDate)) {
			return -1;
		}
		if (sDate.after(eDate)) {
			return 1;
		}
		return 0;
	}
	
	public static Date GetZeroTimeDate(Date date) 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		date = calendar.getTime();
		return date;
	}

}
