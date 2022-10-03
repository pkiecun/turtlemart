insert into product (product, deptid, department) values (
    'apple',
    100,
    'fruits'
),
(
    'banana',
    100,
    'fruits'
),
(
    'broccoli',
    101,
    'vegetables'
),
(
    'Celery',
    101,
    'vegetables'
);

insert into location (id, city, zipcode) values (1, 'Houston', 77003), (2, 'San Francisco', 94117),(3, 'New York City', 10001), (4, 'Los Angeles', 90001);

insert into balance (inventory_slot, balance, location, product) values (1, 10, 1, 1), (2, 20, 2, 1), (3, 30, 3, 1), (4, 40, 4, 1), (5, 50, 1, 2), (6,6,1,2), (7, 7, 2, 2), (8, 8, 3, 2), (9, 9, 4,2), (10, 11, 1, 3), (11,12, 2, 3), (12, 13, 3, 3), (13, 13, 4, 3), (14, 14, 1, 4), (15, 15, 2,4), (16, 16, 3, 4), (17, 17, 4, 4);
