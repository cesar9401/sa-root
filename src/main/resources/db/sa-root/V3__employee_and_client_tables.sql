-- remove old data
TRUNCATE adm_role, adm_user, adm_user_role;

-- new tables
CREATE TABLE adm_person (
    person_id UUID NOT NULL,
    unique_identification_code VARCHAR(50) NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    last_name VARCHAR(150) NOT NULL,
    date_of_birth DATE NOT NULL,
    entry_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT adm_person_pk PRIMARY KEY (person_id)
);

CREATE TABLE adm_employee (
    employee_id UUID NOT NULL,
    person_id UUID NOT NULL,
    CONSTRAINT adm_employee_pk PRIMARY KEY (employee_id)
);

CREATE TABLE adm_client (
    client_id UUID NOT NULL,
    person_id UUID NOT NULL,
    CONSTRAINT adm_client_pk PRIMARY KEY (client_id)
);

ALTER TABLE adm_user ADD person_id UUID NOT NULL;

-- add fk constraints
ALTER TABLE adm_user ADD CONSTRAINT adm_person_adm_user_fk
FOREIGN KEY (person_id)
REFERENCES adm_person (person_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE adm_client ADD CONSTRAINT adm_person_adm_client_fk
FOREIGN KEY (person_id)
REFERENCES adm_person (person_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE adm_employee ADD CONSTRAINT adm_person_adm_employee_fk
FOREIGN KEY (person_id)
REFERENCES adm_person (person_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
