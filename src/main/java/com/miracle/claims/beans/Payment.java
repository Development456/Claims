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
@Document(collection = "payment")
@JsonInclude(value = Include.NON_NULL)
public class Payment {
	@Id
	private String _id;
	
	@ApiModelProperty(required = true, value = "payment id", name = "paymentId", dataType = "Int", example = "1234")
	@Field("payment_id")
	private int paymentId;

	@ApiModelProperty(required = true, value = "invoice amount", name = "invoiceAmount", dataType = "Int", example = "1234")
	@Field("invoice_amount")
	private int invoiceAmount;
	
	@ApiModelProperty(required = true, value = "accural amount", name = "accuralAmount", dataType = "Int", example = "1234")
	@Field("accural_amount")
	private int accuralAmount;
	
	@ApiModelProperty(required = true, value = "Currency Type", name = "currencyType", dataType = "String", example = "1234")
	@Field("currency_type")
	private String currencyType;
	
	@ApiModelProperty(required = true, value = "gl Code", name = "glCode", dataType = "String", example = "1234")
	@Field("gl_code")
	private String glCode;
	
	@ApiModelProperty(required = true, value = "cost center", name = "costCenter", dataType = "String", example = "1234")
	@Field("cost_center")
	private String costCenter;
	
	@ApiModelProperty(required = true, value = "payment date", name = "paymentDate", dataType = "String", example = "1234")
	@Field("payment_date")
	private String paymentDate;
	
	@ApiModelProperty(required = true, value = "payment reference", name = "paymentReference", dataType = "String", example = "1234")
	@Field("payment_reference")
	private String paymentReference;
	
	@ApiModelProperty(required = true, value = "AP vendor", name = "apVendor", dataType = "String", example = "1234")
	@Field("ap_vendor")
	private String apVendor;
}
//{
//    "_id" : ObjectId("63e996d154f0df7af23d49a5"),
//    "invoice_amount" : 595.03,
//    "accural_amount" : 306.31,
//    "currency_type" : "USD",
//    "gl_code" : "US-WY",
//    "cost_center" : "Billing team",
//    "invoice_number" : NumberInt(476),
//    "payment_date" : "02/24/2022",
//    "payment_reference" : "324755907-8",
//    "ap_vendor" : "Vendor",
//    "payment_id" : NumberInt(320)
//}