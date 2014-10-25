$(document).ready(function() {
    $('#purchaseForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            Train_number: {
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
            }
        }
    });
});
