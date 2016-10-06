(function (ng) {
    
    var mod = angular.module('authorModule',[]
    );
    mod.constant("authorsContext", "api/authors");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/authors/';
            $urlRouterProvider.otherwise("/authorsList");
     
            $stateProvider.state('authorsList', {
                url: '/authors',
                views: {
                    'mainView': {
                        controller: 'authorsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'authors.list.html'
                    }
                }
            }).state('authorCreate', {
                url: '/authors/create',
                views: {
                    'mainView': {
                        controller: 'authorsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'authors.create.html'
                    }
                }

            }).state('authorEdit', {
               url: '/authors/{authorId:int}/edit',
               //parent: 'authorsList',
                views: {
                    'mainView': {
                        controller: 'authorsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'authors.create.html'
                    }
                }
            });
        }]);
})(window.angular);