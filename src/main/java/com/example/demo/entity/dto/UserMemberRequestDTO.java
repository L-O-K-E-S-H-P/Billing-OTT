package com.example.demo.entity.dto;

import java.util.List;

import com.example.demo.entity.FamilyMember;

import lombok.Getter;

@Getter
public class UserMemberRequestDTO {
	private Long accountId;
	private List<FamilyMember> familMemberList;
}
