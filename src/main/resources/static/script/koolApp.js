angular.module('koolApp', [])
.controller('koolCtrl', function($scope, $http) {
$scope.test=null;
     $http.get('http://localhost:8080/koolApp/findSegments/').
        then(function(response) {
            $scope.Segments= response.data;
 		$scope.test=$scope.Segments[0]; 
         });
        
 $scope.OnSegmentChange = function(ID) {
  var id=$scope.test.segmentId;
      $http.get("http://localhost:8080/koolApp/findFamiliesBySegment/"+id).
            then(function (responses) {  
                $scope.Families = responses.data;   
          });   
         }  
      
});