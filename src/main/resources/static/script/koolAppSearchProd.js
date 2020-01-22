angular.module('koolAppSearch', [])
.controller('ProductListController', function($scope, $http, $window) {
 $scope.text = $window.location.search.substring(6);
 //alert($scope.text);
 $scope.show=0;
 $scope.hide=0;
      $http.get('/koolApp/findItemsByText/'+$scope.text).
        then(function(response) {
        //alert('id'+ response.data.skuItemList);
        	$scope.comodity= response.data.skuItemList;
            $scope.show=1;
            if(response.data.skuItemList=="")
            {
            	
                 	$scope.hide=1; 
            }
            	
 		 });
      
});