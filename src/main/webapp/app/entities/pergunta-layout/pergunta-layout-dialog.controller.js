(function() {
    'use strict';

    angular
        .module('avaliacao360ChioteApp')
        .controller('PerguntaLayoutDialogController', PerguntaLayoutDialogController);

    PerguntaLayoutDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'PerguntaLayout', 'AvaliacaoModelo'];

    function PerguntaLayoutDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, PerguntaLayout, AvaliacaoModelo) {
        var vm = this;

        vm.perguntaLayout = entity;
        vm.clear = clear;
        vm.save = save;
        vm.avaliacaomodelos = AvaliacaoModelo.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.perguntaLayout.id !== null) {
                PerguntaLayout.update(vm.perguntaLayout, onSaveSuccess, onSaveError);
            } else {
                PerguntaLayout.save(vm.perguntaLayout, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('avaliacao360ChioteApp:perguntaLayoutUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
