<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../head.jsp"/>
  <title>ERROR - Create Scooter</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

</head>
<body>

<jsp:include page="../preloader.jsp"/>

<div id="main-wrapper">

  <jsp:include page="../header-menu.jsp"/>

    <div class="content-body">
      <!-- row -->
      <div class="page-titles">
        <ol class="breadcrumb">
          <li><h5 class="bc-title">ERROR - Create Scooter</h5></li>
          <li class="breadcrumb-item"><a href="<c:url value="/dashboard"/>">
            <svg width="17" height="17" viewBox="0 0 17 17" fill="none" xmlns="http://www.w3.org/2000/svg">
              <path d="M2.125 6.375L8.5 1.41667L14.875 6.375V14.1667C14.875 14.5424 14.7257 14.9027 14.4601 15.1684C14.1944 15.4341 13.8341 15.5833 13.4583 15.5833H3.54167C3.16594 15.5833 2.80561 15.4341 2.53993 15.1684C2.27426 14.9027 2.125 14.5424 2.125 14.1667V6.375Z" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
              <path d="M6.375 15.5833V8.5H10.625V15.5833" stroke="#2C2C2C" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>
            Home </a>
          </li>
          <li class="breadcrumb-item active"><a href="javascript:void(0)">Error Page</a></li>
        </ol>
      </div>

  <hr/>

  <div class="card-body">
  <!-- display the message -->
    <c:import url="/jsp/include/show-message.jsp"/>
  </div>

  <!-- display the just created model, if any and no errors -->
  <c:if test='${not empty newScooter && !message.error}'>
    <c:redirect url="/scooter/list/"/>
    <ul>
      <li>id: <c:out value="${newScooter.id}"/></li>
      <li>date of purchase: <c:out value="${newScooter.dateOfPurchase}"/></li>
      <li>km traveled: <c:out value="${newScooter.kmTraveled}"/></li>
      <li>model: <c:out value="${newScooter.model}"/></li>
      <li>remaining battery life: <c:out value="${newScooter.remainingBatteryLife}"/></li>
      <li>id scooterrack: <c:out value="${newScooter.idScooterrack}"/></li>
    </ul>
  </c:if>

  <a href="${pageContext.request.contextPath}/scooter">GO BACK TO SCOOTERS PAGE</a>
</div>
</div>
<jsp:include page="../foot.jsp"/>
</body>
</html>
