<%@page import="kr.ac.sungkyul.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	List<BoardVo> list = (List<BoardVo>)request.getAttribute("list");
	%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite/board" method="post">
					<input type="text" id="kwd" name="kwd" value=""> <input
						type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>					
					<tr>
					<%
					  for(BoardVo vo : list){
					  %>
						<td><%=vo.getNo() %></td>
						<td><a href=""><%=vo.getTitle() %></a></td>
						<td><%=vo.getUserName() %></td>
						<td><%=vo.getViewCount() %></td>
						<td><%=vo.getRegDate() %></td>
						<td><a href="/mysite/board?a=delete&no=<%=vo.getNo()%>" class="del">삭제</a></td>
					</tr>
					
					<%} %>
				</table>
				<!-- begin:paging -->
            <div class="pager">
               <ul>
                  <li><a href="">◀</a></li>
                  <li><a href="">1</a></li>
                  <li class="selected">2</li>
                  <li><a href="">3</a></li>
                  <li><a href="">4</a></li>
                  <li><a href="">5</a></li>
                  <li><a href="">▶</a></li>
               </ul>
            </div>
            <!-- end:paging -->
				<div class="bottom">
					<a href="" id="new-book">글쓰기</a>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp" />
		<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>