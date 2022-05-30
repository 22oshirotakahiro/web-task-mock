<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:if test="${empty sessionScope.loginUser or sessionScope.loginUser.role != 1}">
	<meta http-equiv="Refresh" content="0;URL=index.jsp">
</c:if>
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="menu.jsp">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">${sessionScope.loginUser.name}さん、こんにちは</p>
      <form class="logout_form" action="logout.jsp" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
    <div class="form_body">
      <p class="error">${requestScope.msg}</p>

      <form action="UpdateServlet" method="get">
        <fieldset class="label-130">
          <div>
            <input type="hidden" name="currentProductId" value="${requestScope.product.productId}">
          </div>
          <div>
            <label>商品ID</label>
            <input type="text" name="productId" value="${requestScope.product.productId}" class="base-text">
            <span class="error">${requestScope.CheckProductId}</span>
          </div>
          <div>
            <label>商品名</label>
            <input type="text" name="productName" value="${requestScope.product.name}" class="base-text">
            <span class="error">${requestScope.CheckProductName}</span>
          </div>
          <div>
            <label>単価</label>
            <input type="text" name="price" value="${requestScope.product.price}" class="base-text">
            <span class="error">${requestScope.CheckPrice}</span>
          </div>
          <div>
            <label>カテゴリ</label> <select name="categoryId" class="base-text">
              <option value="1">筆記具</option>
              <option value="2">紙製品</option>
              <option value="3">事務消耗品</option>
              <option value="4">オフィス機器</option>
              <option value="5">雑貨</option>
            </select>
          </div>
          <div>
            <label>商品説明</label>
            <textarea name="description" class="base-text">${requestScope.product.description}</textarea>
          </div>
          <div>
            <label>画像</label>
            <input type="file" name="imagePath">
            <span class="error">${requestScope.CheckImagePath}</span>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='./menu.jsp'" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">更新</button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
      </form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>