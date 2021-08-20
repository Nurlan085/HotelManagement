package dev.nurlan.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utility {

    public static long getcommonPriceByDayCount(Date fromDate, Date toDate)  {

        long commonDayCount = TimeUnit.DAYS.convert(toDate.getTime() - fromDate.getTime(), TimeUnit.MILLISECONDS);

        return commonDayCount;
    }

}
