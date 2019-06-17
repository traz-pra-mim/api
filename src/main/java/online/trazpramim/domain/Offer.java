package online.trazpramim.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Offer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private Date created_at;
	@Column
	private Date modified_at;
	@Column
	private Integer address_id;
	@Column
	private Integer offer_details_id;
	@Column
	private Integer offer_type_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getModified_at() {
		return modified_at;
	}
	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public Integer getOffer_details_id() {
		return offer_details_id;
	}
	public void setOffer_details_id(Integer offer_details_id) {
		this.offer_details_id = offer_details_id;
	}
	public Integer getOffer_type_id() {
		return offer_type_id;
	}
	public void setOffer_type_id(Integer offer_type_id) {
		this.offer_type_id = offer_type_id;
	}
	
	
	
}
