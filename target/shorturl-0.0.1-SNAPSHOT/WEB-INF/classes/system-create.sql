create table t_core_access_token (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  apikey                    varchar(255),
  secret                    varchar(255),
  version                   integer not null,
  constraint pk_t_core_access_token primary key (id))
;

create table t_ui_args (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  version                   integer not null,
  constraint pk_t_ui_args primary key (id))
;

create table base_model (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  version                   integer not null,
  constraint pk_base_model primary key (id))
;

create table t_ui_library (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  lib_name                  varchar(200),
  display_name              varchar(200),
  tags                      varchar(500),
  lib_path                  varchar(500),
  native_content            varchar(1000),
  relase_version            varchar(50),
  version                   integer not null,
  constraint pk_t_ui_library primary key (id))
;

create table t_ui_menu (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  version                   integer not null,
  constraint pk_t_ui_menu primary key (id))
;

create table t_model01 (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  version                   integer not null,
  constraint pk_t_model01 primary key (id))
;

create table t_core_namedsql (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  version                   integer not null,
  constraint pk_t_core_namedsql primary key (id))
;

create table t_core_option (
  id                        varchar(50) not null,
  library_id                varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  opt_key                   varchar(200),
  opt_name                  varchar(500),
  opt_type                  varchar(200),
  is_default                tinyint(1) default 0,
  is_core                   tinyint(1) default 0,
  order_by                  integer,
  version                   integer not null,
  constraint pk_t_core_option primary key (id))
;

create table t_core_organization (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  orgkey                    varchar(50),
  orgkey_path               varchar(500),
  org_code                  varchar(50),
  org_name                  varchar(50),
  parent_id                 varchar(50),
  org_type_id               varchar(50),
  order_by                  integer,
  create_user_id            varchar(50),
  update_user_id            varchar(50),
  version                   integer not null,
  constraint pk_t_core_organization primary key (id))
;

create table t_core_parameter (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  param_name                varchar(200),
  param_value               varchar(500),
  default_value             varchar(500),
  is_core                   tinyint(1) default 0,
  version                   integer not null,
  constraint pk_t_core_parameter primary key (id))
;

create table t_ui_part (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  version                   integer not null,
  constraint pk_t_ui_part primary key (id))
;

create table t_core_user (
  id                        varchar(50) not null,
  created                   datetime,
  updated                   datetime,
  extend_properties         varchar(1000),
  description               varchar(1000),
  login_name                varchar(50) not null,
  display_name              varchar(50),
  password                  varchar(500),
  salt                      varchar(50),
  lock_expiration_time      datetime,
  last_logined              datetime,
  must_change_password      tinyint(1) default 0,
  version                   integer not null,
  constraint uq_t_core_user_login_name unique (login_name),
  constraint pk_t_core_user primary key (id))
;

alter table t_core_option add constraint fk_t_core_option_t_ui_library_1 foreign key (library_id) references t_ui_library (id) on delete restrict on update restrict;
create index ix_t_core_option_t_ui_library_1 on t_core_option (library_id);
alter table t_core_organization add constraint fk_t_core_organization_orgType_2 foreign key (org_type_id) references t_core_option (id) on delete restrict on update restrict;
create index ix_t_core_organization_orgType_2 on t_core_organization (org_type_id);
alter table t_core_organization add constraint fk_t_core_organization_createUser_3 foreign key (create_user_id) references t_core_user (id) on delete restrict on update restrict;
create index ix_t_core_organization_createUser_3 on t_core_organization (create_user_id);
alter table t_core_organization add constraint fk_t_core_organization_updateUser_4 foreign key (update_user_id) references t_core_user (id) on delete restrict on update restrict;
create index ix_t_core_organization_updateUser_4 on t_core_organization (update_user_id);


