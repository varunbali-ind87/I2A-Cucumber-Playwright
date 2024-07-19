package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils
{
    public String getScreenShotFromCurrentTimestamp()
    {
        var simpleFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        var date = new Date();
        return simpleFormat.format(date) + ".png";
    }
}
