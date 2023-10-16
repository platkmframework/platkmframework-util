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
import java.io.InputStream; 
 
 
/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 * Description: I/0 base
 * @author Eduardo Iglesias
 * @param <E>: object to save and read
 */
public abstract class ManagerBase <E> 
{
	 /**
	  * model class type
	  */
	private Class<E> modelClass;
	
	/**
	 * IO manager
	 */
	protected ManagerIO<E> io;
	 
	
	/**
	 * description: constructor
	 * @param modelClass: model class
	 */
	public ManagerBase(Class<E> modelClass) 
	{
		this.modelClass = modelClass;
		this.io = new ManagerIO<E>(this.modelClass);  
	}
	
  
	/**
	 * description: read model
	 * @param modelFilePath: file path
	 * @return modelFilePath: model object
	 * @throws ManagerException -  error
	 */
	protected E readModel(String modelFilePath) throws ManagerException 
	{ 
		File modelFile = new File(modelFilePath);
		return io.readModel(modelFile); 
	}
	
	/**
	 * description: read model from file
	 * @param inputStream: content
	 * @return model
	 * @throws ManagerException -  error
	 */
	protected E readModel(InputStream inputStream) throws ManagerException 
	{  
		return io.readModel(inputStream); 
	}	
	 
	/**
	 * description: save model object to file
	 * @param modelFilePath: file path
	 * @param model: model object
	 * @throws ManagerException - error
	 */
	protected void writeModel(String modelFilePath, E model) throws ManagerException 
	{ 
		File modelFile = new File(modelFilePath); 
		io.writeModel(modelFile, model); 
	}


	/**
	 * description: save file content
	 * @param filePath: file path
	 * @param content: content
	 * @throws ManagerException -  error
	 */
	protected void writeFile(String filePath, String content) throws ManagerException
	{
		io.writeFile(new File(filePath), content);
	}
	
	/**
	 * Description: read file
	 * @param filePath: path
	 * @return file content
	 * @throws ManagerException - error
	 */
	protected String readFile(String filePath) throws ManagerException
	{
		return io.readFile(new File(filePath));
	}
	
	/**
	 * description: rename 
	 * @param oldFileNamePath: file path
	 * @param newName: new name
	 */
	public void renameTemplate(String oldFileNamePath, String newName) 
	{ 
		File oldFile = new File(oldFileNamePath);
		File newFile = new File(oldFile.getParentFile().getAbsolutePath() + File.separator + newName);
		oldFile.renameTo(newFile); 
		 
	}	
	
	/**
	 * description: remove file
	 * @param filePath: file path
	 * @throws ManagerException - error
	 */
	protected void removeFile(String filePath) throws ManagerException
	{
		io.delete(new File(filePath));
	}

  
}
