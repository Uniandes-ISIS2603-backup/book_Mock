(function (ng) {
    var mod = ng.module("bookModule", ["ngMessages"]);

    mod.constant("booksContext", "api/books");
 
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/books/';
            $urlRouterProvider.otherwise("/booksList");
     
            $stateProvider.state('booksList', {
                url: '/books',
                views: {
                    'mainView': {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.list.html'
                    }
                }
            }).state('bookCreate', {
                url: '/books/create',
                views: {
                    'mainView' : {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.create.html'
                    }
                }

            }).state('bookEdit', {
                url: '/books/:bookId',
                param: {
                    bookId: null
                },
                views: {
                    'mainView': {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.create.html'
                    },
                    'bottomView': {
                        controller: 'reviewsCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.reviews.html'
                    }
                    }               
            });
        }]);
})(window.angular);