package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WorkoutUserData {

    private int workout_user_data_id;
    private int user_id;
    private int exercise_id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate workout_date;
    private int abs_duration;
    private int legs_duration;
    private int back_duration;
    private int cardio_duration;
    private int arms_duration;
    private String exercise_name;

    public String getExercise_name() {
        return exercise_name;
    }
public void setExercise_name(String exercise_name){
        this.exercise_name = exercise_name;
}



    public WorkoutUserData () {

    }

    public int getWorkout_user_data_id() {
        return workout_user_data_id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public void setWorkout_user_data_id(int workout_user_data_id) {
        this.workout_user_data_id = workout_user_data_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LocalDate getWorkout_date() {
        return workout_date;
    }

    public void setWorkout_date(LocalDate workout_date) {
        this.workout_date = workout_date;
    }

    public int getAbs_duration() {
        return abs_duration;
    }

    public void setAbs_duration(int abs_duration) {
        this.abs_duration = abs_duration;
    }

    public int getLegs_duration() {
        return legs_duration;
    }

    public void setLegs_duration(int legs_duration) {
        this.legs_duration = legs_duration;
    }

    public int getBack_duration() {
        return back_duration;
    }

    public void setBack_duration(int back_duration) {
        this.back_duration = back_duration;
    }

    public int getCardio_duration() {
        return cardio_duration;
    }

    public void setCardio_duration(int cardio_duration) {
        this.cardio_duration = cardio_duration;
    }

    public int getArms_duration() {
        return arms_duration;
    }

    public void setArms_duration(int arms_duration) {
        this.arms_duration = arms_duration;
    }
}