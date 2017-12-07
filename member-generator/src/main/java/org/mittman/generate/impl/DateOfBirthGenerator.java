package org.mittman.generate.impl;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.Random;

import org.mittman.generate.domain.Generator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(AccessLevel.PACKAGE)
public class DateOfBirthGenerator implements Generator<LocalDate> {
	@Getter
	private int minAge;
	@Getter
	private int maxAge;
	private int difference;
	@Setter(AccessLevel.PACKAGE)
	private boolean initialized;
	@Setter(AccessLevel.PACKAGE)
	private Random random;
	
	public DateOfBirthGenerator() {
		minAge = 15;
		maxAge = 150;
		
		random = new Random();
	}
	
	protected void reinitialize() {
		difference = maxAge - minAge;
		if (difference<0) {
			throw new IllegalStateException("maxAge is less than minAge");
		}

		initialized = true;
	}
	
	public void setMinAge(int minAge) {
		this.minAge = minAge;
		initialized = false;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
		initialized = false;
	}
	
	@Override
	public LocalDate generate() {
		if (!initialized) {
			reinitialize();
		}
		
		LocalDate now = LocalDate.now();
		
		// year
		int val = difference!=0 ? random.nextInt(difference) + minAge : minAge;
		LocalDate ret = now.minusYears(val);
		
		// month
		val = random.nextInt( (int)ChronoField.MONTH_OF_YEAR.range().getMaximum() ) + 1;
		ret = ret.with(ChronoField.MONTH_OF_YEAR, val);
		
		//day
		Month mo = ret.getMonth();
		int len = mo.length( ret.isLeapYear() );
		val = random.nextInt(len) + 1;
		ret = ret.with(ChronoField.DAY_OF_MONTH, val);
		
		return ret;
	}

}
