insert into seller_info(id, first_name, last_name) values (1, "Dusan", "Srbulovic");
insert into seller_info(id, first_name, last_name) values (2, "Dusan", "Srbulovic");

insert into payment_method(name, service_name, image_src) values ("paypal", "paypal-service", "https://www.paypalobjects.com/webstatic/en_US/i/buttons/checkout-logo-large.png");
insert into payment_method(name, service_name, image_src) values ("bitcoin", "bitcoin-service", "https://cdn.iconscout.com/icon/free/png-256/bitcoin-390-920575.png");
insert into seller_info_payment_methods (seller_info_id, payment_methods_name) values (1 , "paypal");
insert into seller_info_payment_methods (seller_info_id, payment_methods_name) values (1 , "bitcoin");
insert into seller_info_payment_methods (seller_info_id, payment_methods_name) values (2 , "paypal");