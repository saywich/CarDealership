--liquibase formatted sql

--changeset saywich:1 context:0.0.1
CREATE SCHEMA IF NOT EXISTS "purchasing";

CREATE TYPE "purchasing"."order_state" AS ENUM (
    'CANCELLED',
    'PENDING',
    'ACCEPTED',
    'WAITING_FOR_PAYMENT',
    'PAID',
    'WAITING_FOR_DELIVERY',
    'READY',
    'COMPLETED'
);

CREATE TABLE IF NOT EXISTS "purchasing"."customers" (
    "id" uuid PRIMARY KEY,
    "name" varchar(128),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "purchasing"."managers" (
    "id" uuid PRIMARY KEY,
    "name" varchar(128),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "purchasing"."in_stock_orders" (
    "id" uuid PRIMARY KEY,
    "order_state" purchasing.order_state,
    "manager_id" uuid,
    "customer_id" uuid,
    "car_id" uuid,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "purchasing"."manufacture_orders" (
    "id" uuid PRIMARY KEY,
    "order_state" purchasing.order_state,
    "manager_id" uuid,
    "customer_id" uuid,
    "model_id" uuid,
    "option_set_id" uuid,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "purchasing"."test_drive_orders" (
    "id" uuid PRIMARY KEY,
    "customer_id" uuid,
    "car_id" uuid,
    "start_date" timestamptz,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

ALTER TABLE "purchasing"."in_stock_orders" ADD FOREIGN KEY ("manager_id") REFERENCES "purchasing"."managers" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "purchasing"."in_stock_orders" ADD FOREIGN KEY ("customer_id") REFERENCES "purchasing"."customers" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "purchasing"."manufacture_orders" ADD FOREIGN KEY ("manager_id") REFERENCES "purchasing"."managers" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "purchasing"."manufacture_orders" ADD FOREIGN KEY ("customer_id") REFERENCES "purchasing"."customers" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "purchasing"."test_drive_orders" ADD FOREIGN KEY ("customer_id") REFERENCES "purchasing"."customers" ("id") ON DELETE CASCADE DEFERRABLE;

--changeset saywich:2 context:0.0.1-seed
BEGIN;
SET CONSTRAINTS ALL DEFERRED;

TRUNCATE "purchasing"."customers" CASCADE;
TRUNCATE "purchasing"."managers" CASCADE;

INSERT INTO "purchasing"."customers" ("id", "name", "created_at", "updated_at", "removed")
VALUES
    ('7e2a3deb-5737-4b3e-832f-10e3520368db'::uuid, 'Antoha', NOW(), NOW(), FALSE),
    ('f2f03080-54aa-4e22-90f1-9dc8ed014bc2'::uuid, 'Glebas', NOW(), NOW(), FALSE),
    ('4a4a5360-a060-4c1f-bdae-ffd9456dfe07'::uuid, 'Diman', NOW(), NOW(), FALSE),
    ('641f77f3-8b39-4c3c-ac57-f866703a79d3'::uuid, 'Sashka', NOW(), NOW(), FALSE),
    ('4d74ff62-2292-436b-97a5-4e990db41f47'::uuid, 'Edique', NOW(), NOW(), FALSE);

INSERT INTO "purchasing"."managers" ("id", "name", "created_at", "updated_at", "removed")
VALUES
    ('a4ededcf-65d7-4f95-aade-1ea1cf54e581'::uuid, 'Manager Antoha', NOW(), NOW(), FALSE),
    ('9aec6581-6d22-4ea8-9c5a-545aadeb06d6'::uuid, 'Manager Glebas', NOW(), NOW(), FALSE),
    ('b4eb4cae-9ae1-4a88-980a-75221e112d6f'::uuid, 'Manager Diman', NOW(), NOW(), FALSE),
    ('993174ba-8847-4e27-8795-cfdd308d3f99'::uuid, 'Manager Sashka', NOW(), NOW(), FALSE),
    ('fc507631-d868-40d4-a5eb-a485e562586b'::uuid, 'Manager Edique', NOW(), NOW(), FALSE);

COMMIT;
