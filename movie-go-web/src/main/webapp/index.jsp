<html data-ng-app="movieGoApp">
<head>
<base href="/movie-go-web/">
<!-- <script type="stylesheet" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"></script> -->
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-resource.min.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-route.min.js"></script>
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-animate.min.js"></script> -->
<!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular-touch.min.js"></script> -->
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
