package org.mittman.generate.write;

import java.util.Collection;
import java.util.List;

import org.mittman.generate.db.DbConnector;
import org.mittman.generate.db.DbMappings;
import org.mittman.generate.domain.Persistable;

import lombok.Getter;
import lombok.Setter;

/**
 * An OutputWriter that writes objects to a database
 * 
 * @author Edward Mittman
 *
 */
@Getter@Setter
public abstract class DbWriter<T extends Persistable> implements OutputWriter<T> {
	private DbConnector dbConnector;

	/**
	 * Produce mappings that can be used to save an object using
	 * reflection on Java beans to obtain field values
	 * 
	 * @param object
	 * @throws Exception
	 */
	protected abstract DbMappings<T> getDbMapping();
	

	@Override
	public void write(Collection<T> objects) throws Exception {
		List<DbMappings<T>.FieldMapping> fieldMappings = getDbMapping().getFieldMappings();
		
		for(T obj: objects) {
			if (obj.isPersisted()) {
				update(obj, fieldMappings);
			}
			else {
				insert(obj, fieldMappings);
			}
		}
		
	}
	
	protected void insert(T obj, List<DbMappings<T>.FieldMapping> fieldMappings) throws Exception {
		System.out.println("Inserting " + obj.toString());
	}
	
	protected void update(T obj, List<DbMappings<T>.FieldMapping> fieldMappings) throws Exception {
		System.out.println("Updating " + obj.toString());
	}
	
}
