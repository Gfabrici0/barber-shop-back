CREATE TABLE IF NOT EXISTS public.barber (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  barbershop_id UUID NOT NULL,
  FOREIGN KEY (user_id) REFERENCES public.user(id) ON DELETE CASCADE,
  FOREIGN KEY (barbershop_id) REFERENCES public.barbershop(id) ON DELETE CASCADE
);