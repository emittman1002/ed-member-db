package org.mittman.generate.group;
import java.util.concurrent.atomic.AtomicLong;

import org.mittman.generate.impl.IdGenerator;


public class GroupIdGenerator implements IdGenerator<String> {
	private AtomicLong nextId;
	
	public GroupIdGenerator() {
		nextId = new AtomicLong();
	}
	
	@Override
	public String generate() {
		return Long.toString( nextId.incrementAndGet() );
	}

	@Override
	public IdGenerator<String> reserve(int numberOfReservations) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

}
