app.controller("usuarioController", ['$scope', '$location', '$window', '$rootScope', '$http', function ($scope, $location, $window, $rootScope, $http){

	$scope.$on('$viewContentLoaded', function(){
		if($rootScope.token == undefined || $rootScope.token == ""){
			$location.url("/login");
		}
		if($rootScope.vistasPermitidas.usuario == undefined || $rootScope.vistasPermitidas.usuario == ''){
			alert("No tienes los permisos para ver esta pagina.");
			$location.url("/caso");
		}

		$rootScope.usuario = '';
		$scope.cancelarUsuario();
		$scope.cargarUsuarios();
	});

	$scope.open1 = function() {
		$scope.fechaNacimientoPopUp.opened = true;
	};

	$scope.dateOptions = {
		formatYear: 'yy',
		startingDay: 1
	};

	$scope.cargarUsuarios = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerUsuarios?usrKey=' + $rootScope.token
		}).success(function(data, status, headers, config) {
			if(data != "" && data != "No hay usuarios"){
				$rootScope.usuarios = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.eliminarUsuario = function(usuario){
		$scope.cancelarUsuario();
		if(usuario == $rootScope.currentUsr.usuario){
			alert("No puedes eliminar el usuario actual");
		} else {
			$http({
				method: 'DELETE',
				url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/eliminarUsuario?usrKey=' + $rootScope.token 
																						+ '&usuario=' + usuario
			}).success(function(data, status, headers, config) {
				$scope.cancelarUsuario();
				$scope.cargarUsuarios();
			}).error(function(data, status, headers, config) {
				alert("Ha fallado la petición. Estado HTTP:"+status);
			});
		}
	}

	$scope.mostrarAgregarUsuario = function(){
		$scope.cancelarUsuario();
		$scope.botonAgregarUsuario = true;
		$scope.botonModificarUsuario = false;
		$scope.usuarioFormShow = true;
		$scope.fechaNacimientoPopUp = {opened : false};
		$scope.dtFechaDeNacimiento = new Date();
	}

	$scope.agregarUsuario = function(){
		var fecha = "";
		var day = parseInt($scope.dtFechaDeNacimiento.getDate());
		if(day<10)
			fecha += "0" + day;
		else
			fecha += day;
		var month = parseInt($scope.dtFechaDeNacimiento.getMonth())+1;
		if (month<10)
			fecha += "/0" + month + "/" + $scope.dtFechaDeNacimiento.getFullYear();
		else
			fecha += "/" + month + "/" + $scope.dtFechaDeNacimiento.getFullYear();
		$http({
			method: 'POST',
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/agregarUsuario?usrKey=' + $rootScope.token 
																					+ '&usuario=' + $scope.usuario 
																					+ '&contrasenia=' + $scope.contrasenia 
																					+ '&nombre=' + $scope.nombre 
																					+ '&cedula=' + $scope.cedula
																					+ '&email=' + $scope.email
																					+ '&tel=' + $scope.tel
																					+ '&cel=' + $scope.cel
																					+ '&domicilio=' + $scope.domicilio
																					+ '&domicilioLaboral=' + $scope.domicilioLaboral
																					+ '&rut=' + $scope.rut
																					+ '&fechaDeNacimiento=' + fecha
		}).success(function(data, status, headers, config) {
			$scope.cancelarUsuario();
			$scope.cargarUsuarios();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.MostrarModificarUsuario = function(usuario){
		$scope.cancelarUsuario();
		if(usuario == $rootScope.currentUsr.usuario){
			alert("No puedes modificar el usuario actual, hazlo desde la seccion Mi Cuenta");
		} else {
			$scope.botonAgregarUsuario = false;
			$scope.botonModificarUsuario = true;
			for(var i in $rootScope.usuarios){
				if($rootScope.usuarios[i].usuario == usuario){
					$scope.usuarioUsado = $rootScope.usuarios[i].usuario;
					$scope.usuario = $rootScope.usuarios[i].usuario;
					$scope.contraseniaUsada = $rootScope.usuarios[i].contrasenia;
					$scope.contrasenia = $rootScope.usuarios[i].contrasenia;
					$scope.nombre = $rootScope.usuarios[i].nombre;
					$scope.cedula = $rootScope.usuarios[i].cedula;
					$scope.email = $rootScope.usuarios[i].email;
					$scope.tel = $rootScope.usuarios[i].tel;
					$scope.cel = $rootScope.usuarios[i].cel;
					$scope.domicilio = $rootScope.usuarios[i].domicilio;
					$scope.domicilioLaboral = $rootScope.usuarios[i].domicilioLaboral;
					$scope.rut = $rootScope.usuarios[i].rut;
					$scope.fechaDeNacimiento = $rootScope.usuarios[i].fechaDeNacimiento;
					$scope.dtFechaDeNacimiento = new Date();
					var fecha = $scope.fechaDeNacimiento.split("/");
					$scope.dtFechaDeNacimiento.setDate(fecha[0]);
					$scope.dtFechaDeNacimiento.setMonth(fecha[1]-1);
					$scope.dtFechaDeNacimiento.setFullYear(fecha[2]);
				}
			}
			$scope.usuarioFormShow = true;
		}
	}

	$scope.modificarUsuario = function(){
		var fecha = "";
		var day = parseInt($scope.dtFechaDeNacimiento.getDate());
		if(day<10)
			fecha += "0" + day;
		else
			fecha += day;
		var month = parseInt($scope.dtFechaDeNacimiento.getMonth())+1;
		if (month<10)
			fecha += "/0" + month + "/" + $scope.dtFechaDeNacimiento.getFullYear();
		else
			fecha += "/" + month + "/" + $scope.dtFechaDeNacimiento.getFullYear();
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/modificarUsuario?usrKey=' + $rootScope.token 
																					+ '&usuarioUsado=' + $scope.usuarioUsado 
																					+ '&usuario=' + $scope.usuario 
																					+ '&contrasenia=' + $scope.contrasenia 
																					+ '&nombre=' + $scope.nombre 
																					+ '&cedula=' + $scope.cedula
																					+ '&email=' + $scope.email
																					+ '&tel=' + $scope.tel
																					+ '&cel=' + $scope.cel
																					+ '&domicilio=' + $scope.domicilio
																					+ '&domicilioLaboral=' + $scope.domicilioLaboral
																					+ '&rut=' + $scope.rut
																					+ '&fechaDeNacimiento=' + fecha
		}).success(function(data, status, headers, config) {
			if(data == "completado"){
				if(($scope.usuarioUsado != $scope.usuario || $scope.contraseniaUsada != $scope.contrasenia) 
					&& $scope.usuarioUsado == $rootScope.currentUsr.usuario){
					$scope.reLoggear($scope.usuario, $scope.contrasenia);
				} else {
					$scope.cancelarUsuario();
					$scope.cargarUsuarios();
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
			} else {
				//todo error
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
			$scope.cancelarUsuario();
			$scope.cargarUsuarios();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.detalleUsuario = function(usuario){
		$rootScope.usuarioADetallar = usuario;
		$location.url("/detalleUsuario");
	}

	$scope.cancelarUsuario = function(){
		$scope.usuarioFormShow = false;
		$scope.botonAgregarUsuario = false;
		$scope.botonModificarUsuario = false;
		$scope.usuarioUsado = "";
		$scope.usuario = "";
		$scope.contrasenia = "";
		$scope.nombre = "";
		$scope.cedula = "";
		$scope.email = "";
		$scope.tel = "";
		$scope.cel = "";
		$scope.domicilio = "";
		$scope.domicilioLaboral = "";
		$scope.rut = "";
		$scope.fechaDeNacimiento = "";
		$rootScope.usuarioADetallar = "";
	}


	
}]);