app.controller("detalleUsuarioController", ['$scope', '$location', '$window', '$rootScope', '$http', function ($scope, $location, $window, $rootScope, $http){

	$scope.$on('$viewContentLoaded', function(){
		if($rootScope.token == undefined || $rootScope.token == ""){
			$location.url("/login");
		}
		if($rootScope.vistasPermitidas.usuario == undefined || $rootScope.vistasPermitidas.usuario == ''){
			alert("No tienes los permisos para ver esta pagina.");
			$location.url("/caso");
		}
		if($rootScope.usuarioADetallar == undefined || $rootScope.usuarioADetallar == ""){
			$location.url("/usuario");
		}


		$scope.cargarDatosGenerales();
		$scope.cargarCasosAsignados();
		$scope.cargarCasosDisponibles();
		$scope.cargarPermisos();
		$scope.cargarPermisosRestantes();
	});

	$scope.volver = function(){
		$location.url("/usuario");
		$rootScope.usuarioADetallar = "";
		$rootScope.usuarioDetallado = "";
		$rootScope.casosAsignados = "";
		$rootScope.casosDisponibles = "";
		$rootScope.permisosOtorgados = "";
		$rootScope.permisosRestantes = "";
		$scope.modificandoUsuario = false;
		$scope.cancelarModificarUsuario();
		$scope.cancelarAsignarCaso();
		$scope.cancelarAsignarPermiso();
	}

	$scope.cargarDatosGenerales = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerUsuario?usrKey=' + $rootScope.token 
																					+ '&usuario=' + $rootScope.usuarioADetallar
		}).success(function(data, status, headers, config) {
			if(data != "" && data != "No hay usuarios"){
				$rootScope.usuarioDetallado = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarCasosAsignados = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerCasosPorUsuario?usrKey=' + $rootScope.token 
																						+ '&usuario=' + $rootScope.usuarioADetallar
		}).success(function(data, status, headers, config) {
			if(data != [] || data != "" || data != "No hay casos"){
				$rootScope.casosAsignados = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarCasosDisponibles = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerCasosNoAsignadosAUsuario?usrKey=' + $rootScope.token 
																									+ '&usuario=' + $rootScope.usuarioADetallar
		}).success(function(data, status, headers, config) {
			if(data != [] || data != "" || data != "No hay casos"){
				$rootScope.casosDisponibles = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarPermisos = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerPermisos?usrKey=' + $rootScope.token 
																						+'&usuario=' + $rootScope.usuarioADetallar
		}).success(function(data, status, headers, config) {
			$rootScope.permisosOtorgados = data;
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarPermisosRestantes = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerPermisosRestantes?usrKey=' + $rootScope.token 
																								+'&usuario=' + $rootScope.usuarioADetallar
		}).success(function(data, status, headers, config) {
			$rootScope.permisosRestantes = data;
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cambiarAModificar = function(){
		$scope.cancelarAsignarCaso();
		$scope.cancelarAsignarPermiso();
		$scope.modificandoUsuario = true;
	}

	$scope.cancelarModificarUsuario = function(){
		$scope.modificandoUsuario = false;
	}

	$scope.modificarUsuario = function(){
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/modificarUsuario?usrKey=' + $rootScope.token 
																						+ '&usuarioUsado=' + $rootScope.usuarioADetallar 
																						+ '&usuario=' + $rootScope.usuarioDetallado.usuario 
																						+ '&contrasenia=' + $rootScope.usuarioDetallado.contrasenia 
																						+ '&nombre=' + $rootScope.usuarioDetallado.nombre 
																						+ '&cedula=' + $rootScope.usuarioDetallado.cedula
																						+ '&email=' + $rootScope.usuarioDetallado.email
																						+ '&tel=' + $rootScope.usuarioDetallado.tel
																						+ '&cel=' + $rootScope.usuarioDetallado.cel
																						+ '&domicilio=' + $rootScope.usuarioDetallado.domicilio
																						+ '&domicilioLaboral=' + $rootScope.usuarioDetallado.domicilioLaboral
																						+ '&rut=' + $rootScope.usuarioDetallado.rut
																						+ '&fechaDeNacimiento=' + $rootScope.usuarioDetallado.fechaDeNacimiento
		}).success(function(data, status, headers, config) {
			if(data == "completado"){
				if($scope.usuarioADetallar == $rootScope.currentUsr.usuario){
					$scope.reLoggear($rootScope.usuarioDetallado.usuario, $rootScope.usuarioDetallado.contrasenia);
				} else {
					$rootScope.usuarioADetallar =  $rootScope.usuarioDetallado.usuario;
					$scope.modificandoUsuario = false;
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
			$rootScope.usuarioADetallar =  $rootScope.usuarioDetallado.usuario;
			$scope.modificandoUsuario = false;
			$scope.cargarDatosGenerales();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.desasignarCaso = function(IUE){
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/desasociarUsuarioACaso?usrKey=' + $rootScope.token 
																						+ '&usuario=' + $rootScope.usuarioADetallar 
																						+ '&iUE=' + IUE
		}).success(function(data, status, headers, config) {
			$scope.cargarCasosDisponibles();
			$scope.cargarCasosAsignados();
			$scope.cancelarAsignarCaso();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.rebocarPermiso = function(codePermiso){
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/rebocarPermiso?usrKey=' + $rootScope.token 
																					+ '&codePermiso=' + codePermiso
																					+ '&usuario=' + $rootScope.usuarioADetallar 
		}).success(function(data, status, headers, config) {
			if($scope.usuarioADetallar == $rootScope.currentUsr.usuario){
				$scope.recargarPermisos()
			}
			$scope.cargarPermisosRestantes();
			$scope.cargarPermisos();
			$scope.cancelarAsignarPermiso();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.recargarPermisos = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/obtenerPermisos?usrKey=' + $rootScope.token 
																					+ '&usuario=' + $rootScope.usuarioADetallar
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
			$location.url("/caso");
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.mostrarAsignarCaso = function(){
		$scope.modificandoUsuario = false;
		$scope.cancelarAsignarCaso();
		$scope.cancelarAsignarPermiso();
		$scope.cargarCasosDisponibles();
		$scope.asignarCasoFormShow = true;
	}

	$scope.mostrarAsignarPermiso = function(){
		$scope.modificandoUsuario = false;
		$scope.cancelarAsignarCaso();
		$scope.cancelarAsignarPermiso();
		$scope.cargarPermisosRestantes();
		$scope.asignarPermisoFormShow = true;
	}

	$scope.asignarCaso = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/asociarUsuarioACaso?usrKey=' + $rootScope.token 
																						+ '&usuario=' +  $rootScope.usuarioADetallar
																						+ '&iUE=' + $scope.casoSeleccionado.iUE 
																						+ '&tipo=' + $scope.tipo
		}).success(function(data, status, headers, config) {
			$scope.cargarCasosAsignados();
			$scope.cargarCasosDisponibles();
			$scope.cancelarAsignarCaso();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.asignarPermiso = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/EstudioPRLA/rest/UsuarioService/asignarPermiso?usrKey=' + $rootScope.token 
																					+ '&codePermiso=' + $scope.permisoSeleccionado.codePermiso 
																					+ '&usuario=' +  $rootScope.usuarioADetallar
		}).success(function(data, status, headers, config) {
			if($scope.usuarioADetallar == $rootScope.currentUsr.usuario){
				$scope.recargarPermisos()
			}
			$scope.cargarPermisosRestantes();
			$scope.cargarPermisos();
			$scope.cancelarAsignarPermiso();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cancelarAsignarCaso = function(){
		$scope.asignarCasoFormShow = false;
		$scope.tipo = "";
		$scope.casoSeleccionado = "";
	}

	$scope.cancelarAsignarPermiso = function(){
		$scope.asignarPermisoFormShow = false;
		$scope.permisoSeleccionado = "";
	}
	
}]);