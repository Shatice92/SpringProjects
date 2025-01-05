package org.hatice.userjpaqueriesproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hatice.userjpaqueriesproject.enums.Status;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(name = "first_name")
	String firstName;
	@Column(name = "last_name")
	String lastName;
	String email;
	Integer age;
	@Enumerated
	Status status;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	public User(String firstName, String lastName, String email, Integer age, Status status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
		this.status = status;
	}
}