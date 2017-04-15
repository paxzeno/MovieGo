angular.module('movieGoApp').component('newReleases', {
	templateUrl : 'new-releases.html',
	controller : NewReleasesController
});

function NewReleasesController($scope, $resource) {
	var endpoint = 'http://localhost:8080/movie-go-rest/rest/NewMediaReleases';
	
	var resource = $resource(endpoint, {}, {
		query : {
			method : 'GET'
		},
		update : {
			method : 'PUT',
			url : endpoint + '/update/:id'
		}
	})

	$scope.movies = resource.query();

	$scope.checkNewRelease = function(id) {
		var movie = $scope.movies[id];
		if (!('check' in $scope.movies[id])) {
			movie['check'] = true;
		} else {
			var check = movie['check'];
			movie['check'] = !check;
		}
		resource.update({
			id : id
		}, movie);
	}

	$scope.checked = function(id) {
		if ('check' in $scope.movies[id]) {
			return $scope.movies[id]['check'];
		}
		return false;
	}
}