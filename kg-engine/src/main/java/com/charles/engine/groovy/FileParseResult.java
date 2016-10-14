package com.charles.engine.groovy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 文件解析结果对象
 * @author puremancw
 *
 */
public class FileParseResult extends FileParseResultBase {
	
	private List<ParseDetail> detailList = new ArrayList<ParseDetail>();
	
	/** 解析文件（GROOVY）*/
	private File scriptFile;
	
	/** 目标文件*/
	private File targetFile;
	
	/** 清算批次号，与原文件批次号不同*/
	private String settleBatchNo;
	
	/** 解析是否正确*/
	private boolean parseSuccess = false;
	
	/** 数据库操作是否正确*/
	private boolean dbSuccess = false;
	
	/** 信息*/
	private String message;
	
	/** 校验是否正确*/
	private boolean checkSuccess = false;
	
	/** 其它信息*/
	private Map otherInfo = new HashMap();
	
	/**
	 * 是否转换订单号，Y：需要转换，N：不需要转换；以解析脚本为匹配标尺
	 */
	private String changeOrderNo = "N";
	
	/**
     * 增加结果明细
     * @param detail
     */
    public void addDetail(ParseDetail detail) {
        detailList.add(detail);

        // 统计信息
        setTotalRecord(getTotalRecord() + 1);
        getTotalAmount().addTo(detail.getAmount());

        if (detail.isSuccess()) {
            setSuccessRecord(getSuccessRecord() + 1);
            getSuccessAmount().addTo(detail.getAmount());
        } else {
            setFailRecord(getFailRecord() + 1);
            getFailAmount().addTo(detail.getAmount());
        }

        if (detail.isReturnOrder()) {
            setReturnRecord(getReturnRecord() + 1);
            getReturnAmount().addTo(detail.getAmount());
        }
    }
    
	public List<ParseDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<ParseDetail> detailList) {
		this.detailList = detailList;
	}

	public File getScriptFile() {
		return scriptFile;
	}

	public void setScriptFile(File scriptFile) {
		this.scriptFile = scriptFile;
	}

	public File getTargetFile() {
		return targetFile;
	}

	public void setTargetFile(File targetFile) {
		this.targetFile = targetFile;
	}

	public String getSettleBatchNo() {
		return settleBatchNo;
	}

	public void setSettleBatchNo(String settleBatchNo) {
		this.settleBatchNo = settleBatchNo;
	}

	public boolean isParseSuccess() {
		return parseSuccess;
	}

	public void setParseSuccess(boolean parseSuccess) {
		this.parseSuccess = parseSuccess;
	}

	public boolean isDbSuccess() {
		return dbSuccess;
	}

	public void setDbSuccess(boolean dbSuccess) {
		this.dbSuccess = dbSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isCheckSuccess() {
		return checkSuccess;
	}

	public void setCheckSuccess(boolean checkSuccess) {
		this.checkSuccess = checkSuccess;
	}

	public Map getOtherInfo() {
		return otherInfo;
	}
	
	public void setOtherInfo(Map otherInfo) {
		this.otherInfo = otherInfo;
	}

	public String getChangeOrderNo() {
		return changeOrderNo;
	}

	public void setChangeOrderNo(String changeOrderNo) {
		this.changeOrderNo = changeOrderNo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
