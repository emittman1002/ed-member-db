package org.mittman.generate.db;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
/**
 * Metadata used to persist objects of
 * a specified class
 * 
 * @author Edward Mittman
 *
 * @param <T>
 */
@Getter
public class DbMappings<T> {
	public static enum DbColumnType {
		INTEGER,
		BIGINTEGER,
		DECIMAL,
		CHAR,
		DATE,
		TIMESTAMP,
		BOOLEAN,
		CLOB,
		BLOB
	}
	
	public class FieldMapping {
		public String fieldName;
		public String columnName;
		public DbColumnType columnType;
		public int columnLength;
	}

	private Class<T> classBeingPersisted;
	private List<FieldMapping> fieldMappings;
	

	public DbMappings(Class<T> classBeingPersisted) {
		this.classBeingPersisted = classBeingPersisted;
		fieldMappings = new ArrayList<FieldMapping>();
	}

}
