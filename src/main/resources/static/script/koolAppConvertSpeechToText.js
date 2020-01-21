angular.module('koolAppProdbyGender', [])
.controller('ProductListController', function($scope, $http, $window) {
 $scope.show=0;
 $scope.hide=0;
      $http.get('/speechToText/convertSpeechToText').
        then(function(response) {
            $scope.brand= response.data.skuItemList;
            $scope.show=1;
            alert(response.data.skuItemList);
            if(response.data.skuItemList=="")
            {
                 	$scope.hide=1;
            }
            
 			//alert('id'+ response.data);
         });
         
             
 
    
});