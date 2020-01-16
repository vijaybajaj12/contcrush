angular.module('koolApp', [])
.controller('koolCtrl', function($scope, $http,$window) {
$scope.show=0;
$http.get("/koolApp/findFamiliesBySegment/53000000").
            then(function (responses) {  
                $scope.Families = responses.data;
                   
          });

 $scope.OnFamilyChange = function(Families) {
        $http.get("/koolApp/findClassesByFamily/"+$scope.SelectedFamilyId).
            then(function (responses) {  
                $scope.Classes = responses.data;
				$scope.show=1;
          });   
         }  
  
 $scope.OnClassChange = function() {
     $http.get("/koolApp/findCommoditiesByClass/"+$scope.SelectedClassId).
            then(function (responses) {  
               $scope.comodity = responses.data;
          });   
         }  
      
$scope.searchProduct = function (value) {
       // alert(value);
        var url = "/koolAppSearchProd?text="+value;
         //$window.location.href = url;
         $window.open(url, "blank");
    };

$scope.searchProductbyCamera = function (value) {
       // alert(value);
        var url = "/uploadImage";
         //$window.location.href = url;
         $window.open(url, "blank",width=420);
    };


   
      
});