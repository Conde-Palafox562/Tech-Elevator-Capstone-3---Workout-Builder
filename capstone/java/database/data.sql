BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Warm-Up', 'Begin your workout with a quick jog and stretch to warm-up and prepare your body for aerobic activities.'
		, 0, 0, 5, 'Cardio');


INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Dumbbell Curl', 'Slowly curl the weight up by bending your elbow, keeping your elbow close to your body. Then slowly lower the weight to the starting position.', 30, 10, 5, 'Arms');

INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Hammer Curl', 'Hammer curls are biceps curls performed with your hands facing each other. They’re beneficial to add mass to your arms and can help focus more attention on the short head of the biceps.', 30, 8, 6, 'Arms');

INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Preacher Curl', 'A weightlifting exercise for the biceps in which a barbell is lifted by flexing the elbows, with the upper arms resting on an angled bench.', 60, 8, 5, 'Arms');


INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Push-Ups', 'Keeping a prone position, with the hands palms down under the shoulders, the balls of the feet on the ground, and the back straight, pushes the body up and lets it down by an alternate straightening and bending of the arms.', 0, 10, 5, 'Arms');


INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Bodyweight Dip', 'The bodyweight dip is a compound upper-body pressing exercise that strengthens the shoulders, chest, and triceps. Leaning forward targets the chest while an upright position targets the triceps.', 0, 15, 6, 'Arms');


INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Lateral Raise', 'Grab weighted dumbbells and lift them away from your body, bring your arms into a T-pose. Slowly lower your arms back to your sides to complete one repetition.'
		, 15, 8, 1, 'Arms');

		
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Dumbbell Front Raise', 'Grab a dumbbell in each arm and then raise your arms up in front of you.'
		, 15, 8, 1, 'Arms');




INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Russian Twist', 'Sit on the floor with your knees bent and feet flat on the ground, hold your hands together in front of your chest and rotate side to side to complete one rep. ', 0, 20, 5, 'Abs');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Scissor Kicks', 'Lie flat on your back with your arms at your sides and legs straight up in the air. Lower your left leg until it is a few inches off the ground. Switch legs by lifting your left leg and lowering your right leg to complete one rep. ', 0, 20, 5, 'Abs');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Mountain Climbers', 'Start in a push-up position with your hands shoulder-width apart. Bring your right knee to your chest, keeping your foot off the ground. Return to starting position and repeat with your left leg to complete one rep. ', 0, 20, 5, 'Abs');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Leg Raise', 'Lie flat on your back with arms at your sides and legs straight. Lift your legs until they are perpendicular to the ground. Lower your legs back to the starting position to complete one rep.', 0, 20, 5, 'Abs');




INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Squats', 'Stand with feet shoulder-width apart, holding a barbell or dumbbells at your shoulders. Lower your hips back and down as if you were sitting in a chair. Keep your back straight and your weight on your heels. Push back up to the starting position', 200, 12, 10, 'Legs');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Lunges', 'Stand with your feet hip-width apart and your hands on your hips. Take a large step forward with your right foot. Lower your body until your right thigh is parallel to the ground. Push back up to the starting position and repeat with your left leg to complete one rep.', 0, 12, 10, 'Legs');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Deadlifts', 'Stand with feet hip-width apart, holding a barbell in front of your thighs. Keeping your back straight, bend at the hips and lower the bar to the ground. Push through your heels to return to the starting position.', 200, 12, 10, 'Legs');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area) VALUES ('Calf Raises', 'Stand with feet hip-width apart, holding a barbell or dumbbells for added resistance. Raise your heels off the ground as high as you can. Lower your heels back to the starting position.', 100, 12, 10, 'Legs');


INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Lat Pulldown', 'While seated, you pull a hanging bar toward you to reach chin level, then release it back up with control for one repetition.'
		, 50, 8, 1, 'Back');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Wide-Grip Pull-Up', 'Do a pull-up with a wider grip than usual.'
		, 0, 4, 1, 'Back');
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Deadlift', 'Lift a loaded barbell off the ground to the level of the hips, torso perpendicular to the floor, before being placed back on the ground.'
		, 95, 8, 1, 'Back');
		
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Bent-Over Row', 'Hing your hips and lean foward to lift a weighted barbell toward your body with a rowing movement pattern.'
		, 30, 8, 1, 'Back');


INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Running', 'Move at a speed faster than a walk, never having both feet on the ground at the same time.'
		, 0, 1, 15, 'Cardio');
		
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Jogging', 'Run at a steady gentle pace.'
		, 0, 1, 30, 'Cardio');
		
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Swimming', 'Propel yourself through a pool of water using your limbs.'
		, 0, 1, 15, 'Cardio');
		
INSERT INTO exercise (exercise_name, exercise_description, suggested_weight, num_of_reps, exercise_duration, target_area)
VALUES ('Jumping Jacks', 'Jump into a position with your legs spread wide and your hands going overhead, then return to the standing position with your feet together and arms at your sides.'
		, 0, 60, 1, 'Cardio');

INSERT INTO workout_user_data (workout_date, abs_duration, legs_duration, back_duration, cardio_duration, arms_duration)
VALUES ('04/18/2023', 5, 5, 5, 5, 5 );

INSERT INTO trainer (trainer_name)
VALUES ('Chyna Broyles');

INSERT INTO trainer (trainer_name)
VALUES ('Raul Conde');

INSERT INTO trainer (trainer_name)
VALUES ('Victoria Johnson');

INSERT INTO trainer (trainer_name)
VALUES ('Romance Cox');

COMMIT TRANSACTION;
