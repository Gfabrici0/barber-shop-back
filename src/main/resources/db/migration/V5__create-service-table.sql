CREATE TABLE IF NOT EXISTS service (
    id UUID PRIMARY KEY,
    service_name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    value NUMERIC(15, 2) NOT NULL,
    barbershop_id UUID NOT NULL,
    barber_id UUID NOT NULL,
    FOREIGN KEY (barbershop_id) REFERENCES barbershop(id),
    FOREIGN KEY (barber_id) REFERENCES barber(id)
);