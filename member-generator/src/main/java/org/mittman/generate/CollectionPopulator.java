package org.mittman.generate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

public class CollectionPopulator {
	/**
	 * Populate a collection from a file
	 * 
	 * @param collection
	 * @param filename
	 * @throws RuntimeException
	 */
	void populate(Collection<String> collection, String filename, String label) throws RuntimeException {
		BufferedReader reader = null;
		
		try {
			String line;
			reader = reader(filename);
			while((line=reader.readLine())!=null) {
				collection.add(line);
			}
			reader.close();
			reader = null;
		}
		catch(IOException e) {
			if (label==null) {
				label = "";
			}
			String msg = "Unable to initialize the " + label + " collection".replace("  ", " ");
			throw new RuntimeException(msg, e);
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

	/**
	 * Create a buffered reader to read in a file
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	protected BufferedReader reader(String filename) throws IOException {
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
}
