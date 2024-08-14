ALTER TABLE watchlist ADD COLUMN watched BOOLEAN DEFAULT FALSE;
ALTER TABLE watchlist ADD COLUMN watch_date DATE;
ALTER TABLE watchlist ADD COLUMN rating INT;
ALTER TABLE watchlist ADD COLUMN review TEXT;

ALTER TABLE watchlist
ADD CONSTRAINT rating_check CHECK (rating >= 0 AND rating <= 5 OR rating IS NULL);
