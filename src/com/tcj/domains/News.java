package com.tcj.domains;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "news")
@DataTransferObject
public class News implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "autoincrement")
	@GenericGenerator(name = "autoincrement", strategy = "identity")
	@RemoteProperty
	@Column(name = "id",length=19,updatable=true)
	private Integer id;

	@RemoteProperty
	@Column(name = "channel_id",length=19,nullable=true,updatable=true)
	private Integer channelId;

	@RemoteProperty
	@Column(name = "title",length=255,nullable=true,updatable=true)
	private String title;

	@RemoteProperty
	@Column(name = "provider_id",length=10,nullable=true,updatable=true)
	private Integer providerId;
	
	
	public Integer getProviderId() {
		return providerId;
	}

	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}

	@RemoteProperty
	@Column(name = "briefintro",length=255,nullable=true,updatable=true)
	private String briefintro;

	@RemoteProperty
	@Column(name = "image",length=255,nullable=true,updatable=true)
	private String image;

	@RemoteProperty
	@Column(name = "is_scroll",length=255,nullable=true,updatable=true)
	private String isScroll;

	@RemoteProperty
	@Column(name = "content",length=2147483647,nullable=true,updatable=true)
	private String content;

	@RemoteProperty
	@Column(name = "sort",length=10,nullable=true,updatable=true)
	private Integer sort;

	@RemoteProperty
	@Column(name = "status",length=255,nullable=true,updatable=true)
	private String status;

	@RemoteProperty
	@Column(name = "url",length=255,nullable=true,updatable=true)
	private String url;

	@RemoteProperty
	@Column(name = "type",length=255,nullable=true,updatable=true)
	private String type;

	@RemoteProperty
	@Column(name = "gmt_publish",length=0,nullable=true,updatable=true)
	private Date gmtPublish;

	@RemoteProperty
	@Column(name = "gmt_create",length=0,nullable=true,updatable=true)
	private Date gmtCreate;

	@RemoteProperty
	@Column(name = "gmt_modify",length=0,nullable=true,updatable=true)
	private Date gmtModify;

	@RemoteProperty
	@Column(name = "nid",length=32,nullable=true,updatable=true)
	private String nid;

	@RemoteProperty
	@Column(name = "agree_num",length=10,nullable=true,updatable=true)
	private Integer agreeNum;

	@RemoteProperty
	@Column(name = "bid",length=10,nullable=true,updatable=true)
	private Integer bid;

	@RemoteProperty
	@Column(name = "caid",length=32,nullable=true,updatable=true)
	private String caid;

	@RemoteProperty
	@Column(name = "click_num",length=10,nullable=true,updatable=true)
	private Integer clickNum;

	@RemoteProperty
	@Column(name = "create_time",length=0,nullable=true,updatable=true)
	private Date createTime;

	@RemoteProperty
	@Column(name = "create_user",length=32,nullable=true,updatable=true)
	private String createUser;

	@RemoteProperty
	@Column(name = "date",length=0,nullable=true,updatable=true)
	private String date;

	@RemoteProperty
	@Column(name = "disagree_num",length=10,nullable=true,updatable=true)
	private Integer disagreeNum;

	@RemoteProperty
	@Column(name = "english_title",length=25,nullable=true,updatable=true)
	private String englishTitle;

	@RemoteProperty
	@Column(name = "hyperlink",length=255,nullable=true,updatable=true)
	private String hyperlink;

	@RemoteProperty
	@Column(name = "hyperlink_name",length=255,nullable=true,updatable=true)
	private String hyperlinkName;

	@RemoteProperty
	@Column(name = "keywords",length=100,nullable=true,updatable=true)
	private String keywords;

	@RemoteProperty
	@Column(name = "miniture",length=255,nullable=true,updatable=true)
	private String miniture;

	@RemoteProperty
	@Column(name = "mobilecontent",length=2147483647,nullable=true,updatable=true)
	private String mobilecontent;

	@RemoteProperty
	@Column(name = "modify_time",length=0,nullable=true,updatable=true)
	private Date modifyTime;

	@RemoteProperty
	@Column(name = "modify_user",length=32,nullable=true,updatable=true)
	private String modifyUser;

	@RemoteProperty
	@Column(name = "onHome",length=10,nullable=true,updatable=true)
	private Integer onhome;

	@RemoteProperty
	@Column(name = "publish_time",length=0,nullable=true,updatable=true)
	private Date publishTime;

	@RemoteProperty
	@Column(name = "seoDes",length=100,nullable=true,updatable=true)
	private String seodes;

	@RemoteProperty
	@Column(name = "seoKey",length=50,nullable=true,updatable=true)
	private String seokey;


	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setChannelId(Integer channelId){
		this.channelId = channelId;
	}
	public Integer getChannelId(){
		return this.channelId;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return this.title;
	}
	public void setBriefintro(String briefintro){
		this.briefintro = briefintro;
	}
	public String getBriefintro(){
		return this.briefintro;
	}
	public void setImage(String image){
		this.image = image;
	}
	public String getImage(){
		return this.image;
	}
	public void setIsScroll(String isScroll){
		this.isScroll = isScroll;
	}
	public String getIsScroll(){
		return this.isScroll;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return this.sort;
	}
	public void setStatus(String status){
		this.status = status;
	}
	public String getStatus(){
		return this.status;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return this.url;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return this.type;
	}
	public void setGmtPublish(Date gmtPublish){
		this.gmtPublish = gmtPublish;
	}
	public Date getGmtPublish(){
		return this.gmtPublish;
	}
	public void setGmtCreate(Date gmtCreate){
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtCreate(){
		return this.gmtCreate;
	}
	public void setGmtModify(Date gmtModify){
		this.gmtModify = gmtModify;
	}
	public Date getGmtModify(){
		return this.gmtModify;
	}
	public void setNid(String nid){
		this.nid = nid;
	}
	public String getNid(){
		return this.nid;
	}
	public void setAgreeNum(Integer agreeNum){
		this.agreeNum = agreeNum;
	}
	public Integer getAgreeNum(){
		return this.agreeNum;
	}
	public void setBid(Integer bid){
		this.bid = bid;
	}
	public Integer getBid(){
		return this.bid;
	}
	public void setCaid(String caid){
		this.caid = caid;
	}
	public String getCaid(){
		return this.caid;
	}
	public void setClickNum(Integer clickNum){
		this.clickNum = clickNum;
	}
	public Integer getClickNum(){
		return this.clickNum;
	}
	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}
	public Date getCreateTime(){
		return this.createTime;
	}
	public void setCreateUser(String createUser){
		this.createUser = createUser;
	}
	public String getCreateUser(){
		return this.createUser;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return this.date;
	}
	public void setDisagreeNum(Integer disagreeNum){
		this.disagreeNum = disagreeNum;
	}
	public Integer getDisagreeNum(){
		return this.disagreeNum;
	}
	public void setEnglishTitle(String englishTitle){
		this.englishTitle = englishTitle;
	}
	public String getEnglishTitle(){
		return this.englishTitle;
	}
	public void setHyperlink(String hyperlink){
		this.hyperlink = hyperlink;
	}
	public String getHyperlink(){
		return this.hyperlink;
	}
	public void setHyperlinkName(String hyperlinkName){
		this.hyperlinkName = hyperlinkName;
	}
	public String getHyperlinkName(){
		return this.hyperlinkName;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	public String getKeywords(){
		return this.keywords;
	}
	public void setMiniture(String miniture){
		this.miniture = miniture;
	}
	public String getMiniture(){
		return this.miniture;
	}
	public void setMobilecontent(String mobilecontent){
		this.mobilecontent = mobilecontent;
	}
	public String getMobilecontent(){
		return this.mobilecontent;
	}
	public void setModifyTime(Date modifyTime){
		this.modifyTime = modifyTime;
	}
	public Date getModifyTime(){
		return this.modifyTime;
	}
	public void setModifyUser(String modifyUser){
		this.modifyUser = modifyUser;
	}
	public String getModifyUser(){
		return this.modifyUser;
	}
	public void setOnhome(Integer onhome){
		this.onhome = onhome;
	}
	public Integer getOnhome(){
		return this.onhome;
	}
	public void setPublishTime(Date publishTime){
		this.publishTime = publishTime;
	}
	public Date getPublishTime(){
		return this.publishTime;
	}
	public void setSeodes(String seodes){
		this.seodes = seodes;
	}
	public String getSeodes(){
		return this.seodes;
	}
	public void setSeokey(String seokey){
		this.seokey = seokey;
	}
	public String getSeokey(){
		return this.seokey;
	}
}