package com.alberoframework.sample.issuetracker.service.app.entity;

import com.alberoframework.lang.object.BaseObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RootAppEntity extends BaseObject {

	private String username;
}
