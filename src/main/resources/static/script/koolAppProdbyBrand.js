angular.module('koolAppProdbyBrand', [])
.controller('ProductListController', function($scope, $http, $window) {
 $scope.brand = $window.location.search.substring(4);
 //alert($scope.brand);
 $scope.show=0;
 $scope.hide=0;
      $http.get('/koolApp/findItemsByBrand/'+$scope.brand).
        then(function(response) {
            $scope.brand= response.data.skuItemList;
            $scope.show=1;
            if(response.data.skuItemList=="")
            {
                 	$scope.hide=1;
            }
            
 			//alert('id'+ response.data);
         });
         
             
});