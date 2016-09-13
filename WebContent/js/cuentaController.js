app.controller("cuentaController", ['$scope', '$location', '$window', '$rootScope', '$http', function ($scope, $location, $window, $rootScope, $http){

	$scope.$on('$viewContentLoaded', function(){
		if($rootScope.token == undefined || $rootScope.token == ""){
			$location.url("/login");
		}

		$scope.cargarDatosGenerales();
	});

	$scope.cargarDatosGenerales = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerUsuario?usrKey=' + $rootScope.token 
																					+ '&usuario=' + $rootScope.currentUsr.usuario
		}).success(function(data, status, headers, config) {
			if(data != "" && data != "No hay usuarios"){
				$scope.usuarioActual = data.usuario;
				$rootScope.miCuenta = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cambiarAModificar = function(){
		$scope.cancelarCambiarContrasenia();
		$scope.modificandoUsuario = true;
	}

	$scope.mostrarCambiarContrasenia = function(){
		$scope.cancelarModificarUsuario();
		$scope.cambiarContraseniaFormShow = true;
	}

	$scope.modificarUsuario = function(){
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/modificarMiCuenta?usrKey=' + $rootScope.token 
																						+ '&usuario=' + $rootScope.miCuenta.usuario 
																						+ '&contrasenia=' + $rootScope.miCuenta.contrasenia 
																						+ '&nombre=' + $rootScope.miCuenta.nombre 
																						+ '&cedula=' + $rootScope.miCuenta.cedula
																						+ '&email=' + $rootScope.miCuenta.email
																						+ '&tel=' + $rootScope.miCuenta.tel
																						+ '&cel=' + $rootScope.miCuenta.cel
																						+ '&domicilio=' + $rootScope.miCuenta.domicilio
																						+ '&domicilioLaboral=' + $rootScope.miCuenta.domicilioLaboral
																						+ '&rut=' + $rootScope.miCuenta.rut
																						+ '&fechaDeNacimiento=' + $rootScope.miCuenta.fechaDeNacimiento
		}).success(function(data, status, headers, config) {
			if(data == "completado"){
				if($rootScope.miCuenta.usuario != $scope.usuarioActual){
					$scope.reLoggear($rootScope.miCuenta.usuario, $rootScope.miCuenta.contrasenia);
				} else {
					$scope.cancelarModificarUsuario();
					$scope.cargarDatosGenerales();
				}
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cambiarContrasenia = function(){
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/modificarContrasenia?usrKey=' + $rootScope.token 
																						+ '&contrasenia=' + $scope.nuevaPassword 
		}).success(function(data, status, headers, config) {
			if(data == "completado"){
				if($scope.nuevaPassword != $scope.password){
					$scope.reLoggear($rootScope.miCuenta.usuario, $scope.nuevaPassword);
				} else {
					$scope.cancelarCambiarContrasenia();
					$scope.cargarDatosGenerales();
				}
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.reLoggear = function(usuario, contrasenia){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/login?usuario=' + usuario 
																			+ '&contrasenia=' + contrasenia
		}).success(function(data, status, headers, config) {
			if (data != "usuario" && data != "|contrasenia" && data != "usuario|contrasenia") {
				$rootScope.token = data;
				$scope.reCargarUsuario(usuario);
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.reCargarUsuario = function(usuario){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerUsuario?usrKey=' + $rootScope.token 
																					+ '&usuario=' + usuario
		}).success(function(data, status, headers, config) {
			$rootScope.currentUsr = data;
			$scope.cancelarModificarUsuario();
			$scope.cancelarCambiarContrasenia();
			$scope.cargarDatosGenerales();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}


	$scope.cancelarModificarUsuario = function(){
		$scope.modificandoUsuario = false;
	}

	$scope.cancelarCambiarContrasenia = function(){
		$scope.cambiarContraseniaFormShow = false;
		$scope.password = "";
		$scope.nuevaPassword = "";
		$scope.repetirPassword = "";
	}
	
}]);