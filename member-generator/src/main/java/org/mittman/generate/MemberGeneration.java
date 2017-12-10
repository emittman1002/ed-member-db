package org.mittman.generate;

import java.util.ArrayList;
import java.util.List;

import org.mittman.generate.domain.GroupMember;

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
	
	private GroupMemberGenerator<Long,Long> generator;
	
	
	public MemberGeneration() {
		generator = new GroupMemberGenerator<Long,Long>(Long.class, Long.class);
	}

	public List<GroupMember<Long,Long>> generate(int numMembers) throws Exception {
		generator.setValidSsnsOnly(validSsnsOnly);
		generator.setUseIdForMemberNames(useIdsForMemberNames);
		generator.setUseIdForGroupNamesAndNumbers(useIdsForGroupNamesAndNumbers);
		
		List<GroupMember<Long,Long>> mems = new ArrayList<>();
		
		for(int i=0; i<numMembers; ++i) {
			GroupMember<Long,Long> mem = generator.generate();
			mems.add(mem);
		}
		
		return mems;
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
		if (args.length>0) {
			return Integer.valueOf(args[0]);
		}
		
		// Default
		return 100;
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
