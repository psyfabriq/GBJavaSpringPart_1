var pfqApp = angular.module('pfqApp', [ 'infinite-scroll' ]);

pfqApp.controller('TaskController', function($scope, ListLoad) {
	ListLoad.setURL('api/get-list-task');
	$scope.listtask = new ListLoad();
	console.log($scope.listtask);
});

pfqApp.controller('CompanyController', function($scope, ListLoad) {
	ListLoad.setURL('api/get-list-company');
	$scope.listcompany = new ListLoad();
	console.log($scope.listcompany);
});

pfqApp.controller('CategoryController', function($scope, ListLoad) {
	ListLoad.setURL('api/get-list-category');
	$scope.listcategory = new ListLoad();
	console.log($scope.listcategory);
});

pfqApp.service('ConnectService', function($q, $http) {
	this.post = function(req) {
		var deferred = $q.defer();

		$http(req).then(function(response) {
			deferred.resolve(response.data);
		});

		var promise = deferred.promise;
		return deferred.promise;
	}
});

pfqApp.factory('ListLoad', function($http, ConnectService) {
	var ListLoad = function() {
		this.items = [];
		this.busy = false;
		this.after = '';
	};

	var baseUrl = '';

	ListLoad.setURL = function(url) {
		baseUrl = url;
		console.log(baseUrl);
	}

	ListLoad.getTEST = function() {
		return baseUrl;
	}

	ListLoad.prototype.nextPage = function() {
		if (this.busy)
			return;
		this.busy = true;

		var req = {
			method : 'POST',
			url : baseUrl,
			headers : {
				'Content-Type' : 'application/json'
			},
			data : {
				test : 'test'
			}
		};

		ConnectService.post(req).then(function(response) {
			this.busy = false;
			var items = response;
			for (var i = 0; i < items.length; i++) {
				this.items.push(items[i]);
			}
			if (this.items.length != 0) {
				// this.after = "t3_" + this.items[this.items.length - 1].id;
			}
			this.busy = false;

		}.bind(this));

	};

	return ListLoad;
});
