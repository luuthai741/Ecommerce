package com.ecom.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoRequest {
	private int userId;
	private String phonenumber;
	private String address;
	private String firstname;
	private String lastname;
}
