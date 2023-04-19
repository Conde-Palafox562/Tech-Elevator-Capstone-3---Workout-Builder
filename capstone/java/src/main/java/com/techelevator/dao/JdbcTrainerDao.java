package com.techelevator.dao;

import com.techelevator.model.Trainer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTrainerDao implements TrainerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTrainerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "select * from trainer";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Trainer trainer = mapRowToTrainer(results);
            trainers.add(trainer);
        }

        return trainers;
    }

    public boolean assignToUser(int user_id, Trainer trainer) {
        String sql = "UPDATE users SET trainer_id =? WHERE user_id = ?";
        Integer newId;
        try {
            newId = jdbcTemplate.update(sql, trainer.getTrainer_id(), user_id);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    private Trainer mapRowToTrainer(SqlRowSet rs) {
        Trainer trainer = new Trainer();
        trainer.setTrainer_id(rs.getInt("trainer_id"));
        trainer.setTrainer_name(rs.getString("trainer_name"));
        return trainer;
    }
}