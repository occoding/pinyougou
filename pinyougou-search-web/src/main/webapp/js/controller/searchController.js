app.controller("searchControll", function ($scope, searchService) {
    $scope.search = function () {
        searchService.search($scope.searchMap).success(
            function (response) {
                $scope.resultMap = response;//搜索返回的结果
            })
    }
    $scope.searchMap = {'keywords': '', 'category': '', 'brand': '', 'spec': {}};//搜索对象
//添加搜索项
    $scope.addSearchItem = function (key, value) {
        if (key == 'category' || key == 'brand') {//如果点击的是分类或者是品牌
            $scope.searchMap[key] = value;
        } else {
            $scope.searchMap.spec[key] = value;
        }
        $scope.search();//执行搜索
    }

    //移除复合搜索条件
    $scope.removeSearchItem = function (key) {
        if (key == "category" || key == "brand") {//如果是分类或品牌
            $scope.searchMap[key] = "";
        } else {//否则是规格
            delete $scope.searchMap.spec[key];//移除此属性
        }
        $scope.search();//执行搜索
    }
});