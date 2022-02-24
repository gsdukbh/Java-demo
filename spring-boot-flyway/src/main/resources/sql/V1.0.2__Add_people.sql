create table if not exists `people`
(
  `id` int(11) primary key NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `age`  int(11)      NOT NULL
);