(function (ng) {
    var mod = ng.module("authorModule");

    mod.controller("authorsCtrl", ['$scope', '$state', '$stateParams', '$http', 'authorsContext', '$filter',
        function ($scope, $state, $stateParams, $http, context,$filter) {

            // inicialmente el listado de authors está vacio
            $scope.records = {};
            // carga las authors
            $http.get(context).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            // el controlador recibió un authorId ??
            // revisa los parámetros (ver el :authorId en la definición de la ruta)
            if ($stateParams.authorId !== null && $stateParams.authorId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.authorId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                            $scope.currentRecord.birthDate=$filter('date')($scope.currentRecord.birthDate, "yyyy-MM-dd");;
                        }, responseError);

                // el controlador no recibió un authorId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    birthDate: ' '/*Tipo String to convert to date*/,
                    required: true /*Tipo String to convert to Date*/,
                };

                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                
                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('authorsList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('authorsList');
                            }, responseError);
                }
                ;
            };

            this.deleteRecord = function (id) {

                return $http.delete(context + "/" + id)
                        .then(function () {
                            $state.reload('authorsList');
                        }, responseError);
            };

            
            // -----------------------------------------------------------------
            // Funciones para manejar los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);

})(window.angular);