use javaweb;
ALTER  TABLE  t_student DROP stuid;
ALTER  TABLE  t_student ADD stuid mediumint(11) PRIMARY KEY NOT NULL AUTO_INCREMENT FIRST;