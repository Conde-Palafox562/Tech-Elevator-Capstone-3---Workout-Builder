package com.techelevator.dao;

import com.techelevator.model.Exercise;

import java.util.List;

public interface ExerciseDao {

    List<Exercise> allExercises();

    Exercise getExerciseById(int exercise_id);

    boolean createExercise(Exercise exercise);

    Exercise updateExercise(Exercise exercise, int exercise_id);

    void deleteExercise(int exercise_id);
}