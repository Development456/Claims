package com.miracle.claims.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "order")
@JsonInclude(value = Include.NON_NULL)
public class Order {
	
	@Id
	private String _id;
	
	@ApiModelProperty(required = true, value = "customer order id", name = "orderId", dataType = "Int", example = "1234")
	@Field("order_id")
	private int orderId;
	
	@ApiModelProperty(required = true, value = "Item", name = "Item", dataType = "String", example = "64387")
	@Field("item")
	private String item;
	
	@ApiModelProperty(required = true, value = "description", name = "description", dataType = "String", example = "description")
	@Field("description")
	private String description;
	
	@ApiModelProperty(required = true, value = "Date", name = "date", dataType = "String", example = "1234")
	@Field("date")
	private String date;
	
	@ApiModelProperty(required = true, value = "true", name = "lot", dataType = "boolean", example = "true")
	@Field("lot")
	private Boolean lot;
	
	@ApiModelProperty(required = true, value = "quantity", name = "quantity", dataType = "Int", example = "1234")
	@Field("quantity")
	private int quantity;
	
	@ApiModelProperty(required = true, value = "lpn", name = "lpn", dataType = "Int", example = "1234")
	@Field("lpn")
	private int lpn;
	
	@ApiModelProperty(required = true, value = "net", name = "net", dataType = "Int", example = "1234")
	@Field("net")
	private int net;	
	
	@ApiModelProperty(required = true, value = "claim_qty", name = "claimQty", dataType = "Int", example = "1234")
	@Field("claim_qty")
	private int claimQty;
	
	@ApiModelProperty(required = true, value = "category", name = "category", dataType = "String", example = "Overage")
	@Field("category")
	private String category;
	
	@ApiModelProperty(required = true, value = "unit_cost", name = "unitCost", dataType = "Int", example = "1234")
	@Field("unit_cost")
	private int unitCost;
	
	@ApiModelProperty(required = true, value = "uom", name = "uom", dataType = "String", example = "LB")
	@Field("uom")
	private String uom;
	
	@ApiModelProperty(required = true, value = "total", name = "total", dataType = "Int", example = "1234")
	@Field("total")
	private int total;
	
	@ApiModelProperty(required = true, value = "est_unit_cost", name = "estUnitCost", dataType = "Int", example = "1234")
	@Field("est_unit_cost")
	private int estUnitCost;
	
	@ApiModelProperty(required = true, value = "est_total_cost", name = "estTotalCost", dataType = "Int", example = "1234")
	@Field("est_total_cost")
	private int estTotalCost;
	
	@ApiModelProperty(required = true, value = "amount_basis", name = "amountBasis", dataType = "String", example = "Product")
	@Field("amount_basis")
	private String amountBasis;

	@Override
	public String toString() {
		return "Order [_id=" + _id + ", orderId=" + orderId + ", item=" + item + ", description=" + description
				+ ", date=" + date + ", lot=" + lot + ", quantity=" + quantity + ", lpn=" + lpn + ", net=" + net
				+ ", claimQty=" + claimQty + ", category=" + category + ", unitCost=" + unitCost + ", uom=" + uom
				+ ", total=" + total + ", estUnitCost=" + estUnitCost + ", estTotalCost=" + estTotalCost
				+ ", amountBasis=" + amountBasis + "]";
	}
	
}


