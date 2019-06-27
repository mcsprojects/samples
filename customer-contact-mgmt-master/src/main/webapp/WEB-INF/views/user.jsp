<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% // Author: M.C.S. %>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">            
        <title>Customer Contact Management</title>

        <!-- CSS -->
        <link rel="stylesheet" href="//fonts.googleapis.com/css?family=Roboto:400,100,300,500"/>                
        <link rel="stylesheet" href="<c:url value='static/css/bootstrap.css'/>"/>		
        <link rel="stylesheet" href="<c:url value='static/css/stylepages.css'/>"/>
        <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
</head>
 <body> 
       <%@include file="authheader.jsp" %>     
	  
	   <div class="center">
	   <div class="col-sm-8 col-sm-offset-2 text">
		              <h1 class="userpage"><strong>CustomerContact<span>Management</span></strong></h1>      
       </div>
       </div>
		          	
		<div id="mainWrapper">
			<div class="page-container">
				<div class="page-card">
					<h3>Manage the list of customers.</h3>
                    <p>The ‘Customer Contact Management‘ page allows you to add new customers. To list the existing customers,
                     edit or delete them from the application.</p>
                     <p>On the ‘Customer Contact Management‘ you can:</p>
                     <div class="list">
                     <ul>
						 <li>Create new customers</li>
						 <li>Delete them with a single click</li>
						 <li>List all the customers</li>                         
                         <li>Edit customers settings</li>                        
                         <li>Send e-mails your customers</li>
                         <li>Stay in touch with them via Skype</li>                         
                     </ul> 
                     </div>   
					<div class="login-form">
																				
							<div class="form-actions">
							   <a href="<c:url value='/customerslist' />" class="btn btn-block btn-primary btn-default">GET START</a>
							</div>
						
					</div>
				</div>
			</div>
		</div>       
	</body>
</html>
 
