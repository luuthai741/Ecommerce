package com.ecom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
	private int id;
	private String phonenumber;
	private String address;
	private String lastname;
	private String firstname;
}
