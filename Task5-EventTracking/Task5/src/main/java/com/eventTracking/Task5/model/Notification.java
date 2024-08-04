package com.eventTracking.Task5.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String message;
	@Column(nullable = false)
	private long eventId;
	@Column(nullable = false)
	private Date timestamp;
    @Column(nullable = false)
    private String type; // Bildirim türü
    
    @Column(nullable = false)
    private boolean responded;
	
}
