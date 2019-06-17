package online.trazpramim.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OfferDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private Double max_weight;
	@Column
	private Double max_price;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getMax_weight() {
		return max_weight;
	}
	public void setMax_weight(Double max_weight) {
		this.max_weight = max_weight;
	}
	public Double getMax_price() {
		return max_price;
	}
	public void setMax_price(Double max_price) {
		this.max_price = max_price;
	}
		
}
