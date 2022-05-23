package dev.nurlan.util;

import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@UtilityClass
public class Utility {

    public String getClientIp(HttpServletRequest request) {

        if (request != null) {
            String remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr))
                remoteAddr = request.getRemoteAddr();
            return remoteAddr;
        } else {
            return null;
        }
    }

    public long getcommonPriceByDayCount(Date fromDate, Date toDate) {

        long commonDayCount = TimeUnit.DAYS.convert(toDate.getTime() - fromDate.getTime(), TimeUnit.MILLISECONDS);

        return commonDayCount;
    }

}
