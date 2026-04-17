--liquibase formatted sql

--changeset saywich:3 context:0.0.3
BEGIN;
SET CONSTRAINTS ALL DEFERRED;

TRUNCATE "purchasing"."in_stock_orders" CASCADE;
TRUNCATE "cars"."cars" CASCADE;

INSERT INTO "cars"."cars"
    ("id", "model_id", "option_set_id", "vin_number", "created_at", "updated_at", "removed")
VALUES
    (
     '5d1d43eb-9e84-4f81-9e26-cd24da822ea7'::uuid,
     'a1111111-1111-1111-1111-111111111111'::uuid,
     'ab111111-1111-1111-1111-111111111111'::uuid,
     '12345678910',
     NOW(),
     NOW(),
     FALSE
    );


INSERT INTO "purchasing"."in_stock_orders"
    ("id", "order_state", "manager_id", "customer_id", "car_id", "created_at", "updated_at", "removed")
VALUES
    (
     '051f5fbc-42bf-45fa-a3e8-311b94a6a684'::uuid,
     'PENDING',
     'a4ededcf-65d7-4f95-aade-1ea1cf54e581'::uuid,
     '7e2a3deb-5737-4b3e-832f-10e3520368db'::uuid,
    '5d1d43eb-9e84-4f81-9e26-cd24da822ea7'::uuid,
     NOW(),
     NOW(),
     FALSE
    );

COMMIT;