angular.module('DemoApp.controllers').


controller('ListController', function($scope,$location, $sce, $http,vcConfig,ArticleService) {

	console.log("Article API URL: "+ vcConfig.rootUrl + vcConfig.ARTICLE_WEB_URL);
	$scope.pagedItems = [];
	

	
	$scope.addNewItem = function() {
		$location.path('/article-details');
	};
	
	$scope.loadItems = function() {
		ArticleService.loadAllArticles().then(function(result){
			$scope.pagedItems = result;
		});
	};
	
	$scope.loadItems();
	
	$scope.deleteArticle = function(obj) {
		console.log("Delete objec: "+obj.id);
		ArticleService.deleteArticle(obj.id).then(function(){
			$scope.loadItems();
		});
	};
});