package org.platkmframework.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils; 
import org.platkmframework.util.reflection.ReflectionUtil;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class ClassToJsonUtil {

	
	/**public static String process(Class<?> pclass) {
		try {
			return JsonUtil.objectToJson(classToAttributeTypeMap(pclass));
		} catch (JsonException e) { 
			e.printStackTrace();
		}
		return "";
	}
	*/
	
	public static List<Map<String, Object>> process(Class<?> pclass) throws JsonException{
		//Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> results = new ArrayList<>();
		results.add(new HashMap<>());
		results.add(new HashMap<>());
		
		List<Field> fields = ReflectionUtil.getAllFieldHeritage(pclass);
		if(fields != null) {
			List<Class<?>> allSuperInterfaces;
			String additionals;
			String typeName;
			List<Map<String, Object>> children;
			for (Field field : fields){
				allSuperInterfaces = ClassUtils.getAllInterfaces(field.getType());
				if(field.getType().isArray()){
					
					children = process(field.getType().getComponentType());
					results.get(0).put(field.getName(), new Object[] {children.get(0)});
					results.get(1).put(field.getName(), new Object[] {children.get(1)});
					//result.put(field.getName(), new Object[] {classToAttributeTypeMap(field.getType().getComponentType())});
					
				}else if(field.getType().isPrimitive()) {
					
					typeName = field.getType().getSimpleName().toLowerCase();
					
					additionals = (field.isAnnotationPresent(NotBlank.class)? "requerido,":"") +
							 	  (field.isAnnotationPresent(Min.class)? "valor mínimo:" + field.getAnnotation(Min.class).value() + ",":"") +
							 	  (field.isAnnotationPresent(Pattern.class)? "valores admitidos:" + field.getAnnotation(Pattern.class).regexp():"");
				
					results.get(0).put(field.getName(), typeName); 
					results.get(1).put(field.getName(), typeName + (StringUtils.isNotBlank(additionals)? " (" + additionals + ")":"")); 
			
				}else if( allSuperInterfaces!= null && allSuperInterfaces.contains(Iterable.class)){
					
					if(((ParameterizedType) field.getGenericType()) != null  && 
							((ParameterizedType) field.getGenericType()).getActualTypeArguments() != null && 
							((ParameterizedType) field.getGenericType()).getActualTypeArguments().length>0){
						
						children = process((Class)((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
						results.get(0).put(field.getName(), new Object[] {children.get(0)});
						results.get(1).put(field.getName(), new Object[] {children.get(1)});
						//result.put(field.getName(), new Object[] {classToAttributeTypeMap((Class)((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0])});
					
					}else{
						
						results.get(0).put(field.getName(), new Object[] {field.getType().getSimpleName().toLowerCase()});
						results.get(1).put(field.getName(), new Object[] {field.getType().getSimpleName().toLowerCase()});
						
					}

				}else if(field.getType().getPackage().getName().startsWith("java.")){
					
					typeName = field.getType().getSimpleName().toLowerCase();
					
					additionals = (field.isAnnotationPresent(NotBlank.class)? "requerido,":"") +
							 	  (field.isAnnotationPresent(Min.class)? "valor mínimo:" + field.getAnnotation(Min.class).value() + ",":"") +
							 	  (field.isAnnotationPresent(Pattern.class)? "valores admitidos:" + field.getAnnotation(Pattern.class).regexp():"");
				
					results.get(0).put(field.getName(), typeName); 
					results.get(1).put(field.getName(), typeName + (StringUtils.isNotBlank(additionals)? " (" + additionals + ")":"")); 
					
				}else{
					//cutom object, like app object
					children = process(field.getType());
					results.get(0).put(field.getName(), new Object[] {children.get(0)});
					results.get(1).put(field.getName(), new Object[] {children.get(1)});
					 
					
				}
			}
		}
		
		return results;
	}
 
}
