SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
                     id                    INT(11) NOT NULL AUTO_INCREMENT,
                     username              VARCHAR(50) NOT NULL,
                     password              CHAR(80) NOT NULL,
                     first_name            VARCHAR(50) NOT NULL,
                     last_name             VARCHAR(50) NOT NULL,
                     email                 VARCHAR(50) NOT NULL,
                     phone                 VARCHAR(15) NOT NULL,
                     PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS roles;

CREATE TABLE roles (
                     id                    INT(11) NOT NULL AUTO_INCREMENT,
                     name                  VARCHAR(50) DEFAULT NULL,
                     PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles (
                           user_id               INT(11) NOT NULL,
                           role_id               INT(11) NOT NULL,

                           PRIMARY KEY (user_id,role_id),

  --  KEY FK_ROLE_idx (role_id),

                           CONSTRAINT FK_USER_ID_01 FOREIGN KEY (user_id)
                             REFERENCES users (id)
                             ON DELETE NO ACTION ON UPDATE NO ACTION,

                           CONSTRAINT FK_ROLE_ID FOREIGN KEY (role_id)
                             REFERENCES roles (id)
                             ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
                        id                    INT(11) NOT NULL AUTO_INCREMENT,
                        title                 VARCHAR(50) NOT NULL,
                        price                 DECIMAL(8,2) NOT NULL,
                        PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS orders_item;

CREATE TABLE orders_item (
                           id	                INT(11) NOT NULL AUTO_INCREMENT,
                           product_id            INT(11) NOT NULL,
  --  order_id              INT(11) NOT NULL,
                           quantity              INT NOT NULL,
                           item_price            DECIMAL(8,2) NOT NULL,
                           total_price           DECIMAL(8,2) NOT NULL,
                           PRIMARY KEY (id),
  --  CONSTRAINT FK_ORDER_ID FOREIGN KEY (order_id)
  --  REFERENCES orders (id),
                           CONSTRAINT FK_PRODUCT_ID_ORD_IT FOREIGN KEY (product_id)
                             REFERENCES products (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO products (title, price)
VALUES
('Хлеб', 30), ('Молоко', 50), ('Чай', 60), ('Кефир', 50), ('Сметана', 60), ('Творог', 250), ('Пирожок', 20), ('Масло', 120);

INSERT INTO roles (name)
VALUES
('ROLE_EMPLOYEE'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (username,password,first_name,last_name,email,phone)
VALUES
('admin','$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i','Admin','Admin','admin@gmail.com','+79881111111');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3);