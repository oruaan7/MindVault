CREATE TABLE habit_records (

                               id UUID PRIMARY KEY,

                               date DATE NOT NULL,

                               completed BOOLEAN NOT NULL,

                               habit_id UUID NOT NULL,

                               CONSTRAINT fk_habit_records_habit
                                   FOREIGN KEY (habit_id)
                                       REFERENCES habits(id)
                                       ON DELETE CASCADE
);
