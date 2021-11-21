--Payments
INSERT INTO payment(amount,account_Id) VALUES(200,1);
INSERT INTO payment(amount,account_Id) VALUES(100,2);
INSERT INTO payment(amount,account_Id) VALUES(20,2);
INSERT INTO payment(amount,account_Id) VALUES(10,3);
INSERT INTO payment(amount,account_Id) VALUES(25,1);
INSERT INTO payment(amount,account_Id) VALUES(50,2);
INSERT INTO payment(amount,account_Id) VALUES(150,4);
INSERT INTO payment(amount,account_Id) VALUES(350,1);
INSERT INTO payment(amount,account_Id) VALUES(180,2);

--Transactions
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (3752,'DEPOSITE',1);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (4457,'DEPOSITE',1);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (1568,'DEPOSITE',2);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (1988,'WITHDRAW',2);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (50,'DEPOSITE',3);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (156,'DEPOSITE',1);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (148,'WITHDRAW',3);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (1789,'WITHDRAW',2);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (200,'DEPOSITE',1);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (100,'DEPOSITE',4);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (200,'DEPOSITE',3);
INSERT INTO transaction (amount,type_Transaction,account_Id) VALUES (70,'WITHDRAW',1);


--Charges
INSERT INTO charge (amount,description,account_Id) VALUES (3752,'Pago de pizza',1);
INSERT INTO charge (amount,description,account_Id) VALUES (4457,'Pago de Luz',1);
INSERT INTO charge (amount,description,account_Id) VALUES (1568,'Pago de Agua',2);
INSERT INTO charge (amount,description,account_Id) VALUES (1988,'Pago de Internet',2);
INSERT INTO charge (amount,description,account_Id) VALUES (50,'Pago de voleto de avión',3);
INSERT INTO charge (amount,description,account_Id) VALUES (156,'Pago de hamburguesas',1);