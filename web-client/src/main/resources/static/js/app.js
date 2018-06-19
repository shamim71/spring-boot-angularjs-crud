'use strict';


/* Controllers */


angular.module('DemoApp', [
  'DemoApp.services',
  'DemoApp.controllers',
  'ngRoute','ui.bootstrap'
]).
constant("vcConfig", {
	"rootUrl" : window.location.protocol
			+ "//localhost:8080/service/api",
	"ARTICLE_WEB_URL" : "/articles",
	"jobApplicationURL" : "/qbase/candidates"
}).
config(['$routeProvider', function($routeProvider) {
    
	$routeProvider.
	  when("/home", {templateUrl: "views/home.html", controller: "HomeController"}).
	  when("/list", {templateUrl: "views/list.html", controller: "ListController"}).
	  when("/article-details/:id", {templateUrl: "views/article_details.html", controller: "ArticleDetailsController"}).
	  when("/article-details", {templateUrl: "views/article_details.html", controller: "ArticleDetailsController"}).
	  when("/about", {templateUrl: "views/about.html", controller: "HomeController"}).
	  otherwise({redirectTo: '/home'});
  
}]);

angular.module('DemoApp.controllers', ['ngSanitize']);

