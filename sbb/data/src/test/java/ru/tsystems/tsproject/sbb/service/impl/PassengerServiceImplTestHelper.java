package ru.tsystems.tsproject.sbb.service.impl;

import ru.tsystems.tsproject.sbb.entity.Station;
import ru.tsystems.tsproject.sbb.entity.Timetable;
import ru.tsystems.tsproject.sbb.entity.Train;

import java.util.Calendar;

/**
 * Helper class for PassengerServiceImplTest. It creates test data
 * @author  Nikita Efremov
 * @since   1.0
 */
public class PassengerServiceImplTestHelper {

    public static Train getAppropriateTrain(Station startStation, Station endStation) {
        Timetable appropriateTimetableStart = new Timetable();
        Calendar appropriateStartDate = Calendar.getInstance();
        appropriateTimetableStart.setStation(startStation);
        appropriateTimetableStart.setDate(appropriateStartDate.getTime());

        Timetable appropriateTimetableEnd = new Timetable();
        Calendar appropriateEndDate = Calendar.getInstance();
        appropriateEndDate.add(Calendar.MINUTE, 1);
        appropriateTimetableEnd.setStation(endStation);
        appropriateTimetableEnd.setDate(appropriateEndDate.getTime());

        Train appropriateTrain = new Train();
        appropriateTrain.setId(2);
        appropriateTrain.setNumber("455s");
        appropriateTrain.setTotalSeats(43);
        appropriateTrain.addTimetable(appropriateTimetableStart);
        appropriateTrain.addTimetable(appropriateTimetableEnd);
        return appropriateTrain;
    }

    public static Train getUnAppropriateTrainType1(Station startStation, Station endStation) {
        Timetable appropriateTimetableStart = new Timetable();
        Calendar appropriateStartDate = Calendar.getInstance();
        appropriateStartDate.add(Calendar.MINUTE, 1);
        appropriateTimetableStart.setStation(startStation);
        appropriateTimetableStart.setDate(appropriateStartDate.getTime());

        Timetable appropriateTimetableEnd = new Timetable();
        Calendar appropriateEndDate = Calendar.getInstance();
        appropriateTimetableEnd.setStation(endStation);
        appropriateTimetableEnd.setDate(appropriateEndDate.getTime());

        Train appropriateTrain = new Train();
        appropriateTrain.setId(5);
        appropriateTrain.setNumber("44s");
        appropriateTrain.setTotalSeats(439);
        appropriateTrain.addTimetable(appropriateTimetableStart);
        appropriateTrain.addTimetable(appropriateTimetableEnd);
        return appropriateTrain;
    }

    public static Train getUnAppropriateTrainType2(Station startStation, Station endStation) {

        Timetable appropriateTimetableEnd = new Timetable();
        Calendar appropriateEndDate = Calendar.getInstance();
        appropriateEndDate.add(Calendar.MINUTE, 1);
        appropriateTimetableEnd.setStation(endStation);
        appropriateTimetableEnd.setDate(appropriateEndDate.getTime());

        Train appropriateTrain = new Train();
        appropriateTrain.setId(25);
        appropriateTrain.setNumber("455u");
        appropriateTrain.setTotalSeats(3);
        appropriateTrain.addTimetable(appropriateTimetableEnd);
        return appropriateTrain;
    }

    public static Train getUnAppropriateTrainType3(Station startStation, Station endStation) {
        Timetable appropriateTimetableStart = new Timetable();
        Calendar appropriateStartDate = Calendar.getInstance();
        appropriateTimetableStart.setStation(startStation);
        appropriateTimetableStart.setDate(appropriateStartDate.getTime());

        Train appropriateTrain = new Train();
        appropriateTrain.setId(32);
        appropriateTrain.setNumber("55s");
        appropriateTrain.setTotalSeats(48);
        appropriateTrain.addTimetable(appropriateTimetableStart);
        return appropriateTrain;
    }
}
