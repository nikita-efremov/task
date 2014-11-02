$(document).ready(function() {
    $('#createTrainForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            number: {
                message: 'Train number is not valid',
                validators: {
                    notEmpty: {
                        message: 'Train number is required and cannot be empty'
                    },
                    stringLength: {
                        min: 3,
                        max: 5,
                        message: 'Train number must be more than 3 and less than 5 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9]+$/,
                        message: 'Station name can only consist of english alphabetical and number'
                    }
                }
            },
            totalSeats: {
                message: 'Total seats is not valid',
                validators: {
                    integer: {
                        message: 'The value is not numeric'

                    },
                    notEmpty: {
                        message: 'Total seats is required and cannot be empty'
                    },
                    between: {
                        min: 1,
                        max: 999,
                        message: 'Total seats must be between 1 and 999'
                    }
                }
            }
        }
    });
});
