CREATE TABLE IF NOT EXISTS scheduling_status (
  id UUID PRIMARY KEY,
  status VARCHAR(255) NOT NULL,
  description TEXT NOT NULL
);

INSERT INTO scheduling_status (id, status, description)
VALUES
('540c022d-b203-49cb-95e8-ad92cba74abe', 'PENDING_BARBER_APPROVAL', 'Pendente de aprovação do barbeiro'),
('12814fd9-9776-4997-88e3-68a73fca1590', 'BARBER_APPROVED', 'Aprovado pelo Barbeiro'),
('281f6cd0-2a80-4b3c-9bea-e9b7963454b9', 'BARBER_REJECTED', 'Rejeitado pelo Barbeiro'),
('c1bf59a5-b7b3-457d-b46b-f9359c82915b', 'USER_CANCELED', 'Cancelado pelo usuário'),
('a465aff1-9d87-4c16-9502-6d5bab05de58', 'BARBER_CANCELED', 'Cancelado pelo barbeiro'),
('3a9cc61e-5f33-4b70-9702-8df2c3b074dc', 'USER_NOT_FOUND', 'Usuário não encontrado'),
('9c5d8ffb-4a82-4ca4-a4c6-847c476b6e44', 'DONE', 'Finalizado');

CREATE TABLE IF NOT EXISTS scheduling (
  id UUID PRIMARY KEY,
  date TIMESTAMP NOT NULL,
  scheduling_status_id UUID NOT NULL,
  user_id UUID NOT NULL,
  barbershop_id UUID NOT NULL,
  service_id UUID NOT NULL,
  barber_id UUID NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  FOREIGN KEY (scheduling_status_id) REFERENCES scheduling_status(id),
  FOREIGN KEY (user_id) REFERENCES public.user(id),
  FOREIGN KEY (barbershop_id) REFERENCES barbershop(id),
  FOREIGN KEY (service_id) REFERENCES service(id),
  FOREIGN KEY (barber_id) REFERENCES barber(id)
);