(function() {
    'use strict';

    angular
        .module('avaliacao360ChioteApp')
        .controller('PerguntaDetailController', PerguntaDetailController);

    PerguntaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Pergunta', 'AvaliacaoControle'];

    function PerguntaDetailController($scope, $rootScope, $stateParams, previousState, entity, Pergunta, AvaliacaoControle) {
        var vm = this;

        vm.pergunta = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('avaliacao360ChioteApp:perguntaUpdate', function(event, result) {
            vm.pergunta = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
