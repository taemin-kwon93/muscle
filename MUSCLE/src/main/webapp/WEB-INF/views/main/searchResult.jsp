<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<link href="<c:url value="/css/board.css"/>" rel="stylesheet">
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
@import url(https://fonts.googleapis.com/css?family=Open+Sans);

#main-container
{
   margin:0px;
   min-height: 400px;
   padding: 20px;
   border-top: 1px solid #fff;  
   border-right: 1px solid #fff;     
   border-left: 1px solid #fff; 
   border-bottom: 1px solid #fff;    
}

#detailSearch{
	font-family: 'Open Sans', sans-serif; 
	text-align: left;
	font-weight: bold;
	color: pink;
	border: gray;
	background-color:transparent;
}

#DetailCheckBox{
	border: 1px solid gray;
	border-collapse: collapse;
	display:inline-block; 
	width:120%; 
	align:center;
	border-radius: 10px;
}


/* 검색버튼 시작 */


body{
  
  font-family: 'Open Sans', sans-serif;
}

.search {
  width: 100%;
  position: relative;
  display: flex;
}

.searchTerm {
  width: 100%;
  border: 3px solid #C86060;
  border-right: none;
  padding: 5px;
  height: 20px;
  border-radius: 5px 0 0 5px;
  outline: none;
  color: #C86060;
}

.searchTerm:focus{
  color: #C86060;
}

.searchButton {
  width: 60px;
  height: 36px;
  border: 1px solid #C86060;
  background: #C86060;
  text-align: center;
  color: #fff;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
  font-size: 20px;
}

/*Resize the wrap to see the search bar change!*/
.wrap{
  width: 20%;
  position: absolute;
  top: 300px;
  left: 50%;
  transform: translate(-50%, -50%);
}
/* 검색버튼 끝 */

.font1 {
   font-size: 14px;
    color: #666;
    letter-spacing: -0.5px;
    line-height: 24px;
    white-space: normal;
    line-height: 27px;
}

.font2 {
   display: block;
    font-size: 16px;
    font-weight: 400;
    color: #333;
    font-family: unset;
    line-height: 27px;
}

/* css 초기화  */



table {
   border-collapse: collapse;
   border-spacing: 0;
   border: none;
}
/*css 초기화*/
.card {
   float:left;
   height: 300px;
   width: 21%;
   display: inline-block;
   margin-top: 30px;
   margin-left: 30px;
   margin-bottom: 30px;
   position: relative;
   overflow: hidden;
   border: none;
}
.card-header {
   -webkit-transition: 0.5s; /*사파리 & 크롬*/
    -moz-transition: 0.5s;  /*파이어폭스*/
    -ms-transition: 0.5s;   /*인터넷 익스플로러*/
    -o-transition: 0.5s;  /*오페라*/
    transition: 0.5s;
   width: 100%;
   height: 270px;
   border-radius: 15px 15px 0 0;
   background-image: url("resources/images/no_image.png");
   background-size: 100% 280px;
   background-repeat: no-repeat;   
}

.card-header-is_closed{
    background-color: #EF5A31 ;
    color: #FFF ;
    font-weight: bold ;
    text-align: center ;
    float: right;
    margin: 15px 15px 0 0;
    border-radius: 50%;
    font-weight: bold;
    padding: 10px 10px;
    line-height: 20px;
}
.card-header-is_closed2{
    background-color: #3fb50e ;
    color: #FFF ;
    font-weight: bold ;
    text-align: center ;
    float: right;
    margin: 15px 15px 0 0;
    border-radius: 50%;
    font-weight: bold;
    padding: 10px 10px;
    line-height: 20px;
}


.card-body-header{
   line-height: 25px;
   margin: 10px 20px 0px 20px;
}
.card-body-description  {
    opacity: 0;
    color: #757B82;
    line-height: 25px;
    -webkit-transition: .2s ease-in-out; /*사파리&크롬*/
    transition : .2s ease-in-out;
    overflow: hidden;
   height: 180px;
   margin: 5px 20px;
}

.card-body-hashtag {
   color: #2478FF;
   font-style: italic;
}
.card-body-footer {
     position: absolute; 
     margin-top: 15px;
     margin-bottom: 6px;
    bottom: 0; 
    width: 314px;
    font-size: 10px;
    color: #9FA5A8;
    padding: 0 10px;
}
.icon {
    display: inline-block;
    vertical-align: middle;
    margin-right: 2px;
}


.imgswap img:last-child{display:none} 

</style>
</head>
<body>
<%@ include file="/WEB-INF/views/template/header.jsp"%>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="<c:url value='/js/common1.js'/>" charset="utf-8"></script>


<div style="height: 160px;"></div>
<div align="center">
   <h3><b>Search</b></h3> 
</div>
<div style="height: 50px;"></div>

<div style="width:1000px; margin-left:auto; margin-right:auto;" align="center">
   <div  align="left" >
   
   <strong>${keyword} </strong>(으)로 검색된 상품
   </div>
   <hr>
   <div align="center">
   
   <!--리스트-->
   <form:form action="/muscle/main/openMainSearch.do" commandName="searchDetailVO">
	<table style=border-color:gray; id="DetailCheckBox">
		<tr >
	  		<th> &nbsp;카테고리</th>
	  	</tr>
	  	<tr>
		  	<td>
		  		  &nbsp; 홈트레이닝 <input class="checkBoxSort" type="checkbox" name="category1" value="홈트레이닝"/>
		   	</td>
		   	<td>	
		   		  &nbsp; 식품 <input class="checkBoxSort" type="checkbox" name="category1" value="식품"/>
			</td>
			<td>
		 		   &nbsp;홈케어 <input class="checkBoxSort" type="checkbox" name="category1" value="홈케어"/>
		 	</td>
		</tr>
		
	  	<tr>
	  	<th>&nbsp;카테고리2</th>
		</tr>
		<tr>
		   	<td>
		   		   &nbsp;운동복(남) <input type="checkbox" name="category2" value="운동복(남)" />
		   	</td>
		   
		   	<td>
		   		   &nbsp;운동복(여) <input type="checkbox" name="category2" value="운동복(여)" />
		   	</td>
		   
		   	<td>
		   		  &nbsp; 운동용품 <input type="checkbox" name="category2" value="운동용품" />
		   	</td>
		   
		   	<td>
		   		 &nbsp;  운동악세서리 <input type="checkbox" name="category2" value="운동악세서리" />
		   	</td>
		   	<td>
		   		   &nbsp;식사대용 <input type="checkbox" name="category2" value="식사대용" />
		   	</td>
		   
		   	<td>
		   		   &nbsp;건강간식 <input type="checkbox" name="category2" value="건강간식" />
		   	</td>
		   
		   	<td>
		   		  &nbsp; 보충제 <input type="checkbox" name="category2" value="보충제" />
		   	</td>
		   
		   	<td>
		   		   &nbsp;건강음료 <input type="checkbox" name="category2" value="건강음료" />
		   	</td>
		   
		   	<td>
		   		   &nbsp;향초 <input type="checkbox" name="category2" value="향초" />
		   	</td>
		   
		   	<td>
		   		   &nbsp;향수 <input type="checkbox" name="category2" value="향수" />
		   	</td>
		   
		   	<td>
		   		  &nbsp; 디퓨저 <input type="checkbox" name="category2" value="디퓨저" />
		   	</td>
		   
		   	<td>
		   		   &nbsp;제습/가습 <input type="checkbox" name="category2" value="제습/가습" />
		   	</td>
		   
		   	<td>
		   		   &nbsp;무드등 <input type="checkbox" name="category2" value="무드등" />
		   	</td>
		</tr>
		<tr>
			<th> &nbsp;사이즈</th>
		</tr>
			<td>
	   		  &nbsp; FREE <input type="checkbox" name="size" value="FREE" />
		   	</td>
			<td>
		   		  &nbsp; S <input type="checkbox" name="size" value="S" />
		   	</td>
			<td>
		   		   &nbsp;M <input type="checkbox" name="size" value="M" />
		   	</td>
			<td>
		   		  &nbsp; L <input type="checkbox" name="size" value="L" />
		   	</td>
			<td>
		   		   &nbsp;XL <input type="checkbox" name="size" value="XL" />
		   	</td>
		<tr>
			<th> &nbsp;색상</th>
		</tr>
			<td>
		   		   &nbsp;RED <input type="checkbox" name="color" value="RED" />
		   	</td>
			<td>
		   		   &nbsp;BLUE <input type="checkbox" name="color" value="BLUE" />
		   	</td>
			<td>
		   		   &nbsp;BLACK <input type="checkbox" name="color" value="BLACK" />
		   	</td>
			<td>
		   		   &nbsp;WHITE <input type="checkbox" name="color" value="WHITE" />
		   	</td>
			<td>
		   		   &nbsp;GREEN <input type="checkbox" name="color" value="GREEN" />
		   	</td>
		<tr>
			<td colspan="4">
			   	  &nbsp;상품명 : <input type="text" id="keyword" name="keyword" value="${keyword}" /> 
			   	<p>
		 	</td>
		</tr>
		
		<tr>
			<td>
			<p>
			&nbsp;<input style=color:hotpink; type="submit" id="detailSearch" value="상세검색" />
			</td>
		</tr>
	</table>
   </form:form>
   
   
   
   
   
   
   
   
   
   
   <div id="main-container">
<table class="goods_list" style="width:100%">
      <colgroup>
         <col width="100%" />
      </colgroup>
      <thead>
         <tr>
         </tr>
      </thead>
      <tbody>
      </tbody>
      </table>
   <div id="PAGE_NAVI" align="center"></div>
      <input type="hidden" id="PAGE_INDEX" name="PAGE_INDEX" />

<input type="hidden" id="keyword" name="keyword" value="${keyword}" />
<br><br>
</div>
   
   </div>


</div>




<div style="height:80px;"> 
 
</div>

<footer style="border-top:1px solid #D5D5D5;">
<%@ include file="/WEB-INF/views/template/footer.jsp"%> 
</footer>
</body>
</html>
<script type="text/javascript">

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

$(document).ready(function() {
   
   fn_selectGoodsList(1);
   
   $("a[name='title']").on("click", function(e){ //제목 //name 이 title인거
      console.log("잘들어옴");
      e.preventDefault();
      fn_openBoardDetail($(this));
   });
});

function fn_openBoardDetail(obj) {
   console.log("잘들어옴22");
   var comSubmit = new ComSubmit(); // 객체생성
   comSubmit.setUrl("<c:url value='/shop/goodsDetail.do' />"); // url설정
   comSubmit.addParam("IDX", obj.parent().find("#IDX").val()); // IDX; id로 값을넘김
   comSubmit.submit();
   
}

function fn_selectGoodsList(pageNo) {
   var comAjax = new ComAjax();

   //alert(${orderBy});
   comAjax.setUrl("<c:url value='/main/mainSearch.do' />");
   comAjax.setCallback("fn_selectGoodsListCallback");
   comAjax.addParam("PAGE_INDEX", pageNo);
   comAjax.addParam("PAGE_ROW", 16);
   comAjax.addParam("keyword", $('#keyword').val());
   comAjax.ajax();
}

function fn_selectGoodsListCallback(data) {
   var total = data.TOTAL;
   var body = $(".goods_list");
   body.empty();
   
   if (total == 0) {
      var str = "<tr>" + "<td colspan='4'>조회된 결과가 없습니다.</td>"
            + "</tr>";
      body.append(str);
   } else {
      var params = {
         divId : "PAGE_NAVI",
         pageIndex : "PAGE_INDEX",
         totalCount : total,
         recordCount : 16,
         eventName : "fn_selectGoodsList"
      };
      gfn_renderPaging(params);

      var str = "";
      $.each(data.list, function(key, value) {
                                       var imgpath = "<img src='muscle/img/goods_upload"+value.GOODS_IMG_THUM+"' width='200' height='210'>"

                                       var imgpath1 = value.GOODS_IMG_THUM.split(',');
                                       var img0 = imgpath1[0];
                                       var img1 = imgpath1[1];
                                       
                                       var Pick = value.GOODS_HASH.split(',');
                                       var pick1 = "";
                                       var pick2 = "";
                                       var pick3 = "";
                                       var pick4 = "";
                                       var num = "";
                                       for (var i=0; i<Pick.length; i++) {
                                             Pick[i];
                                             if(Pick[0] == null){
                                                pick1 = "";
                                             }else{
                                                pick1 = Pick[0];
                                             }
                                             if(Pick[1] == null){
                                                pick2 = "";
                                             }else {
                                                pick2 = Pick[1];
                                             }
                                             if(Pick[2] == null){
                                                pick3 = "";
                                             }else {
                                                pick3 = Pick[2];
                                             }
                                             if(Pick[3] == null){
                                                pick4 = "";
                                             }else{
                                                pick4 = Pick[3];
                                             }
                                          }
                                       
                                       
                                             
                                       
                                       str += "<div class='card'>"
                                          +      "<a href='#this' name='title'>"
                                          +      "<div class='imgswap'>"
                                          +       "<img src='/muscle/img/goods_upload/"+img0+"' width='200' height='210'>" 
                                          +       "<img src='/muscle/img/goods_upload/"+img1+"' width='200' height='210'>"
                                          +     " </div> "
                                          +     " <c:if test='${"+num+" ne "+pick1+"}'> "
                                          +      " <span style='background-color:#FFB2F5; line-height: 27px; border-radius: 7px;'><font color='#ffffff' size='2'> "
                                          +      pick1 +"</font></span>"   
                                          +     " </c:if>"
                                          +     " <c:if test='${"+num+" ne "+pick2+"}'> "
                                          +     " <span style='background-color:#F361DC; line-height: 27px; border-radius: 7px;'><font color='#ffffff' size='2'> "
                                          +      pick2 + "</font></span>"
                                          +     " </c:if>"
                                          +     " <c:if test='${"+num+" ne "+pick3+" }'> "
                                          +     " <span style='background-color:#F361A6; line-height: 27px; border-radius: 7px;'><font color='#ffffff' size='2'> "  
                                          +       pick3 + "</font></span>"
                                          +     " </c:if>"
                                          +     " <c:if test='${"+num+" ne "+pick4+" }'> "
                                          +     " <span style='background-color:#FFB2F5; line-height: 27px; border-radius: 7px;'><font color='#ffffff' size='2'> "  
                                          +       pick4 + "</font></span>"
                                          +     " </c:if> <br>"
                                          +     " <font class='font1'>"+value.GOODS_NAME+"</font><br>"
                                          +     " <font class='font2'>"+numberWithCommas(value.GOODS_MPRICE)+"원</font> "   
                                          +       " <input type='hidden' id='IDX' name='IDX' value=" + value.GOODS_NUM + ">"
                                          +     " </a>"
                                          +     " </div>";

                     
                  });
      body.append(str);
      $("a[name='title']").on("click", function(e){ //제목 
         e.preventDefault();
         fn_openBoardDetail($(this));
      });
   }
}

</script>
	

