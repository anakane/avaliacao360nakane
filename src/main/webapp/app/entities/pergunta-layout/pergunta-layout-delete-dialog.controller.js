(function() {
    'use strict';

    angular
        .module('avaliacao360ChioteApp')
        .controller('PerguntaLayoutDeleteController',PerguntaLayoutDeleteController);

    PerguntaLayoutDeleteController.$inject = ['$uibModalInstance', 'entity', 'PerguntaLayout'];

    function PerguntaLayoutDeleteController($uibModalInstance, entity, PerguntaLayout) {
        var vm = this;

        vm.perguntaLayout = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            PerguntaLayout.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
