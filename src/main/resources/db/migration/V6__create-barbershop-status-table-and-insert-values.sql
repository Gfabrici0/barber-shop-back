CREATE TABLE IF NOT EXISTS status (
    id UUID PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    description TEXT NOT NULL
);

INSERT INTO status (id, status, description) VALUES
('79b1b5b1-68f0-4941-8375-f57861a8c62a', 'ACTIVE', 'Barbershop is open')
ON CONFLICT (id) DO NOTHING;

INSERT INTO status (id, status, description) VALUES
('7e587724-9456-4989-a647-fd15bd4b43ad', 'INACTIVE', 'Barbershop is inactive')
ON CONFLICT (id) DO NOTHING;

INSERT INTO status (id, status, description) VALUES
('741cf7fd-b5d4-468c-bf5c-561f0601e70c', 'PENDING_APPROVAL', 'Barbershop is pending approval')
ON CONFLICT (id) DO NOTHING;

INSERT INTO status (id, status, description) VALUES
('a0087078-81f4-4af0-b1aa-521d1f398689', 'REJECTED', 'Barbershop was rejected')
ON CONFLICT (id) DO NOTHING;