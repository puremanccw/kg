package com.charles.dal.dataobject;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RecruitDO {
    private Integer recruitid;

    private Integer parentcategory;

    private Integer categoryid;

    private String parentkind;

    private String kind;

    private String name;

    private Date pubtime;

    private Date endtime;

    private Date starttime;

    private Date canceltime;

    private Integer duration;

    private String gender;

    private Integer minage;

    private Integer maxage;

    private String education;

    private String workertreatment;

    private Integer workercount;

    private String workerexperience;

    private Long discount;

    private String discountunit;

    private Integer userid;

    private String contact;

    private String contactphone;

    private String addr;

    private String workarea;

    private String describe;

    private String addrlongitude;

    private String addrlatitude;

    private Integer workid;

    private String reason;

    private String imgurl;

    private String state;

    private String jobtype;

    private String usertype;

    public Integer getRecruitid() {
        return recruitid;
    }

    public void setRecruitid(Integer recruitid) {
        this.recruitid = recruitid;
    }

    public Integer getParentcategory() {
        return parentcategory;
    }

    public void setParentcategory(Integer parentcategory) {
        this.parentcategory = parentcategory;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getParentkind() {
        return parentkind;
    }

    public void setParentkind(String parentkind) {
        this.parentkind = parentkind == null ? null : parentkind.trim();
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind == null ? null : kind.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getPubtime() {
        return pubtime;
    }

    public void setPubtime(Date pubtime) {
        this.pubtime = pubtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getCanceltime() {
        return canceltime;
    }

    public void setCanceltime(Date canceltime) {
        this.canceltime = canceltime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getMinage() {
        return minage;
    }

    public void setMinage(Integer minage) {
        this.minage = minage;
    }

    public Integer getMaxage() {
        return maxage;
    }

    public void setMaxage(Integer maxage) {
        this.maxage = maxage;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getWorkertreatment() {
        return workertreatment;
    }

    public void setWorkertreatment(String workertreatment) {
        this.workertreatment = workertreatment == null ? null : workertreatment.trim();
    }

    public Integer getWorkercount() {
        return workercount;
    }

    public void setWorkercount(Integer workercount) {
        this.workercount = workercount;
    }

    public String getWorkerexperience() {
        return workerexperience;
    }

    public void setWorkerexperience(String workerexperience) {
        this.workerexperience = workerexperience == null ? null : workerexperience.trim();
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public String getDiscountunit() {
        return discountunit;
    }

    public void setDiscountunit(String discountunit) {
        this.discountunit = discountunit == null ? null : discountunit.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workarea) {
        this.workarea = workarea == null ? null : workarea.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getAddrlongitude() {
        return addrlongitude;
    }

    public void setAddrlongitude(String addrlongitude) {
        this.addrlongitude = addrlongitude == null ? null : addrlongitude.trim();
    }

    public String getAddrlatitude() {
        return addrlatitude;
    }

    public void setAddrlatitude(String addrlatitude) {
        this.addrlatitude = addrlatitude == null ? null : addrlatitude.trim();
    }

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype == null ? null : jobtype.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
}