package com.eventTracking.Task5.business.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private String header;
    private String description;
    private LocalDate date;
    private LocalDate completionDate;
    private long categoryId; 
}
