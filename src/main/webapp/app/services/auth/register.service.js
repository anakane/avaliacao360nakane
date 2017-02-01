(function () {
    'use strict';

    angular
        .module('avaliacao360NakaneApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
