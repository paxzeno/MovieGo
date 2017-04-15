var movieGoApp = angular.module('movieGoApp', [ 'ngRoute', 'ngResource' ]);

movieGoApp.controller('MainController',
		function($scope, $route, $routeParams, $location) {
			$scope.$route = $route;
			$scope.$location = $location;
			$scope.$routeParams = $routeParams;
		}).config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/', {
		templateUrl : './app/components/all/new-releases.html',
		controller : NewReleasesController
	}).when('/movies/', {
		templateUrl : './app/components/movies/new-movie-releases.html'
	}).when('/404/', {
		templateUrl : './404.html'
	}).otherwise({
		redirectTo : '/404/'
	});

	$locationProvider.html5Mode(true);
});