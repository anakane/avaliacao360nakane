'use strict';

describe('Controller Tests', function() {

    describe('AvaliacaoControle Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockAvaliacaoControle, MockUser, MockPergunta;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockAvaliacaoControle = jasmine.createSpy('MockAvaliacaoControle');
            MockUser = jasmine.createSpy('MockUser');
            MockPergunta = jasmine.createSpy('MockPergunta');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'AvaliacaoControle': MockAvaliacaoControle,
                'User': MockUser,
                'Pergunta': MockPergunta
            };
            createController = function() {
                $injector.get('$controller')("AvaliacaoControleDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'avaliacao360ChioteApp:avaliacaoControleUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
