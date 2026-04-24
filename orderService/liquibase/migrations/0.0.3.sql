--liquibase formatted sql

--changeset saywich:3 context:0.0.3
CREATE SCHEMA IF NOT EXISTS "messaging";

CREATE TYPE "messaging"."event_type" AS ENUM (
    'IN_STOCK_ORDER',
    'MANUFACTURE_ORDER',
    'TEST_DRIVE_ORDER'
);

CREATE TABLE IF NOT EXISTS "messaging"."outbox_messages" (
    "id" uuid PRIMARY KEY,
    "topic" varchar(255),
    "message_key" varchar(255),
    "event_type" messaging.event_type,
    "payload" jsonb,
    "created_at" timestamptz DEFAULT now(),
    "published_at" timestamptz,
    "retry_count" INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS "messaging"."processed_messages" (
    "event_id" uuid,
    "consumer" varchar(255),
    "processed_at" timestamptz DEFAULT now(),
    PRIMARY KEY ("event_id", "consumer")
);