angular.module('SGPApp')
// Creación del controlador Angular RegisterController
.controller('RegisterController', function($http, $scope, AuthService) {
	$scope.submit = function() {
		$http.post('register', $scope.userMgmt).success(function(res) {
			$scope.userMgmt = null;
			$scope.confirmPassword = null;
			$scope.register.$setPristine();
			$scope.message = "Usuario registrado con éxito.";
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
});
