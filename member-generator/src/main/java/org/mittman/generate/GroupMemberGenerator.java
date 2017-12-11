package org.mittman.generate;

import org.mittman.generate.domain.GroupMember;
import org.mittman.generate.domain.GroupMemberImpl;

import lombok.Getter;
import lombok.Setter;

/**
 * A generator for GroupMember objects.
 * 
 * @author Edward Mittman
 *
 * @param <M> the type of the id for members
 * @param <G> the type of the id for groups
 */
@Getter@Setter
public class GroupMemberGenerator<M,G> extends AbstractGenerator<GroupMember<M,G>> {
	private MemberGenerator<M> memberGenerator;
	private GroupGenerator<G> groupGenerator;

	public GroupMemberGenerator(Class<M> memberIdClass, Class<G> groupIdClass) {
		memberGenerator = new MemberGenerator<M>(memberIdClass);
		groupGenerator = new GroupGenerator<G>(groupIdClass);
	}

	@Override
	protected GroupMemberImpl<M, G> create() {
		return new GroupMemberImpl<M,G>();
	}

	@Override
	protected void populate(GroupMember<M, G> object) {
		memberGenerator.populate(object);
		object.setGroup( groupGenerator.generate() );
	}

	public boolean isValidSsnsOnly() {
		return memberGenerator.isValidSsnOnly();
	}
	public void setValidSsnsOnly(boolean validSsnsOnly) {
		memberGenerator.setValidSsnOnly(validSsnsOnly);
	}
	
	public boolean isUseIdForMemberNames() {
		return memberGenerator.isUseIdToGenerateNames();
	}
	public void setUseIdForMemberNames(boolean useIdForMemberNames) {
		memberGenerator.setUseIdToGenerateNames(useIdForMemberNames);
	}
	
	public boolean isUseIdForGroupNamesAndNumbers() {
		return groupGenerator.isUseIdForNamesAndNumbers();
	}
	public void setUseIdForGroupNamesAndNumbers(boolean useIdForGroupNamesAndNumbers) {
		groupGenerator.setUseIdForNamesAndNumbers(useIdForGroupNamesAndNumbers);
	}
}
