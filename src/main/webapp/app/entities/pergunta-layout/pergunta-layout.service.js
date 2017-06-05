(function() {
    'use strict';
    angular
        .module('avaliacao360ChioteApp')
        .factory('PerguntaLayout', PerguntaLayout);

    PerguntaLayout.$inject = ['$resource'];

    function PerguntaLayout ($resource) {
        var resourceUrl =  'api/pergunta-layouts/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
