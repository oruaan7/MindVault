CREATE TABLE habits (
                        id UUID PRIMARY KEY,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP NOT NULL,

                        title VARCHAR(120) NOT NULL,
                        description VARCHAR(500),
                        active BOOLEAN NOT NULL DEFAULT TRUE,

                        user_id UUID NOT NULL,

                        CONSTRAINT fk_habits_user
                            FOREIGN KEY (user_id)
                                REFERENCES users(id)
                                ON DELETE CASCADE
);
