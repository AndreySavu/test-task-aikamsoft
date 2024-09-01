package ru.projects.test_task_aikamsoft.parser.util;

import ru.projects.test_task_aikamsoft.utils.error.WrongDateFormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
    private final String dateFormat;

    public DateValidator(String dateFormat){
        this.dateFormat = dateFormat;
    }

    public void validateDateStr(String dateStr) throws WrongDateFormatException{
        DateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);

        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(dateStr);
        }
        catch (ParseException exception){
            String message = "Неправильный формат даты! Строка:[" + dateStr + "]";
            throw new WrongDateFormatException(message);
        }
    }

    public void checkStartDateBeforeEndDate(String start, String end) throws WrongDateFormatException {
        DateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
        simpleDateFormat.setLenient(false);

        try {
            if(!simpleDateFormat.parse(start).before(simpleDateFormat.parse(end))){
                String message = "Неправильные даты начала и конца периода! Дата начала должна идти раньше даты конца!";
                throw new WrongDateFormatException(message);
            }
        }
        catch (ParseException exception){
            String message = "Неправильный формат даты!";
            throw new WrongDateFormatException(message);
        }
    }
}
