angular.module('koolApp', [])
.controller('koolCtrl', function($scope, $http) {
$scope.test=null;
$scope.ClassId1=null;
     $http.get('http://localhost:8080/koolApp/findSegments/').
        then(function(response) {
            $scope.Segments= response.data;
 			$scope.test=$scope.Segments[0]; 
         });
        
 $scope.OnSegmentChange = function(ID) {
     $http.get("http://localhost:8080/koolApp/findFamiliesBySegment/"+$scope.Segments).
            then(function (responses) {  
                $scope.Families = responses.data;   
          });   
         }  
 
 $scope.OnFamilyChange = function() {
  //var familyid=$scope.Families[1].familyId;
  //alert($scope.Family);
       $http.get("http://localhost:8080/koolApp/findClassByFamily/"+$scope.Family).
            then(function (responses) {  
                $scope.Classes = responses.data;
				$scope.ClassId1=$scope.Classes[0].classId;
				
          });   
         }  
 
 $scope.OnClassChange = function() {
   alert($scope.Classes);   
  //alert('hello'+$scope.Classes[0].classId);
      $http.get("http://localhost:8080/koolApp/findCommoditiesByClass/"+$scope.Classes).
            then(function (responses) {  
                $scope.comodity = responses.data;
                alert($scope.comodity[0].styleItemNumber);   
          });   
         }  
      
});