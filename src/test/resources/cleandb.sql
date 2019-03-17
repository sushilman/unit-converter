DELETE FROM conversion_factor cascade;
DELETE FROM conversion_category cascade;

ALTER SEQUENCE conversion_category_id_seq RESTART WITH 1;
ALTER SEQUENCE conversion_factor_id_seq RESTART WITH 1;