package com.ecom.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="user_id")
	private Integer userId;
	@Column(name="username")
	private String username;
	@Column(name="address")
	private String address;
	@Column(name="phonenumber")
	private String phonenumber;
	@Column(name="shipping_cost")
	private Long shippingCost;
	@Column(name="payment_method")
	private String paymentMethod;
	@Column(name="total")
	private Long total;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time", nullable = false, updatable = false)
	private Date createTime;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;
	@Column(name="status")
	private int status;
}
