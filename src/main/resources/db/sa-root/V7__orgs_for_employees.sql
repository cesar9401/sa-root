ALTER TABLE public.adm_employee ADD COLUMN organization_id UUID;

UPDATE public.adm_employee SET organization_id = '421cab63-3b9f-406b-8270-4e32070384d9';

ALTER TABLE public.adm_employee ALTER COLUMN organization_id SET NOT NULL;
