package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.WorkoutUserDataDao;
import com.techelevator.model.Exercise;
import com.techelevator.model.User;
import com.techelevator.model.WorkoutUserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@CrossOrigin
    @RestController
@PreAuthorize("isAuthenticated()")
    public class WorkoutUserDataController {

        @Autowired
        WorkoutUserDataDao workoutUserDataDao;

        @Autowired
         UserDao userDao;

        @RequestMapping(path = "/workoutData", method = RequestMethod.GET)
        public List<WorkoutUserData>  allUserWorkoutData() {
            return workoutUserDataDao.allUserWorkoutData();
        }

        @RequestMapping(path = "/workoutData/add", method = RequestMethod.POST)
        public WorkoutUserData addNewWorkoutData(@RequestBody Exercise exercise, Principal token) {
           User user = userDao.findByUsername(token.getName());

            return workoutUserDataDao.createUserWorkoutData(user.getId(), LocalDate.now(), exercise);
        }

        @RequestMapping(path = "/workoutData/me", method = RequestMethod.GET)
        public List<WorkoutUserData> getWorkoutDataForUser(Principal token) {
            User user = userDao.findByUsername(token.getName());
            return workoutUserDataDao.getWorkoutDataForUser(user.getId());
        }

    }


