var pfqApp = angular.module('pfqApp', [ 'infinite-scroll' ]);

pfqApp.controller('MainController', function($scope, ListTask) {
	$scope.listtask = new ListTask();
});

pfqApp.service('ConnectService', function($q,$http) {
    this.post = function (req) {
    	var deferred = $q.defer();

    	
    	$http(req).then(function(response) {
			 deferred.resolve(response.data);
		 });
        
    	var promise = deferred.promise;
    	var resolvedValue;
    	
    	promise.then(function(value) { this.resolvedValue = value; });
    	
        return resolvedValue;
    }
});

pfqApp.factory('ListTask', function($http,ConnectService) {
	var ListTask = function() {
		this.items = [];
		this.busy = false;
		this.after = '';
	};
    
	
	
	ListTask.prototype.nextPage = function() {
		if (this.busy)
			return;
		this.busy = true;
		 
		var req = {
			method : 'POST',
			url : 'http://127.0.0.1:8080/podstavkov/api/get-list-task',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				test : 'test'
			}
		};
		
		console.log(ConnectService.post(req));
		
	};

	return ListTask;
});