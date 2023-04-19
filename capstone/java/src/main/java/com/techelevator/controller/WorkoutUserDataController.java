package com.techelevator.controller;

import com.techelevator.dao.WorkoutUserDataDao;
import com.techelevator.model.WorkoutUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@CrossOrigin
    @RestController
    public class WorkoutUserDataController {

        @Autowired
        WorkoutUserDataDao workoutUserDataDao;

        @RequestMapping(path = "/workoutData", method = RequestMethod.GET)
        public List<WorkoutUserData>  allUserWorkoutData() {
            return workoutUserDataDao.allUserWorkoutData();
        }

        @RequestMapping(path = "/workoutData/add/{user}/{date}/{exercise}", method = RequestMethod.POST)
        public WorkoutUserData addNewWorkoutData(@PathVariable int user, @PathVariable String date, @PathVariable int exercise) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);

            return workoutUserDataDao.createUserWorkoutData(user, localDate, exercise);
        }

        @RequestMapping(path = "/workoutData/{user}", method = RequestMethod.GET)
        public List<WorkoutUserData> getWorkoutDataForUser(@PathVariable int user) {
            return workoutUserDataDao.getWorkoutDataForUser(user);
        }

    }


