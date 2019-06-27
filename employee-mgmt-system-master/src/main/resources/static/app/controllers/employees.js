angular.module('SGPApp')

// Creating the Angular Controller
.controller('EmployeesController', function($http, $scope, $timeout, AuthService){
					
	var edit = false;
	$scope.buttonText = 'Crear';
	var init = function() {
		$http.get('api/employees').success(function(res) {
			$scope.users = res;
			$scope.employeeForm.$setPristine();
			$scope.message='';
			$scope.employeeMgmt = null;
			$scope.buttonText = 'Crear';			
			
		}).error(function(error) {
			$scope.message = error.message;
		});
	};
	$scope.initEdit = function(employeeMgmt) {
		edit = true;
		$scope.employeeMgmt = employeeMgmt;
		$scope.message='';		
		$scope.buttonText = 'Actualizar';
	};
	$scope.initAddEmployee = function() {
		edit = false;
		$scope.employeeMgmt = null;
		$scope.employeeForm.$setPristine();
		$scope.message='';
		$scope.buttonText = 'Crear';
	};		
	$scope.deleteEmployee = function(employeeMgmt) {
		$http.delete('api/employees/'+employeeMgmt.id).success(function(res) {			
			$scope.deleteMessage ="Operación realizada con éxito";
			$scope.delMsg = true;			
			$timeout(function () { $scope.delMsg = false;}, 4000);
			init();
		}).error(function(res) {
			$scope.message = "Error: no es posible borrar empleado";
			
		});
	};	
			
	var editEmployee = function(){
		$http.put('api/employees', $scope.employeeMgmt).success(function(res) {
			$scope.employeeMgmt = null;
			$scope.confirmPassword = null;
			$scope.employeeForm.$setPristine();
			$scope.editMessage = "Operación realizada con éxito";
			$scope.editMsg = true;			
			$timeout(function () { $scope.editMsg = false;}, 4000);
			init();			
		}).error(function(res) {
			$scope.message = "Error al actualizar datos del empleado";
			return res;
		});
	};
	var addEmployee = function(){
		$http.post('api/employees', $scope.employeeMgmt).success(function(res) {
			$scope.employeeMgmt = null;
			$scope.confirmPassword = null;
			$scope.employeeForm.$setPristine();
			$scope.addMessage = "Operación realizada con éxito";		
			init();
			$scope.addMsg = true;			
			$timeout(function () { $scope.addMsg = false;}, 4000);
		}).error(function(res) {			
			$scope.message = "El empleado ya existe";			
            return res; 
			
		});
	};
	
	
	$scope.submit = function() {
		if(edit){
			editEmployee();
		}else{
			addEmployee();	
		}
	};
	init();

});
