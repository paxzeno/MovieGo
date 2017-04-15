angular.module('movieGoApp').component('newReleases', {
	templateUrl : 'new-releases.html',
	controller : NewReleasesController
});

function NewReleasesController($scope, $resource) {

	var newReleases = $resource(
			'http://localhost:8080/movie-go-rest/rest/NewMediaReleases', {}, {
				query : {
					method : 'GET'
				}
			})

	$scope.movies = newReleases.query();
}