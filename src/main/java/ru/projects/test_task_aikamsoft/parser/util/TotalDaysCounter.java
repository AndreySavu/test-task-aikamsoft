package ru.projects.test_task_aikamsoft.parser.util;

import ru.projects.test_task_aikamsoft.utils.error.WrongDateFormatException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class TotalDaysCounter {

    public int getTotalDaysCount(String startDate, String endDate) throws WrongDateFormatException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate start = LocalDate.parse(startDate, dateFormatter);
            LocalDate end = LocalDate.parse(endDate, dateFormatter);

            Set<DayOfWeek> weekend = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

            List<LocalDate> dates = new ArrayList<>();

            LocalDate date = start;
            while (date.isBefore(end)){
                if(!weekend.contains(date.getDayOfWeek())){
                    dates.add(date);
                }

                date = date.plusDays(1);
            }

            return dates.size();
        }
        catch (DateTimeParseException exception){
            String message = "Обнаружен неправильный формат даты при вычислении количества дней между датами:["
                    + startDate + " : " + endDate + "]";
            throw new WrongDateFormatException(message);
        }


    }

}
