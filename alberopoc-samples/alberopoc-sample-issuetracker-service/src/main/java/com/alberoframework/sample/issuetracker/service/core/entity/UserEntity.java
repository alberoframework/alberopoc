package com.alberoframework.sample.issuetracker.service.core.entity;

import org.springframework.data.annotation.Id;

import com.alberoframework.domain.entity.contract.AbstractEntity;
import com.alberoframework.sample.issuetracker.service.core.function.UserFunctions;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntity extends AbstractEntity<String> {

	public static UserEntity create(String userId, String username, String password, UserRoleValue role) {
		return new UserEntity(
			userId,
			username,
			UserFunctions.hashPassword(password),
			role
		);
	}
	@Id
	private String userId;

	private String username;
	private String password;

	private UserRoleValue role;

	public void changePassword(String password) {
		setPassword(UserFunctions.hashPassword(password));
	}

	public boolean isAdmin() {
		return getRole() == UserRoleValue.ADMIN;
	}

	@Override
	public String identity() {
		return getUserId();
	}

}
