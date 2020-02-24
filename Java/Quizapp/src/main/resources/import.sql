insert into tbl_question (id, text) values (1, 'What is the capital of Indonesia?');

insert into tbl_answer (question_id, text, correct, id) values (1, 'Jakarta', 'true', 1);
insert into tbl_answer (question_id, text, correct, id) values (1, 'Manilla', 'false',  2);
insert into tbl_answer (question_id, text, correct, id) values (1, 'Tokyo', 'false', 3);
insert into tbl_answer (question_id, text, correct, id) values (1, 'Bangkok', 'false', 4);

insert into tbl_question (id, text) values (2, 'What is the tallest mountain peak in the world?');

insert into tbl_answer (question_id, text, correct, id) values (2, 'Moldoveanu', 'false', 5);
insert into tbl_answer (question_id, text, correct, id) values (2, 'Everest', 'true',  6);
insert into tbl_answer (question_id, text, correct, id) values (2, 'Mont Blanc', 'false', 7);
insert into tbl_answer (question_id, text, correct, id) values (2, 'Elbrus', 'false', 8);

insert into tbl_question (id, text) values (3, 'What is the deepest lake in the world?');

insert into tbl_answer (question_id, text, correct, id) values (3, 'The Red Lake', 'false', 9);
insert into tbl_answer (question_id, text, correct, id) values (3, 'Lake Superior', 'false',  10);
insert into tbl_answer (question_id, text, correct, id) values (3, 'The Caspian Sea', 'false', 11);
insert into tbl_answer (question_id, text, correct, id) values (3, 'Lake Baikal', 'true', 12);