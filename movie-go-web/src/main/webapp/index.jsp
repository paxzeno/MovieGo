<html data-ng-app="movieGoApp">
<head>
<base href="/movie-go-web/">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="./assets/css/app.css" rel="stylesheet">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-resource.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-route.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-animate.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.1/angular-touch.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.min.js"></script>
<script type="text/javascript" src="./app/app.js"></script>
<script type="text/javascript" src="./app/components/all/new-releases.component.js"></script>
</head>
<body data-ng-controller='MainController'>
	<h2>--Menu--</h2>
	<a href="./">home</a>&nbsp;<a href="./movies/">movies</a>&nbsp;<a href="./series/">series</a>
	<hr>
	<div data-ng-view></div>

</body>
</html>
