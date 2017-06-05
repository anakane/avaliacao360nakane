'use strict';

describe('Controller Tests', function() {

    describe('PerguntaLayout Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPerguntaLayout, MockAvaliacaoModelo;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPerguntaLayout = jasmine.createSpy('MockPerguntaLayout');
            MockAvaliacaoModelo = jasmine.createSpy('MockAvaliacaoModelo');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'PerguntaLayout': MockPerguntaLayout,
                'AvaliacaoModelo': MockAvaliacaoModelo
            };
            createController = function() {
                $injector.get('$controller')("PerguntaLayoutDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'avaliacao360ChioteApp:perguntaLayoutUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
