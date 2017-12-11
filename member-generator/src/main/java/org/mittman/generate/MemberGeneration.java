package org.mittman.generate;

import java.util.ArrayList;
import java.util.List;

import org.mittman.generate.domain.Group;
import org.mittman.generate.domain.GroupMember;
import org.mittman.generate.domain.Member;
import org.mittman.generate.strategy.GroupingStrategy;
import org.mittman.generate.strategy.IndividualGroupingStrategy;
import org.mittman.generate.strategy.RoundRobinGroupingStrategy;
import org.mittman.generate.strategy.SingleGroupingStrategy;

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
	
	private String groupingStrategyName = "single";
	
	
	public MemberGeneration() {
		
		
	}

	/**
	 * Generate the members
	 * 
	 * @param numMembers
	 * @return
	 * @throws Exception
	 */
	public List<GroupMember<Long,Long>> generate(int numMembers) throws Exception {
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
		List<GroupMember<Long,Long>> mems = new ArrayList<>(numMembers);
		
		for(int i=0; i<numMembers; ++i) {
			GroupMember<Long,Long> mem = generator.generate();
			mems.add(mem);
		}
		
		return mems;
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
	public int parseArgs(String []args) {
		// Default number of members to generate
		int ret = 100;
		
		for(String arg: args) {
			Integer iVal = intValue(arg, "numMems=");
			if (iVal!=null) {
				ret = iVal.intValue();
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
		}
		
		return ret;
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
			int numMems = generation.parseArgs(args);
			
			List<GroupMember<Long,Long>> mems = generation.generate(numMems);
			
			System.out.println("Members:");
			for(GroupMember<Long,Long> m: mems) {
				System.out.println(m);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
