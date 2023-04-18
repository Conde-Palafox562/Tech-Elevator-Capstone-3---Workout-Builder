BEGIN TRANSACTION;


DROP TABLE IF EXISTS workout_user_data;
DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS trainer;

CREATE TABLE trainer (
	trainer_id SERIAL NOT NULL,
	trainer_name varchar (100) NOT NULL,
	
	CONSTRAINT PK_trainer PRIMARY KEY (trainer_id)
	
	
);



CREATE TABLE users (
	user_id SERIAL,
	trainer_id int,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_users PRIMARY KEY (user_id),
	CONSTRAINT FK_users_trainer FOREIGN KEY (trainer_id) REFERENCES trainer (trainer_id)
);

CREATE TABLE exercise (
	exercise_id SERIAL,
	exercise_name varchar (50) NOT NULL,
	exercise_description varchar (1000) NOT NULL,
	suggested_weight int,
	num_of_reps int,
	exercise_duration int NOT NULL,
	target_area varchar (50) NOT NULL,
	
	
	
	CONSTRAINT PK_exercise PRIMARY KEY (exercise_id)
	

);

CREATE TABLE workout_user_data (
	workout_user_data_id SERIAL,
	user_id int,
	exercise_id int,
	workout_date DATE,
	abs_duration int,
	legs_duration int,
	back_duration int,
	cardio_duration int,
	arms_duration int,
	
	CONSTRAINT PK_workout_user_data PRIMARY KEY (workout_user_data_id),
	CONSTRAINT FK_workout_user_data_exercise FOREIGN KEY (exercise_id) REFERENCES exercise (exercise_id),
	CONSTRAINT FK_workout_user_data_users FOREIGN KEY (user_id) REFERENCES users (user_id)


);




COMMIT TRANSACTION;

