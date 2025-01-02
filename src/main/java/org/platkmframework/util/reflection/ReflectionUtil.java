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
package org.platkmframework.util.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List; 

import org.apache.commons.lang3.StringUtils;
import org.platkmframework.util.Util;
import org.platkmframework.util.error.InvocationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: reflection util
 */
public class ReflectionUtil {

	private static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);
	
	
	
	/**
	 * description: constructor
	 */
	public ReflectionUtil() {
		super();
	}

	/**
     * This method allow to create an intance of object whoose class was
     * specific in the className atribute
     * @param className: class name
     * @return object
	 * @throws InvocationException - error
     */
    public static Object createInstance(String className) throws InvocationException{
       Object instance = null;
         
            Constructor<?> constructor;
			try {
				constructor = Class.forName(className).getConstructor();
				instance = constructor.newInstance();
			} catch (NoSuchMethodException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			 
				e.printStackTrace(); 
				throw new InvocationException(e.getMessage());
			}
            
            
        
        return instance;
    }
    
    /**
     * description: create instance
     * @param className: class name
     * @param parameterTypes: parameters types
     * @param initargs: initial arguments
     * @return object
     * @throws ClassNotFoundException - error
     * @throws InvocationException - error
     */
    public static Object createInstance(String className, Class<?>[] parameterTypes, Object[] initargs) throws ClassNotFoundException, InvocationException 
    {
   
        return createInstance(Class.forName(className), parameterTypes, initargs);
    }    

    
    /**
     * description: create instance
     * @param clazz: class
     * @param parameterTypes: parameters type
     * @param initargs: initial arguments
     * @return object
     * @throws InvocationException - error
     */
    public static Object createInstance(Class<?> clazz, Class<?>[] parameterTypes, Object[] initargs) throws InvocationException 
    {
        Constructor<?> constructor;
		try {
			constructor = clazz.getConstructor(parameterTypes);
			Object instance = constructor.newInstance(initargs);
			return instance;
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		 
			e.printStackTrace();
			throw new InvocationException(e.getMessage());
		} 
    }
    
    /**
     * description: instance
     * @param constructor: constructor
     * @param initargs: initial arguments
     * @return object
     * @throws InvocationException - error
     */
    public static Object createInstance(Constructor<?> constructor, Object[] initargs) throws InvocationException 
    { 
		try { 
			Object instance = constructor.newInstance(initargs);
			return instance;
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		 
			e.printStackTrace();
			throw new InvocationException(e.getMessage());
		} 
    } 
    
    /**
     * description: create generic instance
     * @param <E>: model
     * @param constructor: constructor
     * @param initargs: initial arguments
     * @return object
     * @throws InvocationException - error
     */
    public static <E> E createGenericInstance(Constructor<E> constructor, Object[] initargs) throws InvocationException 
    { 
		try { 
			return  constructor.newInstance(initargs); 
		} catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
		 
			e.printStackTrace();
			throw new InvocationException(e.getMessage());
		} 
    } 
    
    /**
     * description: create instance
     * @param classType: class type
     * @return object
     * @throws InvocationException - error
     */
    public static Object createInstance(Class<?>  classType) throws InvocationException
    { 
        try {
            return classType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
        	ex.printStackTrace();
			throw new InvocationException(ex.getMessage());
        } 
    }
    
    /**
     * description: create instance
     * @param <E>: model
     * @param classType: class type
     * @return object
     * @throws InvocationException - error
     */
    public static <E>  E createInstance1(Class<E>  classType) throws InvocationException
    { 
        try {
            return classType.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
        	ex.printStackTrace();
			throw new InvocationException(ex.getMessage());
        } 
    }    
 
    
    /**
     * description: invoke method
     * @param object: object
     * @param method: method
     * @return object
     * @throws InvocationException - error
     */
    public static Object invokeMethodByName(Object object, String method) throws InvocationException  { 
        return invokeMethodByName(object, method, null);
    }
    
    /**
     * description: invoke method
     * @param object: object
     * @param methodName: method name
     * @param args: arguments
     * @return  object
     * @throws InvocationException  - error
     */
    public static Object invokeMethodByName(Object object, String methodName, Object[] args) throws InvocationException {
        try {
            //buscando el metodo\
            Method method = null;
            for (Method method1 : object.getClass().getMethods()) {
                if (method1.getName().equals(methodName)) {
                    method = method1;
                    break;
                }
            } 
            if(method!=null){
                if(args!=null){
                    return  method.invoke(object, args); 
                }else{
                     return  method.invoke(object);
                }
            }
        } catch (InvocationTargetException ex) {
        	if(ex.getTargetException() != null)
        		throw  new InvocationException(ex.getTargetException());
        	throw new InvocationException(ex.getMessage());
        } catch (SecurityException | IllegalAccessException | IllegalArgumentException ex) {
        	ex.printStackTrace();
        	throw new InvocationException("error en el proceso, por favor, consulte al administrador");
        }
        return null;
    }
    
    
    /**
     * description: set value using method
     * @param object objeto a ejecutar a ejecutar el metodo
     * @param methodName metodo
     * @param value  valor como parametro al metodo
     * @throws InvocationException  - error
     */
    public static void setValue(Object object, String methodName, Object value) throws InvocationException { 
        try {
            
            //buscar el metodo
            Method methodExec = null;
            for (Method method : object.getClass().getMethods()) {
                if(method.getName().equals(methodName)){
                    methodExec = method;
                    break;
                }
            }
            if(methodExec!=null){
                methodExec.invoke(object,value);
            }
        } catch ( SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
        	throw new InvocationException(ex.getMessage());
        }   
   }     
    
    /**
     * description: whether method name exists
     * @param object: object
     * @param methodName: method name
     * @return  whether method exists
     * @throws InvocationException - error
     */
    public static boolean exsitsMethod(Object object, String methodName ) throws InvocationException { 
        
        boolean exists = false;
        try {
            
            //buscar el metodo 
            for (Method method : object.getClass().getMethods()) {
                if(method.getName().equals(methodName)){
                    exists = true;
                    break;
                }
            } 
        } catch ( SecurityException ex) {
        	throw new InvocationException(ex.getMessage());
        }  
        
        return exists;
   } 
    
   /**
    * description: get method
    * @param field: field
    * @param object: object
    * @return method
    * @throws InvocationException  Invocation Exception
    */
   public static Method getAtributeGETmethod(Object object, Field field ) throws InvocationException { 
         
        try 
        { 
        	String methodName = getAttributeGETmethodName(field);
            //buscar el metodo 
            for (Method method : object.getClass().getMethods())
                if(method.getName().equals(methodName))
                   return method;  
        } catch ( SecurityException ex) {
        	throw new InvocationException(ex.getMessage());
        }   
        return null;
   } 
     
    
   /**
    * description: get attribute value
    * @param attributeName: attribute name
    * @param object: object
    * @return attribute value
    * @throws Exception Exception
    */
    public static Object getAttributeValue(Object object, String attributeName ) throws Exception 
    { 
        try {
            
            //buscar el metodo
            Field fieldFound = null;
            List<Field> fields = getAllFieldHeritage(object.getClass());
            for (Field field : fields)  
                if(field.getName().equals(attributeName)){
                    fieldFound = field;
                    break;
                } 
            
            return getAttributeValue(object, fieldFound); 
            
        } catch ( SecurityException | InvocationException ex) {
        	throw new InvocationException(ex.getMessage());
        }   
         
   }
    
    /**
     * description: get attribute value
     * @param obj: object
     * @param field: field
     * @return value
     * @throws InvocationException - error
     */
    public static Object getAttributeValue(Object obj, Field field ) throws InvocationException 
    {  
    	return invokeMethodByName(obj, getAttributeGETmethodName(field));
    }
    
    /**
     * description: conform field method name
     * @param field
     * @return method name
     * @throws InvocationException
     */
    public static String getAttributeGETmethodName(Field field) throws InvocationException 
    { 
    	if(field != null) 
    		if(field.getType().getName().equals(Boolean.class.getName()) || field.getType().isAssignableFrom(Boolean.class)) {
    			if(field.getType().isPrimitive()) {
    				return "is" + Util.firstUpperCase(field.getName());
    			}else {
    				return "get" + Util.firstUpperCase(field.getName());
    			}
            }else{
                return "get" + Util.firstUpperCase(field.getName());
            }
    	else
    		return null;
    }


    /**
     * description: set attribute value
     * @param object: object
     * @param attributeName: attribute name
     * @param value : value 
     * @throws InvocationException - error
     */
    public static void setAttributeValue(Object object, String attributeName, Object value) throws InvocationException {
        try{ 
            //buscar el metodo
            Field fieldFound = null;
            List<Field> fields = ReflectionUtil.getAllFieldHeritage(object.getClass());
            for (Field field : fields)
                if(field.getName().equals(attributeName)){
                    fieldFound = field;
                    break;
                }
            if(fieldFound != null)
            	setAttributeValue(object, fieldFound, value);
            
        } catch ( SecurityException ex) {
        	throw new InvocationException(ex.getMessage());
        } 
    }
    
    /**
     * description: set attribute value
     * @param object: object
     * @param field: field
     * @param value: value  
     * @throws InvocationException 
     */
    public static void setAttributeValue(Object object, Field field, Object value) throws InvocationException {
    	setAttributeValue(object, field, value, true ); 
    } 
    
    /**
     * description: set attribute value
     * @param object: object
     * @param field: field
     * @param value: value
     * @param withtype: whether check type 
     * @throws InvocationException 
     */
    public static void setAttributeValue(Object object, Field field, Object value, boolean withtype ) throws InvocationException 
    {
    	try{  
    		String attributeName = "set" + Util.firstUpperCase(field.getName());
    		Method method = getMethodByNameAndHeritage(object.getClass(), attributeName, false); 
    		if (method == null) 
    			throw new InvocationException("método no encontrado " + attributeName);
    		if(!withtype) {
    			method.invoke(object, value);  
    		}else{  
    			Object oValue = null;
    			Boolean isEmpty = (value==null || StringUtils.isEmpty(value.toString()));  
	            if(field.getType().getName().equals(Boolean.class.getName()) || field.getType().isAssignableFrom(Boolean.class)){
	            	if(isEmpty){
	            		oValue = Boolean.FALSE;
	            	}else{
	            		oValue = Boolean.parseBoolean(value.toString());
	            	}
	        	//}else if(!field.getType().isPrimitive()) {
	        	//	method.invoke(object, value);  
	            }else if(field.getType().getName().equals(Byte.class.getName()) || field.getType().isAssignableFrom(Byte.class)){ 
	            	if(isEmpty){
	            		if(field.getType().isPrimitive()) {
	            			oValue = 0;
	            		}else {
	            			oValue = null;
	            		}
	            	}else{
	            		oValue = Byte.valueOf(value.toString());
	            	} 
	            }else if(field.getType().getName().equals(Character.class.getName()) || field.getType().isAssignableFrom(Character.class)) {
	            	if(isEmpty){
	            		if(field.getType().isPrimitive()) {
	            			oValue = "0";
	            		}else {
	            			oValue = null;
	            		}
	            	}else{
	            		oValue = Character.valueOf(value.toString().charAt(0));
	            	} 
	            }else if (field.getType().getName().equals(Short.class.getName()) ||  field.getType().isAssignableFrom(Short.class)) {
	            	if(isEmpty){
	            		if(field.getType().isPrimitive()) {
	            			oValue = 0;
	            		}else {
	            			oValue = null;
	            		}
	            	}else{
	            		oValue = Short.parseShort(value.toString());
	            	}  
	            }else if(field.getType().getName().equals(Integer.class.getName()) || field.getType().isAssignableFrom(Integer.class)){ 
	            	if(isEmpty){
	            		if(field.getType().isPrimitive()) {
	            			oValue = 0;
	            		}else {
	            			oValue = null;
	            		}
	            	}else{
	            		oValue = Integer.parseInt(value.toString());
	            	} 
	            }else if(field.getType().getName().equals(Long.class.getName()) || field.getType().isAssignableFrom(Long.class)){
	            	if(isEmpty){
	            		if(field.getType().isPrimitive()) {
	            			oValue = 0;
	            		}else {
	            			oValue = null;
	            		}
	            	}else{
	            		oValue = Long.parseLong(value.toString());
	            	}  
	            }else if(field.getType().getName().equals(Float.class.getName()) || field.getType().isAssignableFrom(Float.class)) {
	            	if(isEmpty){
	            		if(field.getType().isPrimitive()) {
	            			oValue = 0;
	            		}else {
	            			oValue = null;
	            		}
	            	}else{
	            		oValue = Float.parseFloat(value.toString());
	            	} 
	            }else if(field.getType().getName().equals(Double.class.getName()) || field.getType().isAssignableFrom(Double.class)) { 
	            	if(isEmpty){
	            		if(field.getType().isPrimitive()) {
	            			oValue = 0;
	            		}else {
	            			oValue = null;
	            		}
	            	}else{
	            		oValue = Double.parseDouble(value.toString());
	            	}  
	            }
	            method.invoke(object, oValue);
    		}
    		
		} catch ( SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			//throw new ReflectionError(e.getMessage());
			logger.warn( "-->column not found " + e.getMessage()); 
		}	 
    }
 
    
	/**
	 * description: get attribute value
	 * @param field: field
	 * @param value: value
	 * @return object field value
	 * @throws InvocationException - error
	 */
    public static Object getRealAttributeValue(Field field, Object value) throws InvocationException 
    {
    	try 
    	{   
    		 
            if(field.getType().getName().equals(Boolean.class.getName()) || field.getType().isAssignableFrom(Boolean.class)){
            	return StringUtils.isEmpty(value.toString())?Boolean.FALSE: Boolean.parseBoolean(value.toString());
        	}else if(!field.getType().isPrimitive()) {
        		return value;  
            }else if(field.getType().isAssignableFrom(Byte.class) || field.getType().getSimpleName().equalsIgnoreCase(Byte.class.getSimpleName()))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Byte.parseByte(value.toString());
            else if( field.getType().isAssignableFrom(Character.class) || field.getType().getSimpleName().equalsIgnoreCase(Character.class.getSimpleName()))
            	return value==null || StringUtils.isEmpty(value.toString())?"": (Character) value;
            else if ( field.getType().isAssignableFrom(Short.class) || field.getType().getSimpleName().equalsIgnoreCase(Short.class.getSimpleName()))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Short.parseShort(value.toString());
            else if(field.getType().isAssignableFrom(Integer.class) || field.getType().getSimpleName().equalsIgnoreCase(Integer.class.getSimpleName()))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Integer.parseInt(value.toString());
            else if( field.getType().isAssignableFrom(Long.class) || field.getType().getSimpleName().equalsIgnoreCase(Long.class.getSimpleName()))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Long.parseLong(value.toString());
            else if(field.getType().isAssignableFrom(Float.class) || field.getType().getSimpleName().equalsIgnoreCase(Float.class.getSimpleName()))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Float.parseFloat(value.toString());
            else if(field.getType().isAssignableFrom(Double.class) || field.getType().getSimpleName().equalsIgnoreCase(Double.class.getSimpleName())) 
            	return value==null || StringUtils.isEmpty(value.toString())?0: Double.parseDouble(value.toString());
            
            return null;
		} catch (SecurityException | IllegalArgumentException e) {
			//throw new ReflectionError(e.getMessage());
			logger.error( "-->column not found " + e.getMessage());
			throw new InvocationException("error reflection value");
		} 
    	 
    }
    
    /**
     * get Real Primitive Value
     * @param javaClassType class type
     * @param value value
     * @return real value from field
     * @throws InvocationException Invocation Exception
     */
    public static Object getRealPrimitiveValue(Class<?>  javaClassType, Object value) throws InvocationException 
    {
    	try 
    	{   
    		 
            if(javaClassType.getName().equals(Boolean.class.getName()) || javaClassType.isAssignableFrom(Boolean.class)){
            	return StringUtils.isEmpty(value.toString())?Boolean.FALSE: Boolean.parseBoolean(value.toString());
        	}
            //else if(!javaClassType.isPrimitive()) {
        //		return value;  
            //}else 
            	if(javaClassType.isAssignableFrom(Byte.class))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Byte.parseByte(value.toString());
            else if( javaClassType.isAssignableFrom(Character.class))
            	return value==null || StringUtils.isEmpty(value.toString())?"": (Character) value;
            else if ( javaClassType.isAssignableFrom(Short.class))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Short.parseShort(value.toString());
            else if(javaClassType.isAssignableFrom(Integer.class))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Integer.parseInt(value.toString());
            else if( javaClassType.isAssignableFrom(Long.class))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Long.parseLong(value.toString());
            else if(javaClassType.isAssignableFrom(Float.class))
            	return value==null || StringUtils.isEmpty(value.toString())?0: Float.parseFloat(value.toString());
            else if(javaClassType.isAssignableFrom(Double.class)) 
            	return value==null || StringUtils.isEmpty(value.toString())?0: Double.parseDouble(value.toString());
            
            return value;
		} catch (SecurityException | IllegalArgumentException e) {
			//throw new ReflectionError(e.getMessage());
			logger.error("-->column not found " + e.getMessage());
			throw new InvocationException("error reflection value");
		} 
    	 
    }
    
    /**
     * description: get real value from field
     * @param field: field
     * @param value: value
     * @return value real value
     */
    public static Object getRealValueFromField(Field field, Object value){
    
        Object returnValue = value;
        if(field != null){
        	if(field.getType().getName().equals(Boolean.class.getName()) || field.getType().isAssignableFrom(Boolean.class)){
        	
        		if(value==null || StringUtils.isEmpty(value.toString().trim())) return Boolean.FALSE;
        	
        	}else if(field.getType().isPrimitive() && value==null){
               if(field.getType().isAssignableFrom(Byte.class) ||
                        field.getType().isAssignableFrom(Character.class) ||
                        field.getType().isAssignableFrom(Short.class) ||
                        field.getType().isAssignableFrom(Integer.class) ||
                        field.getType().isAssignableFrom(Long.class) ||
                        field.getType().isAssignableFrom(Float.class) ||
                        field.getType().isAssignableFrom(Double.class) 
                        
                        ){
                    returnValue = 0;
                }
                
            }//byte, char, short, int, long, float, and double.
        }
        
        return returnValue;
    }
    
    /**
     * 
     * @param classType class type
     * @return whether primitive
     */
    public static boolean isPrimitiveType(Class<?> classType){
    	
    	return classType.isAssignableFrom(Byte.class) ||
                classType.isAssignableFrom(Character.class) ||
                classType.isAssignableFrom(Short.class) ||
                classType.isAssignableFrom(Integer.class) ||
                classType.isAssignableFrom(Long.class) ||
                classType.isAssignableFrom(Float.class) ||
                classType.isAssignableFrom(Double.class)||
                classType.isAssignableFrom(Boolean.class);
    }
    
    /**
     * primitive To Object Type
     * @param javaType class tpe
     * @return
     */
    public static Class<?> primitiveToObjectType(Class<?> javaType){

    	if(javaType.isPrimitive() && javaType.getComponentType() != null){
    		if(javaType.getComponentType().getName().equals(Byte.class.getSimpleName().toLowerCase())){
    			
    		}else if(javaType.getComponentType().getName().equals(Character.class.getSimpleName().toLowerCase())){
    			return Character.class;
        	}else if(javaType.getComponentType().getName().equals(Short.class.getSimpleName().toLowerCase())){
        		return Short.class;
        	}else if(javaType.getComponentType().getName().equals(Integer.class.getSimpleName().toLowerCase())){
        		return Integer.class;
        	}else if(javaType.getComponentType().getName().equals(Long.class.getSimpleName().toLowerCase())){
        		return Long.class;
        	}else if(javaType.getComponentType().getName().equals(Float.class.getSimpleName().toLowerCase())){
        		return Float.class;
        	}else if(javaType.getComponentType().getName().equals(Double.class.getSimpleName().toLowerCase())){
        		return Double.class;
        	}else if(javaType.getComponentType().getName().equals(Character.class.getSimpleName().toLowerCase())){
        		return Short.class;
        	}else if(javaType.getComponentType().getName().equals(Boolean.class.getSimpleName().toLowerCase())){
        		return Boolean.class;
        	} 
        }
    
        return javaType;
    }

    /**
     * description: invoke method
     * @param ob: object
     * @param method: method
     * @param args: arguments
     * @return object
     * @throws InvocationException  Invocation Exception
     */
	public static Object invokeMethod(Object ob, Method method,  Object[] args) throws InvocationException 
	{
		try {
			return method.invoke(ob, args);
		} catch (IllegalAccessException | IllegalArgumentException e) 
		{
			e.printStackTrace(); 
			throw new InvocationException(e.getMessage());
		
		}catch (InvocationTargetException inve)
		{ 
			inve.printStackTrace(); 
			if(inve.getTargetException() != null) {
				throw new InvocationException(inve.getTargetException());
			}else {
				throw new InvocationException(inve.getMessage());
			} 
		}
	}	
	
	/**
	 * description: get all field heritage
	 * @param class1: class
	 * @return field list
	 */
	public static List<Field> getAllFieldHeritage(Class<?> class1)
	{
		return getAllFieldHeritage(class1, false);
	}
	
	/**
	 * description: get all field heritage
	 * @param class1: class
	 * @param excludeStatic: whether exclude static
	 * @return field list
	 */
	public static List<Field> getAllFieldHeritage(Class<?> class1, boolean excludeStatic)
	{
		List<Field> listFields = new ArrayList<>(); 
		Field[] fields = class1.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) 
		{
			Field f = fields[i];  
			if(!java.lang.reflect.Modifier.isFinal(f.getModifiers()))  
				listFields.add(f); 
		}  
		Class<?> superC = class1.getSuperclass();
		if(superC != null)
			listFields.addAll(getAllFieldHeritage(superC, excludeStatic));
		
		return listFields; 
	}
	
	
	/**
	 *  get All Methodd Heritage
	 * @param class1
	 * @return
	 */
	public static List<Method> getAllMethoddHeritage(Class<?> class1)
	{
		return getAllMethoddHeritage(class1, false);
	}
	
	
	/**
	 * get All Method Heritage
	 * @param class1
	 * @param excludeStatic
	 * @return
	 */
	public static List<Method> getAllMethoddHeritage(Class<?> class1, boolean excludeStatic)
	{
		List<Method> listMethods = new ArrayList<>(); 
		Method[] methods = class1.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) 
		{
			Method m = methods[i];  
			if(!java.lang.reflect.Modifier.isFinal(m.getModifiers()))  
				listMethods.add(m); 
		}  
		Class<?> superC = class1.getSuperclass();
		if(superC != null)
			listMethods.addAll(getAllMethoddHeritage(superC, excludeStatic));
		
		return listMethods; 
	}
	
	/**
	 * get Method By Name And Heritage
	 * @param class1
	 * @param methodName
	 * @param excludeStatic
	 * @return
	 */
	public static Method getMethodByNameAndHeritage(Class<?> class1, String methodName, boolean excludeStatic)
	{ 
		Method[] methods = class1.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) 
		{
			Method m = methods[i];  
			if(!java.lang.reflect.Modifier.isFinal(m.getModifiers()) && m.getName().equals(methodName)) return m;
				 
		}  
		Class<?> superC = class1.getSuperclass();
		if(superC != null)
			return getMethodByNameAndHeritage(superC, methodName, excludeStatic);
		else
			return null; 
	}
	
}
