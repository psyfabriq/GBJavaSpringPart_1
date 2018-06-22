var pfqApp = angular.module('pfqApp', [ 'infinite-scroll' ]);

pfqApp.controller('TaskController', function($scope, ListLoad) {
	
	$scope.filterData = {
			position : 0,
			count : 10,
			selectedCompany : [],
			selectedCategory : []
	};
	
	$scope.filter = {
		company : [{id : '', name : 'ALL'}],
		category : [{id : '', name : 'ALL'}]
	};
	ListLoad.setData($scope.filterData);
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
		this.load = true;
		this.after = '';
	};

	var baseUrl = '';
	
	var _data = { 
			position : 0,
			count : 6
	};

	ListLoad.setURL = function(url) {
		baseUrl = url;
	}
	
	ListLoad.setData = function(data) {
		_data = data;
	}

	ListLoad.getTEST = function() {
		return baseUrl;
	}

	ListLoad.prototype.nextPage = function() {
		if (this.busy || !this.load)
			return;
		this.busy = true;

		var req = {
			method : 'POST',
			url : baseUrl,
			headers : {
				'Content-Type' : 'application/json'
			},
			data : _data
		};

		ConnectService.post(req).then(function(response) {
			this.busy = false;
			var items = response;
			if(items.length != 0){
				_data.position = _data.position + items.length + 1;
				for (var i = 0; i < items.length; i++) {
					this.items.push(items[i]);
				}
			}else{
				this.load = false;
			}
			
			this.busy = false;

		}.bind(this));

	};

	return ListLoad;
});
