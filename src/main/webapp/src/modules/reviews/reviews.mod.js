(function (ng) {
    
    var mod = angular.module('reviewModule',[]
    );
    mod.constant("reviewsContext", "/reviews");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/reviews/';
            $urlRouterProvider.otherwise("/reviewsList");
     
            $stateProvider.state('reviewsList', {
                url: '/reviews',
                parent: 'bookEdit',
                views: {
                    'bookInstanceView': {
                        controller: 'reviewsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reviews.list.html'
                    }
                }
            }).state('reviewCreate', {
                url: '/reviews/create',
                parent: 'bookEdit',
                views: {
                    'bookInstanceView': {
                        controller: 'reviewsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'reviews.create.html'
                    }
                }

            });
        }]);
})(window.angular);