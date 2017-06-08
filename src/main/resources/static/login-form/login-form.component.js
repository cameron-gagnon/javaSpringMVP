'use strict';

// Register `phoneDetail` component, along with its associated controller and template
angular.
  module('loginForm').
  component('loginForm', {
    templateUrl: 'login-form/login-form.template.html',
    controller: ['$scope', '$http',
      function LoginFormController($scope, $http) {
        var lf = this;
        $http.get('/tokenInfo').then(function(response){
            console.log(response);
            lf.token = response.data;
        })

        lf.username = null;
        lf.password = null;
        lf.enrolledInMFA = "true";
        lf.requireMFA = "true";

        lf.login = function (){
            console.log("Logging in");
            var data = {"username": lf.username,
                        "password": lf.password,
                        "MFARequired": lf.requireMFA,
                        "MFAEnrolled": lf.enrolledInMFA};
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
                        lf.error = "Invalid step to perform"
                    }

                /* failure */
                } else {
                    console.log("Error is: ");
                    console.log(data.error);
                    lf.error = data.error;
                }
            })
        }
    }]
  });