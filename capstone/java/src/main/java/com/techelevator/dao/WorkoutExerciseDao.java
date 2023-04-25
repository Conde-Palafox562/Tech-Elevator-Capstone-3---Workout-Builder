package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.WorkoutExercise;

public interface WorkoutExerciseDao {

    WorkoutExercise getExerciseByWorkoutId(long workoutId);

    boolean deleteWorkoutExercise(Long id);

    boolean deleteExerciseWorkout(Long id);

    boolean deleteExerciseFromWorkout(Long id, Long workoutID);

    boolean updateWorkoutExercise(Long id, WorkoutExercise updatedWorkout);

    void add(Long id, WorkoutExercise workoutExercise );

}