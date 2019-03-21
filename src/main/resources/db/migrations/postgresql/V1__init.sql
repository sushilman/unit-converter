CREATE TABLE conversion_category (
    id BIGSERIAL NOT NULL,
    category CHARACTER VARYING(255) NOT NULL,
    base_unit CHARACTER VARYING(255) NOT NULL
);

ALTER TABLE ONLY conversion_category ADD CONSTRAINT conversion_category_pkey PRIMARY KEY(id);
ALTER TABLE ONLY conversion_category ADD CONSTRAINT conversion_category_unit_type_unique UNIQUE(category);

CREATE TABLE conversion_factor (
    id BIGSERIAL NOT NULL,
    conversion_category_id BIGSERIAL NOT NULL,
    target_unit CHARACTER VARYING(255) NOT NULL,
    factor DOUBLE PRECISION NOT NULL
);

ALTER TABLE ONLY conversion_factor ADD CONSTRAINT conversion_factor_pkey PRIMARY KEY(id);
ALTER TABLE ONLY conversion_factor ADD CONSTRAINT fk_conversion_factor_target_unit FOREIGN KEY(conversion_category_id)
REFERENCES conversion_factor(id);
