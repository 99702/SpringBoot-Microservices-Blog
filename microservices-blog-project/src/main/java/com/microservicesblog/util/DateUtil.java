package com.microservicesblog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Rajan Paudel <rajan99702@proton.me>
 */
public class DateUtil {
    public static Date generateExpiryDateForToken() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 3);
        var ss = sdf.format(c.getTime());
        System.out.println();
        return sdf.parse(ss);
//        Date date = Date.parse(dt);
    }

    public static void main(String[] args) throws ParseException {
        var ss =generateExpiryDateForToken();
        System.out.println();
    }
}
