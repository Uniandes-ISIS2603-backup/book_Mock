(function (ng) {
    var mod = ng.module("bookModule");
    mod.controller("booksAuthorCtrl", ['$scope', '$state', '$stateParams', '$http', 'booksContext', 'authorsContext', '$log',
        function ($scope, $state, $stateParams, $http, booksContext, authorsContext, $log) {

            // carga los authores del book $stateParams.bookId
            if ($stateParams.bookId !== null && $stateParams.bookId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.bookId;
                // obtiene el dato del recurso REST
                $log.warn('Get ' + booksContext + "/" + id + "/authors");
                $http.get(booksContext + "/" + id + "/authors")
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.bookAuthors = response.data;
                            $scope.selectedAuthors = Array.from($scope.bookAuthors);
                        }, responseError);
                // el controlador no recibió un bookId
            } else {
                showError(" El libro no existe");
                $scope.bookAuthors = {};
                $scope.selectedAuthors = []; // Contendra la lista de authores del libro en $stateParams.bookId
            }


            $scope.records = {}; // Contendra la lista de authores del libro en $stateParams.bookId
            $scope.allAuthors = [];
            // carga todos los authors
            $log.warn(authorsContext);

            $http.get(authorsContext).then(function (response) {
                $scope.records = response.data;
                var log = [];
                $log.warn(angular.forEach(Array.from($scope.records), function (v, k) {
                    this.push(k + ': ' + v);
                }, log));

                $scope.allAuthors = Array.from($scope.records);
            }, responseError);



            this.updateBookAuthors = function (selectedAuthors) {
                $log.warn('Put ' + booksContext + "/" + id + "/authors");
                var log = [];
                $log.warn(angular.forEach(Array.from(selectedAuthors), function (v, k) {
                    this.push(k + ': ' + v);
                }, log));
                $http.put(booksContext + "/" + id + "/authors", selectedAuthors).then(function (response) {

                    $state.go('bookAuthorsList');
                }, responseError);
            };
            $scope.disabled = undefined;
            $scope.searchEnabled = undefined;

            this.enable = function () {
                $scope.disabled = false;
            };

            this.disable = function () {
                $scope.disabled = true;
            };

            this.enableSearch = function () {
                $scope.searchEnabled = true;
            }

            this.disableSearch = function () {
                $scope.searchEnabled = false;
            }


            this.deleteBookAuthor = function (_id) {
                $log.warn('Delete: One author from book ' + booksContext + "/" + id + "/authors"+ "/" + _id);
                return $http.delete(booksContext + "/" + id + "/authors" + "/" + _id)
                        .then(function () {
                            $state.reload('bookAuthorsList');
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
        }
    ]);
})(window.angular);