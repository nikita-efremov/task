$(document).ready(function() {
    $('#registerForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstName: {
                message: 'First name is not valid',
                validators: {
                    notEmpty: {
                        message: 'First name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: 'First name must be more than 2 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'First name can only consist of english alphabetical'
                    }
                }
            },
            lastName: {
                message: 'Last name is not valid',
                validators: {
                    notEmpty: {
                        message: 'Last name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 2,
                        max: 30,
                        message: 'Last name must be more than 2 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'Last name can only consist of english alphabetical'
                    }
                }
            },
            docNumber: {
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
            birthDate: {
                validators: {
                    notEmpty: {
                        message: 'The date of birth is required'
                    },
                    dateTime: {
                        format: 'dd-MM-yyyy',
                        message: 'The date of birth is not valid'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: 'The password and its confirm are not the same'
                    },
                    stringLength: {
                        min: 6,
                        msx: 16,
                        message: 'The password must have at least 6 and not more than 16 characters'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: 'Password can only consist of english alphabetical and number'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    notEmpty: {
                        message: 'The password confirm is required and cannot be empty'
                    },
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    },
                    stringLength: {
                        min: 6,
                        msx: 16,
                        message: 'The password confirm must have at least 6 and not more than 16 characters'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: 'Password confirm can only consist of english alphabetical and number'
                    }
                }
            }
        }
    });
});

