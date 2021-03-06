'use strict';

describe('Controller Tests', function() {

    describe('AvaliacaoModelo Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAvaliacaoModelo, MockEquipe, MockPerguntaLayout;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAvaliacaoModelo = jasmine.createSpy('MockAvaliacaoModelo');
            MockEquipe = jasmine.createSpy('MockEquipe');
            MockPerguntaLayout = jasmine.createSpy('MockPerguntaLayout');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'AvaliacaoModelo': MockAvaliacaoModelo,
                'Equipe': MockEquipe,
                'PerguntaLayout': MockPerguntaLayout
            };
            createController = function() {
                $injector.get('$controller')("AvaliacaoModeloDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'avaliacao360ChioteApp:avaliacaoModeloUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
