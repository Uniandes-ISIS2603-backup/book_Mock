(function (ng) {

    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        'ui.select',
        'ngSanitize',
        // Internal modules dependencies
        'authorModule',
        'editorialModule',
        'reviewModule',
        'bookModule'
    ]);
    app.directive('datePicker', [function () {
        return {
            scope: {
                model: '=',
                name: '@'
            },
            restrict: 'E',
            templateUrl: 'src/utils/datepicker.tpl.html',
            controller: ['$scope', function ($scope) {
                $scope.today = function () {
                    $scope.value = new Date();
                };

                $scope.clear = function () {
                    $scope.value = null;
                };

                $scope.open = function ($event) {
                    $event.preventDefault();
                    $event.stopPropagation();

                    $scope.opened = true;
                };
            }]
        };
    }]);
    

})(window.angular);



