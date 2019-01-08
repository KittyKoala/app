DROP DATABASE IF EXISTS appdb;

CREATE DATABASE appdb
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

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

-- ----------------------------
--  Table structure for tb_novel
-- ----------------------------
DROP TABLE
IF EXISTS tb_novel;

CREATE TABLE tb_novel
(
  novel_id     BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '小说ID',
  source       VARCHAR(32)                           NOT NULL
  COMMENT '来源',
  code         VARCHAR(20)                           NOT NULL
  COMMENT '小说代码',
  name         VARCHAR(32)                           NOT NULL
  COMMENT '书名',
  author       VARCHAR(32)                           NOT NULL
  COMMENT '作者',
  cover        VARCHAR(256)                          NOT NULL                    DEFAULT ''
  COMMENT '封面',
  summary      VARCHAR(2048)                         NOT NULL
  COMMENT '描述',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '小说表';
CREATE UNIQUE INDEX source_code_UNIQUE
  ON tb_novel (source, code);
CREATE INDEX ix_source
  ON tb_novel (source);
CREATE INDEX ix_code
  ON tb_novel (code);

-- ----------------------------
--  Table structure for tb_section
-- ----------------------------
DROP TABLE
IF EXISTS tb_section;

CREATE TABLE tb_section
(
  section_id   BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '章节ID',
  novel_id   BIGINT(20)                           NOT NULL
  COMMENT '小说ID',
  code         VARCHAR(20)                           NOT NULL
  COMMENT '章节代码',
  title        VARCHAR(64)                           NOT NULL
  COMMENT '标题',
  content      LONGTEXT                              NOT NULL
  COMMENT '内容',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '章节表';
CREATE UNIQUE INDEX novel_id_code_UNIQUE
  ON tb_section (novel_id, code);
CREATE INDEX ix_novel_id
  ON tb_section (novel_id);

-- ----------------------------
--  Table structure for tb_novel_queue
-- ----------------------------
DROP TABLE
IF EXISTS tb_novel_queue;

CREATE TABLE tb_novel_queue
(
  queue_id     BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '队列ID',
  novel_id     BIGINT(20)                            NOT NULL
  COMMENT '小说ID',
  status       CHAR(1)                               NOT NULL                    DEFAULT 'N'
  COMMENT '状态',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '小说更新队列表';
CREATE INDEX ix_novel_id
  ON tb_novel_queue (novel_id);
CREATE INDEX ix_status
  ON tb_novel_queue (status);

-- ----------------------------
--  Table structure for tb_record
-- ----------------------------
DROP TABLE
IF EXISTS tb_record;

CREATE TABLE tb_record
(
  id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL
  COMMENT '记录ID',
  content      LONGTEXT                              CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
  COMMENT '内容',
  file_names   VARCHAR(2048)                         NOT NULL                    DEFAULT ''
  COMMENT '图片',
  video_names  VARCHAR(2048)                         NOT NULL                    DEFAULT ''
  COMMENT '视频',
  openid       VARCHAR(128)                          NOT NULL
  COMMENT 'openid',
  is_deleted   TINYINT                               NOT NULL                    DEFAULT 0
  COMMENT '逻辑删除',
  created_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  updated_time TIMESTAMP                             NOT NULL                    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '更新时间'
)
  COMMENT '记录表';
CREATE INDEX ix_openid
  ON tb_record (openid);

#====================初始数据====================#

-- ----------------------------
--  data for tb_user
-- ----------------------------
INSERT INTO tb_user
(user_id, email, PASSWORD, salt)
VALUES
  # 密码：11111111
  (1, 'admin@kangyonggan.com', '8d0d54520fe0466ac80827d9f2f038b22e3c7c2d', 'd820c214488d7c6f');

INSERT INTO tb_user_profile
(user_id, name, id_type, id_no, ip_address)
VALUES
  (1, '管理员', '0', '', '127.0.0.1');

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
  ('CONTENT_CATEGORY', '栏目管理', 'CONTENT', 'dashboard/content/category', 1, ''),

  ('SITES', '网站', '', '', 3, 'menu-icon fa fa-globe'),
  ('SITES_NOVEL', '小说管理', 'SITES', 'dashboard/sites/novel', 0, ''),
  ('SITES_NOVEL_QUEUE', '小说队列', 'SITES', 'dashboard/sites/novelQueue', 1, ''),
  ('SITES_RECORD', '宝宝点滴', 'SITES', 'dashboard/sites/record', 2, '');

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
  ('NAV_BAR', '', 'novel', '小说', '/novel', 1, 0),
  ('NAV_BAR', '', 'tool', '工具', '/tool', 2, 0),
  ('NAV_BAR', 'tool', 'format', '代码格式化', '/tool/format', 0, 0),
  ('NAV_BAR', 'tool', 'idNoCheck', '身份证校验', '/tool/idNoCheck', 3, 0),
  ('NAV_BAR', 'tool', 'idNoGen', '生成身份证', '/tool/idNoGen', 4, 0);

INSERT INTO tb_novel
(source, code, name, author, cover, summary)
VALUE
('NS02', '2722', '逆天邪神', '火星引力', 'app/images/novel/2722.jpg', '掌天毒之珠，承邪神之血，修逆天之力，一代邪神，君临天下！【添加微信公众号：火星引力】【我们的yy频道：49554】，各位书友要是觉得《逆天邪神》还不错的话请不要忘记向您QQ群和微博里的朋友推荐哦！'),
('NS06', '40359', ' 仁手邪妃倾世心', '凌婧', '', '被嫡姐设计，错上神秘男子床榻，声名狼藉。五年后，她浴血归来，不谈情爱，只为复仇，却被权倾天下的冷面摄政王盯上。“王爷，妾身不是第一次了，身子早就不干净了，连孩子都有了，您现在退婚还来得及。”垂眸假寐的男子，豁然睁开双目，精光迸射：“娶一送一，爷赚了。”');
