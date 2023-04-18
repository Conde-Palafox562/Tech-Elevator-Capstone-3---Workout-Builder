package com.techelevator.model;

public class Exercise {

    private int exercise_id;
    private String exercise_name;
    private String exercise_description;
    private int exercise_duration;
    private String target_area;
    private int suggested_weight;
    private int num_of_reps;

    public Exercise() {

    }


public Exercise(int exercise_id, String exercise_name, String exercise_description, int exercise_duration,
String target_area, int suggested_weight, int num_of_reps) {

    this.exercise_id = exercise_id;
    this.exercise_name = exercise_name;
    this.exercise_description = exercise_description;
    this.exercise_duration = exercise_duration;
    this.target_area = target_area;
    this.suggested_weight = suggested_weight;
    this.num_of_reps = num_of_reps;

}

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getExercise_description() {
        return exercise_description;
    }

    public void setExercise_description(String exercise_description) {
        this.exercise_description = exercise_description;
    }

    public int getExercise_duration() {
        return exercise_duration;
    }

    public void setExercise_duration(int exercise_duration) {
        this.exercise_duration = exercise_duration;
    }

    public String getTarget_area() {
        return target_area;
    }

    public void setTarget_area(String target_area) {
        this.target_area = target_area;
    }

    public int getSuggested_weight() {
        return suggested_weight;
    }

    public void setSuggested_weight(int suggested_weight) {
        this.suggested_weight = suggested_weight;
    }

    public int getNum_of_reps() {
        return num_of_reps;
    }

    public void setNum_of_reps(int num_of_reps) {
        this.num_of_reps = num_of_reps;
    }
}
