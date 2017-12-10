package org.mittman.generate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.mittman.generate.domain.PersonName;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Generates random person names.  Names are generated
 * from a list of names in three files, one each for first
 * middle, and last names.  Default lists are supplied but
 * others can be specified.
 * 
 * @author Edward Mittman
 *
 */
@Getter(AccessLevel.PACKAGE)@Setter(AccessLevel.PACKAGE)
public class PersonNameGenerator implements Generator<PersonName> {
	private String firstNameFile = "first-names.txt";
	private String middleNameFile = "middle-names.txt";
	private String lastNameFile = "last-names.txt";
	private boolean initialized;
	
	private List<String> firstNames;
	private List<String> middleNames;
	private List<String> lastNames;
	private Random random;
	
	
	public PersonNameGenerator() {
		random = new Random();
	}
	
	@Override
	public PersonName generate() {
		if (!initialized) {
			reinitialize();
		}
		
		PersonName pn = new PersonName();
		pn.setFirstName(randomNameFrom(firstNames));
		pn.setMiddleName(randomNameFrom(middleNames));
		pn.setLastName(randomNameFrom(lastNames));
		
		return pn;
	}

	private String randomNameFrom(List<String> names) {
		int idx = random.nextInt(names.size());
		return names.get(idx);
	}
	
	void reinitialize() throws RuntimeException {
		BufferedReader reader = null;
		
		try {
			String line;
			
			List<String> firstNames = new ArrayList<String>();
			reader = reader(firstNameFile);
			while((line=reader.readLine())!=null) {
				firstNames.add(line);
			}
			reader.close();
			reader = null;
			
			List<String> middleNames = new ArrayList<String>();
			reader = reader(middleNameFile);
			while((line=reader.readLine())!=null) {
				middleNames.add(line);
			}
			reader.close();
			reader = null;
			
			List<String> lastNames = new ArrayList<String>();
			reader = reader(lastNameFile);
			while((line=reader.readLine())!=null) {
				lastNames.add(line);
			}
			
			// Everything read, initialize
			this.firstNames = firstNames;
			this.middleNames = middleNames;
			this.lastNames = lastNames;
			initialized = true;
		}
		catch(IOException e) {
			throw new RuntimeException("Unable to initialize name lists", e);
		}
		finally {
			if (reader!=null) {
				try {
					reader.close();
				}
				catch(Exception e) {
					// ignore it
				}
			}
		}
	}

	private BufferedReader reader(String filename) throws IOException {
		BufferedReader reader = null;
		
		// Defaults are relative pathnames
		InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
		
		if (in!=null) {
			reader = new BufferedReader(new InputStreamReader(in));
		}
		else {
			// Try using absolute path
			reader = new BufferedReader(new FileReader(filename));
		}
		
		return reader;
	}

	public String getFirstNameFile() {
		return firstNameFile;
	}
	public void setFirstNameFile(String firstNameFile) {
		this.firstNameFile = firstNameFile;
		initialized = false;
	}

	public String getMiddleNameFile() {
		return middleNameFile;
	}
	public void setMiddleNameFile(String middleNameFile) {
		this.middleNameFile = middleNameFile;
		initialized = false;
	}

	public String getLastNameFile() {
		return lastNameFile;
	}
	public void setLastNameFile(String lastNameFile) {
		this.lastNameFile = lastNameFile;
		initialized = false;
	}
}
