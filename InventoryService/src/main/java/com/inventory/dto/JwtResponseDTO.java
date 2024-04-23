package com.inventory.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponseDTO {
   

	public  String accessToken;
	
	 public JwtResponseDTO(String token) {
			this.accessToken=token;
		}
  
}