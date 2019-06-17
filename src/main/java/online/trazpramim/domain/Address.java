package online.trazpramim.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String first_part;
	
	@Column
	private String second_part;

	@Column
	@NotNull
	private String neighborhood;
	
	@Column
	private String postal_code; 
	
	@Column
	private Long state_id;
	
}
