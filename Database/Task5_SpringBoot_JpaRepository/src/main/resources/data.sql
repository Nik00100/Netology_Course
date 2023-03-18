INSERT INTO CUSTOMERS(name, surname, age, phone_number)
VALUES ('Ivan', 'Ivanov', 28, '8(999)999-99-99'),
       ('Petr', 'Petrov', 20, '8(999)888-88-88'),
       ('Nikolay', 'Nikolaev', 38, '8(999)777-77-77'),
       ('Elena', 'Blinova', 22, '8(999)666-66-66'),
       ('Oleg', 'Olegov', 52, '8(999)555-55-55'),
       ('Alexey', 'Alekseev', 42, '8(999)444-44-44');

INSERT INTO ORDERS(customer_id, product_name, amount)
VALUES (1, 'telephone', 15000),
       (5, 'tablet', 50000),
       (2, 'telephone', 25000),
       (2, 'notebook', 65000),
       (4, 'telephone', 22000),
       (6, 'computer', 80000);