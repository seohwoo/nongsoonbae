<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<select id="cate3" name="cate3">
		<option value="-------">-------</option>
		<c:forEach var="cate3" items="${cate3}">
			<option value="${cate3.cate3}">${cate3.catename}</option>
		</c:forEach>
	</select>
	<div id="c4"></div>