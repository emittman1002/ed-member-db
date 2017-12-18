package org.mittman.generate;

import java.util.ArrayList;
import java.util.List;

import org.mittman.generate.domain.Group;
import org.mittman.generate.domain.GroupMember;
import org.mittman.generate.domain.Member;
import org.mittman.generate.format.GeneratedObjectFormatter;
import org.mittman.generate.strategy.GroupingStrategy;
import org.mittman.generate.strategy.IndividualGroupingStrategy;
import org.mittman.generate.strategy.RoundRobinGroupingStrategy;
import org.mittman.generate.strategy.SingleGroupingStrategy;
import org.mittman.generate.write.OutputWriter;
import org.mittman.generate.write.PrintStreamOutputWriter;

import lombok.Getter;
import lombok.Setter;

/**
 * Generation of members
 * 
 * @author Edward Mittman
 *
 */
public class MemberGeneration {
	@Getter@Setter
	private boolean validSsnsOnly = true;
	@Getter@Setter
	private boolean useIdsForMemberNames;
	@Getter@Setter
	private boolean useIdsForGroupNamesAndNumbers;
	@Getter@Setter
	private int numberOfGroups;
	@Getter@Setter
	private int numberOfMembersToGenerate;
	@Getter@Setter
	private String propertyFilename;
	@Getter@Setter
	private String dbPropertyFilename;

	/**
	 * A strategy for grouping objects that are generated.
	 * The default strategy is to place all objects in
	 * the same group
	 */
	private String groupingStrategyName = "single";
	/**
	 * Optional formatting of the objects
	 * that are generated.  Default behavior
	 * is to call the object's toString() method
	 */
	@Getter@Setter
	private GeneratedObjectFormatter<GroupMember<Long,Long>> outputFormatter;
	@Getter@Setter
	/**
	 * Where output will be sent to.  Defaults
	 * to System.out
	 */
	private OutputWriter<GroupMember<Long,Long>> outputWriter;
	
	
	public MemberGeneration() {
		numberOfMembersToGenerate = 10;
		outputWriter = new PrintStreamOutputWriter<GroupMember<Long,Long>>();
		propertyFilename = getClass().getSimpleName().toLowerCase() + ".properties";
	}

	/**
	 * Generate the members
	 * 
	 * @param numMembers
	 * @throws Exception
	 */
	public void generate() throws Exception {
		GroupMemberGenerator<Long,Long> generator =
				new GroupMemberGenerator<Long,Long>(Long.class, Long.class);
		
		// Customize group generation
		GroupingGroupGenerator<Long,Member<Long>> groupGenerator =
				new GroupingGroupGenerator<Long,Member<Long>>(Long.class);
		
		generator.setValidSsnsOnly(validSsnsOnly);
		generator.setUseIdForMemberNames(useIdsForMemberNames);
		generator.setUseIdForGroupNamesAndNumbers(useIdsForGroupNamesAndNumbers);
		groupGenerator.setGroupingStrategy( groupingStrategy() );

		generator.setGroupGenerator(groupGenerator);
		
		// Generate the members
		List<GroupMember<Long,Long>> mems = new ArrayList<GroupMember<Long,Long>>(2);
		
		for(int i=0; i<numberOfMembersToGenerate; ++i) {
			GroupMember<Long,Long> mem = generator.generate();
			mems.add(mem);
			outputWriter.write(mems);
			mems.clear();
		}
	}
	
	private GroupingStrategy<Group<Long>,Member<Long>> groupingStrategy() {
		GroupingStrategy<Group<Long>,Member<Long>> strategy;
		
		if ("roundrobin".equals(groupingStrategyName)) {
			strategy =
					new RoundRobinGroupingStrategy<Group<Long>,Member<Long>>(numberOfGroups);
		}
		else
		if ("individual".equals(groupingStrategyName)) {
			strategy = new IndividualGroupingStrategy<Group<Long>,Member<Long>>();
		}
		else {
			// Default single strategy
			strategy = new SingleGroupingStrategy<Group<Long>,Member<Long>>();
		}
		
		return strategy;
	}
	
	/**
	 * Parse the number of members to generate from the
	 * first argument.  If no argument was specified use
	 * a default value of 100
	 * 
	 * @param args
	 * @return
	 */
	public void parseArgs(String []args) throws Exception {
		// Override with anything specified on the command line
		for(String arg: args) {
			Integer iVal = intValue(arg, "numMems=");
			if (iVal!=null) {
				numberOfMembersToGenerate = iVal.intValue();
			}

			iVal = intValue(arg, "numGroups=");
			if (iVal!=null) {
				numberOfGroups = iVal.intValue();
			}
			
			String sVal = stringValue(arg, "groupingStrategy=");
			if (sVal!=null) {
				if ("single".compareToIgnoreCase(sVal) == 0 ||
						"default".compareToIgnoreCase(sVal) == 0) {
					
					groupingStrategyName = "single";
				}
				else
				if ("roundrobin".compareToIgnoreCase(sVal) == 0) {
					groupingStrategyName = "roundrobin";
				}
				else
				if ("individual".compareToIgnoreCase(sVal) == 0) {
					groupingStrategyName = "individual";
				}
			}
			
			sVal = stringValue(arg, "formatterClass=");
			if (sVal!=null) {
				@SuppressWarnings("unchecked")
				Class<GeneratedObjectFormatter<GroupMember<Long,Long>>> fmtClazz = (Class<GeneratedObjectFormatter<GroupMember<Long,Long>>>) getClass().getClassLoader().loadClass(sVal);
				outputFormatter = fmtClazz.newInstance();
			}
			
			sVal = stringValue(arg, "outputWriterClass=");
			if (sVal!=null) {
				@SuppressWarnings("unchecked")
				Class<GeneratedObjectFormatter<GroupMember<Long,Long>>> fmtClazz = (Class<GeneratedObjectFormatter<GroupMember<Long,Long>>>) getClass().getClassLoader().loadClass(sVal);
				outputFormatter = fmtClazz.newInstance();
			}
			
			sVal = stringValue(arg, "propFile=");
			if (sVal!=null) {
				propertyFilename = sVal;
			}
			
			sVal = stringValue(arg, "dbPropFile=");
			if (sVal!=null) {
				dbPropertyFilename = sVal;
			}
		}
	}
	
	private Integer intValue(String arg, String target) {
		if (arg.startsWith(target)) {
			return Integer.valueOf( argValue(arg, target) );
		}
		return null;
	}
	
	private String stringValue(String arg, String target) {
		if (arg.startsWith(target)) {
			return argValue(arg, target);
		}
		return null;
	}
	
	private String argValue(String arg, String target) {
		return arg.substring(target.length());
	}
	
	
	public static void main(String[] args) {
		MemberGeneration generation = new MemberGeneration();

		try {
			generation.parseArgs(args);
			generation.generate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
