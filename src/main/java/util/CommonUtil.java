package util;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public final class CommonUtil {

  public static String resolveOffsetDate(int offsetInDays){
    LocalDate date = LocalDate.now().plusDays(offsetInDays);
    String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
    String s = Integer.toString(date.getDayOfMonth());
    int year = date.getYear();
    if(s.length() == 1){
      s = "0"+s;
    }
    return String.format("%s %s %s %d", dayOfWeek, month, s, year);

  }
}
