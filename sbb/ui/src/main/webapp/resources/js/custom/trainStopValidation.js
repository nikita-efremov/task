$(document).ready(function() {
    $('#createStopForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            stationName: {
                message: 'Station name is not valid',
                validators: {
                    notEmpty: {
                        message: 'Station name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'Station must be more than 3 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'Station name can only consist of english alphabetical'
                    }
                }
            },
            date: {
                message: 'Departure date is not valid',
                validators: {
                    notEmpty: {
                        message: 'Departure date is required'
                    },
                    dateTime: {
                        format: 'dd-MM-yyyy HH:mm',
                        message: 'Departure date is not valid'
                    }
                }
            }

        }
    });
});
