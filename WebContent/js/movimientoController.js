app.controller("movimientoController", ['$scope', '$location', '$window', '$rootScope', '$http', function ($scope, $location, $window, $rootScope, $http){

	$scope.$on('$viewContentLoaded', function(){
		if($rootScope.token == undefined || $rootScope.token == ""){
			$location.url("/login");
		}
		if($rootScope.vistasPermitidas.movimiento == undefined || $rootScope.vistasPermitidas.movimiento == ''){
			alert("No tienes los permisos para ver esta pagina.");
			$location.url("/caso");
		}

		$rootScope.movimientos = '';
		$scope.cargarMisCasosSuscritos();
	});

	$scope.cargarMovimientos = function(fechaInicio, fechaFin){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerMovimientos?usrKey=' + $rootScope.token
																					+ '&fechaInicio=' + fechaInicio
																					+ '&fechaFin=' + fechaFin
		}).success(function(data, status, headers, config) {
			if(data != "" && data != []){
				for(var i in data){
					data[i].titulo = data[i].iUE + ": " + data[i].caratulado;
					data[i].agregarMensaje = false;
					data[i].adjuntarMovimiento = false;
					data[i].contenido = "";
					if(data[i].decreto != ""){
						var auxDecretoNro = data[i].decreto.substring(0,data[i].decreto.indexOf("&%"));
						var auxDecretoContenidoYAutor = data[i].decreto.substring(data[i].decreto.indexOf(".")+1,
																									data[i].decreto.length);
						var auxDecretoContenido = auxDecretoContenidoYAutor.substring(0,auxDecretoContenidoYAutor.indexOf("Dra"));
						var auxAutor = auxDecretoContenidoYAutor.substring(auxDecretoContenidoYAutor.indexOf("Dra"),auxDecretoContenidoYAutor.length);
						data[i].decretoContenido = auxDecretoContenido;
						data[i].decretoAutor = auxAutor;
						data[i].decreto = auxDecretoNro;
					}
					for(var x in $rootScope.misCasosSuscriptos){
						if(data[i].iUE == $rootScope.misCasosSuscriptos[x].iUE){
							$rootScope.misCasosSuscriptos[x].movimientos.push(data[i]);
						}	
					}
				}
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	}

	$scope.cargarMisCasosSuscritos = function(){
		$http({
			method: 'GET', 
			url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/obtenerCasosSuscritosPorUsuario?usrKey=' + $rootScope.token 
																									+'&usuario=' + $rootScope.currentUsr.usuario
		}).success(function(data, status, headers, config) {
			if(data.length != 0 && data != "" && data != "No hay casos"){
				$rootScope.misCasosSuscriptos = data;
				for(var i in $rootScope.misCasosSuscriptos){
					$rootScope.misCasosSuscriptos[i].movimientos = [];
				}
				$scope.cargarMovimientos('14/04/2016','14/09/2016');
			} else {
				$rootScope.misCasosSuscriptos = '';
			}
		}).error(function(data, status, headers, config) {
			alert("Ha fallado la petición. Estado HTTP:"+status);
		});
	};
	
	$scope.enviarMensaje = function(index, iUE){
		for(var x in $rootScope.misCasosSuscriptos) {
			if($rootScope.misCasosSuscriptos[x].iUE == iUE){
				for(var i in $rootScope.misCasosSuscriptos[x].movimientos){
					if($rootScope.misCasosSuscriptos[x].movimientos[i].index == index){
						var contenido = $rootScope.misCasosSuscriptos[x].movimientos[i].contenido;

						if($rootScope.misCasosSuscriptos[x].movimientos[i].adjuntarMovimiento) {
							contenido += "</br><b>";
							if($rootScope.misCasosSuscriptos[x].movimientos[i].sede!="")
								contenido += "Sede: " + $rootScope.misCasosSuscriptos[x].movimientos[i].sede + "</br>";
							if($rootScope.misCasosSuscriptos[x].movimientos[i].tipo!="")
								contenido += "Tipo: " + $rootScope.misCasosSuscriptos[x].movimientos[i].tipo + "</br>";
							if($rootScope.misCasosSuscriptos[x].movimientos[i].decreto!=""){
								contenido += "Decreto: " + $rootScope.misCasosSuscriptos[x].movimientos[i].decreto + "</br>";
								contenido += "<i>" + $rootScope.misCasosSuscriptos[x].movimientos[i].decretoContenido + "</br>";
								contenido += $rootScope.misCasosSuscriptos[x].movimientos[i].decretoAutor + "</i></br>";
							}
							if($rootScope.misCasosSuscriptos[x].movimientos[i].vencimiento!="")
								contenido += "Vencimiento: " + $rootScope.misCasosSuscriptos[x].movimientos[i].vencimiento + "</br>";
							if($rootScope.misCasosSuscriptos[x].movimientos[i].fecha!="")
								contenido += "Fecha: " + $rootScope.misCasosSuscriptos[x].movimientos[i].fecha + "</br>";
							contenido += "</b>"
						}
						
						$rootScope.misCasosSuscriptos[x].movimientos[i].contenido = "";
						$rootScope.misCasosSuscriptos[x].movimientos[i].agregarMensaje = false;
						$rootScope.misCasosSuscriptos[x].movimientos[i].adjuntarMovimiento = false;

						$http({
							method: 'POST',
							url: 'http://localhost:8080/EstudioPRLA/rest/CasoService/agregarMensaje?usrKey=' + $rootScope.token 
																									+ '&iUE=' + $rootScope.misCasosSuscriptos[x].movimientos[i].iUE 
																									+ '&contenido=' + contenido
																									+ '&infoMensaje=' + true
						}).success(function(data, status, headers, config) {
						}).error(function(data, status, headers, config) {
							alert("Ha fallado la petición. Estado HTTP:"+status);
						});	
					}
				}
			}
		}
	}
}]);