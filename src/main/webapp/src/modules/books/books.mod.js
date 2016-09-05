(function (ng) {
    var mod = ng.module("bookModule", ["ngMessages"]);

    mod.constant("booksContext", "api/books");

    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/books/';
            $urlRouterProvider.otherwise("/booksList");

            $stateProvider.state('books', {
                url: '/books',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.html'
                    }
                }
            }).state('booksList', {
                url: '/list',
                parent: 'books',
                views: {
                    'bookView': {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.list.html'
                    }
                }
            }).state('bookCreate', {
                url: '/create',
                parent: 'books',
                views: {
                    'bookView': {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.create.html'
                    }
                }
            }).state('bookEdit', {
                url: '/{bookId:int}/edit',
                param: { 'bookId' : null},
                parent: 'books',
                views: {
                    'bookView': {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.create.html'
                    },
                    'childsView': {
                        templateUrl: basePath + 'books.instance.html'
                    }
                }
            });
        }]);
})(window.angular);