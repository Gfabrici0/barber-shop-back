CREATE TABLE IF NOT EXISTS public.user (
  id UUID PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  document VARCHAR(255) NOT NULL UNIQUE,
  phone_number VARCHAR(50) NOT NULL,
  date_of_birth DATE
);

CREATE TABLE IF NOT EXISTS public.address (
  id UUID PRIMARY KEY,
  address_number VARCHAR(255),
  address_street VARCHAR(255),
  address_city VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS public.user_address (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  address_id UUID NOT NULL,
  FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE,
  FOREIGN KEY (address_id) REFERENCES public.address(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.role (
  id UUID PRIMARY KEY,
  role VARCHAR(50) NOT NULL
);

INSERT INTO public.role (id, role) VALUES
('ff500698-7296-4449-a42b-073f85741c12', 'ADMIN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO public.role (id, role) VALUES
('1da1f148-728e-45f9-bf10-b0dcf1a6107e', 'BARBERSHOP')
ON CONFLICT (id) DO NOTHING;

INSERT INTO public.role (id, role) VALUES
('e6b305fb-59ff-4806-a6a9-256729f52b4d', 'BARBER')
ON CONFLICT (id) DO NOTHING;

INSERT INTO public.role (id, role) VALUES
('18257477-5555-4c78-88e4-a9ec3a101e8d', 'USER')
ON CONFLICT (id) DO NOTHING;

CREATE TABLE IF NOT EXISTS public.user_role (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  role_id UUID NOT NULL,
  FOREIGN KEY (role_id) REFERENCES public.role(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.barbershop (
  id UUID PRIMARY KEY,
  owner_name VARCHAR(255) NOT NULL,
  trade_name VARCHAR(255) NOT NULL,
  corporate_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  document VARCHAR(255) NOT NULL UNIQUE,
  phone_number VARCHAR(50) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS public.barbershop_user (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  barbershop_id UUID NOT NULL,
  FOREIGN KEY (barbershop_id) REFERENCES public.barbershop(id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS public.barbershop_address (
  id UUID PRIMARY KEY,
  barbershop_id UUID NOT NULL,
  address_id UUID NOT NULL,
  FOREIGN KEY (address_id) REFERENCES public.address(id) ON DELETE CASCADE,
  FOREIGN KEY (barbershop_id) REFERENCES public.barbershop(id) ON DELETE CASCADE
);