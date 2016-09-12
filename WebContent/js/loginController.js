app.controller("loginController", ['$scope', '$location', '$http', '$rootScope', function ($scope, $location, $http, $rootScope) {  
	
	$scope.login = function(){
		if($scope.usuario != "" && $scope.usuario != undefined && $scope.contrasenia != "" && $scope.contrasenia != undefined){
			$http({
				method: 'GET', 
				url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/login?usuario=' + $scope.usuario 
																				+ '&contrasenia=' + $scope.contrasenia
			}).success(function(data, status, headers, config) {
				if (data != "usuario" && data != "|contrasenia" && data != "usuario|contrasenia") {
					$scope.tokenAux = data;
					$scope.cargarUsuario();
					$scope.cargarPermisos();
				} else {
					//todo error
				}
			}).error(function(data, status, headers, config) {
				alert("Ha fallado la petición. Estado HTTP:"+status);
			});
		} 
	}

	$scope.cargarUsuario = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerUsuario?usrKey=' + $scope.tokenAux 
																					+ '&usuario=' + $scope.usuario
		}).success(function(data, status, headers, config) {
			$rootScope.currentUsr = data;
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarPermisos = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerPermisos?usrKey=' + $scope.tokenAux 
																					+ '&usuario=' + $scope.usuario
		}).success(function(data, status, headers, config) {
			$rootScope.permisos = data;
			$rootScope.vistasPermitidas = {usuario: '', configuracion: ''};
			$rootScope.accionesPermitidas = {usuarioAgregar: '', usuarioModificar : '', usuarioBorrar : '', casoAgregar : '', casoEliminar : '', casoModificar : '', 
				involucradoAgregar : '', involucradoModificar : '', involucradoBorrar : '', asignarRebocarPermiso : '', asociarDesasociarUsuario : '', 
				obtenerTodosCasos : '', obtenerCasosPorUsuario : '', obtenerUsuariosPorCaso : ''};
			for(var i in data){
				switch (data[i].code) {
				    case "OU":
				        $rootScope.vistasPermitidas.usuario = true;
				        break;
				    case "CON":
				        $rootScope.vistasPermitidas.configuracion = true;
				        break;
				    case "AU":
				        $rootScope.accionesPermitidas.usuarioAgregar = true;
				        break;
				    case "EU":
				        $rootScope.accionesPermitidas.usuarioBorrar = true;
				        break;
				    case "MU":
				        $rootScope.accionesPermitidas.usuarioModificar = true;
				        break;
				    case "AC":
				        $rootScope.accionesPermitidas.casoAgregar = true;
				        break;
				    case "EC":
				        $rootScope.accionesPermitidas.casoEliminar = true;
				        break;
				    case "MC":
				        $rootScope.accionesPermitidas.casoModificar = true;
				        break;
				    case "AI":
				        $rootScope.accionesPermitidas.involucradoAgregar = true;
				        break;
				    case "EI":
				        $rootScope.accionesPermitidas.involucradoBorrar = true;
				        break;
				    case "MI":
				        $rootScope.accionesPermitidas.involucradoModificar = true;
				        break;
				    case "ARP":
				        $rootScope.accionesPermitidas.asignarRebocarPermiso = true;
				        break;
				    case "ADUC":
				        $rootScope.accionesPermitidas.asociarDesasociarUsuario = true;
				        break;
				    case "OTC":
				        $rootScope.accionesPermitidas.obtenerTodosCasos = true;
				}
			}
			$rootScope.token = $scope.tokenAux;
			$location.url("/caso");
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

}]);