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
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils; 

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: general util
 */
public class Util {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final String ALPHA_NUMERIC_CAPPS_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	
	/**
	 * description: constructor
	 */
	public Util() {
		super();
	}

	/**
	 * description: first upper case
	 * @param value: value
	 * @return result string
	 */
	 public static  String firstUpperCase(String value) {

        if (value == null || "".equals(value)) {
            return value;
        }
        if (value.length() == 1) {
            return value.toUpperCase();
        }
        return (value.substring(0, 1)).toUpperCase() + value.substring(1);
    }   
	 
	 /**
	  * description: generate string length 8
	  * @return result string
	  */
	 public static String generateId() {
		 return generateId(8);
	 }
	 
	 /**
	  * description:: generate string 
	  * @param speed: size
	  * @return string generated
	  */
    public static String generateId(long speed) {
        Integer rand = new Random(speed).nextInt();
        if (rand < 0) {
            rand *= -1;
        }
        return String.valueOf(new GregorianCalendar().getTimeInMillis()) + rand;
    }	 
    
    /**
     * description: input stream to string
     * @param inputStream:  inputstream
     * @return result string
     * @throws IOException - error
     */
    public static String inputSteamToString( InputStream inputStream) throws IOException 
    {
    	StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, "UTF-8"); 
		return writer.toString(); 
    }


    /**
     * description: string to date
     * @param strDate: string date
     * @return date formatted
     * @throws ParseException - error
     */
	public static Date stringToDate(String strDate) throws ParseException 
	{
		DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
		return formatter.parse(strDate);
	}

	/**
	 * description: get file extension
	 * @param fileName: file name
	 * @return file extension
	 */
	public static String getFileExtension(String fileName) 
	{
		if(StringUtils.isEmpty(fileName))
			return null;
		
		int index = fileName.lastIndexOf(".");
		if(index <0)
			return null;
		return fileName.substring(index+1);
	}

	/**
	 * description: string to list by split char
	 * @param path: path
	 * @param splitChar : split char
	 * @return string list
	 */
	public static List<String> stringToList(String path, String splitChar) 
	{
		
		if(StringUtils.isEmpty(path)) return null;
		
		return Stream.of(path.split(splitChar))
			  .map(String::trim)
			  .map(String::valueOf)
			  .collect(Collectors.toList());
	}
	 
	/** 
	 * description: values to map
	 * @param values: string values
	 * @param defaultKey: default key
	 * @return map
	 */
	public static Map<String, Object> keyValueToMap(String values, boolean defaultKey)
	{
		return keyValueToMap(values,"\\r?\\n", defaultKey);
	}
	

	/**
	 * description values to map by split regex
	 * @param values: string
	 * @param splitRegex: split regex
	 * @param defaultKey: default value
	 * @return map
	 */
	public static Map<String, Object> keyValueToMap(String values, String splitRegex, boolean defaultKey)
	{
		Map<String, Object> m = new HashMap<>(); 
		if(StringUtils.isNotEmpty(values))
		{
			String[] splitValues = values.split(splitRegex);
			if(splitValues != null && splitValues.length>0)
			{
				String[] keyValue;  
				for (int i = 0; i < splitValues.length; i++)  
					if(defaultKey)
						m.put(String.valueOf(i), splitValues[i]);
					else
					{
						keyValue = splitValues[i].split("="); 
						if(keyValue != null && keyValue.length > 1) 
							m.put(keyValue[0], keyValue[1]); 
					} 
			} 
		}
		return m;
	}
	
	/**
	 * description list coma string
	 * @param list string
	 * @return string result
	 */
	public static String listToStringComa(List<String> list)
	{
		return listToStringComa(list, ",");
	}
	
	
	/**
	 * description: list to string coma	
	 * @param list: string list
	 * @param separtor:  string separator
	 * @return string result
	 */
	public static String listToStringComa(List<String> list, String separtor)
	{
		return list.stream() 
                .collect(Collectors.joining(separtor));
	}
	
	/**
	 * list to string with coma separator
	 * @param <I>: objec type
	 * @param list: list
	 * @return string result
	 */
	public static <I> String listObjectToStringColon(List<I> list)
	{
		return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
	}
	
	/**
	 * description string to long string
	 * @param stringComa: separator
	 * @return long string
	 */
	public List<Long> stringColonToLongList(String stringComa){
		
		if(StringUtils.isEmpty(stringComa)) return null;
		
		return Stream.of(stringComa.split(","))
			  .map(String::trim)
			  .map(Long::parseLong)
			  .collect(Collectors.toList());

	}

	/**
	 * description: random string
	 * @param count: count
	 * @param characters: characters
	 * @return string
	 */
	public static String randomString(int count, String characters) 
	{ 
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) 
		{
			int character = (int)(Math.random()*characters.length());
			builder.append(characters.charAt(character));
		}
		
		return builder.toString(); 
	}
	
	/**
	 * description: random alfa numeric string
	 * @param count: count
	 * @return string result
	 */
	public static String randomAlfaNumericString(int count) 
	{ 
		return randomString(count, ALPHA_NUMERIC_STRING);
	}
	
	/**
	 * random caps string
	 * @param count: count
	 * @return string result
	 */
	public static Object randomCapsString(int count) {
		return randomString(count, ALPHA_NUMERIC_CAPPS_STRING);
	}
	
	/**
	 * description: random string by size
	 * @param count: count
	 * @return string random
	 */
	public static String randomString(int count) {
		return randomString(count, ALPHA_STRING);
	}
	
	/**
	 * description: convert to list type
	 * @param <T> : type
	 * @param array: array
	 * @return list
	 */
    public static <T> List<T> convertArrayToList(T array[]) 
    {  
        List<T> list = new ArrayList<>();  
        for (T t : array) 
            list.add(t); 
        return list; 
    } 
    
    
    /**
     * description:  check arguments
     * @param value : value
     * @param args: arguments
     * @return  string result
     */
	public static String checkingArgs(String value, String[] args) {
        if(args != null)
        {
        	String[] keyValue;
        	for (int i = 0; i < args.length; i++) {
        		keyValue = args[i].split("=");
        		value = value.replaceAll("(?i)"+Pattern.quote("${" + keyValue[0] + "}"), keyValue[1]);
			}
        }
        return value; 
	}    
	
	
	/**
	 * description check properties arguments
	 * @param value: value
	 * @param props: properties
	 * @return string result
	 */
	public static String checkingArgs(String value, Properties props) {
         
		String propValue;
		for(String key : props.stringPropertyNames()) {
			propValue = props.getProperty(key);
			value = value.replaceAll("(?i)"+Pattern.quote("${" + key + "}"),propValue);
		} 
        return value; 
	} 
	
	/**
	 * description: replace from map key value
	 * @param map: map
	 * @param str: string
	 * @return string result
	 */
	public static String replace(Map<String,String> map, String str) {
		
		if(map != null)
			 for (Map.Entry<String,String> entry : map.entrySet())  
				 str = str.replace(entry.getKey(), entry.getValue()); 
		
		return str;
	}

	/**
	 * description: check contains alfa numeric
	 * @param str: string
	 * @return boolean
	 */
	public static boolean isAlphaNumeric(String str) { 
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		return pattern.matcher(str).matches();
	}	
	
	/**
	 * description: fix to entity name
	 * @param name name
	 * @return fixed name
	 */
    public static String fixToEntityName(String name) {
        return fixName(name, null, true);
    }
    
    /**
     * description: fix to attribute name
     * @param name: name
     * @return fixed name
     */
    public static String fixToAttributeName(String name) {
        return fixName(name, null, false);
    }
    
    /**
     * description: fix name
     * @param name: name
     * @param extraCharacters: extra characters
     * @param firstUpperCase: whether first should be upper case
     * @return name fixed
     */
    public static String fixName(String name, String extraCharacters,  boolean firstUpperCase) {
        String result = "";
        String pattern ="abcdefghijklmnopqrstuvwxyz";
        String patternNumber ="1234567890";
        
        if(StringUtils.isNotEmpty(extraCharacters)){
            pattern+=extraCharacters;
        }
        
        if(StringUtils.isNotEmpty(name) && !"".equals(name.trim()) ){
           String auxName = name.trim();
           
           //todo en minuscula
           auxName = auxName.toLowerCase();
           boolean primerCaractOk = false;
           boolean nextUpperCase  = false;
           for (int i = 0; i < auxName.length(); i++) {
                char  varChar = auxName.charAt(i);
                if(pattern.contains(String.valueOf(varChar))){
                    //es un caracter alfa numerico
                    if(!primerCaractOk){
                        if(firstUpperCase){
                             varChar = Character.toUpperCase(varChar);
                        }else{
                            varChar = Character.toLowerCase(varChar);
                        }
                        result  = result + String.valueOf(varChar);
                        primerCaractOk = true;
                        nextUpperCase = false;
                    }else{
                        if(nextUpperCase){
                            varChar = Character.toUpperCase(varChar);
                            result  = result + String.valueOf(varChar);
                            nextUpperCase = false;
                        }else{
                            result  = result + String.valueOf(varChar);
                        }
                    }
                }else{
                    if(i>0 && patternNumber.contains(String.valueOf(varChar)))
                    {
                       //es un numero y no es el primer caracter, se deja adicionar
                       result  = result + String.valueOf(varChar);
                    }else
                    {
                       //no es alfa numerico y se marca que el proximo va en mayuscula
                       nextUpperCase = true;
                    }
                    
                } 
            }
            
           if(StringUtils.isEmpty(result)){
               result = "No Name Found";
           }
           
        }
        
        return result;
     }
    
    /**
     * description: is valid phone
     * @param phoneNumber: string phone number
     * @return whether valid phone number
     */
    public static boolean isValidCellPhone(String phoneNumber) {
    	Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
        Matcher matcher = pattern.matcher(phoneNumber); 
        return matcher.matches();
    }
    
    /**
     * description: languaje type
     * @param languageType: language type
     * @return class result
     * @throws ClassNotFoundException - error
     */
    public static Class<?> getLanguageType(String languageType) throws ClassNotFoundException { 
			return Class.forName(languageType); 
	}
    
    /**
     * description: convert inputstream to string
     * @param inputStream: input stream
     * @return string result
     * @throws IOException - error
     */
    public static String multipartToString(InputStream inputStream) throws IOException {
    	return IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
    }
    
	/*
	 * public static void main(String[] a) {
	 * System.out.println(randomCapsString(255)); }
	 */
	
}
