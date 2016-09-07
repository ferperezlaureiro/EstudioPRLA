app.controller("indexController", ['$scope', '$location', '$rootScope', function ($scope, $location, $rootScope) {  
	
	$scope.navClass = function (page) {
		var currentRoute = $location.path().substring(1) || 'caso';
		return page === currentRoute ? 'active' : '';
	};

	$scope.toCaso = function(){
		$location.url("/caso");
	}
	$scope.toUsuario = function(){
		$location.url("/usuario");
	}
	$scope.toCuenta = function(){
		$location.url("/cuenta");
	}
	$scope.toConfiguracion = function(){
		$location.url("/configuracion");
	}

	$scope.cerrarSession = function(){
		$rootScope.token = "";
		$rootScope.vistasPermitidas.usuario = '';
		$rootScope.vistasPermitidas.configuracion = '';
		$rootScope.currentUsr = '';
		$location.url("/login");
	}
}]);