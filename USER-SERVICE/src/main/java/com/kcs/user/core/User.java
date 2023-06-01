package com.kcs.user.core;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User extends Auditable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_role_id")
	private UserRole userRole;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;
	
	@Column(name = "phone_no")
	private String phoneNo;
	
	@Column(name="status")
	private Boolean status;
	
	@Column(name = "organization_id")
	private  Long  organizationId;
	
	@Column(name = "desk_id")
	private  Long  deskId;
	
	@Column(name = "floor_id")
	private  Long  floorId;
	
}
