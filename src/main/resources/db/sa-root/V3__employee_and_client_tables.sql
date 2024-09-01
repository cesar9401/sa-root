-- remove old data
TRUNCATE adm_role, adm_user, adm_user_role;

CREATE TABLE adm_employee (
    user_id UUID NOT NULL,
    cur_salary NUMERIC (19, 4) NOT NULL,
    CONSTRAINT adm_employee_pk PRIMARY KEY (user_id)
);

CREATE TABLE adm_client (
    user_id UUID NOT NULL,
    CONSTRAINT adm_client_pk PRIMARY KEY (user_id)
);

ALTER TABLE adm_user ADD first_name VARCHAR(150) NOT NULL;
ALTER TABLE adm_user ADD last_name VARCHAR(150) NOT NULL;
ALTER TABLE adm_user ADD date_of_birth DATE NOT NULL;

-- add fk constraints
ALTER TABLE adm_client ADD CONSTRAINT adm_user_adm_client_fk
FOREIGN KEY (user_id)
REFERENCES adm_user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE adm_employee ADD CONSTRAINT adm_user_adm_employee_fk
FOREIGN KEY (user_id)
REFERENCES adm_user (user_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
