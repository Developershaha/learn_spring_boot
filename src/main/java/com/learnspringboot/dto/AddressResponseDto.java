package com.learnspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    private String city;
    private String state;
    private String Country;
    private AddressRequestDTO address;
}
