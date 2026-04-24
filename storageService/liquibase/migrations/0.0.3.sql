--liquibase formatted sql

--changeset saywich:3 context:0.0.3
CREATE SCHEMA IF NOT EXISTS "assembling";
CREATE SCHEMA IF NOT EXISTS "stock";

CREATE TYPE "assembling"."assemble_request_status" AS ENUM (
    'CREATED',
    'ASSEMBLED',
    'FAIL'
);

CREATE TABLE IF NOT EXISTS "assembling"."warehouse_admins" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "assembling"."assemble_requests" (
    "id" uuid PRIMARY KEY,
    "order_id" uuid,
    "warehouse_admin_id" uuid,
    "status" assembling.assemble_request_status,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

ALTER TABLE "assembling"."assemble_requests"
ADD FOREIGN KEY ("warehouse_admin_id")
REFERENCES "assembling"."warehouse_admins" ("id")
ON DELETE CASCADE DEFERRABLE;

CREATE TYPE "stock"."part_type" AS ENUM (
    'ENGINE',
    'TRANSMISSION',
    'RIMS',
    'INTERIOR',
    'COLOR'
);

CREATE TABLE IF NOT EXISTS "stock"."part_stock_units" (
    "id" uuid PRIMARY KEY,
    "part_id" uuid,
    "part_type" stock.part_type,
    "total_count" integer NOT NULL CHECK ("total_count" >= 0),
    "reserved_count" integer NOT NULL DEFAULT 0 CHECK ("reserved_count" >= 0),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "stock"."stock_part_reservations" (
    "id" uuid PRIMARY KEY,
    "assemble_request_id" uuid,
    "part_stock_unit_id" uuid,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

ALTER TABLE "stock"."stock_part_reservations"
ADD FOREIGN KEY ("assemble_request_id")
REFERENCES "assembling"."assemble_requests" ("id")
ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "stock"."stock_part_reservations"
ADD FOREIGN KEY ("part_stock_unit_id")
REFERENCES "stock"."part_stock_units" ("id")
ON DELETE CASCADE DEFERRABLE;

CREATE TABLE IF NOT EXISTS "stock"."stock_car_reservations" (
    "id" uuid PRIMARY KEY,
    "assemble_request_id" uuid,
    "car_id" uuid,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

ALTER TABLE "stock"."stock_car_reservations"
ADD FOREIGN KEY ("assemble_request_id")
REFERENCES "assembling"."assemble_requests" ("id")
ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "stock"."stock_car_reservations"
ADD FOREIGN KEY ("car_id")
REFERENCES "cars"."cars" ("id")
ON DELETE CASCADE DEFERRABLE;

--changeset saywich:4 context:0.0.3-seed
INSERT INTO "assembling"."warehouse_admins"
(id, name, created_at, updated_at, removed) VALUES
(
     'b9d4a9ee-e307-4215-839b-62f8e8d2d479'::uuid,
     'Warehouse Andrey',
     NOW(),
     NOW(),
     false
);
