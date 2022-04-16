package com.pract7.joins.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CentroDTO {
    private Long employerId;
    private String employerName;
    private Long postalCode;
}
