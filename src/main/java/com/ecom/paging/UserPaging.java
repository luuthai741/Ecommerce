package com.ecom.paging;

import java.util.List;

import com.ecom.dto.OrderDTO;
import com.ecom.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPaging {
	private List<UserDTO> users;
	private int currentPage;
	private int pages;
}
