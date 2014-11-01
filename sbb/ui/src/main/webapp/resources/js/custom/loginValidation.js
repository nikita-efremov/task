
$(document).ready(function() {
    $('#loginForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            login: {
                message: 'The login is not valid',
                validators: {
                    notEmpty: {
                        message: 'The username is required and cannot be empty'
                    },
                    stringLength: {
                        min: 5,
                        max: 30,
                        message: 'The username must be more than 5 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: 'The username can only consist of alphabetical and number'
                    },
                    different: {
                        field: 'password',
                        message: 'The username and password cannot be the same as each other'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    different: {
                        field: 'login',
                        message: 'The password cannot be the same as username'
                    },
                    stringLength: {
                        min: 6,
                        message: 'The password must have at least 6 characters'
                    }
                }
            },
            Document_number: {
                message: 'Document number is not valid',
                validators: {
                    notEmpty: {
                        message: 'Document number is required and cannot be empty'
                    },
                    stringLength: {
                        min: 10,
                        max: 10,
                        message: 'Document number must be 10 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: 'Last name can only consist of english alphabetical and number'
                    }
                }
            },
            j_username: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: 'The username is required and cannot be empty'
                    },
                    stringLength: {
                        min: 5,
                        max: 10,
                        message: 'The username must be more than 5 and less than 10 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: 'The username can only consist of alphabetical and number'
                    },
                    different: {
                        field: 'j_password',
                        message: 'The username and password cannot be the same as each other'
                    }
                }
            },
            j_password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    different: {
                        field: 'j_username',
                        message: 'The password cannot be the same as username'
                    },
                    stringLength: {
                        min: 6,
                        message: 'The password must have at least 6 characters'
                    }
                }
            }
        }
    });
});
