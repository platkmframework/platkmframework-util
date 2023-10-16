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
package org.platkmframework.util.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *   Author: 
 *     Eduardo Iglesias
 *   Contributors: 
 *   	Eduardo Iglesias - initial API and implementation
 *   @param <E> : generic para los datos
*/
public class TreeNode<E> implements Serializable {
	 
	private static final long serialVersionUID = 1L;
	
	
	 /**
	  * description: constructor
	  */
	public TreeNode() {
		super();
	}

	/**
	 * description: info
	 */
	private E info;
	
	/**
	 * description: children
	 */
	private List<TreeNode<E>> children = new ArrayList<TreeNode<E>>();
 
	/**
	 * info
	 * @return object
	 */
	public E getInfo() {
		return info;
	}

	/**
	 * description: set object
	 * @param info: info
	 */
	public void setInfo(E info) {
		this.info = info;
	}

	/**
	 * description: get children
	 * @return children list
	 */
	public List<TreeNode<E>> getChildren() { 
		return children;
	}

	/**
	 * description: set children
	 * @param children: children
	 */
	public void setChildren(List<TreeNode<E>> children) {
		this.children = children;
	} 
 
}
