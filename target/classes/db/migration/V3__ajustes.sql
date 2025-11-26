-- V3__ajustes.sql

-- Asegurarse que un evento siempre tenga nombre
ALTER TABLE event
ALTER COLUMN name SET NOT NULL;

-- Opcional: si quieres, se puede agregar ON DELETE CASCADE
ALTER TABLE event
DROP CONSTRAINT fk_event_venue;

ALTER TABLE event
ADD CONSTRAINT fk_event_venue
FOREIGN KEY (venue_id) REFERENCES venue(id)
ON DELETE CASCADE;