package DateTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;

public class Dataexm {
    //    public static void main(String[] args) {
//        LocalDate oldDate = LocalDate.of(2017, 6, 18);
//        LocalDate newDate = LocalDate.of(2017, (oldDate.getMonth()+1), 20);
//        Period period = Period.between(oldDate, newDate);
//        System.out.println("相差 %d 年 %d 月 %d 日"+ period.getYears()+ period.getMonths()+period.getDays());
//
//    }
    public static void calculateTimeDifferenceByPeriod(int year, Month month, int dayOfMonth) {
        LocalDate today = LocalDate.now();
        System.out.println("Today：" + today);
        LocalDate oldDate = LocalDate.of(year, month, dayOfMonth);
        System.out.println("OldDate：" + oldDate);
        Period p = Period.between(oldDate, today);
        System.out.printf("目标日期距离今天的时间差：%d 年 %d 个月 %d 天\n", p.getYears(), p.getMonths(), p.getDays());
    }

    public static void main(String[] args) {
        calculateTimeDifferenceByPeriod(2019,Month.MARCH,20);
    }
}
