<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<% // Author: M.C.S. %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Customer Contact Management Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>	
	<link href="<c:url value='/static/css/styles.css' />" rel="stylesheet"></link>	
	<link href="<c:url value='/static/css/font-awesome.min.css' />" rel="stylesheet"></link>	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'></link>
	
</head>

<body>
	<div class="generic-container-2">
		<%@include file="authbar2.jsp" %>	
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="title-2">List of Customers </span>
		  	
		  	<span class="floatCustom"><i class="fa fa-search fa_custom fa-2x"></i>      
            <input type="text" id="filter" onkeyup="search()"  placeholder="Search for customers..."/></span>           
		  	
		  	</div>
			<table class="table table-hover table-list" id="table">
	    		<thead>
		      		<tr>
		      		    <th class="custom-th-2"><em class="fa fa-cog"></em></th>						
				        <th class="custom-th">Name</th>
				        <th class="custom-th">Address</th>
				        <th class="custom-th">ZIP</th>
				        <th class="custom-th">Contact Person</th>
				        <th class="custom-th">Position</th>
				        <th class="custom-th">Phone</th>
				        <th class="custom-th">Email</th>
				        <th class="custom-th">Last Contact<span class="blue">*</span></th>
				        <th class="custom-th">Next Contact<span class="blue">*</span></th>
				        <th class="custom-th">Lead Status</th>
				        <th class="custom-th">Notes</th>
				        <sec:authorize access="hasRole('USER')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('USER')">
				        	<th width="100"></th>
				        </sec:authorize>				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${customers}" var="user">
				<tr>
					
				  <td class="custom-td-2">
				  <sec:authorize access="hasRole('USER')">		   
                  <a href="<c:url value='/edit-customer-${user.email}' />"class="btn btn-primary"><em class="fa fa-pencil"></em></a>
                  </sec:authorize>
                  <sec:authorize access="hasRole('USER')">				   
                  <a href="<c:url value='/delete-customer-${user.email}' />" class="btn btn-danger"><em class="fa fa-trash"></em></a>
                  </sec:authorize>
                  </td>
                  
						<td class="custom-td">${user.name}</td>
						<td class="custom-td">${user.address}</td>
						<td class="custom-td">${user.zip}</td>
						<td class="custom-td">${user.contactPerson}</td>
						<td class="custom-td">${user.position}</td>
						<td class="custom-td"><a href="skype:${user.phone}?call">${user.phone}</a></td>
						<td class="custom-td"><a href="mailto:${user.email}">${user.email}</a></td>
						<td class="custom-td">${user.lastContact}</td>
						<td class="custom-td">${user.nextContact}</td>
						<td class="custom-td">${user.leadStatus}</td>
						<td class="custom-td">${user.notes}</td>										    
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('USER')">
		 	<div class="well">
		 	 <div class="clearfix">
		 		<span class="floatLeft1"><a href="<c:url value='/addcustomer'/>" class="btn btn-default btn-sm" >Add New Customer</a></span>		 	
		 		<span class="floatLeft2"><a href="<c:url value='/customersReport'/>" class="btn btn-default btn-sm">Customers Report</a></span>		 		                              
                <span class="floatRight1"><span class="blue-2">*</span>ISO 8601 date format (yyyy/mm/dd)</span>
                        
                              
		 	 </div>	
		 	</div>
	 	</sec:authorize>
   	</div>
   	
<script>
function search() {
  // Declare variables 
  var input, filter, table, tr, td, i;
  input = document.getElementById("filter");
  filter = input.value.toUpperCase();
  table = document.getElementById("table");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    } 
  }
}
</script>
</body>
</html>
