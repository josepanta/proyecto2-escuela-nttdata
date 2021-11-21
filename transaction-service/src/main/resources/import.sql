--Payments
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(200,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',1);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(100,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',2);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(20,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',2);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(50,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',2);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(25,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',1);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(10,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',3);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(150,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',4);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(350,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',1);
INSERT INTO payment(amount,date_create,date_update,product_Id) VALUES(180,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69',2);

--Transactions
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (3752,'2012-09-17 18:47:52.69','DEPOSITE',1);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (4457,'2012-09-17 18:47:52.69','DEPOSITE',1);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (1568,'2012-09-17 18:47:52.69','DEPOSITE',2);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (1988,'2012-09-17 18:47:52.69','WITHDRAW',2);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (50,'2012-09-17 18:47:52.69','DEPOSITE',3);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (156,'2012-09-17 18:47:52.69','DEPOSITE',1);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (148,'2012-09-17 18:47:52.69','WITHDRAW',3);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (1789,'2012-09-17 18:47:52.69','WITHDRAW',2);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (200,'2012-09-17 18:47:52.69','DEPOSITE',1);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (100,'2012-09-17 18:47:52.69','DEPOSITE',4);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (200,'2012-09-17 18:47:52.69','DEPOSITE',3);
INSERT INTO transaction (amount,date_time,type_Transaction,product_Id) VALUES (70,'2012-09-17 18:47:52.69','WITHDRAW',1);


--Charges
INSERT INTO charge (amount,date_create,date_update,description,product_Id) VALUES (3752,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69','Pago de pizza',1);
INSERT INTO charge (amount,date_create,date_update,description,product_Id) VALUES (4457,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69','Pago de Luz',1);
INSERT INTO charge (amount,date_create,date_update,description,product_Id) VALUES (1568,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69','Pago de Agua',2);
INSERT INTO charge (amount,date_create,date_update,description,product_Id) VALUES (1988,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69','Pago de Internet',2);
INSERT INTO charge (amount,date_create,date_update,description,product_Id) VALUES (50,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69','Pago de voleto de avión',3);
INSERT INTO charge (amount,date_create,date_update,description,product_Id) VALUES (156,'2012-09-17 18:47:52.69','2012-09-17 18:47:52.69','Pago de hamburguesas',1);