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