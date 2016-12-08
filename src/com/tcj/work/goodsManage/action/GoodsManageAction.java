package com.tcj.work.goodsManage.action;

import java.io.File;
import java.io.FileInputStream;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.apache.commons.collections.MapUtils;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.tcj.common.EgladServiceException;
import com.tcj.common.ResultBean;
import com.tcj.common.dao.model.Page;
import com.tcj.common.poi.util.ExcelUtil;
import com.tcj.common.poi.util.ResponseUtil;
import com.tcj.domains.AssocBuildingGoods;
import com.tcj.domains.AssocCategoryGoods;
import com.tcj.domains.Category;
import com.tcj.domains.Goods;
import com.tcj.domains.LoginEntity;

import com.tcj.domains.Tbk_Goods;
import com.tcj.domains.User;
import com.tcj.work.authorize.service.IAuthorizeService;
import com.tcj.work.goodsManage.biz.GoodsManageBiz;
import com.tcj.work.goodsManage.service.GoodsManageService;

import net.sf.json.JSONObject;
/**
 * 
 * 
 * 
 * 
 * @author  zxs
 * @version 1.0
 * @date 2016/11/3日下午一点
 *
 */




@Controller
@RemoteProxy(name="goodsManageAction")
public class GoodsManageAction {

	@Autowired
	@Qualifier("goodsManageBizImpl")
	private GoodsManageBiz goodsManageBiz;
	@Autowired()
	@Qualifier("authorizeService")
	private IAuthorizeService authorizeService;

	@Autowired
	@Qualifier("goodsManageServiceImpl")
	private GoodsManageService goodsManageService;
	@RemoteMethod
	public Page getGoodsList(Map<String,String>map){
		return goodsManageBiz.getList(map);
				

	}
	/**
	 * 
	 * 
	 * 
	 * 删除
	 * 逻辑删除（或者物理删除）
	 * 
	 */

	@RemoteMethod
	public ResultBean deleteGoodsList(String id){
		return goodsManageBiz.deleteList(id);
	}








	/**
	 * 
	 * 新增和修改页面
	 * 
	 * saveorupdate
	 * 
	 */
	@RemoteMethod
	public ResultBean saveOrUpdate(Map map){
		System.out.println("111");
		return goodsManageBiz.saveorUpdate(map);
	}


	/**
	 * 
	 * 
	 * 查询category里面的parent_id
	 */

	@RemoteMethod
	public List<Category> getCategoryId(Map<String, String> map) {

		return (List<Category>) goodsManageBiz.getCategoryId(map);

	}

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	@RemoteMethod
	public List<Category> getParaentId(Map<String, String> map) {

		return (List<Category>) goodsManageBiz.getParaentId(map);

	}



	/**
	 * 
	 * 根据id查询详情
	 */
	@RemoteMethod
	public ResultBean getById(Integer id) {

		return  goodsManageBiz.getById(id);
	}



	/**
	 * 
	 * 
	 * 修改
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@RemoteMethod
	public ResultBean updategoods(Map map){
		return goodsManageBiz.savegoods(map);
	}

	/**
	 * 
	 * 上传excel
	 * 
	 * 
	 */

	private File userUploadFile;

	public File getUserUploadFile() {
		return userUploadFile;
	}

	public void setUserUploadFile(File userUploadFile) {
		this.userUploadFile = userUploadFile;
	}


	@RequestMapping(value="/excelManage")
	@RemoteMethod
	public String upload(@RequestParam(value = "userUploadFile") MultipartFile image,
			@RequestParam(value = "id") String id,
			@RequestParam(value = "categoryId") String categoryId,HttpSession session)throws Exception{
		LoginEntity entity = null;
		User userdata=new User();
		entity = (LoginEntity) session.getAttribute("LogInDemoEntity");
		System.out.println("用户id:"+entity.getUserId());
		//查询用户信息
		userdata= goodsManageService.getByUserId(Integer.parseInt(entity.getUserId()));

		System.out.println("excel=========="+image);
		System.out.println("id=========="+id);
		System.out.println("categoryId=========="+categoryId);
		POIFSFileSystem fs=new POIFSFileSystem(image.getInputStream());
		System.out.println("fs==========="+fs);
		System.out.println("userUploadFile"+fs);
		HSSFWorkbook wb=new HSSFWorkbook(fs);
		HSSFSheet hssfSheet=wb.getSheetAt(0);  // 获取第一个Sheet页
		if(hssfSheet!=null){
			for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
				HSSFRow hssfRow=hssfSheet.getRow(rowNum);
				if(hssfRow==null){
					continue;
				}
				//	User user=new User();

				//user.setName(ExcelUtil.formatCell(hssfRow.getCell(0)));
				//	user.setPhone(ExcelUtil.formatCell(hssfRow.getCell(1)));
				//	user.setEmail(ExcelUtil.formatCell(hssfRow.getCell(2)));
				//	user.setQq(ExcelUtil.formatCell(hssfRow.getCell(3)));
				//	Connection con=null;
			//	Integer goodsId;
				Goods goods=new Goods();
			
			//	goods= goodsManageService.getgoodsId(goods.getId());
				
				//if(null==goods.getId() && "".equals(goods.getId())){
			//	System.out.println("你好，id======"+goods.getId());
//				if(goods.getId()==ExcelUtil.formatCell1(hssfRow.getCell(0))){
//					goodsManageBiz.saveorUpdate(goods);
//				}if
				if (null==ExcelUtil.formatCell(hssfRow.getCell(0)) || "".equals(ExcelUtil.formatCell(hssfRow.getCell(0)))){				
				System.out.println("id为空"+goods.getId());
				//goods.setId(ExcelUtil.formatCell1(hssfRow.getCell(0)));
				goods.setName(ExcelUtil.formatCell(hssfRow.getCell(1)));
				goods.setTitle(ExcelUtil.formatCell(hssfRow.getCell(2)));
				goods.setAliasname(ExcelUtil.formatCell(hssfRow.getCell(3)));
				goods.setBarcode(ExcelUtil.formatCell(hssfRow.getCell(4)));
				goods.setPrivatecode(ExcelUtil.formatCell(hssfRow.getCell(5)));
				goods.setPubliccode(ExcelUtil.formatCell(hssfRow.getCell(6)));
				goods.setTradecode(ExcelUtil.formatCell(hssfRow.getCell(7)));
				goods.setBriefintro(ExcelUtil.formatCell(hssfRow.getCell(8)));
				goods.setDetailintro(ExcelUtil.formatCell(hssfRow.getCell(9)));
				goods.setMiniature(ExcelUtil.formatCell(hssfRow.getCell(10)));
				goods.setImage1(ExcelUtil.formatCell(hssfRow.getCell(11)));
				goods.setImage2(ExcelUtil.formatCell(hssfRow.getCell(12)));
				goods.setImage3(ExcelUtil.formatCell(hssfRow.getCell(13)));
				goods.setImage4(ExcelUtil.formatCell(hssfRow.getCell(14)));
				goods.setImage5(ExcelUtil.formatCell(hssfRow.getCell(15)));
				goods.setSize(ExcelUtil.formatCell(hssfRow.getCell(16)));
				goods.setWeight(ExcelUtil.formatCell(hssfRow.getCell(17)));
				goods.setModel(ExcelUtil.formatCell(hssfRow.getCell(18)));
				goods.setSpec(ExcelUtil.formatCell(hssfRow.getCell(19)));		
				goods.setBrandId((ExcelUtil.formatCell1(hssfRow.getCell(20))));
				System.out.println("brandId===="+ExcelUtil.formatCell1(hssfRow.getCell(20)));
				goods.setMaterial(ExcelUtil.formatCell(hssfRow.getCell(21)));
				goods.setColor(ExcelUtil.formatCell(hssfRow.getCell(22)));
				goods.setSerialno(ExcelUtil.formatCell(hssfRow.getCell(23)));
				goods.setProducearea(ExcelUtil.formatCell(hssfRow.getCell(24)));
				goods.setProductdate(ExcelUtil.formatCell(hssfRow.getCell(25)));
				goods.setProviderId((ExcelUtil.formatCell1(hssfRow.getCell(26))));
				goods.setRefprice(ExcelUtil.formatCell(hssfRow.getCell(27)));
				System.out.println("原价+++"+ExcelUtil.formatCell(hssfRow.getCell(27)));
				goods.setCostprice(ExcelUtil.formatCell(hssfRow.getCell(28)));
				goods.setPrice(ExcelUtil.formatCell(hssfRow.getCell(29)));
				goods.setVipprice(ExcelUtil.formatCell(hssfRow.getCell(30)));
				goods.setDiscount((ExcelUtil.formatCell1(hssfRow.getCell(31))));
				goods.setUrl(ExcelUtil.formatCell(hssfRow.getCell(32)));
				goods.setRecommendlevel((ExcelUtil.formatCell1(hssfRow.getCell(33))));
				goods.setRecommendremark(ExcelUtil.formatCell(hssfRow.getCell(34)));
				goods.setViewnum((ExcelUtil.formatCell1(hssfRow.getCell(35))));
				goods.setOrdernum((ExcelUtil.formatCell1(hssfRow.getCell(36))));
				goods.setSalenum((ExcelUtil.formatCell1(hssfRow.getCell(37))));
				goods.setStocknum((ExcelUtil.formatCell1(hssfRow.getCell(38))));
				goods.setIsfreshgoods((ExcelUtil.formatCell1(hssfRow.getCell(39))));
				goods.setIshotgoods((ExcelUtil.formatCell1(hssfRow.getCell(40))));
				goods.setIsoutofstock((ExcelUtil.formatCell1(hssfRow.getCell(41))));
				goods.setCreator((ExcelUtil.formatCell1(hssfRow.getCell(42))));
				System.out.println("creator+++==="+ExcelUtil.formatCell1(hssfRow.getCell(42)));
				
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                try {
//                        Date createtime = (Date) sdf.parse(ExcelUtil.formatCell(hssfRow.getCell(42)));
//                         String s = sdf.format(createtime);
//                      System.out.println(s);
//                     } catch (ParseException e) {
//                    e.printStackTrace();
//              }
//				
				
				
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(43)))){
			//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//Date date = sdf.parse("2008-08-08 12:10:12");
					java.util.Date  createtime=  sdf.parse(("2008-08-08 12:10:12"));
					//System.out.println("时间++=="+
					goods.setCreatetime(createtime);
					System.out.println("创建时间+++========"+createtime);
				}	
				
				goods.setClassification(ExcelUtil.formatCell(hssfRow.getCell(44)));
				goods.setSort((ExcelUtil.formatCell1(hssfRow.getCell(45))));

				if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(46)))){
					java.util.Date supplydate=sdf.parse("2008-08-08 12:10:12");
					goods.setSupplydate(supplydate);
				}

				//	goods.setSupplydate((ExcelUtil.formatCell2(hssfRow.getCell(42))));
				if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(47)))){
				goods.setStatus("0");
				
				}
				goods.setType(ExcelUtil.formatCell(hssfRow.getCell(48)));

				if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(49)))){
					java.util.Date lastModified=sdf.parse("2008-08-08 12:10:12");
					goods.setLastModified(lastModified);
				}
				goods.setScoreAdd((ExcelUtil.formatCell1(hssfRow.getCell(50))));
				goods.setUnit(ExcelUtil.formatCell(hssfRow.getCell(51)));
				goods.setIndexid(ExcelUtil.formatCell(hssfRow.getCell(52)));

				
				
			
				
				try{
					System.out.println("你好");
					Integer goodsid=	goodsManageBiz.saveuploadGoods(goods);
				
					System.out.println("shangp"+goodsid);
					AssocCategoryGoods acg=new AssocCategoryGoods();
					acg.setGoodsId(goodsid); 
				
					acg.setCategoryId(Integer.valueOf(categoryId)); 
					goodsManageService.saveAssocCategoryGoods(acg);	
			
				
				}catch(Exception e){
					e.printStackTrace();

				}
				}else{
					System.out.println("hellonihao");
					goods.setId(ExcelUtil.formatCell1(hssfRow.getCell(0)));
					goods.setName(ExcelUtil.formatCell(hssfRow.getCell(1)));
					goods.setTitle(ExcelUtil.formatCell(hssfRow.getCell(2)));
					goods.setAliasname(ExcelUtil.formatCell(hssfRow.getCell(3)));
					goods.setBarcode(ExcelUtil.formatCell(hssfRow.getCell(4)));
					goods.setPrivatecode(ExcelUtil.formatCell(hssfRow.getCell(5)));
					goods.setPubliccode(ExcelUtil.formatCell(hssfRow.getCell(6)));
					goods.setTradecode(ExcelUtil.formatCell(hssfRow.getCell(7)));
					goods.setBriefintro(ExcelUtil.formatCell(hssfRow.getCell(8)));
					goods.setDetailintro(ExcelUtil.formatCell(hssfRow.getCell(9)));
					goods.setMiniature(ExcelUtil.formatCell(hssfRow.getCell(10)));
					goods.setImage1(ExcelUtil.formatCell(hssfRow.getCell(11)));
					goods.setImage2(ExcelUtil.formatCell(hssfRow.getCell(12)));
					goods.setImage3(ExcelUtil.formatCell(hssfRow.getCell(13)));
					goods.setImage4(ExcelUtil.formatCell(hssfRow.getCell(14)));
					goods.setImage5(ExcelUtil.formatCell(hssfRow.getCell(15)));
					goods.setSize(ExcelUtil.formatCell(hssfRow.getCell(16)));
					goods.setWeight(ExcelUtil.formatCell(hssfRow.getCell(17)));
					goods.setModel(ExcelUtil.formatCell(hssfRow.getCell(18)));
					goods.setSpec(ExcelUtil.formatCell(hssfRow.getCell(19)));		
					goods.setBrandId((ExcelUtil.formatCell1(hssfRow.getCell(20))));
					System.out.println("brandId===="+ExcelUtil.formatCell1(hssfRow.getCell(20)));
					goods.setMaterial(ExcelUtil.formatCell(hssfRow.getCell(21)));
					goods.setColor(ExcelUtil.formatCell(hssfRow.getCell(22)));
					goods.setSerialno(ExcelUtil.formatCell(hssfRow.getCell(23)));
					goods.setProducearea(ExcelUtil.formatCell(hssfRow.getCell(24)));
					goods.setProductdate(ExcelUtil.formatCell(hssfRow.getCell(25)));
					goods.setProviderId((ExcelUtil.formatCell1(hssfRow.getCell(26))));
					goods.setRefprice(ExcelUtil.formatCell(hssfRow.getCell(27)));
					System.out.println("原价+++"+ExcelUtil.formatCell(hssfRow.getCell(27)));
					goods.setCostprice(ExcelUtil.formatCell(hssfRow.getCell(28)));
					goods.setPrice(ExcelUtil.formatCell(hssfRow.getCell(29)));
					goods.setVipprice(ExcelUtil.formatCell(hssfRow.getCell(30)));
					goods.setDiscount((ExcelUtil.formatCell1(hssfRow.getCell(31))));
					goods.setUrl(ExcelUtil.formatCell(hssfRow.getCell(32)));
					goods.setRecommendlevel((ExcelUtil.formatCell1(hssfRow.getCell(33))));
					goods.setRecommendremark(ExcelUtil.formatCell(hssfRow.getCell(34)));
					goods.setViewnum((ExcelUtil.formatCell1(hssfRow.getCell(35))));
					goods.setOrdernum((ExcelUtil.formatCell1(hssfRow.getCell(36))));
					goods.setSalenum((ExcelUtil.formatCell1(hssfRow.getCell(37))));
					goods.setStocknum((ExcelUtil.formatCell1(hssfRow.getCell(38))));
					goods.setIsfreshgoods((ExcelUtil.formatCell1(hssfRow.getCell(39))));
					goods.setIshotgoods((ExcelUtil.formatCell1(hssfRow.getCell(40))));
					goods.setIsoutofstock((ExcelUtil.formatCell1(hssfRow.getCell(41))));
					goods.setCreator((ExcelUtil.formatCell1(hssfRow.getCell(42))));
					System.out.println("creator+++==="+ExcelUtil.formatCell1(hssfRow.getCell(42)));
					
//					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//	                try {
//	                        Date createtime = (Date) sdf.parse(ExcelUtil.formatCell(hssfRow.getCell(42)));
//	                         String s = sdf.format(createtime);
//	                      System.out.println(s);
//	                     } catch (ParseException e) {
//	                    e.printStackTrace();
//	              }
//					
					
					
	                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(43)))){
				//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//Date date = sdf.parse("2008-08-08 12:10:12");
						java.util.Date  createtime=  sdf.parse(("2008-08-08 12:10:12"));
						//System.out.println("时间++=="+
						goods.setCreatetime(createtime);
						System.out.println("创建时间+++========"+createtime);
					}	
					
					goods.setClassification(ExcelUtil.formatCell(hssfRow.getCell(44)));
					goods.setSort((ExcelUtil.formatCell1(hssfRow.getCell(45))));

					if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(46)))){
						java.util.Date supplydate=sdf.parse("2008-08-08 12:10:12");
						goods.setSupplydate(supplydate);
					}

					//	goods.setSupplydate((ExcelUtil.formatCell2(hssfRow.getCell(42))));
					if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(47)))){
					goods.setStatus("0");
					
					}
					goods.setType(ExcelUtil.formatCell(hssfRow.getCell(48)));

					if(!"".equals(ExcelUtil.formatCell(hssfRow.getCell(49)))){
						java.util.Date lastModified=sdf.parse("2008-08-08 12:10:12");
						goods.setLastModified(lastModified);
					}
					goods.setScoreAdd((ExcelUtil.formatCell1(hssfRow.getCell(50))));
					goods.setUnit(ExcelUtil.formatCell(hssfRow.getCell(51)));
					goods.setIndexid(ExcelUtil.formatCell(hssfRow.getCell(52)));
					goodsManageService.updategoods(goods);
				}
				
				
			}
			
		}
		//	JSONObject result=new JSONObject();
		//	System.out.println("你好!");
		//	result.put("success", "true");
		//	ResponseUtil.write(ServletActionContext.getResponse(), result);
		System.out.println("你好a!!!");
		return "/work/TbkGoods/goods";
	}








}


