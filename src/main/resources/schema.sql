CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256) NOT NULL,
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4000),
  autoapprove VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256),
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication BLOB
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(256), authentication BLOB
);

CREATE TABLE IF NOT exists oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);


--CREATE TABLE IF NOT EXISTS users (
--  id INT AUTO_INCREMENT PRIMARY KEY,
--  username VARCHAR(256) NOT NULL,
--  password VARCHAR(256) NOT NULL,
--  enabled TINYINT(1),
--  UNIQUE KEY unique_username(username)
--);
--
--CREATE TABLE IF NOT EXISTS authorities (
--  username VARCHAR(256) NOT NULL,
--  authority VARCHAR(256) NOT NULL,
--  PRIMARY KEY(username, authority)
--);

--		this.enabled = enabled;
--		this.accountNonExpired = accountNonExpired;
--		this.credentialsNonExpired = credentialsNonExpired;
--		this.accountNonLocked = accountNonLocked;

CREATE TABLE if not exists credentials (
  id  varchar(32),  
  name varchar(255) not null,
  password varchar(255) not null,
  version integer,  
  email_address varchar(255),
  enabled boolean not null default true,
  account_expired boolean not null default false,
  credentials_expired boolean not null default false,
  account_locked boolean not null default false,
  login_failure_count int not null default 0,    
  account_expiry_date date,
  credentials_expiry_date date,
  department_id varchar(32),
  created_at datetime,
  updated_at datetime,
  created_by varchar(255),
  last_modified_by varchar(255),
  constraint  credentials_pk primary key  (id)
);

CREATE TABLE if not exists credentials_authorities (
  credentials_id varchar(32) not null,
  authorities_id varchar(32) not null
);

CREATE TABLE if not exists authority (
  id  varchar(32),
  authority varchar(255),
  primary key (id)
);


CREATE TABLE if not exists department (
	id varchar(32),
	name varchar(255),
	description varchar(255),
	organization_id varchar(255),
	constraint department_pk primary key (id)
);


CREATE TABLE if not exists organization (
	id varchar(32),
	name varchar(255),
	description varchar(255),
	constraint organization_pk primary key (id)
);






