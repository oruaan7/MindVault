CREATE TABLE notes (

                       id UUID PRIMARY KEY,

                       title VARCHAR(150) NOT NULL,

                       content TEXT NOT NULL,

                       favorite BOOLEAN NOT NULL DEFAULT FALSE,

                       archived BOOLEAN NOT NULL DEFAULT FALSE,

                       user_id UUID NOT NULL,

                       created_at TIMESTAMP NOT NULL,

                       updated_at TIMESTAMP NOT NULL,

                       CONSTRAINT fk_notes_user
                           FOREIGN KEY (user_id)
                               REFERENCES users(id)
                               ON DELETE CASCADE

);
