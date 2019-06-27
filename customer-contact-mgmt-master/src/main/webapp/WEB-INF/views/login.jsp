<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% // Author: M.C.S. %>
<!DOCTYPE html>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Customer Contact Management</title>	
   <link href="<c:url value='static/css/bootstrap.css' />" rel="stylesheet"></link>	
   <link href="<c:url value='static/css/login.css' />" rel="stylesheet"></link>			
   <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css"></link>
   <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'></link>	    
   <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
   
      
</head>

<body>
  <section class="split">   
      <div class="col-lg-6 col-sm-12 left">
      <div class="title-app">
		  <h1>CustomerContact<span>Management</span></h1>          
      </div>            
	     <img class="centered" src="<c:url value='static/images/photo01.png'/>"/> 
	     <p id="custom">Spring 4 + Spring Security 4 + Hibernate 4 +  PostgreSQL + JasperReports +  Maven Integration Example </p>	    	          
      </div>
          
      <div class="col-lg-6 col-sm-12 right">
   
      <div class="login-form">
	
	  <div class="login">
	   <c:url var="loginUrl" value="/login" />    
       <form action="${loginUrl}"  method="post">
		<c:if test="${param.error != null}">
			<div class="alert alert-danger">
			<p>Invalid email and password.</p>
			</div>
			</c:if>
		    <c:if test="${param.logout != null}">
			<div class="alert alert-success">
			<p>You have been logged out successfully.</p>
			</div>
		    </c:if> 
		<div class="avatar"><i class="material-icons">&#xE7FF;</i></div>
    	<h4 class="modal-title">Login to Your Account</h4>
        <div class="form-group">
            <input type="text" class="form-control"  name="email" id="username" placeholder="Enter Email" >
        </div>
        <div class="form-group">
            <input type="password" class="form-control" name="password" id="password" placeholder="Enter Password" >
        </div>
        
        <input type="submit" class="btn btn-primary btn-block btn-lg" value="Log in">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />              
    </form>			
   
    </div>		 
    </div>  
    </div>
    
  </section>  
</body>
</html>
