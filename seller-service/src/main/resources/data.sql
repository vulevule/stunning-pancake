insert into seller_info(id, first_name, last_name) values (1, "Dusan", "Srbulovic");
insert into payment_method(name, service_name, image_src) values ("paypal", "paypal-service", "https://www.paypalobjects.com/webstatic/en_US/i/buttons/checkout-logo-large.png");
insert into seller_info_payment_methods (seller_info_id, payment_methods_name) values (1 , "paypal");