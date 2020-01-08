angular.module('koolApp', [])
.controller('koolCtrl', function($scope, $http) {
$scope.test=null;
$scope.ClassId1=null;
$scope.show=0;
//$scope.selectedSegment='';
 //    $http.get('/koolApp/findSegments/').
  //      then(function(response) {
   //         $scope.Segments= response.data;
 //			$scope.test=$scope.Segments[0]; 
  //       });

$http.get("/koolApp/findFamiliesBySegment/53000000").
            then(function (responses) {  
                $scope.Families = responses.data;
                   
          });
 
// $scope.OnSegmentChange = function () {
//alert($scope.Segments[0].segmentId);
//$http.get("/koolApp/findFamiliesBySegment/53000000").
  //          then(function (responses) {  
   //             $scope.Families = responses.data;   
    //      });
//}

 $scope.OnFamilyChange = function(Families) {
// alert("family Id"+$scope.SelectedFamilyId);
         $http.get("/koolApp/findClassesByFamily/"+$scope.SelectedFamilyId).
            then(function (responses) {  
                $scope.Classes = responses.data;
				$scope.ClassId1=$scope.Classes[0].classId;
				$scope.show=1;
          });   
         }  
  
 $scope.OnClassChange = function() {
  alert($scope.SelectedClassId);
   $http.get("/koolApp/findCommoditiesByClass/"+$scope.SelectedClassId).
            then(function (responses) {  
               $scope.comodity = responses.data;
           //    alert(responses.data);
                //alert($scope.comodity[0].styleItemNumber);   
          });   
         }  
      

      
});