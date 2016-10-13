(function (ng) {
    var mod = ng.module("bookModule");

    mod.controller("reviewsCtrl", ['$scope', '$state', '$stateParams', '$http', 'booksContext',
        function ($scope, $state, $stateParams, $http, booksContext) {

            // inicialmente el listado de reviews está vacio
            $scope.reviewsContext = '/reviews';
            $scope.records = {};
            // carga las reviews del book
            $http.get(booksContext + "/" + $stateParams.bookId + $scope.reviewsContext).then(function (response) {
                $scope.records = response.data;
                console.log($scope.records)
            }, responseError);

            // el controlador recibió un reviewId ??
            // revisa los parámetros (ver el :reviewId en la definición de la ruta)
            if ($stateParams.reviewId !== null && $stateParams.reviewId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.reviewId;
                // obtiene el dato del recurso REST
                $http.get(booksContext + "/" + $stateParams.bookId + $scope.reviewsContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        }, responseError);

                // el controlador no recibió un reviewId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    source: '' /*Tipo String*/,
                    description: '' /*Tipo String*/,
                };

                $scope.alerts = [];
            }



            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(booksContext + "/" + $stateParams.bookId + $scope.reviewsContext, currentRecord)
                            .then(function () {
                                
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('reviewsList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {

                    // ejecuta PUT en el recurso REST
                    return $http.put(booksContext + "/" + $stateParams.bookId + $scope.reviewsContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('reviewsList');
                            }, responseError);
                }
                ;
            };

            this.deleteRecord = function (id) {
                return $http.delete(booksContext + "/" + $stateParams.bookId + $scope.reviewsContext + "/" + id)
                        .then(function () {
                            $state.reload('reviewsList');
                        }, responseError);
            };

            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


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