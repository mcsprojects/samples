<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">            
        <title>ADMIN page</title>

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
		              <h1><strong>CustomerContact<span>Management</span></strong></h1>              
                                                
       <div class="description"> <h2><strong>Welcome to the User Management Page</strong></h2></div>
       </div>
       </div>
		          	
		<div id="mainWrapper">
			<div class="page-container">
				<div class="page-card">
					<h3>Managing Users and Permissions</h3>
                    <p>The ‘User Management‘ page allows you to add new users. To list the existing users,
                     change their current user role, edit or delete them from the application.</p>
                     <p>While logged in as admin, you can:</p>
                     <div class="list">
                     <ul>
						 <li>Create new users</li>
						 <li>Delete them with a single click</li>
						 <li>List all the users</li>                         
                         <li>Edit user settings</li>                         
                         <li>Change user roles (the options are: 'USER', 'ADMIN', 'DBA')</li>                         
                     </ul> 
                     </div>   
					<div class="login-form">
																				
							<div class="form-actions">		
								
								<a href="<c:url value='/userslist' />" class="btn btn-block btn-primary btn-default">GET START</a>
							</div>
						
					</div>
				</div>
			</div>
		</div>       
	</body>
</html>
 
