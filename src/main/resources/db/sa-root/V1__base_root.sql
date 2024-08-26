CREATE TABLE adm_permission
(
    permission_id        UUID         NOT NULL,
    parent_permission_id UUID,
    name                 VARCHAR(100) NOT NULL,
    href                 VARCHAR(250) NOT NULL,
    CONSTRAINT adm_permission_pk PRIMARY KEY (permission_id)
);

CREATE TABLE adm_role
(
    role_id     UUID        NOT NULL,
    name        VARCHAR(75) NOT NULL,
    description VARCHAR(200),
    CONSTRAINT adm_role_pk PRIMARY KEY (role_id)
);

CREATE TABLE adm_role_permission
(
    role_permission_id UUID    NOT NULL,
    role_id            UUID    NOT NULL,
    permission_id      UUID    NOT NULL,
    read_permission    BOOLEAN NOT NULL,
    create_permission  BOOLEAN NOT NULL,
    update_permission  BOOLEAN NOT NULL,
    delete_permission  BOOLEAN NOT NULL,
    export_permission  BOOLEAN NOT NULL,
    CONSTRAINT adm_role_permission_pk PRIMARY KEY (role_permission_id)
);

CREATE TABLE adm_user
(
    user_id    UUID         NOT NULL,
    email      VARCHAR(150) NOT NULL,
    password   VARCHAR(500) NOT NULL,
    entry_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT adm_user_pk PRIMARY KEY (user_id)
);

CREATE TABLE adm_user_role
(
    user_role_id UUID NOT NULL,
    role_id      UUID NOT NULL,
    user_id      UUID NOT NULL,
    CONSTRAINT adm_user_role_pk PRIMARY KEY (user_role_id)
);

ALTER TABLE public.adm_user
    ADD CONSTRAINT adm_user_email_uq
        UNIQUE (email);

ALTER TABLE public.adm_permission
    ADD CONSTRAINT adm_permission_adm_permission_fk
        FOREIGN KEY (parent_permission_id)
            REFERENCES adm_permission (permission_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION;

ALTER TABLE adm_role_permission
    ADD CONSTRAINT adm_permission_adm_role_permission_fk
        FOREIGN KEY (permission_id)
            REFERENCES adm_permission (permission_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE adm_role_permission
    ADD CONSTRAINT adm_role_adm_role_permission_fk
        FOREIGN KEY (role_id)
            REFERENCES adm_role (role_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE adm_user_role
    ADD CONSTRAINT adm_role_adm_user_role_fk
        FOREIGN KEY (role_id)
            REFERENCES adm_role (role_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;

ALTER TABLE adm_user_role
    ADD CONSTRAINT adm_user_adm_user_role_fk
        FOREIGN KEY (user_id)
            REFERENCES adm_user (user_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
            NOT DEFERRABLE;
