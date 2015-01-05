package com.invsol.getfoodyc.models;

public class OrderItems {
	private int order_item_id;
	private String order_item_name;
	private int order_item_qty;

	public OrderItems(int order_item_id, int order_item_qty) {
		super();
		this.order_item_id = order_item_id;
		this.order_item_qty = order_item_qty;
	}

	public int getOrder_item_id() {
		return order_item_id;
	}

	public void setOrder_item_id(int order_item_id) {
		this.order_item_id = order_item_id;
	}

	public int getOrder_item_qty() {
		return order_item_qty;
	}

	public void setOrder_item_qty(int order_item_qty) {
		this.order_item_qty = order_item_qty;
	}

	public String getOrder_item_name() {
		return order_item_name;
	}

	public void setOrder_item_name(String order_item_name) {
		this.order_item_name = order_item_name;
	}

	
}

