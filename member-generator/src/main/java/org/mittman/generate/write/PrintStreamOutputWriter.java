package org.mittman.generate.write;

import java.io.PrintStream;
import java.util.Collection;

import org.mittman.generate.format.GeneratedObjectFormatter;
/**
 * Formats and prints objects to a PrintStream.  If no
 * formatter is specified, just calls each object's toString() method.
 * The default output stream is System.out
 * 
 * @author Edward Mittman
 *
 * @param <T>
 */
public class PrintStreamOutputWriter<T> implements OutputWriter<T> {
	private PrintStream stream;
	private GeneratedObjectFormatter<T> formatter;

	
	public PrintStreamOutputWriter() {
		stream = System.out;
	}

	@Override
	public void write(Collection<T> objects) throws Exception {
		for(T o: objects) {
			String s;
			if (formatter!=null) {
				s = formatter.format(o);
			}
			else {
				s = o.toString();
			}
			
			stream.println(s);
		}
		
	}

}
