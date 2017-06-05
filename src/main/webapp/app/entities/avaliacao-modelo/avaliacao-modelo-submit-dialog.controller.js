(function() {
    'use strict';

    angular
        .module('avaliacao360ChioteApp')
        .controller('AvaliacaoModeloSubmitController',AvaliacaoModeloSubmitController);

    AvaliacaoModeloSubmitController.$inject = ['$log','$uibModalInstance', 'entity', 'AvaliacaoModelo'];

    function AvaliacaoModeloSubmitController($log ,$uibModalInstance, entity, AvaliacaoModelo) {
        var vm = this;

        vm.avaliacaoModelo = entity;
        vm.clear = clear;
        vm.confirmSubmit = confirmSubmit;

        //console.info('PASSEI AQUI %j' , vm.avaliacaoModelo);
        $log.info('PASSEI AQUI :: entity :: %j' , entity);

        // recuperar da equipe os membros e fazer o for excluindo vc 

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmSubmit (id) {

            console.log('confirmSubmit', id);

            AvaliacaoModelo.submit(
                id,
                 function () {
                    alert('sucesso')
                });
            

            // AvaliacaoModelo.save({id: id},
            //     function () {
            //         alert('sucesso')
            //     });
            // AvaliacaoControle.save({id: id},
            //     function(){
            //         $uibModalInstance.close(true); 
            //         alert('sucesso');    
            //     }
            // )
        }
    }
})();
