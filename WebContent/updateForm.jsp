<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.javaex.vo.PersonVo"%>

<%
//파라미터 꺼내기
PersonVo personVo = (PersonVo) request.getAttribute("personVo");
System.out.println(personVo);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>수정폼</h2>

	<p>수정화면입니다. 아래의 항목을 수정하고 "수정" 버튼을 클릭하세요</p>
	<form action="/phonebook2/pbc?action=update" method="get">
		<label>이름(name)</label> <input type="text" name="name"
			value="<%=personVo.getName()%>"> <br> <label>핸드폰(hp)</label>
		<input type="text" name="hp" value="<%=personVo.getHp()%>"> <br>
		<label>회사(company)</label> <input type="text" name="company"
			value="<%=personVo.getCompany()%>"> <br> <label>pk(id)</label>
		<input type="text" name="id" value="<%=personVo.getPersonId()%>">
		<br> <input type="text" name="action" value="update"><br>
		<button type="submit">수정</button>
	</form>
</body>
</html>