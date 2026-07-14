CREATE TABLE projects (

                          id UUID PRIMARY KEY,

                          title VARCHAR(150) NOT NULL,

                          description TEXT,

                          status VARCHAR(30) NOT NULL,

                          priority VARCHAR(30) NOT NULL,

                          user_id UUID NOT NULL,

                          created_at TIMESTAMP NOT NULL,

                          updated_at TIMESTAMP NOT NULL,

                          CONSTRAINT fk_projects_user
                              FOREIGN KEY (user_id)
                                  REFERENCES users(id)
                                  ON DELETE CASCADE

);
