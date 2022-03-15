-- insert into USER(user_id,email,nickname) values (1,'@com','11');

insert into USER(user_id,email,nickname) values (1,'@com','11');
insert into Post(post_id,user_id,context) values (3,1,'첫 포스팅');
insert into Post(post_id,user_id,context) values (4,1,'첫 포스팅');

insert into Comment(comment_id,post_id,user_id,context) values (5,3,1,'개굳 개굳');
insert into Comment(comment_id,post_id,user_id,context) values (6,3,1,'개굳 개굳2');
insert into Comment(comment_id,post_id,user_id,context) values (7,3,1,'개굳 개굳3');

