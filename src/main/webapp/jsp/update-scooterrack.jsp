<!--
Copyright 2018-2023 University of Padua, Italy

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Author: Nicola Ferro (ferro@dei.unipd.it)
Version: 1.0
Since: 1.0
-->

<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Update Scooterrack</title>
</head>

<body>
<h1>Update Scooterrack</h1>
<hr/>

<!-- display the message -->
<c:import url="/jsp/include/show-message.jsp"/>

<!-- display the just created scooterrack, if any and no errors -->
<c:if test='${not empty updateScooterrack && !message.error}'>
  <ul>
    <li>name: <c:out value="${updateScooterrack.id}"/></li>
    <li>total parking spots: <c:out value="${updateScooterrack.totalParkingSpots}"/></li>
    <li>available parking spots: <c:out value="${updateScooterrack.availableParkingSpots}"/></li>
    <li>postal code: <c:out value="${updateScooterrack.postalCode}"/></li>
    <li>address: <c:out value="${updateScooterrack.address}"/></li>
  </ul>
</c:if>

<a href="${pageContext.request.contextPath}/jsp/Menu.jsp">go back to the homepage</a>
</body>
</html>
