app.controller("detalleCasoController", ['$scope', '$location', '$window', '$rootScope', '$http', function ($scope, $location, $window, $rootScope, $http){

	$scope.$on('$viewContentLoaded', function(){
		if($rootScope.token == undefined || $rootScope.token == ""){
			$location.url("/login");
		}

		if($rootScope.casoADetallar == undefined || $rootScope.casoADetallar == ""){
			$location.url("/caso");
		}

		$scope.modificandoCaso = false;

		$scope.cancelarInvolucrado();
		$scope.cancelarAsginarUsuario();
		$scope.cargarDatosGenerales();
		$scope.cargarTodosLosInvolucrados();
		$scope.cargarUsuariosPorIUE();
		$scope.cargarUsuariosDisponiblesPorIUE();
	});

	$scope.volver = function(){
		$location.url("/caso");
		$rootScope.casoADetallar = "";
		$rootScope.todosInvolucrados = "";
		$rootScope.usuariosAsignados = "";
		$rootScope.usuariosDisponibles = "";
	}

	$scope.cargarDatosGenerales = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerCasoPorIUE?usrKey=' + $rootScope.token 
																					+ '&iUE=' + $rootScope.casoADetallar
		}).success(function(data, status, headers, config) {
			if(data != "" && data != "No hay casos"){
				$rootScope.casoDetallado = data;
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarTodosLosInvolucrados = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerInvolucrados?usrKey=' + $rootScope.token 
																						+ '&iUE=' + $rootScope.casoADetallar
		}).success(function(data, status, headers, config) {
			if(data != [] && data != "" && data != "No hay involucrados"){
				$rootScope.todosInvolucrados = data;
			} else {
				$rootScope.todosInvolucrados = '';
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cambiarAModificar = function(){
		$scope.cancelarInvolucrado();
		$scope.cancelarAsginarUsuario();
		$scope.modificandoCaso = true;
	}

	$scope.cancelarModificarCaso = function(){
		$scope.modificandoCaso = false;
	}
	
	$scope.modificarCaso = function(){
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/modificarCaso?usrKey=' + $rootScope.token 
																				+ '&iUEUsado='  + $rootScope.casoADetallar 
																				+ '&iUE=' +  $rootScope.casoDetallado.iUE 
																				+ '&juzgado=' + $rootScope.casoDetallado.juzgado 
																				+ '&turno=' + $rootScope.casoDetallado.turno 
																				+ '&caratulado=' + $rootScope.casoDetallado.caratulado
		}).success(function(data, status, headers, config) {
			$rootScope.casoADetallar =  $rootScope.casoDetallado.iUE;
			$scope.modificandoCaso = false;
			$scope.cargarDatosGenerales();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.eliminarInvolucrado = function(cedula){
		$scope.cancelarInvolucrado();
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/eliminarInvolucrado?usrKey=' + $rootScope.token 
																				+ '&iUE=' + $rootScope.casoADetallar 
																				+ '&ciInvolucrado=' + cedula
		}).success(function(data, status, headers, config) {
				$scope.cargarTodosLosInvolucrados();
				$scope.cancelarInvolucrado();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.mostrarAgregarInvolucrado = function(){
		$scope.modificandoCaso = false;
		$scope.cancelarInvolucrado();
		$scope.cancelarModificarCaso();
		$scope.botonAgregarInvolucrado = true;
		$scope.botonModificarInvolucrado = false;
		$scope.involucradoFormShow = true;
	}
	
	$scope.agregarInvolucrado = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/agregarInvolucrado?usrKey=' + $rootScope.token 
																					+ '&iUE=' + $rootScope.casoADetallar 
																					+ '&fechaDeNacimiento=' + $scope.fechaDeNacimiento 
																					+ '&nombre=' + $scope.nombre 
																					+ '&cedula=' + $scope.cedula
																					+ '&nacionalidad=' + $scope.nacionalidad
																					+ '&domicilio=' + $scope.domicilio
																					+ '&clase=' + $scope.clase
		}).success(function(data, status, headers, config) {
			$scope.cargarTodosLosInvolucrados();
			$scope.cancelarInvolucrado();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}


	$scope.mostrarModificarInvolucrado = function(cedula){
		$scope.modificandoCaso = false;
		$scope.cancelarInvolucrado();
		$scope.cancelarModificarCaso();
		for (var i in $rootScope.todosInvolucrados){
			if(cedula == $rootScope.todosInvolucrados[i].cedula){
				$scope.cedulaUsada = $rootScope.todosInvolucrados[i].cedula; 
				$scope.fechaDeNacimiento = $rootScope.todosInvolucrados[i].fechaDeNacimiento;
				$scope.nombre = $rootScope.todosInvolucrados[i].nombre;
				$scope.cedula = $rootScope.todosInvolucrados[i].cedula;
				$scope.nacionalidad = $rootScope.todosInvolucrados[i].nacionalidad;
				$scope.domicilio = $rootScope.todosInvolucrados[i].domicilio;
				$scope.clase = $rootScope.todosInvolucrados[i].clase;
			}
		}
		$scope.botonAgregarInvolucrado = false;
		$scope.botonModificarInvolucrado = true;
		$scope.involucradoFormShow = true;
	}
	
	$scope.modificarInvolucrado = function(){
		$http({
			method: 'PUT',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/modificarInvolucrado?usrKey=' + $rootScope.token 
																						+ '&iUE=' +  $rootScope.casoADetallar 
																						+ '&cedulaUsada=' + $scope.cedulaUsada 
																						+ '&fechaDeNacimiento=' + $scope.fechaDeNacimiento 
																						+ '&nombre='+ $scope.nombre 
																						+ '&cedula='+ $scope.cedula
																						+ '&nacionalidad=' + $scope.nacionalidad
																						+ '&domicilio=' + $scope.domicilio
																						+ '&clase=' + $scope.clase
		}).success(function(data, status, headers, config) {
			$scope.cargarTodosLosInvolucrados();
			$scope.cancelarInvolucrado();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cancelarInvolucrado = function(){
		$scope.involucradoFormShow = false;
		$scope.botonAgregarInvolucrado = false;
		$scope.botonModificarInvolucrado = false;
		$scope.cedulaUsada = "";
		$scope.fechaDeNacimiento = "";
		$scope.nombre = "";
		$scope.cedula = "";
		$scope.nacionalidad = "";
		$scope.domicilio = "";
		$scope.clase = "";
	}

	$scope.cargarUsuariosPorIUE = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerUsuariosPorCaso?usrKey=' + $rootScope.token 
																						+ '&iUE=' + $rootScope.casoADetallar
		}).success(function(data, status, headers, config) {
			if(data != [] && data != "" && data != "No hay usuarios"){
				$rootScope.usuariosAsignados = data;
			} else {
				$rootScope.usuariosAsignados = '';
			}
			$scope.cargarMensajes();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarUsuariosDisponiblesPorIUE = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerUsuariosNoAsignadosACaso?usrKey=' + $rootScope.token 
																									+ '&iUE=' + $rootScope.casoADetallar
		}).success(function(data, status, headers, config) {
			if(data != [] && data != "" && data != "No hay usuarios"){
				$rootScope.usuariosDisponibles = data;
			} else {
				$rootScope.usuariosDisponibles = '';
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.desasignarUsuario = function(usuario){
		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/desasociarUsuarioACaso?usrKey=' + $rootScope.token 
																						+ '&usuario=' + usuario 
																						+ '&iUE=' + $rootScope.casoADetallar
		}).success(function(data, status, headers, config) {
			$scope.cargarUsuariosPorIUE();
			$scope.cargarUsuariosDisponiblesPorIUE();
			$scope.cancelarAsginarUsuario();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.mostrarAsignarUsuario = function(){
		$scope.modificandoCaso = false;
		$scope.cancelarInvolucrado();
		$scope.cargarUsuariosDisponiblesPorIUE();
		$scope.asignarUsuarioFormShow = true;
	}

	$scope.asignarUsuario = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/asociarUsuarioACaso?usrKey=' + $rootScope.token 
																						+ '&usuario=' +  $scope.usuarioSeleccionado.usuario
																						+ '&iUE=' + $rootScope.casoADetallar 
																						+ '&tipo=' + $scope.tipo
		}).success(function(data, status, headers, config) {
			$scope.cargarUsuariosPorIUE();
			$scope.cargarUsuariosDisponiblesPorIUE();
			$scope.cancelarAsginarUsuario();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarMensajes = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerConversacion?usrKey=' + $rootScope.token 
																						+ '&iUE=' + $rootScope.casoADetallar
		}).success(function(data, status, headers, config) {
			if(data != [] && data != "" && data != "No hay mensajes"){
				$rootScope.mensajes = data;
			} else {
				$rootScope.mensajes = '';
			}
			for(var i in $rootScope.usuariosAsignados){
				for(var x in $rootScope.mensajes){
					if($rootScope.mensajes[x].idUsuario == $rootScope.usuariosAsignados[i].id){
						$rootScope.mensajes[x].usuarioString = $rootScope.usuariosAsignados[i].usuario;
					}
				}
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.enviarMensaje = function(){
		$http({
			method: 'POST',
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/agregarMensaje?usrKey=' + $rootScope.token 
																					+ '&iUE=' + $rootScope.casoADetallar 
																					+ '&contenido=' + $scope.contenido
		}).success(function(data, status, headers, config) {
			$scope.contenido = "";
			$scope.cargarMensajes();
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cancelarAsginarUsuario = function(){
		$scope.asignarUsuarioFormShow = false;
	}
}]);