select * from USERS;
update users set email='ysi1246@sungkyul.ac.kr' where no=3;

select * from BOARD;
drop table board;
desc board;

drop SEQUENCE seq_board;

create sequence seq_board
start with 1
increment by 1;

delete BOARD;

insert into board values(seq_board.nextval, '테스트 게시글 입니다.', '테스트 Test', sysdate, 0, 1, 1, 1, 4);
insert into board values(seq_board.nextval, '이건 테스트에요', 'Test is 테스트', sysdate, 0, 1, 1, 1, 3);

select board.no, board.title, board.content, to_char(board.reg_date, 'yyyy-mm-dd pm hh:mi:ss'), board.VIEW_COUNT, USERS.NAME from BOARD, users where BOARD.USER_NO=USERS.NO order by BOARD.NO desc;

commit;