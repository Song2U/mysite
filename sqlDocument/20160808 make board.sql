select * from USERS;
update users set email='ysi1246@sungkyul.ac.kr' where no=3;

desc board;

drop SEQUENCE seq_board;

create sequence seq_board
start with 1
increment by 1;