ALTER TABLE event
ADD CONSTRAINT fk_event_venue
FOREIGN KEY (venue_id) REFERENCES venue(id);

CREATE INDEX idx_event_venue ON event(venue_id);
