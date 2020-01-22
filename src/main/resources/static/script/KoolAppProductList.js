angular.module('koolAppProductApp', [])
.controller('ProductListController', function($scope, $http, $window) {
 $scope.commodityId = $window.location.search.substring(4);
 $scope.show=0;
 $scope.hide=0;
      $http.get('/koolApp/findItemsByCommodity/'+$scope.commodityId).
        then(function(response) {
            $scope.comodity= response.data.skuItemList;;
            $scope.show=1;
            if(response.data.skuItemList=="")
            {
                 	$scope.hide=1;
            }
            
 			//alert('id'+ response.data);
         });
         
             
 
    
});