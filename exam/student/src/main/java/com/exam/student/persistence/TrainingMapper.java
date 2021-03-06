package com.exam.student.persistence;

import com.exam.common.domain.training.Training;
import com.exam.common.domain.training.TrainingSection;
import com.exam.common.domain.training.TrainingSectionProcess;
import com.exam.common.domain.training.UserTrainingHistory;
import com.exam.common.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface TrainingMapper {

	/**
	 * 获取培训列表
	 * @param page
	 * @return
	 */
	public List<Training> getTrainingList(@Param("page") Page<Training> page);
		
	/**
	 * 获取培训章节
	 * @param trainingId
	 * @param page
	 * @return
	 */
	public List<TrainingSection> getTrainingSectionByTrainingId(@Param("trainingId") int trainingId, @Param("page") Page<TrainingSection> page);

	/**
	 * 获取培训章节
	 * @param sectionId
	 * @param page
	 * @return
	 */
	public List<TrainingSection> getTrainingSectionById(@Param("sectionId") int sectionId, @Param("page") Page<TrainingSection> page);

	/**
	 * 获取用户培训历史
	 * @param sectionId
	 * @param userId
	 * @return
	 */
	public UserTrainingHistory getTrainingHistBySectionId(@Param("sectionId") int sectionId, @Param("userId") int userId);

	/**
	 * 增加或更新用户培训历史
	 * @param hist
	 */
	public void setUserTrainingHistory(UserTrainingHistory hist);

	/**
	 * 获取用户培训进度清单
	 * @param userId
	 * @param page
	 * @return
	 */
	public List<TrainingSectionProcess> getTrainingSectionProcessListByUserId(@Param("userId") int userId, @Param("page") Page<TrainingSectionProcess> page);
}
