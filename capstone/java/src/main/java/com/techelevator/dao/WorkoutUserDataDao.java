package com.techelevator.dao;

import com.techelevator.model.WorkoutUserData;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

public interface WorkoutUserDataDao {

    List<WorkoutUserData> allUserWorkoutData();

    WorkoutUserData getWorkoutDataById(int workout_user_data_id);

    WorkoutUserData createUserWorkoutData(int user_id, LocalDate workout_date, int exercise_id);

    List<WorkoutUserData> getWorkoutDataForUser(int user_id);
}
