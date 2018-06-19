angular.module('DemoApp.controllers').


controller('HomeController', function($scope,$location, $sce, $http,vcConfig) {

	console.log("Root URL: "+ vcConfig.rootUrl);
	 		
});