(function (ng) {

    var mod = angular.module('editorialModule', []);

    mod.constant("editorialsContext", "api/editorials");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/editorials/';
            $urlRouterProvider.otherwise("/editorialsList");

            $stateProvider.state('editorialsList', {
                url: '/editorials',
                views: {
                    'mainView': {
                        controller: 'editorialsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editorials.list.html'
                    }
                }
            }).state('editorialCreate', {
                url: '/editorials/create',
                views: {
                    'mainView': {
                        controller: 'editorialsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editorials.create.html'
                    }
                }

            }).state('editorialEdit', {
                url: '/{editorialId:int}/edit',
                parent: 'editorialsList',
                views: {
                    'mainView': {
                        controller: 'editorialsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'editorials.create.html'
                    }
                }

            });
        }]);
})(window.angular);