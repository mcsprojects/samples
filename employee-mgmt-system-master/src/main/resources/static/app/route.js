angular.module('SGPApp').config(function($stateProvider, $urlRouterProvider) {	
	// ui router redirige a page-not-found 	
	$urlRouterProvider.otherwise('/page-not-found');
	// vista parent 
	$stateProvider.state('nav', {
		abstract : true,
		url : '',
		views : {
			'nav@' : {
				templateUrl : 'app/views/nav.html',
				controller : 'NavController'
			}
		}
	}).state('login', {
		parent : 'nav',
		url : '/login',
		views : {
			'content@' : {
				templateUrl : 'app/views/login.html',
				controller : 'LoginController'
			}
		}
	}).state('users', {		
		parent : 'nav',
		url : '/users',
		data : {
			role: 'ADMIN'			 
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/users.html',
				controller : 'UsersController',
			}
		}
	}).state('employees', {
		parent : 'nav',
		url : '/employees',
		data : {
			role:  'USER'			 
		},
		views : {
			'content@' : {
				templateUrl : 'app/views/employees.html',
				controller : 'EmployeesController' 
				
			}
		}
	}).state('home', {
		parent : 'nav',
		url : '/home',
		views : {
			'content@' : {
				templateUrl : 'app/views/home.html',
				controller : 'HomeController'
			}
		}
	}).state('page-not-found', {
		parent : 'nav',
		url : '/page-not-found',
		views : {
			'content@' : {
				templateUrl : 'app/views/page-not-found.html',
				controller : 'PageNotFoundController'
			}
		}	
	}).state('access-denied', {
		parent : 'nav',
		url : '/access-denied',
		views : {
			'content@' : {
				templateUrl : 'app/views/access-denied.html',
				controller : 'AccessDeniedController'
			}
		}
	
	}).state('register', {
		parent : 'nav',
		url : '/register',
		views : {
			'content@' : {
				templateUrl : 'app/views/register.html',
				controller : 'RegisterController'
			}
		}
	});	
});


