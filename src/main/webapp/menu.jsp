<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:if test="${empty sessionScope.loginUser}">
	<meta http-equiv="Refresh" content="0;URL=index.jsp">
</c:if>
<title>メニュー</title>
<link href="css/commons.css" rel="stylesheet">
<link
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
</head>
<body>
	<div id="app">

		<div class="header">
			<h1 class="site_logo">
				<a href="menu.jsp">商品管理システム</a>
			</h1>
			<div class="user">
				<p class="user_name">${sessionScope.loginUser.name}さん、こんにちは</p>
				<form class="logout_form" action="logout.jsp" method="get">
					<button class="logout_btn" type="submit">
						<img src="images/ドアアイコン.png">ログアウト
					</button>
				</form>
			</div>
		</div>

		<hr>
		<c:if test="${sessionScope.loginUser.role == 1}">
			<div class="btn">
				<a class="basic_btn regist" href="insert.jsp">新規登録</a>
			</div>
		</c:if>
		<p>
			<c:if test="${not empty requestScope.msg}">
				<c:out value="${requestScope.msg}" />
			</c:if>
		</p>
		<form method="get" action="SearchServlet" class="search_container">
			<input type="text" name="keyWord" size="25" placeholder="キーワード検索">
			<input type="submit" value="&#xf002">
		</form>

		<table>
			<div class="caption">
				<p>検索結果：${sessionScope.result.size()}件</p>
			</div>
			<div class="order">
				<select class="base-text">
					<option>並び替え</option>
					<option>商品ID</option>
					<option>カテゴリ</option>
					<option>単価：安い順</option>
					<option>単価：高い順</option>
					<option>登録日：古い順</option>
					<option>登録日：新しい順</option>
				</select>
			</div>
			<theand>
			<tr>
				<th>商品ID</th>
				<th>商品名</th>
				<th>単価</th>
				<th>カテゴリ</th>
				<th>詳細</th>
			</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${sessionScope.result}">
					<tr>
						<td>${product.productId}</td>
						<td>${product.name}</td>
						<td>${product.price}</td>
						<td>${product.ctgry.name}</td>
						<td>
							<a class="detail_btn" href="DetailServlet?productId=${product.productId}">詳細</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<footer></footer>
</body>
</html>
