angular.module('front-index',[]).controller('indexController', function($scope, $http) {
    const contextPath = 'http://localhost:8289/api/v1/products'

    $scope.loadProducts = function () {
        $http({
            url: contextPath,
            method: 'GET',
            params : {
                page: $scope.pageIndex
            }
        }).then(function (response) {
            $scope.productsPage = response.data;
        })
    }

    $scope.deleteProduct = function (id) {
        $http({
            url: contextPath + '/' + id,
            method: 'DELETE'
        }).then(function (response) {
            $scope.loadProducts()
        })
    }

    $scope.decreasePrice = function (p) {
        if (p.price - 1 > 0) {
            $http({
                url: contextPath,
                method: 'PUT',
                data: {
                    id: p.id,
                    title: p.title,
                    price: p.price - 1
                }
            }).then(function (response) {
                $scope.loadProducts()
            })
        }
    }

    $scope.increasePrice = function (p) {
        $http({
            url: contextPath,
            method: 'PUT',
            data: {
                id: p.id,
                title: p.title,
                price: p.price + 1
            }
        }).then(function (response) {
            $scope.loadProducts()
        })
    }

    $scope.loadProducts();
});