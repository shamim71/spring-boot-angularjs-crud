'use strict';
var myAppServices = angular.module('DemoApp.services',[]);

myAppServices.factory('ArticleService', ['$q','$http','vcConfig',function($q,$http,vcConfig) {

    var service= {};
    var path = vcConfig.rootUrl + vcConfig.ARTICLE_WEB_URL;
    service.loadAllArticles= function(){
		
		var deferred = $q.defer();
		
		$http({	method : 'GET',	url : path}).
		success(function(data, status, headers, config) {
			deferred.resolve(data);
		}).error(function(data, status, headers, config) {
			deferred.reject(status);
		});

		return deferred.promise;
	};
    service.saveArticle= function(article){

    	var deferred = $q.defer();
		$http({
			method : 'POST',
			url : path,
			data: article
		}).success(function(data, status, headers, config) {
			deferred.resolve(data);
		}).error(function(data, status, headers, config) {
			deferred.reject(status);
		});
		return deferred.promise;
	};
    service.deleteArticle= function(id){

    	var deferred = $q.defer();
    	
		$http({
			method : 'DELETE',
			url : path+'/'+id
		}).then(function(response) {
    	    deferred.resolve({});
    	}, function(rejection) {
      	    deferred.reject({});
    	});
    	

		return deferred.promise;
	};
	
    return service;

}]);
