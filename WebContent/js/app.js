var app = angular.module("app", ['ngRoute']);
 
app.config(function($routeProvider){

	$routeProvider.when("/", {
		templateUrl : "login.html",
		controller : "loginController"
	})
	.when("/login", {
		templateUrl : "login.html",
		controller : "loginController"
	})
	.when("/caso", {
		templateUrl : "caso.html",
		controller : "casoController"
	})
	.when("/usuario", {
		templateUrl : "usuario.html",
		controller : "usuarioController"
	})
	.when("/configuracion", {
		templateUrl : "configuracion.html",
		controller : "configuracionController"
	})
	.when("/cuenta", {
		templateUrl : "cuenta.html",
		controller : "cuentaController"
	})
	.otherwise({ reditrectTo : "/login" });
});