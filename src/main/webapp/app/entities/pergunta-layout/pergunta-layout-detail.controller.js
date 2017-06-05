(function() {
    'use strict';

    angular
        .module('avaliacao360ChioteApp')
        .controller('PerguntaLayoutDetailController', PerguntaLayoutDetailController);

    PerguntaLayoutDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PerguntaLayout', 'AvaliacaoModelo'];

    function PerguntaLayoutDetailController($scope, $rootScope, $stateParams, previousState, entity, PerguntaLayout, AvaliacaoModelo) {
        var vm = this;

        vm.perguntaLayout = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('avaliacao360ChioteApp:perguntaLayoutUpdate', function(event, result) {
            vm.perguntaLayout = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
