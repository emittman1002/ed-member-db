package org.mittman.generate;
import org.mittman.generate.domain.Group;
import org.mittman.generate.domain.GroupImpl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PACKAGE)@Setter(AccessLevel.PACKAGE)
public class GroupGenerator<I> extends AbstractGenerator<Group<I>> {
	private LongIdGenerator idGenerator;
	private Class<?> idClass;
	@Setter
	private Generator<String> nameGenerator;
	@Setter
	private Generator<Long> groupNumberGenerator;
	@Getter@Setter
	private boolean useIdForNamesAndNumbers;

	
	public GroupGenerator(Class<I> idClass) {
		if (String.class.equals(idClass) || Long.class.equals(idClass)) {
			this.idClass = idClass;
		}
		else {
			throw new RuntimeException("Unsupported group ID class " + idClass.getName());
		}
		
		idGenerator = new LongIdGenerator();
		
		StringGenerator stringGen = new StringGenerator();
		stringGen.setFilename("business-names.txt");
		nameGenerator = stringGen;
		
		groupNumberGenerator = new LongIdGenerator();
	}
	
	@Override
	protected Group<I> create() {
		return new GroupImpl<I>();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void populate(Group<I> object) {
		Long id = idGenerator.generate();
		
		if (Long.class.equals(idClass)) {
			object.setId((I) id);
		}
		else if (String.class.equals(idClass)) {
			object.setId((I) id.toString());
		}
		
		if (useIdForNamesAndNumbers) {
			populateNameAndNumberFromId(id.toString(), object);
		}
		else {
			object.setName(nameGenerator.generate());
			object.setGroupNumber(groupNumberGenerator.generate().toString());
		}
	}
	
	private void populateNameAndNumberFromId(String id, Group<I> g) {
		g.setName("GroupName" + id);
		g.setGroupNumber("GroupNumber" + id);
	}
}
