angular.module('koolAppSearch', [])
.controller('ProductListController', function($scope, $http, $window) {
 $scope.commodityId = $window.location.search.substring(4);
 $scope.show=0;
 $scope.hide=0;
      $http.get('/koolApp/findItemsByCommodity/'+$scope.commodityId).
        then(function(response) {
            $scope.comodity= response.data;
            $scope.show=1;
            if(response.data=="")
            {
                 	$scope.hide=1;
            }
            
 			//alert('id'+ response.data);
         });
         
             
 
    
});