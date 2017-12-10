package org.mittman.generate;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;

import org.mittman.generate.domain.NoIdAvailableException;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * Generates Long ids
 * 
 * @author Edward Mittman
 *
 */
public class LongIdGenerator implements IdGenerator<Long> {
	@Getter(AccessLevel.PACKAGE)
	private AtomicLong nextId;

	
	public LongIdGenerator() {
		nextId = new AtomicLong();
	}
	
	@Override
	public Long generate() {
		return nextId.incrementAndGet();
	}

	@Override
	public IdGenerator<Long> reserve(int numberOfReservations) throws UnsupportedOperationException {
		LongUnaryOperator operator = new LongUnaryOperator() {
			private final long VALUE = (long) numberOfReservations;
			
			@Override
			public long applyAsLong(long initialValue) {
				return initialValue + VALUE;
			}
		};
		
		IdGenerator<Long> gen = new IdGenerator<Long>() {
			final AtomicLong al = new AtomicLong(nextId.getAndUpdate(operator) + 1);
			int numberOfRemainingIds = numberOfReservations;

			@Override
			public Long generate() {
				if (--numberOfRemainingIds>=0) {
					return al.getAndIncrement();
				}
				throw new NoIdAvailableException();
			}

			@Override
			public IdGenerator<Long> reserve(int numberOfReservations) throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}
		};

		return gen;
	}

}
