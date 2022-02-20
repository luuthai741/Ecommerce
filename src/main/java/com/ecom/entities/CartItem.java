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
@Table(name="cart_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="order_id")
	private Integer orderId;
	@Column(name="product_id")
	private Integer productId;
	@Column(name="product_name")
	private String productName;
	@Column(name="quantity")
	private Integer quantity;
	@Column(name="item_price")
	private Long itemPrice;
	@Column(name="total")
	private Long Total;
	@Column(name="image_url")
	private String imageURL;
	@Column(name="image_name")
	private String imageName;
}
