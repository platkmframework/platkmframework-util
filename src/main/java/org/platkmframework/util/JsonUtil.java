/*******************************************************************************
 * Copyright(c) 2023 the original author Eduardo Iglesias Taylor.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	 https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * 	Eduardo Iglesias Taylor - initial API and implementation
 *******************************************************************************/
package org.platkmframework.util;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule; 

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: JSON util 
 *
 */
public class JsonUtil 
{
	/**
	 * description: mapper
	 */
	private static ObjectMapper mapper;
	 
	
	/**
	 * description: constructor
	 */
	private JsonUtil() {
		super();
	}

	/**
	 * description: mapper
	 * @param mapper: mapper
	 * @return mapper
	 */
	public static ObjectMapper setObjectMapper(ObjectMapper mapper) {
		return JsonUtil.mapper = mapper;
	}
	
	/**
	 * description: init
	 */
	public static void init() {
		
		 mapper = JsonMapper.builder() // or different mapper for other format
				   .addModule(new ParameterNamesModule())
				   .addModule(new Jdk8Module())
				   .addModule(new JavaTimeModule())
				   .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
				   .build();
		 mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	/**
	 * description: string to object
	 * @param <E>: object
	 * @param json: string
	 * @param class1: class
	 * @return object result
	 * @throws JsonException : error
	 */
    public static <E> E jsonToObject(String json, Class<E> class1) throws JsonException
    { 
		 try 
		 {  
			return mapper.readValue(json, class1);
			
		} catch (IOException e) 
		{ 
			e.printStackTrace();
			throw new JsonException(e.getMessage());
		} 
    }
    /**
     * description: create object from references
     * @param <E>: class result
     * @param json: JSON string
     * @param TypeReference: reference
     * @return object from JSON string
     * @throws JsonException : error
     */
    public static <E> E jsonToObjectTypeReference(String json, TypeReference<E> TypeReference) throws JsonException
    { 
		 try 
		 {  
			return mapper.readValue(json, TypeReference);
			
		} catch (IOException e) 
		{ 
			e.printStackTrace();
			throw new JsonException(e.getMessage());
		} 
    }
    
	/**
	 * description: jston to object list
	 * @param <E>: class type
	 * @param json: string json
	 * @param class1: class type
	 * @return list result
	 * @throws JsonException - error
	 */
	public static <E> List<E> jsonToListObject(String json,  Class<E> class1) throws JsonException{
		try 
		 {  
			JavaType type = mapper.getTypeFactory().constructCollectionType(List.class,class1);
			return mapper.readValue(json, type); 
		
		 } catch (IOException e) 
		 { 
			e.printStackTrace();
			throw new JsonException(e.getMessage());
		} 
	}	

	/**
	 * description: object to json
	 * @param result: objcet
	 * @return json string
	 * @throws JsonException : error
	 */
	public static String objectToJson(Object result) throws JsonException{
		 try{
			 
			 if(result == null || StringUtils.isEmpty(result.toString().trim())) return "";
			 if(result instanceof String) return result.toString();
			   
			return mapper.writeValueAsString(result);
			
		} catch (IOException e){ 
			e.printStackTrace();
			throw new JsonException(e.getMessage());
		}
	}

}
