var pfqApp = angular.module('pfqApp', ['infinite-scroll']);


pfqApp.controller('MainController', function($scope, ListTask) {
	  $scope.listtask = new ListTask();
	});

pfqApp.factory('ListTask', function($http) {
	  var ListTask = function() {
	    this.items = [];
	    this.busy = false;
	    this.after = '';
	  };

	  ListTask.prototype.nextPage = function() {
	    if (this.busy) return;
	    this.busy = true;
	    
	    /*
	    $scope.method = 'GET';
	    $scope.url = "https://api.reddit.com/hot?after=" + this.after + "&jsonp=JSON_CALLBACK";

	      $http({method: $scope.method, url: $scope.url, cache: $templateCache}).
	        then(function(response) {
	          $scope.status = response.status;          
	          $scope.data = response.data
	          
	        }, function(response) {
	          $scope.data = response.data || 'Request failed';
	          $scope.status = response.status;
	      });
	      */

	  };

	  return ListTask;
	});