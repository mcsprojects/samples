<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>User Management Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/styles.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/font-awesome.min.css' />" rel="stylesheet"></link>	
	<link href="<c:url value='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'/>" rel="stylesheet"></link>
    
    
</head>

<body>
	<div class="generic-container">
		<%@include file="authbar.jsp" %>	
		<div class="panel panel-default">
			  
		  	<div class="panel-heading"><span class="title">List of Users </span>		  	          
		  	
		  	<span class="floatCustom"><i class="fa fa-search fa_custom fa-2x"></i>      
            <input type="text" id="filter" onkeyup="search()"  placeholder="Search for users..."/></span>            
            
		  	</div>
			<table class="table table-hover table-list" id="table">
	    		<thead>
		      		<tr>
		      		    <th class="custom-th"></th>		      		    
				        <th class="custom-th">Firstname</th>
				        <th class="custom-th">Lastname</th>
				        <th class="custom-th">Email</th>				        
				        
				        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
					    <td class="custom-td-2">
					    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
						<a href="<c:url value='/edit-user-${user.email}' />" class="btn btn-primary"><em class="fa fa-pencil"></em></a>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
						<a href="<c:url value='/delete-user-${user.email}' />" class="btn btn-danger"><em class="fa fa-trash"></em></a>
        				</sec:authorize>
        				</td>
					
						<td class="custom-td">${user.firstName}</td>
						<td class="custom-td">${user.lastName}</td>
						<td class="custom-td"><a href="mailto:${user.email}">${user.email}</a></td>						
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />">Add New User</a>
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

