angular.module('koolApp', [])
.controller('koolCtrl', function($scope, $http) {
$scope.OnCategory = function() {
$scope.show=0;
$http.get("/koolApp/findFamiliesBySegment/53000000").
            then(function (responses) {  
                $scope.Families = responses.data;
                   
          });
}
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
      

      
});