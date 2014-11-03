
$(document).ready(function() {
    $('#trainSearchForm').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            stationStartName: {
                message: 'Station name is not valid',
                validators: {
                    notEmpty: {
                        message: 'Station start name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'Station must be more than 3 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'Station name can only consist of english alphabetical'
                    },
                    different: {
                        field: 'stationEndName',
                        message: 'The start station name and end station name cannot be the same as each other'
                    }
                }
            },
            stationEndName: {
                message: 'Station name is not valid',
                validators: {
                    notEmpty: {
                        message: 'Station end name is required and cannot be empty'
                    },
                    stringLength: {
                        min: 3,
                        max: 30,
                        message: 'Station name must be more than 3 and less than 30 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z]+$/,
                        message: 'Station name can only consist of english alphabetical'
                    },
                    different: {
                        field: 'stationStartName',
                        message: 'The start station name and end station name cannot be the same as each other'
                    }
                }
            },
            startDate: {
                message: 'Start date is not valid',
                validators: {
                    notEmpty: {
                        message: 'Start date is required'
                    },
                    dateTime: {
                        format: 'dd-MM-yyyy HH:mm',
                        message: 'Start date is not valid'
                    },
                    different: {
                        field: 'endDate',
                        message: 'Start date and end date cannot be the same as each other'
                    }
                }
            },
            endDate: {
                message: 'End date is not valid',
                validators: {
                    notEmpty: {
                        message: 'End date is required'
                    },
                    dateTime: {
                        format: 'dd-MM-yyyy HH:mm',
                        message: 'End date is not valid'
                    },
                    different: {
                        field: 'startDate',
                        message: 'Start date and end date cannot be the same as each other'
                    }
                }
            }
        }
    });
});