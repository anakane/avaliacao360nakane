(function() {
    'use strict';

    angular
        .module('avaliacao360ChioteApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('pergunta-layout', {
            parent: 'entity',
            url: '/pergunta-layout',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'avaliacao360ChioteApp.perguntaLayout.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pergunta-layout/pergunta-layouts.html',
                    controller: 'PerguntaLayoutController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('perguntaLayout');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('pergunta-layout-detail', {
            parent: 'entity',
            url: '/pergunta-layout/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'avaliacao360ChioteApp.perguntaLayout.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/pergunta-layout/pergunta-layout-detail.html',
                    controller: 'PerguntaLayoutDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('perguntaLayout');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PerguntaLayout', function($stateParams, PerguntaLayout) {
                    return PerguntaLayout.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'pergunta-layout',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('pergunta-layout-detail.edit', {
            parent: 'pergunta-layout-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pergunta-layout/pergunta-layout-dialog.html',
                    controller: 'PerguntaLayoutDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PerguntaLayout', function(PerguntaLayout) {
                            return PerguntaLayout.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pergunta-layout.new', {
            parent: 'pergunta-layout',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pergunta-layout/pergunta-layout-dialog.html',
                    controller: 'PerguntaLayoutDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                assunto: null,
                                texto: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('pergunta-layout', null, { reload: 'pergunta-layout' });
                }, function() {
                    $state.go('pergunta-layout');
                });
            }]
        })
        .state('pergunta-layout.edit', {
            parent: 'pergunta-layout',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pergunta-layout/pergunta-layout-dialog.html',
                    controller: 'PerguntaLayoutDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['PerguntaLayout', function(PerguntaLayout) {
                            return PerguntaLayout.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pergunta-layout', null, { reload: 'pergunta-layout' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('pergunta-layout.delete', {
            parent: 'pergunta-layout',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/pergunta-layout/pergunta-layout-delete-dialog.html',
                    controller: 'PerguntaLayoutDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['PerguntaLayout', function(PerguntaLayout) {
                            return PerguntaLayout.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('pergunta-layout', null, { reload: 'pergunta-layout' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
