INSERT INTO BANK.ACCOUNT (ACCOUNTID, VERSION, ACCOUNTNUMBER, BALANCE)
VALUES
(1, 0, '1358270981', 10950),
(2, 0, '7461839470', 2050),
(3, 0, '8570314738', 2720),
(4, 0, '4576231651', 4210),
(5, 0, '3479562329', 5250);

INSERT INTO BANK.TRANSACTION (ID, SOURCEACCOUNTID, DESTINATIONACCOUNTID, AMOUNT, TRANSACTIONTIME)
VALUES
(1, 1, 2, 50, '2022-09-17 11:26:01.232000'),
(2, 1, 5, 180, '2022-09-17 11:26:01.232000'),
(3, 3, 4, 210, '2022-09-17 11:26:01.232000'),
(4, 3, 5, 70, '2022-09-17 11:26:01.232000');