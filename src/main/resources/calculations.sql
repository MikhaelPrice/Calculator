-- Table: public.calculations

-- DROP TABLE IF EXISTS public.calculations;

CREATE TABLE IF NOT EXISTS public.calculations
(
    id bigint NOT NULL,
    expression character varying(255) COLLATE pg_catalog."default" NOT NULL,
    result character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT calculations_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.calculations
    OWNER to postgres;