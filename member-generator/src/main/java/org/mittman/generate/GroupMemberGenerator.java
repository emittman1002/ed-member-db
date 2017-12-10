package org.mittman.generate;

import org.mittman.generate.domain.GroupMember;
import org.mittman.generate.domain.GroupMemberImpl;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * A generator for GroupMember objects
 * 
 * @author Edward Mittman
 *
 * @param <M>
 * @param <G>
 */
@Getter(AccessLevel.PACKAGE)
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

}
