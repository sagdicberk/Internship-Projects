package com.example.task4.business.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUserResponse {
	private long id;
	private String name;
	private String email;
}
