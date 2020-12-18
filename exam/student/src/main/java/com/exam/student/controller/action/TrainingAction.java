package com.exam.student.controller.action;

import com.exam.common.domain.exam.Message;
import com.exam.common.domain.training.UserTrainingHistory;
import com.exam.student.security.UserInfo;
import com.exam.student.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class TrainingAction {

	@Autowired
	private TrainingService trainingService;
	
	@RequestMapping(value = "student/set-training-hist", method = RequestMethod.POST)
	public @ResponseBody Message setTrainingHist(@RequestBody UserTrainingHistory history){
		
		UserInfo userInfo = (UserInfo) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		Date now = new Date();
		history.setUserId(userInfo.getUserid());
		history.setLastStateTime(now);
		Message msg = new Message();
		try {
			UserTrainingHistory historyOri = trainingService.getTrainingHistBySectionId(history.getSectionId(), userInfo.getUserid());
			if(historyOri != null){
				history.setStartTime(historyOri.getStartTime());
				history.setUserTrainingDetail(historyOri.getUserTrainingDetail());
			}else{
				history.setStartTime(now);
			}
			trainingService.setUserTrainingHistory(history);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg.setResult(e.getClass().getName());
			e.printStackTrace();
		}
		return msg;
	}
}
