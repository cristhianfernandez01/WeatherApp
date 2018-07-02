package com.testidea1.data.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;

public class JsonSerializer {

    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> List<T> mapJsonToObjectList(String json, Class clazz) throws Exception
    {
        TypeFactory t = TypeFactory.defaultInstance();

        return mapper.readValue(json, t.constructCollectionType(ArrayList.class,clazz));
    }

    public static <T> String entityToJson(T entity) throws Exception{

        return mapper.writeValueAsString(entity);
    }

    public static <T> String entityToJson(List<T> entity) throws Exception{

        return mapper.writeValueAsString(entity);
    }

    public static <T> T jsonToEntity(String json, Class<T> clazz) throws Exception{

        return mapper.readValue(json, clazz);
    }

}
