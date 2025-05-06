package org.example.calcutask.Controller;

import org.example.calcutask.Service.SubtaskService;
import org.springframework.stereotype.Controller;

@Controller
public class SubtaskController {


        private final SubtaskService subtaskService;
        public SubtaskController(SubtaskService subtaskService) {
            this.subtaskService = subtaskService;
        }



    }





