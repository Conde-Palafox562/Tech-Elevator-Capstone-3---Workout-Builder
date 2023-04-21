package com.techelevator.dao;


import com.techelevator.model.Trainer;
import com.techelevator.model.WorkoutUserData;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcWorkoutUserDataDao implements WorkoutUserDataDao {

    private final JdbcTemplate jdbcTemplate;
    public JdbcWorkoutUserDataDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<WorkoutUserData> allUserWorkoutData() {
        List<WorkoutUserData> workoutData = new ArrayList<>();
        String sql = "SELECT * FROM workout_user_data";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            WorkoutUserData workoutUsersData = mapRowToWorkoutData(results);
            workoutData.add(workoutUsersData);
        }
        return workoutData;
    }



    @Override
    public WorkoutUserData getWorkoutDataById(int workout_user_data_id) {
        String sql = "SELECT * FROM workout_user_data WHERE workout_user_data_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, workout_user_data_id);
        if (results.next()) {
            return mapRowToWorkoutData(results);
        } else {
            return null;
        }
    }


    @Override
    public WorkoutUserData createUserWorkoutData(int user_id, LocalDate workout_date, Exercise exercise) {
        WorkoutUserData workoutData = new WorkoutUserData();
        workoutData.setUser_id(user_id);
        workoutData.setExercise_id(exercise.getExercise_id());
        workoutData.setWorkout_date(workout_date);
        if (exercise.getTarget_area().equals("Legs")) {
            workoutData.setLegs_duration(exercise.getExercise_duration());
        } else if (exercise.getTarget_area().equals("Arms")) {
            workoutData.setArms_duration(exercise.getExercise_duration());
            workoutData.setAbs_duration(exercise.getExercise_duration());
        } else if (exercise.getTarget_area().equals("Abs")) {
            workoutData.setAbs_duration(exercise.getExercise_duration());
        } else if (exercise.getTarget_area().equals("Back")) {
            workoutData.setBack_duration(exercise.getExercise_duration());
        } else if (exercise.getTarget_area().equals("Cardio")) {
            workoutData.setCardio_duration(exercise.getExercise_duration());
        }

/*
        String exercisesql = "SELECT * FROM exercise WHERE exercise_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(exercisesql, workoutData.getExercise_id());
        if (results.next()) {
            if(results.getString("target_area").equals("abs")) {
                workoutData.setAbs_duration(results.getInt("exercise_duration"));
            }

            if(results.getString("target_area").equals("legs")) {
                workoutData.setLegs_duration(results.getInt("exercise_duration"));
            }

            if(results.getString("target_area").equals("back")) {
                workoutData.setBack_duration(results.getInt("exercise_duration"));
            }

            if(results.getString("target_area").equals("cardio")) {
                workoutData.setCardio_duration(results.getInt("exercise_duration"));
            }

            if(results.getString("target_area").equals("arms")) {
                workoutData.setArms_duration(results.getInt("exercise_duration"));
            }


        }
*/
        String sql = "insert into workout_user_data(user_id, exercise_id, workout_date, abs_duration, legs_duration, back_duration, cardio_duration, arms_duration) VALUES (?,?,?,?,?,?,?,?) RETURNING workout_user_data_id";
        Integer newId;
        try {
            newId = jdbcTemplate.queryForObject(sql,
                    Integer.class, workoutData.getUser_id(), workoutData.getExercise_id(), workoutData.getWorkout_date(), workoutData.getAbs_duration(),
            workoutData.getLegs_duration(), workoutData.getBack_duration(), workoutData.getCardio_duration(), workoutData.getArms_duration());
            workoutData.setWorkout_user_data_id(newId);
        } catch (DataAccessException e){
            return workoutData;
        }
        return workoutData;



    }

    @Override
    public List<WorkoutUserData> getWorkoutDataForUser(int user_id) {
        List<WorkoutUserData> workoutData = new ArrayList<>();
        String sql = "SELECT * FROM workout_user_data WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);
        while (results.next()) {
           WorkoutUserData workoutUserData = mapRowToWorkoutData(results);
           String sql2 = "SELECT exercise_name FROM exercise WHERE exercise_id =?";
           String nameResult = jdbcTemplate.queryForObject(sql2,String.class,workoutUserData.getExercise_id());
           workoutUserData.setExercise_name(nameResult);
           workoutData.add(workoutUserData);
        }
        return workoutData;
    }




    private WorkoutUserData mapRowToWorkoutData(SqlRowSet rs) {
        WorkoutUserData workoutUsersData = new WorkoutUserData();
        workoutUsersData.setWorkout_user_data_id(rs.getInt("workout_user_data_id"));
        workoutUsersData.setUser_id(rs.getInt("user_id"));
        workoutUsersData.setExercise_id(rs.getInt("exercise_id"));
        workoutUsersData.setWorkout_date(rs.getDate("workout_date").toLocalDate());
        workoutUsersData.setAbs_duration(rs.getInt("abs_duration"));
        workoutUsersData.setLegs_duration(rs.getInt("legs_duration"));
        workoutUsersData.setBack_duration(rs.getInt("back_duration"));
        workoutUsersData.setCardio_duration(rs.getInt("cardio_duration"));
        workoutUsersData.setArms_duration(rs.getInt("arms_duration"));

        return workoutUsersData;
    }
}
