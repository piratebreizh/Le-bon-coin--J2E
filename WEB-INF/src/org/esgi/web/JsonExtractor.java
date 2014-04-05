package org.esgi.web;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonExtractor {
	
	private Hashtable<String, ArrayList<String>> _tree;
	
	public JsonExtractor(){
		_tree = new Hashtable<>();
	}
	
	/**
	 * Builds a list of dependencies an returns them inside an ArrayList
	 * @param filePath The path to a JSON file
	 * @return
	 */
	public ArrayList<String> getDependencies(String filePath){
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode rootNode = mapper.readValue(new File(filePath), JsonNode.class);
			buildTree(rootNode, null);
			return buildDependencies();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Inserts a value in the values list of a node.<br>
	 * If the node is not found in the tree, a new node will be created<br>
	 * in the tree with the specified value.<br>
	 * @param nodeName The node which will recieve the value
	 * @param value The value to insert in the node's list
	 */
	private void treeInsert(String nodeName, String value){
		ArrayList<String> nodeValues = _tree.get(nodeName);
		if(nodeValues == null){
			nodeValues = new ArrayList<>();
			nodeValues.add(value);
			_tree.put(nodeName, nodeValues);
		}else{
			nodeValues.add(value);
		}
	}
	
	/**
	 * Removes a value from the tree. All parents are concerned.<br>
	 * If a parent becomes child-less, it will also be removed<br>
	 * from the tree.
	 * @param valueToRemove The value to remove from the tree
	 */
	private void removeValueFromTree(String valueToRemove){
		Iterator<String> it = _tree.keySet().iterator();
		while(it.hasNext()){
			String parent = it.next();
			
			ArrayList<String> values = _tree.get(parent);
			if(values.contains(valueToRemove)){
				//System.out.println("Removed value " + valueToRemove + " from parent " + parent);
				values.remove(valueToRemove);
			}
			if(values.isEmpty()){
				//System.out.println("Parent " + parent + " is empty and has been removed from tree");
				it.remove();
			}
		}
	}
	
	/**
	 * Builds the tree. This function is for internal use only<br>
	 * and must be called before buildDependencies()<br>
	 * @param root The node to parse
	 * @param parent The node's parent
	 */
	private void buildTree(JsonNode root, JsonNode parent){
		if(root.isArray()){
			for(JsonNode n : root){
				buildTree(n, parent);
			}
			return;
		}
		
		if(root.isContainerNode()){
			String name = getNodeName(root);
			String parentName = getNodeName(parent);
			JsonNode value = root.findValue(name);
			
			treeInsert(parentName,name);
			//System.out.println("Container: " + name + " begin");
			buildTree(value, root);
			//System.out.println("Container: " + name + " end");
			return;
		}
		
		if(root.isValueNode()){
			String parentName = getNodeName(parent);
			String value = root.asText();
			//System.out.println("Value: " + value + " (parent " + parentName + ")");
			treeInsert(parentName, value);
			return;
		}
	}
	
	/**
	 * Creates a list of dependencies, sorted in an array.<br>
	 * The tree must be built with buildTree() vefore calling this function.<br>
	 * This function is for internal use only, call getDependencies instead.<br>
	 * @return The sorted dependencies list
	 */
	private ArrayList<String> buildDependencies(){
		ArrayList<String> orderedList = new ArrayList<>();
		ArrayList<String> leafs; //Nodes without dependencies
		
		leafs = getTreeLeafs();
		while(!leafs.isEmpty()){
			for(String leaf : leafs){
				//System.out.println("  " + leaf);
				orderedList.add(orderedList.size(), leaf);
				removeValueFromTree(leaf);
			}
			leafs = getTreeLeafs();
		}
		return orderedList;
	}
	
	/**
	 * Gets a list of the tree's leaf.
	 * @return The leafs array
	 */
	private ArrayList<String> getTreeLeafs(){
		ArrayList<String> leafs = new ArrayList<>();
		for(ArrayList<String> valueList : _tree.values()){
			for(String value : valueList){
				if(_tree.containsKey(value)) continue;
				if(leafs.contains(value)) continue;
				leafs.add(value);
			}
		}
		return leafs;
	}
	
	
	/**
	 * Extracts the name of a jacksons' JsonNode.
	 * @param n The node to extract informations from
	 * @return The JsonNode textual name
	 */
	private String getNodeName(JsonNode n){
		if(null == n) return "null";
		if(!n.isContainerNode() || n.isArray()) return "Not a container";
		Iterator<String> it = n.fieldNames();
		return it.next();
	}
}
