<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>조아요행</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${root}/assets/img/logo.PNG" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" type="text/css" />
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${root}/assets/css/style.css" rel="stylesheet" />
		<style>
		    :root {
		        --bg-image-url: url('${root}/assets/img/bg-masthead.jpg');
		    }
		</style>	
	</head>
	<body>
        <nav class="navbar navbar-light bg-light static-top" style="height: 100px">
            <div class="container">
                <div style="display: flex; align-items: center;">
                    <img src="${root}/assets/img/logo.PNG"  href="${root}/index.jsp" alt="Avatar Logo" style="width:60px;" class="rounded-pill"> 
                    <a class="navbar-brand" href="${root}/index.jsp">조아요행</a>
                </div>
                <div id="always-menu">
                  <a class="btn me-2" href="${root}/trip">지역별여행지</a>
                  <a class="btn me-2" href="${root}/plan">나의여행계획</a>
                  <a class="btn me-2" href="${root}/board?action=list">여행정보공유</a>
                </div>
                <div id="visitor-menu">
                  <a class="btn btn-warning me-2" href="${root}/member?action=mvlogin">로그인</a>
                  <a class="btn btn-warning" href="${root}/member?action=mvjoin">회원가입</a>
                </div>
                <div id="member-menu" style="display: none">
                    <a class="btn btn-warning me-2" href="${root}/member?action=logout">로그아웃</a>
                    <a class="btn btn-warning" href="${root}/member?action=mypage">마이페이지</a>
                </div>  
            </div>
        </nav>