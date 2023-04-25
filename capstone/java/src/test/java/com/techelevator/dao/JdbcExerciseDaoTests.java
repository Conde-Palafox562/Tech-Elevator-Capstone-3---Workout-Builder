package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseNotFoundException;
import com.techelevator.model.Workout;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcExerciseDaoTests extends BaseDaoTests {

    protected static final Exercise Exercise1 = new Exercise(1L, 0L, "Stair Climbing", "Find some stairs and climb up and down. Great for inside or outside!","Cardio", "10 min","Cardio", 2, 5);

    protected static final Exercise Exercise2 = new Exercise(2L, 0L, "Elliptical Trainer", "An elliptical trainer or cross-trainer is a stationary exercise machine used to stair climb, walk, or run without causing excessive pressure to the joints, hence decreasing the risk of impact injuries.", "Cardio", "5 min", "Cardio", 2, 5);

    private JdbcExerciseDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcExerciseDao(jdbcTemplate);
    }

    @Test
    public void getAll_should_return_number_of_workouts() {
        List<Exercise> exerciseList = sut.getAll();

        Assert.assertNotNull(exerciseList);
        Assert.assertEquals(43, exerciseList.size());

    }

    @Test(expected = ExerciseNotFoundException.class)
    public void getExerciseById_given_null_should_throw_exception() {
        sut.getExerciseById(null);
    }

    @Test(expected = ExerciseNotFoundException.class)
    public void getExerciseById_when_invalid_exercise_throws_Exception() {
        sut.getExerciseById(-1L);
    }

    // @Test
    // public void getExerciseById_given_valid_Exercise_id_returns_exercise(){
    //     Exercise actualExercise = sut.getExerciseById(Exercise1.getId());
    //     Assert.assertEquals(Exercise1,actualExercise);
    // }

    @Test
    public void getExercisesByStatusId_returns_expected() {
        List<Exercise> exerciseList = sut.getExercisesByStatusId(2);

        Assert.assertEquals(43, exerciseList.size());
    }

    @Test
    public void getExercisesByUserId_returns_expected() {
        List<Exercise> actual = sut.getExercisesByUserId(2L);
        List<Exercise> expected = new ArrayList<>();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExercisesByMuscleGroup_returns_expected() {
        List<Exercise> actual = sut.findByMuscleGroup("Cardio");

        Assert.assertEquals(5, actual.size());
    }

    // @Test
    // public void getExercisesByWorkout_returns_expected() {
    //     List<Exercise> actual = sut.findByWorkout(1L);

    //     Assert.assertEquals(5, actual.size());
    // }
    

    
}

