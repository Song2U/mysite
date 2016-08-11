<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
		<c:import url='/WEB-INF/views/include/header.jsp' />
		<div id="content">
			<div id="board">
				<form id="search_form" action="" method="post">
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
					<c:forEach var='vo' items='${boardlist }'>
						<tr>
							<td>[${vo.no }]</td>
							<td style="text-align:left;padding-left:${(vo.depth-1)*10}px"><a
								href="/mysite/board?a=view&no=${vo.no}">${vo.title}</a></td>
							<c:if test='${vo.depth > 1 }'>
								<img src="">
							</c:if>
							<td>${vo.userName}</td>
							<td>${vo.viewCount }</td>
							<td>${vo.regDate }</td>
							<td>
							<c:if test="${vo.userNo==authUser.no }">
								<a href="/mysite/board?a=delete&no=${vo.no }" class="del">삭제</a>
							</c:if>
							</td>
						</tr>
					</c:forEach>

				</table>
				<!-- begin:paging -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:forEach begin='${beginPage }' end='${endPage }' step='1'
							var='i'>
							<c:choose>
								<c:when test='${currentPage==i }'>
									<li class="selected"><a href="">1</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/mysite/board?a=list&p=${i}">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test='${endPage<total }'>
							<li><a href="/mysite/board?a=list&p=${endPage + 1}">▶</a></li>
						</c:if>
					</ul>
				</div>
				<!-- end:paging -->
				<div class="bottom">
					<c:if test='${authUser!=null }'>
						<a href="/mysite/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url='/WEB-INF/views/include/navi.jsp' />
		<c:import url='/WEB-INF/views/include/footer.jsp' />
	</div>
</body>
</html>
