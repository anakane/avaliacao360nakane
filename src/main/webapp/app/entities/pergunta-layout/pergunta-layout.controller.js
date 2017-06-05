(function() {
    'use strict';

    angular
        .module('avaliacao360ChioteApp')
        .controller('PerguntaLayoutController', PerguntaLayoutController);

    PerguntaLayoutController.$inject = ['$scope', '$state', 'PerguntaLayout'];

    function PerguntaLayoutController ($scope, $state, PerguntaLayout) {
        var vm = this;

        vm.perguntaLayouts = [];

        loadAll();

        function loadAll() {
            PerguntaLayout.query(function(result) {
                vm.perguntaLayouts = result;
                vm.searchQuery = null;
            });
        }
    }
})();
