angular.module('SGPApp')
// Creación del controlador Angular UsersController
.controller('UsersController', function($http, $scope, $timeout, AuthService) {
	var edit = false;
	$scope.buttonText = 'Crear';
	var init = function() {
		$http.get('api/users').success(function(res) {
			$scope.users = res;
			$scope.userForm.$setPristine();
			$scope.message='';			
			$scope.userMgmt = null;
			$scope.buttonText = 'Crear';
			
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.initEdit = function(userMgmt) {
		edit = true;
		$scope.userMgmt = userMgmt;
		$scope.message='';		
		$scope.buttonText = 'Actualizar';
	};
	$scope.initAddUser = function() {
		edit = false;
		$scope.userMgmt = null;
		$scope.userForm.$setPristine();
		$scope.message='';		
		$scope.buttonText = 'Crear';
	};	
	$scope.deleteUser = function(userMgmt) {
		$http.delete('api/users/'+userMgmt.id).success(function(res) {
			$scope.deleteMessage ="Operación realizada con éxito";
			$scope.delMsg = true;			
			$timeout(function () { $scope.delMsg = false;}, 4000);
			init();			
		}).error(function(res) {
			$scope.message = "Error: no es posible borrar usuario";
			
		});
		
	};	
	var editUser = function(){
		$http.put('api/users', $scope.userMgmt).success(function(res) {
			$scope.userMgmt = null;
			$scope.confirmPassword = null;
			$scope.userForm.$setPristine();
			$scope.editMessage = "Operación realizada con éxito";			
			init();			
			$scope.editMsg = true;			
			$timeout(function () { $scope.editMsg = false;}, 4000);
		}).error(function(res) {
			$scope.message = "Error al actualizar datos del usuario";
			return res;
		});
	};
	var addUser = function(){
		$http.post('api/users', $scope.userMgmt).success(function(res) {
			$scope.userMgmt = null;
			$scope.confirmPassword = null;
			$scope.userForm.$setPristine();
			$scope.addMessage = "Operación realizada con éxito";	
			init();
			$scope.addMsg = true;			
			$timeout(function () { $scope.addMsg = false;}, 4000);
		}).error(function(res) {
			$scope.message = "El usuario ya existe";			
            return res;            
		});
	};		
	$scope.submit = function() {
		if(edit){
			editUser();			
		}else{
			addUser();	
		}
	};
	init();
	
});
