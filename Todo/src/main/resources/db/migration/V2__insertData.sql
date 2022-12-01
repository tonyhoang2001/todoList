
insert into user(id, username, password, role)
values('user_1','tony','12345','admin');
insert into user(id, username, password, role)
values('user_2','stark','12345','user');
insert into user(id, username, password, role)
values('user_3','alex','12345','user');
insert into user(id, username, password, role)
values('user_4','falcon','12345','admin');
insert into user(id, username, password, role)
values('user_5','antony','12345','user');

INSERT INTO todo(id,name, description, user_id)
VALUES ('todo_1','go to gym', 'improve the body and health','user_1');
INSERT INTO todo(id,name, user_id)
VALUES ('todo_2','water the tree','user_2');
INSERT INTO todo(id,name, description, user_id)
VALUES ('todo_3','make some food', 'go to the kitchen and cooking','user_1');
INSERT INTO todo(id,name, description, user_id)
VALUES ('todo_4','go to company', 'to meet friends, peers','user_3');
INSERT INTO todo(id,name, description, user_id)
VALUES ('todo_5','feed the dog', 'make the dog full','user_4');
INSERT INTO todo(id,name, description, user_id)
VALUES ('todo_6','buy the shoe', 'avoid your feet dirt','user_3');


