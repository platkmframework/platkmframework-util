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
package org.platkmframework.util.manager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.platkmframework.util.io.ModelIO;
 
/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * description: io manager
 * @param <E> : model
 */
public class ManagerIO<E> {

	/**
	 * description: class 
	 */
	private Class<E> class1;
	 
	/**
	 * description: constructor
	 * @param class1: class
	 */
	public ManagerIO(Class<E> class1)
	{
		this.class1 = class1;
	}
	
	/**
	 * description: read model
	 * @param file: file
	 * @return model object
	 * @throws ManagerException - error
	 */
	public E readModel(File file) throws ManagerException 
	{ 
		
		try {
			return ModelIO.readModel(file, class1);
		} catch (JAXBException e) {
			 
			e.printStackTrace();
			throw new ManagerException(e.getMessage());
		}
		 
	}
	
	/**
	 * description: read model
	 * @param inputStream:  input stream object
	 * @return model
	 * @throws ManagerException - error
	 */
	public E readModel(InputStream inputStream) throws ManagerException 
	{ 
		
		try {
			return ModelIO.readModel(inputStream, class1);
		} catch (JAXBException e) {
			 
			e.printStackTrace();
			throw new ManagerException(e.getMessage());
		}
		 
	}	
	
	/**
	 * description: write model
	 * @param file: file 
	 * @param model: model
	 * @throws ManagerException - error
	 */
	public void writeModel(File file, E model) throws ManagerException 
	{ 
		
		 try {
			ModelIO.writeModel(file, model, class1);
		} catch (JAXBException e) {
			 
			e.printStackTrace();
			throw new ManagerException(e.getMessage());
		}
		  
	}
	 
	/**
	 * description: write file
	 * @param file: file
	 * @param content: content
	 * @throws ManagerException - error
	 */
	public void writeFile(File file, String content) throws ManagerException
	{
		writeFile(file, content, "UTF-8");
	}
	
	/**
	 * description: write file
	 * @param file: file
	 * @param content: content
	 * @param charSet: charset
	 * @throws ManagerException - error
	 */
	public void writeFile(File file, String content, String charSet) throws ManagerException
	{
		try {
			
			FileUtils.writeStringToFile(file, content, charSet);
			
		} catch (IOException e) 
		{ 
			e.printStackTrace();
			throw  new ManagerException("write file exception " + file.getName());
		} 
	}
	 
	/**
	 * description: read file
	 * @param file: file
	 * @return file content
	 * @throws ManagerException - error
	 */
	public String readFile(File file) throws ManagerException
	{
		return readFile(file, "UTF-8");
	}
	
	/**
	 * description: read file
	 * @param file: file
	 * @param charSet : char set
	 * @return file content
	 * @throws ManagerException - error
	 */
	public String readFile(File file, String charSet) throws ManagerException
	{
		try 
		{
			return FileUtils.readFileToString(file, charSet);
		
		} catch (IOException e) {
			e.printStackTrace();
			throw  new ManagerException("read file exception " + file.getName());
		} 
	}
	
	/**
	 * description: file to delete
	 * @param file: file
	 * @throws ManagerException - error
	 */
	public void delete(File file) throws ManagerException
	{
		try 
		{ 
			FileUtils.forceDelete(file); 
		} catch (IOException e) 
		{ 
			e.printStackTrace();
			throw  new ManagerException("delete file exception " + file.getName());
		}
	}
	
	
}
