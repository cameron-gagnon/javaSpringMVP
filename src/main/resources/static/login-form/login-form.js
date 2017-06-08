angular.module('mfaPrototype', [])
  .controller('LoginController', function($scope, $http) {
    $http.get('/tokenInfo').then(function(response){
        console.log(response);
        $scope.token = response.data;
    })

    var lc = $scope;
    lc.username = null;
    lc.password = null;
    lc.enrolledInMFA = "true";
    lc.requireMFA = "true";

    lc.login = function (){
        console.log("Logging in");
        var data = {"username": lc.username,
                    "password": lc.password,
                    "MFARequired": lc.requireMFA,
                    "MFAEnrolled": lc.enrolledInMFA};
        console.log(data);
        $http.post("/login", data).then(function(response){
            console.log("Response is: ");
            console.log(response);
            var data = response.data;
            if (data.success === "true"){
                console.log("Valid user")
                /* check which step we need to do, and proceed */
                if (data.step === "doMFA"){
                    console.log("Performing MFA step")
                } else if (data.step === "doMFAEnrollment"){
                    console.log("Performing MFA enrollment")
                } else {
                    $scope.error = "Invalid step to perform"
                }

            /* failure */
            } else {
                console.log("Error is: ");
                console.log(data.error);
                $scope.error = data.error;
            }
        })
    }
});