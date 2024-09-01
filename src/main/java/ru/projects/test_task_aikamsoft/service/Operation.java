package ru.projects.test_task_aikamsoft.service;

import ru.projects.test_task_aikamsoft.utils.error.CriteriaNotFoundException;
import ru.projects.test_task_aikamsoft.utils.error.WrongDateFormatException;
import ru.projects.test_task_aikamsoft.result.Result;

import java.sql.SQLException;

public interface Operation {
    Result performOperation() throws SQLException, CriteriaNotFoundException, WrongDateFormatException;
}
