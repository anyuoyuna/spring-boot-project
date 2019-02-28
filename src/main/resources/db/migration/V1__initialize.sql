SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
                        id                    INT(11) NOT NULL AUTO_INCREMENT,
                        title                 VARCHAR(50) NOT NULL,
                        cost                  DECIMAL(8,2) NOT NULL,
                        PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO products (title, cost)
VALUES
('Хлеб', 30), ('Молоко', 50);