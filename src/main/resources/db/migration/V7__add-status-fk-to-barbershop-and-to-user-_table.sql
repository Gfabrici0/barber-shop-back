ALTER TABLE barbershop
ADD COLUMN status_id UUID NOT NULL,
ADD CONSTRAINT fk_status
FOREIGN KEY (status_id) REFERENCES status(id);

ALTER TABLE public.user
ADD COLUMN status_id UUID NOT NULL,
ADD CONSTRAINT fk_status
FOREIGN KEY (status_id) REFERENCES status(id);