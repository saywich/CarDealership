--liquibase formatted sql

--changeset saywich:2 context:0.0.2
BEGIN;
SET CONSTRAINTS ALL DEFERRED;

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

COMMIT;