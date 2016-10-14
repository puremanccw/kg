package com.charles.engine.groovy;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.charles.common.constants.utils.Money;

public class FileParseResultBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 文件返回批次号*/
	private String returnBatchNo;
	
	/** 清算通道*/
	private String settleApi;
	
	/** 总笔数*/
	private int totalRecord;
	
	/** 总金额*/
	private Money totalAmount = new Money();
	
	/** 成功笔数*/
	private int successRecord;
	
	/** 成功金额*/
	private Money successAmount = new Money();
	
	/** 失败笔数*/
	private int failRecord;
	
	/** 失败金额*/
	private Money failAmount = new Money();
	
	/** 差异笔数*/
	private int differentRecord;
	
	/** 差异金额*/
	private  Money differentAmount = new Money();
	
	/** 退货笔数*/
	private int returnRecord;
	
	/** 退货金额*/
	private Money returnAmount = new Money();
	
	/** 是否获取工作日，转换清算日期*/
	private String coverFlag;

	public String getReturnBatchNo() {
		return returnBatchNo;
	}

	public void setReturnBatchNo(String returnBatchNo) {
		this.returnBatchNo = returnBatchNo;
	}

	public String getSettleApi() {
		return settleApi;
	}

	public void setSettleApi(String settleApi) {
		this.settleApi = settleApi;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Money getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Money totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getSuccessRecord() {
		return successRecord;
	}

	public void setSuccessRecord(int successRecord) {
		this.successRecord = successRecord;
	}

	public Money getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(Money successAmount) {
		this.successAmount = successAmount;
	}

	public int getFailRecord() {
		return failRecord;
	}

	public void setFailRecord(int failRecord) {
		this.failRecord = failRecord;
	}

	public Money getFailAmount() {
		return failAmount;
	}

	public void setFailAmount(Money failAmount) {
		this.failAmount = failAmount;
	}

	public int getDifferentRecord() {
		return differentRecord;
	}

	public void setDifferentRecord(int differentRecord) {
		this.differentRecord = differentRecord;
	}

	public Money getDifferentAmount() {
		return differentAmount;
	}

	public void setDifferentAmount(Money differentAmount) {
		this.differentAmount = differentAmount;
	}

	public int getReturnRecord() {
		return returnRecord;
	}

	public void setReturnRecord(int returnRecord) {
		this.returnRecord = returnRecord;
	}

	public Money getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Money returnAmount) {
		this.returnAmount = returnAmount;
	}

	public String getCoverFlag() {
		return coverFlag;
	}

	public void setCoverFlag(String coverFlag) {
		this.coverFlag = coverFlag;
	}
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
