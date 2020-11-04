package com.corelab.blog.model;

public enum ProfilesType {

	LOCAL("dev,local"),
	OPERATION("real,prod,server");

	private String value;

	ProfilesType(String value) { this.value = value; }

	public String toString() { return value; }

	/*
	* Enum  순회로 찾기
	* */
	/*public static AccountRoleType findByProfilesType(String role) {
		return Arrays.stream(AccountRoleType.values())
				.filter( s -> s.toString().equals(role))
				.findFirst()
				.orElse(null);
	}*/

}