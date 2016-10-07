(function (ng) {

    var mod = angular.module('editorialModule', []);

    mod.constant("editorialsContext", "api/editorials");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/editorials/';
            $urlRouterProvider.otherwise("/editorialsList");

            $stateProvider.state('editorials', {
                url: '/editorials',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'editorialsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editorials.html'
                    }
                }
            }).state('editorialsList', {
                url: '/list',
                parent: 'editorials',
                views: {
                    'editorialsView': {
                        controller: 'editorialsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editorials.list.html'
                    }
                }
            }).state('editorialCreate', {
                url: '/create',
                parent: 'editorials',
                views: {
                    'editorialsView': {
                        controller: 'editorialsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editorials.create.html'
                    }
                }

            }).state('editorialEdit', {
                url: '/{editorialId:int}/edit',
                parent: 'editorials',
                views: {
                    'editorialsView': {
                        controller: 'editorialsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editorials.create.html'
                    }
                }

            });
        }]);
})(window.angular);