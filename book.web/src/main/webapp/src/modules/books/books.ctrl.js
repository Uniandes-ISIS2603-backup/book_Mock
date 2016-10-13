(function (ng) {
    var mod = ng.module("bookModule");
    

    mod.controller("booksCtrl", ['$scope', '$state', '$stateParams', '$http', 'booksContext', 'editorialsContext','$filter',
        function ($scope, $state, $stateParams, $http, booksContext, editorialsContext,$filter) {

            // inicialmente el listado de books está vacio
            $scope.records = {};
            // carga las books
            $http.get(booksContext).then(function (response) {
                $scope.records = response.data;
            }, responseError);

            // el controlador recibió un bookId ??
            // revisa los parámetros (ver el :bookId en la definición de la ruta)
            if ($stateParams.bookId !== null && $stateParams.bookId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.bookId;
                // obtiene el dato del recurso REST
                $http.get(booksContext + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;                                           
                            $scope.currentRecord.publishingdate=$filter('date')($scope.currentRecord.publishingdate, "yyyy-MM-dd");;
                        }, responseError);

                // el controlador no recibió un bookId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                    image: '' /*Tipo String*/,
                    isbn: '' /*Tipo String*/,
                    publishingdate: '' /*Tipo String to convert to Date*/,
                    required: true /*Tipo String to convert to Date*/,
                    editorial: {} /*Objeto que representa instancia de Editorial*/
                };

                $scope.alerts = [];
            }

            $http.get(editorialsContext).then(function (response) {
                $scope.editorials = response.data;
            });
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;

                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(booksContext, currentRecord)
                            .then(function () {
                                // $http.post es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('booksList');
                            }, responseError);

                    // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    

                    // ejecuta PUT en el recurso REST
                    return $http.put(booksContext + "/" + currentRecord.id, currentRecord)
                            .then(function () {
                                // $http.put es una promesa
                                // cuando termine bien, cambie de estado
                                $state.go('booksList');
                            }, responseError);
                }
                ;
            };
            
            
            this.deleteRecord = function (id) {
                
                return $http.delete(booksContext + "/" + id)
                        .then(function () {
                            $state.reload('booksList');
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