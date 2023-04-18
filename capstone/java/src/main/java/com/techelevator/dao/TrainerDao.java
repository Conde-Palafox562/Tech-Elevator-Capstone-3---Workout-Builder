package com.techelevator.dao;

import com.techelevator.model.Trainer;


import java.util.List;

public interface TrainerDao {

    List<Trainer> getAllTrainers();

    boolean assignToUser(int user_id, Trainer trainer);

}