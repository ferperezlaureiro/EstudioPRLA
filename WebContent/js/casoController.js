app.controller("casoController", ['$scope', '$location', '$window', '$rootScope', '$http', function casoController($scope, $location, $window, $rootScope, $http){


	$scope.$on('$viewContentLoaded', function(){
		if($rootScope.token == undefined || $rootScope.token == ""){
			$location.url("/login");
		}

		if ($rootScope.accionesPermitidas.obtenerTodosCasos == true) {
			$rootScope.todosCasos = "";
			$scope.cargarTodosLosCasos();
		}

		$rootScope.misCasos = "";
		$scope.cargarMisCasos();
	});

	$scope.cargarTodosLosCasos = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerCasos?usrKey=' + $rootScope.token
		}).success(function(data, status, headers, config) {
			if(data.length != 0 || data != "" || data != "No hay casos"){
				$rootScope.todosCasos = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	};

	$scope.cargarMisCasos = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerCasosPorUsuario?usrKey=' + $rootScope.token +'&usuario=' + $rootScope.currentUsr.usuario
		}).success(function(data, status, headers, config) {
			if(data.length != 0 || data != "" || data != "No hay casos"){
				$rootScope.misCasos = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	};

	$scope.eliminarCaso = function(IUE){
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/eliminarCaso?usrKey=' + $rootScope.token +'&iUE=' + IUE
		}).success(function(data, status, headers, config) {
			if ($rootScope.accionesPermitidas.obtenerTodosCasos == true) {
				$scope.cargarTodosLosCasos();
			}

			$scope.cargarMisCasos();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.mostrarModificarCaso = function(IUE){
		alert("mostrarModificar   " + IUE);
	}

	$scope.detalleCaso = function(IUE){
		alert("detalle   " + IUE);
	}
}]);