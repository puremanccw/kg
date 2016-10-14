package com.charles.engine.groovy;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.charles.common.constants.utils.Money;

/**
 * 解析明细基类
 * @author puremancw
 *
 */
public class ParseDetail {
	/** 业务序列号 */
	private String bizSerialNo;
	
	/** 银行序列号 */
	private String bankSerialNo;
	
	/** 银行日期 */
	private String bankDate;
	
	/** 金额 */
	private Money amount = new Money();
	
	/** 备注 */
	private String memo;
	
	/** 是否成功，必须设置，null则抛出异常*/
	private boolean success;
	
	/** 是否为退货订单 */
    protected boolean returnOrder   = false;
	
	/** 错误代码*/
	private String errorCode = "";
	
	/** 错误信息*/
	private String errorMessage = "";
	
	/** 数据校验类型*/
	private String dataCheckType = "";
	
	/** 外部订单号*/
	private String outNo;
	
	/**
	 * 默认构造器
	 */
	public ParseDetail() {}
	
	public ParseDetail(String bizSerialNo, String bankSerialNo, Money amount, String bankDate,
            String memo, boolean success) {
		this.bizSerialNo = bizSerialNo;
        this.bankSerialNo = bankSerialNo;
        this.amount = amount;
        this.bankDate = bankDate;
        this.memo = memo;
        this.success = success;
	}

	public String getBizSerialNo() {
		return bizSerialNo;
	}

	public void setBizSerialNo(String bizSerialNo) {
		this.bizSerialNo = bizSerialNo;
	}

	public String getBankSerialNo() {
		return bankSerialNo;
	}

	public void setBankSerialNo(String bankSerialNo) {
		this.bankSerialNo = bankSerialNo;
	}

	public String getBankDate() {
		return bankDate;
	}

	public void setBankDate(String bankDate) {
		this.bankDate = bankDate;
	}

	public Money getAmount() {
		return amount;
	}

	public void setAmount(Money amount) {
		this.amount = amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getDataCheckType() {
		return dataCheckType;
	}

	public void setDataCheckType(String dataCheckType) {
		this.dataCheckType = dataCheckType;
	}

	public String getOutNo() {
		return outNo;
	}

	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}
	
	public boolean isReturnOrder() {
		return returnOrder;
	}

	public void setReturnOrder(boolean returnOrder) {
		this.returnOrder = returnOrder;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
