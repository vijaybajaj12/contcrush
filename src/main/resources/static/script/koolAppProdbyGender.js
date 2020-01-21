angular.module('koolAppProdbyGender', [])
.controller('ProductListController', function($scope, $http, $window) {
 $scope.gender = $window.location.search.substring(4);
 //alert($scope.gender);
 $scope.show=0;
 $scope.hide=0;
      $http.get('/koolApp/findItemsByGender/'+$scope.gender).
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