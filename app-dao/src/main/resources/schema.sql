DROP DATABASE IF EXISTS appdb;

CREATE DATABASE appdb
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE appdb;

-- ----------------------------
--  Table structure for tb_user
-- ----------------------------
DROP TABLE
IF EXISTS tb_user;

CREATE TABLE tb_user
(
  user_id      BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '用户ID',
  email        VARCHAR(64)                           NOT NULL
  COMMENT '电子邮件',
  password     VARCHAR(64)                           NOT NULL
  COMMENT '密码',
  salt         VARCHAR(64)                           NOT NULL
  COMMENT '密码盐',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户表';
CREATE UNIQUE INDEX tb_email_UNIQUE
  ON tb_user (email);

-- ----------------------------
--  Table structure for tb_user_profile
-- ----------------------------
DROP TABLE
IF EXISTS tb_user_profile;

CREATE TABLE tb_user_profile
(
  user_id      BIGINT(20)   NOT NULL
  COMMENT '用户ID',
  name         VARCHAR(32)  NOT NULL                    DEFAULT ''
  COMMENT '姓名',
  id_type      VARCHAR(1)   NOT NULL                    DEFAULT '0'
  COMMENT '证件类型',
  id_no        VARCHAR(128) NOT NULL                    DEFAULT ''
  COMMENT '证件号码',
  ip_address   VARCHAR(20)  NOT NULL                    DEFAULT ''
  COMMENT 'IP地址',
  skin         VARCHAR(20)  NOT NULL                    DEFAULT ''
  COMMENT '皮肤',
  is_deleted   TINYINT      NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP    NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP    NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '用户信息表';
CREATE UNIQUE INDEX user_id_UNIQUE
  ON tb_user_profile (user_id);

-- ----------------------------
--  Table structure for tb_role
-- ----------------------------
DROP TABLE
IF EXISTS tb_role;

CREATE TABLE tb_role
(
  role_id      BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  role_code    VARCHAR(32)                           NOT NULL
  COMMENT '角色代码',
  role_name    VARCHAR(32)                           NOT NULL
  COMMENT '角色名称',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '角色表';
CREATE UNIQUE INDEX role_code_UNIQUE
  ON tb_role (role_code);

-- ----------------------------
--  Table structure for tb_menu
-- ----------------------------
DROP TABLE
IF EXISTS tb_menu;

CREATE TABLE tb_menu
(
  menu_id      BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '主键, 自增',
  menu_code    VARCHAR(32)                           NOT NULL
  COMMENT '菜单代码',
  menu_name    VARCHAR(32)                           NOT NULL
  COMMENT '菜单名称',
  parent_code  VARCHAR(32)                           NOT NULL                DEFAULT ''
  COMMENT '父菜单代码',
  url          VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单地址',
  sort         INT(11)                               NOT NULL                DEFAULT 0
  COMMENT '菜单排序(从0开始)',
  icon         VARCHAR(128)                          NOT NULL                DEFAULT ''
  COMMENT '菜单图标的样式',
  is_deleted   TINYINT                               NOT NULL                DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '菜单表';
CREATE INDEX sort_ix
  ON tb_menu (sort);
CREATE UNIQUE INDEX menu_code_UNIQUE
  ON tb_menu (menu_code);

-- ----------------------------
--  Table structure for tb_user_role
-- ----------------------------
DROP TABLE
IF EXISTS tb_user_role;

CREATE TABLE tb_user_role
(
  user_id BIGINT(20) NOT NULL
  COMMENT '用户ID',
  role_id BIGINT(20) NOT NULL
  COMMENT '角色ID',
  PRIMARY KEY (user_id, role_id)
)
  COMMENT '用户角色表';

-- ----------------------------
--  Table structure for rtb_ole_menu
-- ----------------------------
DROP TABLE
IF EXISTS tb_role_menu;

CREATE TABLE tb_role_menu
(
  role_id BIGINT(20) NOT NULL
  COMMENT '角色ID',
  menu_id BIGINT(20) NOT NULL
  COMMENT '菜单ID',
  PRIMARY KEY (role_id, menu_id)
)
  COMMENT '角色菜单表';

-- ----------------------------
--  Table structure for tb_dict
-- ----------------------------
DROP TABLE
IF EXISTS tb_dict;

CREATE TABLE tb_dict
(
  dict_id      BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '字典ID',
  dict_type    VARCHAR(20)                           NOT NULL
  COMMENT '字典类型',
  dict_code    VARCHAR(64)                           NOT NULL
  COMMENT '字典代码',
  value        VARCHAR(256)                          NOT NULL
  COMMENT '值',
  remark       VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '备注',
  sort         INT(11)                               NOT NULL                    DEFAULT 0
  COMMENT '排序（从0开始）',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '字典表';
CREATE UNIQUE INDEX type_code_UNIQUE
  ON tb_dict (dict_type, dict_code);

-- ----------------------------
--  Table structure for tb_email
-- ----------------------------
DROP TABLE
IF EXISTS tb_email;

CREATE TABLE tb_email
(
  email_id     BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '邮件ID',
  subject      VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '标题',
  from_email   VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '发送方',
  to_email     VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT '接收方',
  type         VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '邮件类型',
  ip_address   VARCHAR(20)                           NOT NULL                    DEFAULT ''
  COMMENT 'IP地址',
  code         VARCHAR(16)                           NOT NULL                    DEFAULT ''
  COMMENT '验证码',
  content      LONGTEXT
  COMMENT '邮件内容',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '邮件表';
CREATE INDEX code_UNIQUE
  ON tb_email (code);
CREATE INDEX ix_type
  ON tb_email (type);

-- ----------------------------
--  Table structure for tb_category
-- ----------------------------
DROP TABLE
IF EXISTS tb_category;

CREATE TABLE tb_category
(
  category_id   BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '栏目ID',
  category_type VARCHAR(20)                           NOT NULL
  COMMENT '栏目类型',
  parent_code   VARCHAR(20)                           NOT NULL                    DEFAULT ''
  COMMENT '父栏目代码',
  category_code VARCHAR(20)                           NOT NULL
  COMMENT '栏目代码',
  category_name VARCHAR(20)                           NOT NULL
  COMMENT '栏目名称',
  url           VARCHAR(128)                          NOT NULL                    DEFAULT ''
  COMMENT '地址',
  sort          INT(11)                               NOT NULL                    DEFAULT 0
  COMMENT '栏目排序(从0开始)',
  is_blank      TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '是否开启新界面',
  is_deleted    TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time  TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '栏目表';
CREATE UNIQUE INDEX category_type_code_UNIQUE
  ON tb_category (category_type, category_code);

-- ----------------------------
--  Table structure for tb_login_log
-- ----------------------------
DROP TABLE
IF EXISTS tb_login_log;

CREATE TABLE tb_login_log
(
  login_id     BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '登录ID',
  user_id      BIGINT(20)                            NOT NULL
  COMMENT '用户ID',
  email        VARCHAR(64)                           NOT NULL
  COMMENT '电子邮件',
  ip_address   VARCHAR(20)                           NOT NULL                    DEFAULT ''
  COMMENT '登录IP',
  app_source   VARCHAR(3)                            NOT NULL                    DEFAULT ''
  COMMENT '应用来源',
  jsessionid   VARCHAR(64)                           NOT NULL                    DEFAULT ''
  COMMENT 'jsessionid',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '登录日志表';
CREATE INDEX ix_email
  ON tb_login_log (email);

#====================初始数据====================#

-- ----------------------------
--  data for tb_user
-- ----------------------------
INSERT INTO tb_user
(user_id, email, PASSWORD, salt)
VALUES
  # 密码：11111111
  (1, 'admin@kangyonggan.com', '8d0d54520fe0466ac80827d9f2f038b22e3c7c2d', 'd820c214488d7c6f'),
  (2, 'java@kangyonggan.com', '8d0d54520fe0466ac80827d9f2f038b22e3c7c2d', 'd820c214488d7c6f');

INSERT INTO tb_user_profile
(user_id, name, id_type, id_no, ip_address)
VALUES
  (1, '管理员', '0', '', '127.0.0.1'),
  (2, '康永敢', '0', '', '127.0.0.1');

-- ----------------------------
--  data for tb_role
-- ----------------------------
INSERT INTO tb_role
(role_id, role_code, role_name)
VALUES
  (1, 'ROLE_ADMIN', '管理员');

-- ----------------------------
--  data for tb_menu
-- ----------------------------
INSERT INTO tb_menu
(menu_code, menu_name, parent_code, url, sort, icon)
VALUES
  ('DASHBOARD', '工作台', '', 'dashboard', 0, 'menu-icon fa fa-dashboard'),

  ('SYSTEM', '系统', '', '', 1, 'menu-icon fa fa-cogs'),
  ('SYSTEM_USER', '用户管理', 'SYSTEM', 'dashboard/system/user', 0, ''),
  ('SYSTEM_ROLE', '角色管理', 'SYSTEM', 'dashboard/system/role', 1, ''),
  ('SYSTEM_MENU', '菜单管理', 'SYSTEM', 'dashboard/system/menu', 2, ''),

  ('CONTENT', '内容', '', '', 2, 'menu-icon fa fa-folder-open-o'),
  ('CONTENT_DICT', '字典管理', 'CONTENT', 'dashboard/content/dict', 0, ''),
  ('CONTENT_CATEGORY', '栏目管理', 'CONTENT', 'dashboard/content/category', 1, '');

-- ----------------------------
--  data for tb_user_role
-- ----------------------------
INSERT INTO tb_user_role
VALUES
  (1, 1);

-- ----------------------------
--  data for tb_role_menu
-- ----------------------------
INSERT INTO tb_role_menu SELECT
                           1,
                           menu_id
                         FROM tb_menu;

-- ----------------------------
--  data for tb_dict
-- ----------------------------
INSERT INTO tb_dict
(dict_type, dict_code, value, sort)
VALUES
  ('ID_TYPE', '0', '身份证', 0);

-- ----------------------------
--  data for tb_dict
-- ----------------------------
INSERT INTO tb_category
(category_type, parent_code, category_code, category_name, url, sort, is_blank)
VALUES
  ('NAV_BAR', '', 'blog', '博客', 'https://blog.kangyonggan.com', 0, 1),
  ('NAV_BAR', '', 'novel', '小说', '', 1, 0),
  ('NAV_BAR', '', 'photo', '相册', '', 2, 0),
  ('NAV_BAR', 'photo', 'lifePhoto', '生活照', '', 0, 0),
  ('NAV_BAR', 'photo', 'screenshot', '截图', '', 1, 0),
  ('NAV_BAR', 'photo', 'tourPhoto', '旅游照', '', 2, 0),
  ('NAV_BAR', 'photo', 'jobPhoto', '工作照', '', 3, 0),
  ('NAV_BAR', 'photo', 'familyPhoto', '家庭照', '', 4, 0),
  ('NAV_BAR', 'photo', 'otherPhoto', '其他', '', 5, 0),
  ('NAV_BAR', '', 'video', '视频', '', 3, 0),
  ('NAV_BAR', 'video', 'lifeVideo', '生活视频', '', 0, 0),
  ('NAV_BAR', 'video', 'gameVideo', '游戏视频', '', 1, 0),
  ('NAV_BAR', 'video', 'tourVideo', '旅游视频', '', 2, 0),
  ('NAV_BAR', 'video', 'familyVideo', '家庭视频', '', 3, 0),
  ('NAV_BAR', 'video', 'other', '其他', '', 4, 0),
  ('NAV_BAR', '', 'tool', '工具', '', 4, 0),
  ('NAV_BAR', 'tool', 'xml', 'XML格式化', '', 0, 0),
  ('NAV_BAR', 'tool', 'json', 'JSON格式化', '', 1, 0),
  ('NAV_BAR', 'tool', 'sql', 'SQL格式化', '', 2, 0),
  ('NAV_BAR', 'sql', 'MySQL', 'MySQL', '', 0, 0),
  ('NAV_BAR', 'sql', 'SQLServer', 'SQLServer', '', 1, 0),
  ('NAV_BAR', 'sql', 'Oracle', 'Oracle', '', 2, 0),
  ('NAV_BAR', 'tool', 'idNoCheck', '身份证校验', '', 3, 0),
  ('NAV_BAR', 'tool', 'idNoGen', '生成身份证', '', 4, 0);