
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/template/header.jsp"%>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
<!-- header -->
<div style="height: 160px;"></div>
<div align="center">
   <h3><b>상품관리</b></h3>
</div>
<div style="height: 30px;"></div>
<!-- body -->
<div align="center" style="width:1000px; margin-left:auto; margin-right:auto;">
   <ul class="nav nav-tabs">
      <li class="nav-item">
       <a style=color:black; class="nav-link" href="<c:url value='/admin/openAdminMember.do'/>">회원관리</a>
     </li>
     <li class="nav-item">
       <a style=color:hotpink; class="nav-link active" href="<c:url value='/admin/openAdminGoods.do'/>">상품관리</a>
     </li>
     <li class="nav-item">
       <a style=color:black; class="nav-link" href="<c:url value='/report/openAdminReport.do'/>">신고관리</a>
     </li>
     <li class="nav-item">
       <a style=color:black; class="nav-link" href="<c:url value='/admin/acsBoardList.do'/>">공지관리</a>
     </li>
     <li class="nav-item">
       <a style=color:black; class="nav-link" href="<c:url value='/admin/openDailyAccountingList.do'/>">매출관리</a>
     </li>
     <li class="nav-item">
       <a style=color:black; class="nav-link" href="javascript:void(window.open('https://desk.channel.io/#/channels/66232/user_chats/6172b6a2a9e910f2c971'))">고객채팅</a>
     </li>
   </ul>
<div class="container" style="margin-top:20px;">
   <!-- search bar -->
   <div style="padding:10px; float:right; display:inline-block;">
     <form id="frm" method="post" action="/muscle/admin/SearchGoods.do">
    	<select id="searchType" name="searchType">
     		<option value="ALL" ${searchDTO.searchType == 'ALL' ? 'selected' : ''}>전체</option>
   	  		<option value="GOODS_NUM" ${searchDTO.searchType == 'GOODS_NUM' ? 'selected' : ''}>상품번호</option>
   	  		<option value="GOODS_NAME" ${searchDTO.searchType == 'GOODS_NAME' ? 'selected' : ''}>상품명</option>
  			<option value="GOODS_CATEGORY1" ${searchDTO.searchType == 'GOODS_CATEGORY1' ? 'selected' : ''}>카테고리1</option>
   			<option value="GOODS_CATEGORY2" ${searchDTO.searchType == 'GOODS_CATEGORY2' ? 'selected' : ''}>카테고리2</option>
  		 </select>
        <input type="text" name="keyword" id="keyword" value="${searchDTO.replaceKeyword}" class="form-control"  placeholder="카테고리 선택 후 검색해주세요." style="width:300px; display:inline-block;" />
        <input type="submit" value="검색" class="btn btn-primary btn-sm" style="display:inline-block; background-color:pink; border-color:pink; margin-bottom:5px;"/>
    	<!-- <input type="submit" value="검색" class="btn btn-primary btn-sm" style="display:inline-block; margin-botton:5px;"/> -->
    	
    </form>
   </div>
   <table align="center" class="table table-hover" style="cellpadding:7px;">
      <thead>
      <tr style="background-color:pink;">
         <th style="width:13%" colspan="2">
         <div class="dropdown">
            <a type="button" class="dropdown-toggle" data-toggle="dropdown">전 체</a>
            <div class="dropdown-menu">
               <a class="dropdown-item" href="/muscle/admin/allList.do">전 체</a>
               <a class="dropdown-item" href="/muscle/admin/notice.do">Home Training</a>
               <a class="dropdown-item" href="/muscle/admin/event.do">Healthy Diet</a>
               <a class="dropdown-item" href="/muscle/admin/fdsafd.do">Home Care</a>
            </div>
         </div>
         </th>
         <th style="width:10%">상품번호</th>
         <th style="width:*">상품명</th>
         <th style="width:15%">카테고리1</th>
         <th style="width:15%">카테고리2</th>
         <th style="width:10%">원 가</th>
         <th style="width:10%">회원가</th>
         <th style="width:10%">비회원가</th>
      </tr>
      </thead>
      <tbody>
      <c:choose>
         <c:when test="${fn:length(list) > 0 }">
            <c:forEach items="${list}" var="row">
               <tr>
                    <td><a href="#this" name="mdf"><input type="button" style="background-color:pink; border-color:pink; color:white;"class="btn btn-outline-primary btn-sm" value="수정">
                    <input type="hidden" name="GOODS_NUM" id="GOODS_NUM" value="${row.GOODS_NUM}"></a></td>
                   <td><a href="#this" name="delete1"><input type="button" style="background-color:hotpink; border-color:hotpink; color:white;" class="btn btn-outline-primary btn-sm"  value="삭제">
                   <input  type="hidden" name="GOODS_NUM" id="GOODS_NUM" value="${row.GOODS_NUM}"></a></td>
                  <td align="center">${row.GOODS_NUM}</td>
                  <td>${row.GOODS_NAME}</td>
                  <td>${row.GOODS_CATEGORY1}</td>
                  <td>${row.GOODS_CATEGORY2}</td>
                  <td>${row.GOODS_CPRICE}</td>
                  <td>${row.GOODS_MPRICE}</td>
                  <td>${row.GOODS_NMPRICE}</td>
              </tr>
            </c:forEach>
         </c:when>
      <c:otherwise>
         <tr>
            <td colspan="9" align=center> 조회된 결과가 없습니다.</td>
         </tr>
      </c:otherwise>
      </c:choose>
      </tbody>
      </table>
      <hr>
      <div align="right">
      <button type="button" style="background-color:hotpink; color:whtie; border-color:hotpink;" class="btn btn-primary btn-sm" onclick="location.href='/muscle/shop/openGoodsWrite.do'">상품 등록</button>
      </div>
   </div>
  </div>
   <br>
   <script type="text/javascript">
   $(document).ready(function(){
         $("a[name='delete1']").on("click",function(e){
            e.preventDefault();
            fn_delete($(this));
            });
         $("a[name='mdf']").on("click",function(e){
               e.preventDefault();
               fn_update($(this));
               });
   });
     function fn_delete(obj){
          var comSubmit = new ComSubmit();
          var CONFIRM = confirm("정말로 삭제하시겠습니까?");
          if(CONFIRM==true){
          comSubmit.setUrl("<c:url value='/admin/deleteAdminGoods.do'/>");
           comSubmit.addParam("GOODS_NUM", obj.parent().find("#GOODS_NUM").val());
           comSubmit.submit();
           alert("상품이 삭제 되었습니다.");
          }
     }
     function fn_update(obj) {
 		var comSubmit = new ComSubmit();
 		comSubmit.setUrl("<c:url value='/admin/goodsModifyForm.do'/>");
 		comSubmit.addParam("IDX",  obj.parent().find("#GOODS_NUM").val());
 		comSubmit.submit();
 	}
     var searchType = $("#search option:selected").val();
    function fn_search(searchType) {
    	var comSubmit = new ComSunmit();
    	comSubmit.set
    }
  	
</script>
<!-- footer       -->
<div style="height:80px;">
<%@ include file="/WEB-INF/include/include-body.jspf" %>
<footer style="border-top:1px solid #D5D5D5;">
<%@ include file="/WEB-INF/views/template/footer.jsp"%>
</footer>
</div>
</body>
</html>