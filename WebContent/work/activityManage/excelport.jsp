<html xmlns:v="urn:schemas-microsoft-com:vml"
xmlns:o="urn:schemas-microsoft-com:office:office"
xmlns:x="urn:schemas-microsoft-com:office:excel"
xmlns="http://www.w3.org/TR/REC-html40">
<%@ page language="java" import="com.tcj.domains.StatisticsEntity" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.addHeader("content-disposition","attachment;filename=excelreport.xls");
List<StatisticsEntity> list = (List<StatisticsEntity>)request.getSession().getAttribute("list");
%>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../inc.jsp"%>
<link rel='stylesheet' type='text/css' href='<%=basePath%>css/excelstylesheet.css' />
<script type="text/javascript">
alert("list:"+list.get(0).status);
</script>
<!--[if gte mso 9]>
   <xml>
    <o:DocumentProperties>
     <o:Created>2016-08-13T16:57:21</o:Created>
     <o:LastSaved>2016-08-13T17:00:21</o:LastSaved>
    </o:DocumentProperties>
    <o:CustomDocumentProperties>
     <o:KSOProductBuildVer dt:dt="string">2052-10.1.0.5850</o:KSOProductBuildVer>
    </o:CustomDocumentProperties>
   </xml>
  <![endif]-->
<!--[if gte mso 9]>
   <xml>
    <x:ExcelWorkbook>
     <x:ExcelWorksheets>
      <x:ExcelWorksheet>
       <x:Name>excelreport (4)</x:Name>
       <x:WorksheetOptions>
        <x:DefaultRowHeight>285</x:DefaultRowHeight>
        <x:Selected/>
        <x:Panes>
         <x:Pane>
          <x:Number>3</x:Number>
          <x:ActiveCol>3</x:ActiveCol>
          <x:ActiveRow>0</x:ActiveRow>
          <x:RangeSelection>D1</x:RangeSelection>
         </x:Pane>
        </x:Panes>
        <x:DoNotDisplayGridlines/>
        <x:ProtectContents>False</x:ProtectContents>
        <x:ProtectObjects>False</x:ProtectObjects>
        <x:ProtectScenarios>False</x:ProtectScenarios>
        <x:Print>
         <x:PaperSizeIndex>9</x:PaperSizeIndex>
        </x:Print>
       </x:WorksheetOptions>
      </x:ExcelWorksheet>
     </x:ExcelWorksheets>
     <x:ProtectStructure>False</x:ProtectStructure>
     <x:ProtectWindows>False</x:ProtectWindows>
     <x:WindowHeight>8370</x:WindowHeight>
     <x:WindowWidth>20385</x:WindowWidth>
    </x:ExcelWorkbook>
   </xml>
  <![endif]-->
</head>
<body link="blue" vlink="purple" class="xl65">
  <table width="1166" border="0" cellpadding="0" cellspacing="0" style='width:874.50pt;border-collapse:collapse;table-layout:fixed;'>
   <col width="124" class="xl65" style='mso-width-source:userset;mso-width-alt:3968;'/>
   <col width="149" class="xl65" style='mso-width-source:userset;mso-width-alt:4768;'/>
   <col width="144" span="4" class="xl65" style='mso-width-source:userset;mso-width-alt:4608;'/>
   <col width="142" class="xl65" style='mso-width-source:userset;mso-width-alt:4544;'/>
   <col width="146" class="xl65" style='mso-width-source:userset;mso-width-alt:4672;'/>
   <col width="170" class="xl65" style='mso-width-source:userset;mso-width-alt:5440;'/>
   <col width="147" class="xl65" style='mso-width-source:userset;mso-width-alt:4704;'/>
   <col width="72" span="246" class="xl65" style='mso-width-source:userset;mso-width-alt:2304;'/>
   <tr height="19" style='height:14.25pt;mso-height-source:userset;mso-height-alt:285;'>
    <td class="xl66" class="xl66" height="19" width="124" style='height:14.25pt;width:93.00pt;' x:str>课程名称</td>
    <td class="xl66" width="149" style='width:111.75pt;' x:str>缴费项目</td>
    <td class="xl66" width="144" style='width:108.00pt;' x:str>客户姓名</td>
    <td class="xl66" width="144" style='width:108.00pt;' x:str>联系电话</td>
    <td class="xl66" width="144" style='width:108.00pt;' x:str>付款状态</td>
    <td class="xl66" width="144" style='width:108.00pt;' x:str>支付类型</td>
    <td class="xl66" width="142" style='width:106.50pt;' x:str>价格</td>
    <td class="xl66" width="146" style='width:109.50pt;' x:str>课程起始时间</td>
    <td class="xl66" width="170" style='width:127.50pt;' x:str>课程终止时间</td>
   </tr>
  <c:forEach items="${list }" var="list">
   <tr height="19" style='height:14.25pt;mso-height-source:userset;mso-height-alt:285;'>
    <td class="xl67" height="19" style='height:14.25pt;' x:str>${list.activityName }</td>
    <td class="xl67" x:str>${list.activity_project_name }</td>
    <td class="xl67" x:str>${list.actMemberName }</td>
    <td class="xl67" x:num>${list.mobile }</td>
    <td class="xl67" x:str>${list.status }</td>
    <td class="xl67" x:str>${list.pay_type } </td>
    <td class="xl67" x:num>&yen;${list.payment } </td>
    <td class="xl68" x:num>${list.start_time } </td>
    <td class="xl68" x:num>${list.end_time } </td>
   </tr>
</c:forEach>
   <![if supportMisalignedColumns]>
   <tr width="0" style='display:none;'>
     <td width="124" style='width:93;'></td>
     <td width="149" style='width:112;'></td>
     <td width="144" style='width:108;'></td>
     <td width="142" style='width:107;'></td>
     <td width="146" style='width:110;'></td>
     <td width="170" style='width:128;'></td>
     <td width="147" style='width:110;'></td>
     <td width="72" style='width:54;'></td>
    </tr>
   <![endif]>
  </table>
 </body>

</html>
