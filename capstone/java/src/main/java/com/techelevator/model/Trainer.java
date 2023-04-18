package com.techelevator.model;

public class Trainer {
    private int trainer_id;
    private String trainer_name;

public Trainer () {

}

public Trainer (int trainer_id, String trainer_name) {
    this.trainer_id = trainer_id;
    this.trainer_name = trainer_name;
}

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getTrainer_name() {
        return trainer_name;
    }

    public void setTrainer_name(String trainer_name) {
        this.trainer_name = trainer_name;
    }
}