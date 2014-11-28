delete from ticket where id > 0;
delete from timetable where id > 0;
delete from passenger where id > 0;
delete from train where id > 0;
delete from station where id > 0;



INSERT INTO `station` (`id`,`name`) VALUES (1,'Sochi');
INSERT INTO `station` (`id`,`name`) VALUES (2,'Ufa');
INSERT INTO `station` (`id`,`name`) VALUES (3,'Orel');
INSERT INTO `station` (`id`,`name`) VALUES (4,'Pskov');
INSERT INTO `station` (`id`,`name`) VALUES (5,'Kazan');



INSERT INTO `passenger` (`id`,`first_name`,`last_name`,`birthdate`,`doc_number`,`password`) VALUES (1,'Beatrix','Kiddo','2014-11-11','3333333333','b427ebd39c845eb5417b7f7aaf1f9724');
INSERT INTO `passenger` (`id`,`first_name`,`last_name`,`birthdate`,`doc_number`,`password`) VALUES (2,'Alyx','Vance','2004-11-16','4444444444','b427ebd39c845eb5417b7f7aaf1f9724');
INSERT INTO `passenger` (`id`,`first_name`,`last_name`,`birthdate`,`doc_number`,`password`) VALUES (3,'Maria','Brink','1977-12-17','5555555555','b427ebd39c845eb5417b7f7aaf1f9724');



INSERT INTO `train` (`id`,`number`,`seats`,`total_seats`) VALUES (1,'111q',23,25);
INSERT INTO `train` (`id`,`number`,`seats`,`total_seats`) VALUES (2,'222w',1,2);
INSERT INTO `train` (`id`,`number`,`seats`,`total_seats`) VALUES (3,'333e',119,120);



INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (1,1,3,'2014-12-01 16:00:00');  /*must be in the past*/
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (2,1,1,'2014-12-02 10:05:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (3,1,2,'2014-12-04 23:32:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (4,1,5,'2014-12-05 18:05:00');

INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (5,2,4,'2014-12-03 13:55:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (6,2,1,'2014-12-05 04:30:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (7,2,3,'2014-12-06 22:25:00');

INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (8,3,3,'2014-12-01 21:05:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (9,3,1,'2014-12-02 23:50:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (10,3,4,'2014-12-03 13:30:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (11,3,2,'2014-12-04 16:10:00');
INSERT INTO `timetable` (`id`,`train_id`,`station_id`,`date`) VALUES (12,3,5,'2014-12-06 12:00:00');


INSERT INTO `ticket` (`id`,`passenger_id`,`train_id`,`ticket_number`) VALUES (1,2,1,27000008);
INSERT INTO `ticket` (`id`,`passenger_id`,`train_id`,`ticket_number`) VALUES (2,3,1,27000009);

INSERT INTO `ticket` (`id`,`passenger_id`,`train_id`,`ticket_number`) VALUES (3,2,2,27000010);

INSERT INTO `ticket` (`id`,`passenger_id`,`train_id`,`ticket_number`) VALUES (4,1,3,27000011);