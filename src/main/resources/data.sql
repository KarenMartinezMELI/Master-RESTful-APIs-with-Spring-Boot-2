--See in console to view order of values, otherwise define it explicity
insert into `users` (`id`,`user_name`,`first_name`,`last_name`,`email`,`role`,`ssn`)
values (101,'kreddy', 'Kaylan', 'Reddy', 'kreddy@stacksimplify.com', 'admin', 'ssn101');

insert into `users` (`id`,`user_name`,`first_name`,`last_name`,`email`,`role`,`ssn`)
values (102,'gwiser','Greg', 'Wiser', 'gwiser@stacksimplify.com',  'admin', 'ssn102');

insert into `users` (`id`,`user_name`,`first_name`,`last_name`,`email`,`role`,`ssn`)
values (103,'dmark','David', 'Mark',  'dmark@stacksimplify.com', 'admin', 'ssn103');

insert into `users` (`id`,`user_name`,`first_name`,`last_name`,`email`,`role`,`ssn`)
values (104,'kmartinez', 'Karen', 'Martinez', 'karen@stacksimplify.com','admin', 'ssn104');

insert into `orders` (`id`,`description`,`user_id`)
values (2001, 'order11', 101);

insert into `orders` (`id`,`description`,`user_id`)
values (2002, 'order12', 101);

insert into `orders` (`id`,`description`,`user_id`)
values (2003, 'order13', 101);

insert into `orders` (`id`,`description`,`user_id`)
values (2004, 'order21', 102);

insert into `orders` (`id`,`description`,`user_id`)
values (2005, 'order22', 102);

insert into `orders` (`id`,`description`,`user_id`)
values (2006, 'order31', 103);