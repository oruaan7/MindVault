CREATE TABLE goals (

                       id UUID PRIMARY KEY,

                       title VARCHAR(120) NOT NULL,

                       description VARCHAR(500),

                       target_value INTEGER NOT NULL,

                       current_value INTEGER NOT NULL DEFAULT 0,

                       completed BOOLEAN NOT NULL DEFAULT FALSE,

                       user_id UUID NOT NULL,

                       created_at TIMESTAMP NOT NULL,

                       updated_at TIMESTAMP NOT NULL,

                       CONSTRAINT fk_goals_user
                           FOREIGN KEY (user_id)
                               REFERENCES users(id)
                               ON DELETE CASCADE

);
