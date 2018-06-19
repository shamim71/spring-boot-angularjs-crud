angular.module('DemoApp.controllers').


controller('ArticleDetailsController', function($scope, $location, $routeParams,ArticleService) {

	$scope.device = {};
	$scope.deviceCategories = ["Cell Phone","Tools", "Laptop"];
	$scope.inProgress = false;
	console.log($routeParams.id);
	
	$scope.saveArticle = function(device) {
		$scope.inProgress = true;
		ArticleService.saveArticle(device).then(function(result){
			$scope.inProgress = false;
			$location.path('/list');
		});
	};

	$scope.back = function() {
		$location.path('/list');
	};		
});