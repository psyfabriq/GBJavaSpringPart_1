
var pfqApp = angular.module('pfqApp', [ 'infinite-scroll' ]);

pfqApp.controller('TaskController', function($scope, $timeout, ListLoad) {
	$scope.baseUrl = '';
	$scope.filterData = {
		position : 0,
		count : 20,
		selectedCompany : [],
		selectedCategory : []
	};

	$scope.updateCategory = function() {
		$scope.filterData.selectedCategory = [];
	};

	$scope.updateCompany = function() {
		$scope.filterData.selectedCompany = [];
	};

	$scope.seFilter = function() {
		$scope.filterData.position = 0;
		$scope.listtask.reload($scope.filterData);
	};

	ListLoad.setData($scope.filterData);
	ListLoad.setURL('api/get-list-task');
	$scope.listtask = new ListLoad();
	
});

pfqApp.controller('CompanyController', function($scope, $timeout, ListLoad) {
	$scope.baseUrl = '';
	ListLoad.setURL('api/get-list-company');
	$scope.listcompany = new ListLoad();
});

pfqApp.controller('CategoryController', function($scope, $timeout, ListLoad) {
	$scope.baseUrl = '';
	ListLoad.setURL('api/get-list-category');
	$scope.listcategory = new ListLoad();
});

pfqApp.directive('baseUrl', function($timeout) {
	return {
		restrict : 'EAC',
		template : '',
		scope : {
			model : '=model',
			url : '@'
		},
		link : function($scope, element) {
			function setBaseUrl() {
				$scope.model = $scope.url;
			}
			setBaseUrl();

		}
	};
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
		this.count = 0;
		this.after = '';
	};

	var baseUrl = '';

	var _data = {
		position : 0,
		count : 15
	};

	ListLoad.setURL = function(url) {
		baseUrl = url;
	}
	
	ListLoad.setData = function(data) {
		_data = data;
	}
	
	 function privateFunction(){

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
			if (this.count == 0) {
				if (items.length != 0) {
					_data.position = _data.position + items.length + 2;
					for (var i = 0; i < items.length; i++) {
						this.items.push(items[i]);
					}
					this.count = items.length;
				} else {
					this.load = false;
				}
			}else{ this.count--;}

			this.busy = false;

		}.bind(this));

	};
	
	ListLoad.prototype.reload = function(newdata){
		this.items = [];
		this.busy = false;
		this.load = true;
		this.count = 0;
		this.after = '';
		
		_data = newdata;
		
		
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
			if (this.count == 0) {
				if (items.length != 0) {
					_data.position = _data.position + items.length + 2;
					for (var i = 0; i < items.length; i++) {
						this.items.push(items[i]);
					}
					this.count = items.length;
				} else {
					this.load = false;
				}
			}else{ this.count--;}

			this.busy = false;

		}.bind(this));
		
     };

	return ListLoad;
});
