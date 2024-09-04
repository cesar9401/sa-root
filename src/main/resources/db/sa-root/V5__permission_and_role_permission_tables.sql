CREATE TABLE public.adm_permission
(
    permission_id          UUID         NOT NULL,
    parent_permission_id   UUID,
    permission_name        VARCHAR(100) NOT NULL,
    permission_description VARCHAR(250) NOT NULL,
    CONSTRAINT adm_permission_pk PRIMARY KEY (permission_id)
);

CREATE TABLE public.adm_role_permission
(
    role_permission_id UUID    NOT NULL,
    role_id            UUID    NOT NULL,
    permission_id      UUID    NOT NULL,
    read_permission    BOOLEAN NOT NULL,
    create_permission  BOOLEAN NOT NULL,
    update_permission  BOOLEAN NOT NULL,
    delete_permission  BOOLEAN NOT NULL,
    CONSTRAINT adm_role_permission_pk PRIMARY KEY (role_permission_id)
);

ALTER TABLE public.adm_permission
    ADD CONSTRAINT adm_permission_adm_permission_fk
        FOREIGN KEY (parent_permission_id)
            REFERENCES adm_permission (permission_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE public.adm_role_permission
    ADD CONSTRAINT adm_permission_adm_role_permission_fk
        FOREIGN KEY (permission_id)
            REFERENCES adm_permission (permission_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE public.adm_role_permission
    ADD CONSTRAINT adm_role_adm_role_permission_fk
        FOREIGN KEY (role_id)
            REFERENCES adm_role (role_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;
