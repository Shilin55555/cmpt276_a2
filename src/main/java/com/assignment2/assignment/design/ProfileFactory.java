package com.assignment2.assignment.design;

import com.assignment2.assignment.model.RoleType;

public class ProfileFactory {

	public static StaffMemberProfile fromRole(RoleType roleType) {
		if (roleType == null) {
			return () -> "Staff Member";
		}

		return switch (roleType) {
			case TA -> new TAProfile();
			case PROF -> new ProfProfile();
			default -> () -> "Staff Member";
		};
	}
}
