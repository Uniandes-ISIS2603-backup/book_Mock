(function (ng) {

    var mod = angular.module('authorModule', []
            );
    mod.constant("authorsContext", "api/authors");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/authors/';
            $urlRouterProvider.otherwise("/authorsList");

            $stateProvider.state('authors', {
                url: '/authors',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'authorsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'authors.html'
                    }
                }
            }).state('authorsList', {
                url: '/list',
                parent: 'authors',
                views: {
                    'authorView': {
                        controller: 'authorsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'authors.list.html'
                    }
                }
            }).state('authorCreate', {
                url: '/create',
                parent: 'authors',
                views: {
                    'authorView': {
                        controller: 'authorsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'authors.create.html'
                    }
                }

            }).state('authorEdit', {
                url: '/{authorId:int}/edit',
                parent:'authors',
                param: {'authorId': null},
                views: {
                    'authorView': {
                        controller: 'authorsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'authors.create.html'
                    }
                }
            });
        }]);
})(window.angular);