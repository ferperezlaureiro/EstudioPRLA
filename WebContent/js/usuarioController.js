app.controller("usuarioController", ['$scope', '$location', '$window', '$rootScope', function ($scope, $location, $window, $rootScope){

	$scope.$on('$viewContentLoaded', function(){
		if($rootScope.token == undefined || $rootScope.token == ""){
			$location.url("/login");
		}
	});
	
}]);