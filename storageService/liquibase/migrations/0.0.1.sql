--liquibase formatted sql

--changeset saywich:1 context:0.0.1
CREATE SCHEMA IF NOT EXISTS "parts";

CREATE SCHEMA IF NOT EXISTS "cars";

CREATE TYPE "parts"."body_type" AS ENUM (
  'SEDAN',
  'HATCHBACK',
  'SUV'
);

CREATE TYPE "parts"."drive_train" AS ENUM (
  'FRONT',
  'REAR',
  'ALL'
);

CREATE TYPE "parts"."fuel_type" AS ENUM (
  'PETROL',
  'DIESEL',
  'ELECTRICITY'
);

CREATE TYPE "parts"."transmission_type" AS ENUM (
  'MANUAL',
  'AUTOMATIC'
);

CREATE TABLE IF NOT EXISTS "parts"."colors" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "hex_code" integer,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "parts"."engines" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "power" integer,
    "volume" decimal,
    "fuel_type" parts.fuel_type,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "parts"."interiors" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "parts"."rims" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "diameter" integer,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "parts"."transmissions" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "gears_count" integer,
    "type" parts.transmission_type,
    "drive_train" parts.drive_train,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."brands" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."models" (
    "id" uuid PRIMARY KEY,
    "name" varchar(64),
    "brand_id" uuid,
    "default_option_set_id" uuid,
    "price" numeric(15,3),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."option_sets" (
    "id" uuid PRIMARY KEY,
    "available_engine_id" uuid,
    "available_transmission_id" uuid,
    "available_rims_id" uuid,
    "available_interior_id" uuid,
    "available_color_id" uuid,
    "available_body_type_id" uuid,
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."cars" (
    "id" uuid PRIMARY KEY,
    "model_id" uuid,
    "option_set_id" uuid,
    "vin_number" varchar(64),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."available_engines" (
    "id" uuid PRIMARY KEY,
    "model_id" uuid,
    "engine_id" uuid,
    "price_delta" numeric(15,3),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."available_colors" (
    "id" uuid PRIMARY KEY,
    "model_id" uuid,
    "color_id" uuid,
    "price_delta" numeric(15,3),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."available_body_types" (
    "id" uuid PRIMARY KEY,
    "model_id" uuid,
    "body_type" parts.body_type,
    "price_delta" numeric(15,3),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."available_interiors" (
    "id" uuid PRIMARY KEY,
    "model_id" uuid,
    "interior_id" uuid,
    "price_delta" numeric(15,3),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."available_rims" (
    "id" uuid PRIMARY KEY,
    "model_id" uuid,
    "rims_id" uuid,
    "price_delta" numeric(15,3),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE TABLE IF NOT EXISTS "cars"."available_transmissions" (
    "id" uuid PRIMARY KEY,
    "model_id" uuid,
    "transmission_id" uuid,
    "price_delta" numeric(15,3),
    "created_at" timestamptz,
    "updated_at" timestamptz,
    "removed" boolean
);

CREATE UNIQUE INDEX ON "cars"."available_engines" ("model_id", "engine_id");

CREATE UNIQUE INDEX ON "cars"."available_colors" ("model_id", "color_id");

CREATE UNIQUE INDEX ON "cars"."available_body_types" ("model_id", "body_type");

CREATE UNIQUE INDEX ON "cars"."available_interiors" ("model_id", "interior_id");

CREATE UNIQUE INDEX ON "cars"."available_rims" ("model_id", "rims_id");

CREATE UNIQUE INDEX ON "cars"."available_transmissions" ("model_id", "transmission_id");

ALTER TABLE "cars"."models" ADD FOREIGN KEY ("brand_id") REFERENCES "cars"."brands" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."models" ADD FOREIGN KEY ("default_option_set_id") REFERENCES "cars"."option_sets" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."option_sets" ADD FOREIGN KEY ("available_engine_id") REFERENCES "cars"."available_engines" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."option_sets" ADD FOREIGN KEY ("available_transmission_id") REFERENCES "cars"."available_transmissions" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."option_sets" ADD FOREIGN KEY ("available_rims_id") REFERENCES "cars"."available_rims" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."option_sets" ADD FOREIGN KEY ("available_interior_id") REFERENCES "cars"."available_interiors" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."option_sets" ADD FOREIGN KEY ("available_color_id") REFERENCES "cars"."available_colors" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."option_sets" ADD FOREIGN KEY ("available_body_type_id") REFERENCES "cars"."available_body_types" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."cars" ADD FOREIGN KEY ("model_id") REFERENCES "cars"."models" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."cars" ADD FOREIGN KEY ("option_set_id") REFERENCES "cars"."option_sets" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_engines" ADD FOREIGN KEY ("model_id") REFERENCES "cars"."models" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_engines" ADD FOREIGN KEY ("engine_id") REFERENCES "parts"."engines" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_colors" ADD FOREIGN KEY ("model_id") REFERENCES "cars"."models" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_colors" ADD FOREIGN KEY ("color_id") REFERENCES "parts"."colors" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_body_types" ADD FOREIGN KEY ("model_id") REFERENCES "cars"."models" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_interiors" ADD FOREIGN KEY ("model_id") REFERENCES "cars"."models" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_interiors" ADD FOREIGN KEY ("interior_id") REFERENCES "parts"."interiors" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_rims" ADD FOREIGN KEY ("model_id") REFERENCES "cars"."models" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_rims" ADD FOREIGN KEY ("rims_id") REFERENCES "parts"."rims" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_transmissions" ADD FOREIGN KEY ("model_id") REFERENCES "cars"."models" ("id") ON DELETE CASCADE DEFERRABLE;

ALTER TABLE "cars"."available_transmissions" ADD FOREIGN KEY ("transmission_id") REFERENCES "parts"."transmissions" ("id") ON DELETE CASCADE DEFERRABLE;

--changeset saywich:2 context:0.0.1-seed
BEGIN;
SET CONSTRAINTS ALL DEFERRED;

INSERT INTO cars.brands (id, name, created_at, updated_at, removed)
VALUES
    ('11111111-1111-1111-1111-111111111111'::uuid, 'Toyota', NOW(), NOW(), FALSE),
    ('22222222-2222-2222-2222-222222222222'::uuid, 'Honda', NOW(), NOW(), FALSE);

INSERT INTO parts.colors (id, name, hex_code, created_at, updated_at, removed)
VALUES
    ('aaa11111-aaaa-1111-aaaa-111111aaaa11'::uuid, 'Red', 16711680, NOW(), NOW(), FALSE),
    ('aaa22222-aaaa-1111-aaaa-111111aaaa22'::uuid, 'Blue', 255, NOW(), NOW(), FALSE);

INSERT INTO parts.engines (id, name, power, volume, fuel_type, created_at, updated_at, removed)
VALUES
    ('bbb11111-bbbb-1111-bbbb-111111bbbb11'::uuid, 'V6 3.5L', 300, 3.5, 'PETROL', NOW(), NOW(), FALSE),
    ('bbb22222-bbbb-1111-bbbb-111111bbbb22'::uuid, 'I4 2.0L Turbo', 250, 2.0, 'DIESEL', NOW(), NOW(), FALSE);

INSERT INTO parts.transmissions (id, name, gears_count, type, drive_train, created_at, updated_at, removed)
VALUES
    ('ccc11111-cccc-1111-cccc-111111cccc11'::uuid, '6-speed Manual', 6, 'MANUAL', 'FRONT', NOW(), NOW(), FALSE),
    ('ccc22222-cccc-1111-cccc-111111cccc22'::uuid, '8-speed Automatic', 8, 'AUTOMATIC', 'ALL', NOW(), NOW(), FALSE);

INSERT INTO parts.rims (id, name, diameter, created_at, updated_at, removed)
VALUES
    ('ddd11111-dddd-1111-dddd-111111dddd11'::uuid, '18-inch Alloy', 18, NOW(), NOW(), FALSE),
    ('ddd22222-dddd-1111-dddd-111111dddd22'::uuid, '20-inch Alloy', 20, NOW(), NOW(), FALSE);

INSERT INTO parts.interiors (id, name, created_at, updated_at, removed)
VALUES
    ('eee11111-eeee-1111-eeee-111111eeee11'::uuid, 'Leather Black', NOW(), NOW(), FALSE),
    ('eee22222-eeee-1111-eeee-111111eeee22'::uuid, 'Fabric Gray', NOW(), NOW(), FALSE);

INSERT INTO cars.available_engines (id, model_id, engine_id, price_delta, created_at, updated_at, removed)
VALUES
    ('ae111111-aaaa-1111-aaaa-111111aaaa11'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, 'bbb11111-bbbb-1111-bbbb-111111bbbb11'::uuid, 0.0, NOW(), NOW(), FALSE),
    ('ae222222-aaaa-1111-aaaa-111111aaaa22'::uuid, 'b2222222-2222-2222-2222-222222222222'::uuid, 'bbb22222-bbbb-1111-bbbb-111111bbbb22'::uuid, 0.0, NOW(), NOW(), FALSE);

INSERT INTO cars.available_transmissions (id, model_id, transmission_id, price_delta, created_at, updated_at, removed)
VALUES
    ('a3111111-cccc-1111-cccc-111111cccc11'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, 'ccc11111-cccc-1111-cccc-111111cccc11'::uuid, 0.0, NOW(), NOW(), FALSE),
    ('a3222222-cccc-1111-cccc-111111cccc22'::uuid, 'b2222222-2222-2222-2222-222222222222'::uuid, 'ccc22222-cccc-1111-cccc-111111cccc22'::uuid, 0.0, NOW(), NOW(), FALSE);

INSERT INTO cars.available_rims (id, model_id, rims_id, price_delta, created_at, updated_at, removed)
VALUES
    ('a3111111-dddd-1111-dddd-111111dddd11'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, 'ddd11111-dddd-1111-dddd-111111dddd11'::uuid, 0.0, NOW(), NOW(), FALSE),
    ('a3222222-dddd-1111-dddd-222222dddd22'::uuid, 'b2222222-2222-2222-2222-222222222222'::uuid, 'ddd22222-dddd-1111-dddd-111111dddd22'::uuid, 0.0, NOW(), NOW(), FALSE);

INSERT INTO cars.available_interiors (id, model_id, interior_id, price_delta, created_at, updated_at, removed)
VALUES
    ('a3111111-eeee-1111-eeee-111111eeee11'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, 'eee11111-eeee-1111-eeee-111111eeee11'::uuid, 0.0, NOW(), NOW(), FALSE),
    ('a3222222-eeee-1111-eeee-222222eeee22'::uuid, 'b2222222-2222-2222-2222-222222222222'::uuid, 'eee22222-eeee-1111-eeee-111111eeee22'::uuid, 0.0, NOW(), NOW(), FALSE);

INSERT INTO cars.available_colors (id, model_id, color_id, price_delta, created_at, updated_at, removed)
VALUES
    ('ac111111-aaaa-1111-aaaa-111111aaaa11'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, 'aaa11111-aaaa-1111-aaaa-111111aaaa11'::uuid, 0.0, NOW(), NOW(), FALSE),
    ('ac222222-aaaa-1111-aaaa-111111aaaa22'::uuid, 'b2222222-2222-2222-2222-222222222222'::uuid, 'aaa22222-aaaa-1111-aaaa-111111aaaa22'::uuid, 0.0, NOW(), NOW(), FALSE);

INSERT INTO cars.available_body_types (id, model_id, body_type, price_delta, created_at, updated_at, removed)
VALUES
    ('ab111111-bbbb-1111-bbbb-111111bbbb11'::uuid, 'a1111111-1111-1111-1111-111111111111'::uuid, 'SEDAN', 0.0, NOW(), NOW(), FALSE),
    ('ab222222-bbbb-1111-bbbb-222222bbbb22'::uuid, 'b2222222-2222-2222-2222-222222222222'::uuid, 'HATCHBACK', 0.0, NOW(), NOW(), FALSE);

INSERT INTO cars.option_sets (
    id,
    available_engine_id,
    available_transmission_id,
    available_rims_id,
    available_interior_id,
    available_color_id,
    available_body_type_id,
    created_at,
    updated_at,
    removed
)
VALUES
    ('ab111111-1111-1111-1111-111111111111'::uuid,
     'ae111111-aaaa-1111-aaaa-111111aaaa11'::uuid,
     'a3111111-cccc-1111-cccc-111111cccc11'::uuid,
     'a3111111-dddd-1111-dddd-111111dddd11'::uuid,
     'a3111111-eeee-1111-eeee-111111eeee11'::uuid,
     'ac111111-aaaa-1111-aaaa-111111aaaa11'::uuid,
     'ab111111-bbbb-1111-bbbb-111111bbbb11'::uuid,
     NOW(), NOW(), FALSE);

INSERT INTO cars.models (id, name, brand_id, default_option_set_id, price, created_at, updated_at, removed)
VALUES
    ('a1111111-1111-1111-1111-111111111111'::uuid, 'Camry', '11111111-1111-1111-1111-111111111111'::uuid, 'ab111111-1111-1111-1111-111111111111'::uuid, 30000.000, NOW(), NOW(), FALSE),
    ('b2222222-2222-2222-2222-222222222222'::uuid, 'Civic', '22222222-2222-2222-2222-222222222222'::uuid, 'ab111111-1111-1111-1111-111111111111'::uuid, 25000.000, NOW(), NOW(), FALSE);

COMMIT;