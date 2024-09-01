package ru.projects.test_task_aikamsoft.result.serialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.projects.test_task_aikamsoft.result.ErrorResult;

import java.lang.reflect.Type;

public class ErrorResultSerializer implements JsonSerializer<ErrorResult> {

    @Override
    public JsonElement serialize(ErrorResult result, Type type, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.add("type", context.serialize(result.getType()));
        json.add("message", context.serialize(result.getMessage()));
        return json;
    }
}
