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