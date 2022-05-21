<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<script src="<c:url value='/js/common.js'/>" charset="utf-8"></script>
</head>
<body>
	<form id="frm1" name="frm1" method="post">
		<div style="display: inline-block;">
			<div style="width:300px; margin-left:20px; float:left; padding:20px 0px 0px 0px;">
<a href="/muscle/main/openMainList.do">
  <span
style="
font-style: italic ; 
font-weight: bold;
font-size: 2.1em;
line-height: 1.0em; 
color: hotpink;
font-family: arial;
">
삼 대 오 백
</span>
   
</a>   
</div>
		</div>
		<div class="container">
			<div align="center" style="margin-bottom: 10px;">
				<h4><b>신고하기</b></h4>
			</div>
			<div style="margin-bottom: 10px;">

				<span><strong>회원 닉네임</strong></span>&nbsp; <span><font color='hotpink'>${map.BOARD_NICK}</font></span>
				<input type="hidden" name="BOARD_NUM" id="BOARD_NUM"
					value="${map.BOARD_NUM}" /> <input type="hidden" name="MEM_NUM"
					id="MEM_NUM" value="${session_MEMBER.MEM_NUM}" /> <input
					type="hidden" name="BOARD_WRITER" name="BOARD_WRITER"
					value="${map.BOARD_WRITER}" />
			</div>
			<div>
				<span><strong>신고 사유</strong></span>
				<textarea class="form-control" id="REPORT_COMMENT"
					name="REPORT_COMMENT" rows="5" placeholder="신고 사유를 입력해주세요."
					style="margin-top: 5px; width: 100%;"></textarea>
			</div>
			<div align="center" style="margin-top: 20px;">
				<a href="#this" id="report"><button class="btn btn-warning">신고</button></a>
				<a href="#this" id="clo"><button class="btn btn-outline-warning" >취소</button></a>
			</div>
		</div>
	</form>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#clo").on("click", function(e) {
				e.preventDefault();
				selfClose();
			});

			$("#report").on("click", function(e) {
				e.preventDefault();
				fn_report();
			});

		});

		function fn_report() {
			var comSubmit = new ComSubmit("frm1");
			comSubmit.setUrl("<c:url value='/report/insertReport.do'/>");
			if (!$("#REPORT_COMMENT").val()) {
				alert("내용을 입력하세요.");
				$("#REPORT_COMMENT").focus();
				return false;
			}
			alert("신고가 접수 되었습니다.");
			comSubmit.submit();
		}

		function selfClose() {
			self.close();
		}
	</script>

	<%@ include file="/WEB-INF/include/include-body.jspf"%>
</body>
</html>