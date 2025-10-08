CREATE TABLE IF NOT EXISTS customer (
  customer_id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  mobile_number VARCHAR(20) NOT NULL,
  created_at DATE NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at DATE DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
  account_number SERIAL PRIMARY KEY,
  customer_id INT NOT NULL,
  account_type VARCHAR(100) NOT NULL,
  branch_address VARCHAR(200) NOT NULL,
  created_at DATE NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at DATE DEFAULT NULL,
  updated_by VARCHAR(50) DEFAULT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
  CONSTRAINT unique_customer_account UNIQUE (customer_id, account_type, branch_address)
);
