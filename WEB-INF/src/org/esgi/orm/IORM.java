package org.esgi.orm;

import java.util.ArrayList;

import org.esgi.orm.annotations.ORM_SEARCH_WITHOUT_PK;

public interface IORM {

	/** Create or update a record in db. */
	public Object _save(Object o);
	
	/** return an instance of clazz */
	public Object _load(Class clazz, Object id);
	
	/** delete an record from clazz persistence layer */
	public boolean _remove(Class clazz, Object id);

	/** Return a list of selection */
	public  ArrayList<Object> _loadWithoutPrimaryKey(Class clazz, ORM_SEARCH_WITHOUT_PK critere);
	
}
