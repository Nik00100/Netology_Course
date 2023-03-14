select product_name
from ORDERS O
         join CUSTOMERS C on O.customer_id = C.id
where lower(C.name) = lower(:name);