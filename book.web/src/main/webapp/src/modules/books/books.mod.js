(function (ng) {
    var mod = ng.module("bookModule", ["ngMessages",'ui.bootstrap']);

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
                param: {'bookId': null},
                parent: 'books',
                views: {
                    'bookView': {
                        controller: 'booksCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'books.create.html'
                    },
                    'childView': {
                        templateUrl: basePath + 'books.instance.html'
                    }
                }
            }).state('bookAuthorsList', {
                url: '/authors/list',             
                parent: 'bookEdit',
                views: {                 
                    'bookInstanceView': {
                        controller: 'booksAuthorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'booksauthors/' + 'booksAuthor.list.html'
                    }
                }
            }).state('bookAuthorEdit', {
                url: '/authors/edit',             
                parent: 'bookEdit',
                views: {                 
                    'bookInstanceView': {
                        controller: 'booksAuthorCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'booksauthors/' + 'booksAuthor.edit.html'
                    }
                }
            });
        }]);
})(window.angular);
