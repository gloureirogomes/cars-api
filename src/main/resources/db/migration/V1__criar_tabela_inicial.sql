CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE cars (
car_id uuid NOT NULL DEFAULT uuid_generate_v4(),
model VARCHAR(50) NOT NULL,
brand VARCHAR(100) NOT NULL,
color VARCHAR(50) NOT NULL,
year INTEGER NOT NULL,
automatic BOOLEAN NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT now(),
updated_at TIMESTAMP,
PRIMARY KEY(car_id)
);