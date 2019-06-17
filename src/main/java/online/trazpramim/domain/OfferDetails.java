package online.trazpramim.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

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
	@Column
	@DateTimeFormat(pattern = "Y-m-d")
	private Date departure_day;
	@Column
	@DateTimeFormat(pattern = "Y-m-d")
	private Date arrival_day;
	
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
	public Date getDeparture_day() {
		return departure_day;
	}
	public void setDeparture_day(Date departure_day) {
		this.departure_day = departure_day;
	}
	public Date getArrival_day() {
		return arrival_day;
	}
	public void setArrival_day(Date arrival_day) {
		this.arrival_day = arrival_day;
	}
	
		
}
