angular.module('DemoApp.controllers').


controller('ArticleDetailsController', function($scope, $location, $routeParams,ArticleService) {

	$scope.device = {};
	$scope.deviceCategories = ["Cell Phone","Tools", "Laptop"];
	$scope.update = false;

	$scope.saveArticle = function(device) {
		if($scope.update == true){
			ArticleService.updateArticle(device.id, device).then(function(result){
				$scope.back();
			});
		}
		else{
			ArticleService.saveArticle(device).then(function(result){
				$scope.back();
			});
		}
	};
	
	$scope.loadItem = function(id) {
		ArticleService.getArticle(id).then(function(result){
			$scope.device = result.data;
		});
	};
	
	if($routeParams.id != null || $routeParams.id == undefined){
		$scope.update = true;
		$scope.loadItem($routeParams.id);
	}
	
	$scope.back = function() {
		$location.path('/list');
	};		
});