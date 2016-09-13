(function (ng) {
    var mod = ng.module("datePickerModule",[]);

    mod.controller("datePickerCtrl", ['$scope',
        function ($scope) {

            // -----------------------------------------------------------------
            // Funciones para manejar las fechas

            $scope.popup = {
                opened: false
            };
            $scope.dateOptions = {
                dateDisabled: disabled,
                formatYear: 'yy',
                maxDate: new Date(2020, 5, 22),
                minDate: new Date(),
                startingDay: 1
            };

            $scope.today = function () {
                $scope.dt = new Date();
            };
            $scope.today();

            $scope.clear = function () {
                $scope.dt = null;
            };
            $scope.setDate = function (year, month, day) {
                $scope.dt = new Date(year, month, day);
            };

            $scope.open = function ($event) {
                 $event.preventDefault();
                 $event.stopPropagation();
                $scope.popup.opened = true;
            };

            function disabled(data) {
                var date = data.date,
                        mode = data.mode;
                return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
            }


        }]);

})(window.angular);