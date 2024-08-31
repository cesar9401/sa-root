-- remove constraints
ALTER TABLE adm_role_permission DROP CONSTRAINT adm_permission_adm_role_permission_fk;
ALTER TABLE adm_role_permission DROP CONSTRAINT adm_role_adm_role_permission_fk;

-- remove tables
DROP TABLE adm_role_permission;
DROP TABLE adm_permission;
