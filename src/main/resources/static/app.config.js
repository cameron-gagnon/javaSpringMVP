'use strict';

angular.
  module('mfaPrototype').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/login', {
          template: '<login-form></login-form>'
        }).
        otherwise('/login');
    }
  ]);
