package com.corelab.blog.model;

public enum AccountRoleType {

	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN"),
	MANAGER("ROLE_MANAGER");

	private String value;

	AccountRoleType(String value) { this.value = value; }

	public String toString() { return value; }

	/*
	* Enum  순회로 찾기
	* */
	/*public static AccountRoleType findByAccountRoleType(String role) {
		return Arrays.stream(AccountRoleType.values())
				.filter( s -> s.toString().equals(role))
				.findFirst()
				.orElse(null);
	}*/

}