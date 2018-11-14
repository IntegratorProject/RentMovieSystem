
package util;

import java.util.Calendar;
import java.util.Date;

public class DateCalculator {
    
    public static int getAgeByBirthDate(Date birthDate){
        
        Calendar date = Calendar.getInstance();
        date.setTime(birthDate);
        
        Calendar today = Calendar.getInstance();
        
        int age = today.get(Calendar.YEAR) - date.get(Calendar.YEAR);
        date.add(Calendar.YEAR, age);
        if(today.before(date)) age--;
 
        return age;
        
    }
    
}
