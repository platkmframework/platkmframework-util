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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.platkmframework.util.error.InvocationException;
import org.platkmframework.util.reflection.ReflectionUtil;
  
/** 
 *   description: Clone util
 * 	 @author  Eduardo Iglesias
 *   contributors   Eduardo Iglesias - initial API and implementation
 */
public class CloneUtil {

	/**
	 * description: mapper
	 */
    public static Mapper mapper;

    static {
 
        mapper = new DozerBeanMapper();
    }

    /**
     * description: constructor
     */
    private CloneUtil() {
		super(); 
	}

	/**
     * Description: clone function
     * @param <E>: clone result
     * @param source: source object
     * @param cloneListsWithoutSet: clone list without set method
     * @param class1: class to clone
     * @return cloned object
     */
    public static <E> E clone(Object sourceObject, boolean cloneListsWithoutSet, Class<E> targetClass) {
    	
    	E targetObject = null;
    	try {
	    	//mapper = new DozerBeanMapper();
	        if (sourceObject == null) return null;
	        
	        //E object = mapper.map(source, class1);
	        targetObject = ReflectionUtil.createInstance1(targetClass);
	        Field targetField;
	        List<Field> sourceFields = ReflectionUtil.getAllFieldHeritage(sourceObject.getClass());
	        List<Field> targetFields = ReflectionUtil.getAllFieldHeritage(targetClass);
	        for (Field sourceField : sourceFields) {
	        	//find target field by source field name
	        	targetField = getFieldByName(targetFields, sourceField.getName());
	        	if(targetField != null && (targetObjectContainGETandSetMethod(targetField.getName(), targetObject))){
	        		if(sourceField.getType().isAssignableFrom(List.class)){
			            if (cloneListsWithoutSet) {
		                	List listSource = (List) ReflectionUtil.getAttributeValue(sourceObject, sourceField);
			                if(listSource != null) {
		                		List listTarget = (List) ReflectionUtil.createInstance(List.class);
		                		for (Object  childSourceObject : listSource) {
		                			listTarget.add(clone(childSourceObject, targetField.getType()));
		                		}
		                		ReflectionUtil.setAttributeValue(targetObject, targetField, listTarget);
		                	}
		                }
		        	}else if (sourceField.getType().getName().startsWith("java.")){
		        		setFieldValue(sourceField, sourceObject, targetField,targetObject);
		        	}else {
		        		clone(sourceField.get(sourceObject), targetField.getType());
		        	}
	        	}
	        }
    	}catch (Exception e) {
    		Logger.getLogger(CloneUtil.class.getName()).log(Level.SEVERE, null, e);
    	}
        return targetObject; 
    }

    private static void setFieldValue(Field sourceField, Object sourceObject, Field targetField, Object targetObject) throws InvocationException {
	    try {
	    	
	    	boolean sourceAccessValue = sourceField.canAccess(sourceObject);
	    	sourceField.setAccessible(true);
	    	
	    	boolean targetAccessValue = targetField.canAccess(targetObject);
	    	targetField.setAccessible(true); 
	    	
	    	targetField.set(targetObject, sourceField.get(sourceObject));
	    	
	    	sourceField.setAccessible(sourceAccessValue); 
	    	targetField.setAccessible(targetAccessValue); 
	    	
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new InvocationException("setting property error-> " + e.getMessage());
		} 
		
	}

	private static boolean targetObjectContainGETandSetMethod(String fieldName, Object targetObject) throws InvocationException {
    	String fieldFirtUpperCase = Util.firstUpperCase(fieldName);
    	return ReflectionUtil.exsitsMethod(targetObject, "set" + fieldFirtUpperCase) &&
    			(ReflectionUtil.exsitsMethod(targetObject, "get" + fieldFirtUpperCase) || ReflectionUtil.exsitsMethod(targetObject,"is" + fieldFirtUpperCase));
    		 
	}

	/**
     * description: get method by name
     * @param methods : method list
     * @param name: method name
     * return: method found
     */
    private static Field getFieldByName(List<Field> targetFields, String name) { 
		if(targetFields != null) {
			for (int i = 0; i < targetFields.size(); i++) {
				if(targetFields.get(i).getName().equals(name))  
					return targetFields.get(i);
			}
		}
		return null;
	}

    /**
     * description: clone function
     * @param <E> : object clone result
     * @param source: soure
     * @param class1: class result
     * @return  cloned object
     */
	public static <E> E clone(Object source, Class<E> class1) {
        return clone(source, true, class1);
    }
	
    /**
     * description: clone function
     * @param source
     * @param target
     */
	public static void clone(Object source, Object target) {
		mapper.map(source, target);
    }

	/**
	 * description: clone a list
	 * @param <E>: object type to clone
	 * @param collectionSource : list object to be clonse
	 * @param cloneCollectionWithoutSet: whether clone list
	 * @param class1: class type
	 * @return  cloned list
	 */
    public static <E>  List<E> cloneList(Collection<E> collectionSource, boolean cloneCollectionWithoutSet, Class<E> class1) {
        List<E> listTarget = new ArrayList<>();
        if (collectionSource != null) {
            for (Object object : collectionSource){
            	listTarget.add(clone(object, cloneCollectionWithoutSet,class1));
            }
        }
        return listTarget;
    }
 
    /**
     * description:list to be clone
     * @param <E>: class result
     * @param listSource: list resource
     * @param class1: class type
     * @return  cloned list
     */
    public static <E>  List<E>  cloneList(Collection<E>  listSource, Class<E> class1) {
        return cloneList(listSource, true, class1); 
    }
    
    /**
     * description: clone object by class 
     * @param <E>: class result type
     * @param o : source object
     * @param type: target type
     * @return  cloned object
     */
    public static <E> E  mapTo(Object o, Class<E> type){
        return mapper.map(o, type);
    }
    
    /**
     * description: map a list
     * @param <E>: class name
     * @param list: list to map
     * @param class1: class type
     * @return  cloned list
  	*/
	public static <E> List<E> mapperList(List<?>  list, Class<E> class1) 
	{
		List<E> listResult = new ArrayList<>();
		list.stream().forEach(fd -> listResult.add(mapper.map(fd, class1)));
	    return listResult;
	}
      
}   
