--liquibase formatted sql

--changeset saywich:2 context:0.0.2
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
