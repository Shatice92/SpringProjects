package org.hatice.userjpaqueriesproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	private String city;
	private String country;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Address(String street, String city, String country) {
		this.street = street;
		this.city = city;
		this.country = country;
	}
	
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Address{");
		sb.append("street='").append(getStreet()).append('\'');
		sb.append(", city='").append(getCity()).append('\'');
		sb.append(", country='").append(getCountry()).append('\'');
		sb.append('}');
		return sb.toString();
	}
}