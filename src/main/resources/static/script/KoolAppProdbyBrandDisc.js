angular.module('koolAppProdBrandDisc', [])
.controller('ProductListController', function($scope, $http, $window) {
 $scope.brandName = $window.location.search.substring(7);
 //alert($scope.brandName);
 $scope.show=0;
 $scope.hide=0;
      $http.get('/koolApp/findItemsByBrandAndDiscount/'+$scope.brandName +"/0.4").
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