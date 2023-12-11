package com.murphy.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.List;

/**
 * JsonUtils
 */
public class JsonUtils {


	//private static final ObjectMapper MAPPER = new I18NObjectMapper();
	private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
    	MAPPER.setSerializationInclusion(Include.NON_NULL); 
    	MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); 
    }
    /**
     *	 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJsonWhitI18N(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
   public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
	/**
	 * 将json结果集转化为对象
	 *
	 * @param jsonData json数据
	 * @return
	 */
	public static <T> T jsonToPojo(Object jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(objectToJson(jsonData), beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    


}
