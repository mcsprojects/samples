angular.module('SGPApp')
// Creando el controlador Angular LoginController
.controller('LoginController', function($http, $scope, $state, AuthService, $rootScope) {	
	// método para login
	$scope.login = function() {
		// Solicitando el token para usename and passoword
		$http({
			url : 'authenticate',
			method : "POST",
			params : {
				username : $scope.username,
				password : $scope.password			
			}
		}).success(function(res) {			
			$scope.password = null;
			// verificando si el token está disponible
			if (res.token) {
				$scope.message = '';
				// añadir a los servicios que requieren autorización el Bearer token con JWT token
				$http.defaults.headers.common['Authorization'] = 'Bearer ' + res.token;

				// Usuario en AuthService
				AuthService.user = res.user;				
				$rootScope.$broadcast('LoginSuccessful');				
				$state.go('home');
			} else {
				// si el token no está presente en la respuesta la autenticación
				// no ha sido exitosa. Aparece entonces el mensaje de error.
				$scope.message = '¡Autenticación Fallida!';
			}
		}).error(function(error) {
			// Si la autenticación ha sido fallida aparece el mensaje de error.
			$scope.message = '¡Autenticación Fallida!';
		});
	};
});
