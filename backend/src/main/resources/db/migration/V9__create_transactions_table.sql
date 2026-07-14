CREATE TABLE transactions (

                              id UUID PRIMARY KEY,

                              title VARCHAR(120) NOT NULL,

                              description VARCHAR(500),

                              amount NUMERIC(15,2) NOT NULL,

                              type VARCHAR(20) NOT NULL,

                              category VARCHAR(30) NOT NULL,

                              transaction_date DATE NOT NULL,

                              user_id UUID NOT NULL,

                              created_at TIMESTAMP NOT NULL,

                              updated_at TIMESTAMP NOT NULL,

                              CONSTRAINT fk_transactions_user
                                  FOREIGN KEY (user_id)
                                      REFERENCES users(id)

);
