
package com.example.carquotesystem.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends BaseModel{
	private String model;
	private double basePrice;
}
