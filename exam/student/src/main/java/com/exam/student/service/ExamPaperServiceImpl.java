package com.exam.student.service;

import com.exam.common.domain.exam.ExamPaper;
import com.exam.student.persistence.ExamPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("examPaperService")
public class ExamPaperServiceImpl implements ExamPaperService {

	@Autowired
	private ExamPaperMapper examPaperMapper;
	
	@Override
	public ExamPaper getExamPaperById(int examPaperId) {
		// TODO Auto-generated method stub
		return examPaperMapper.getExamPaperById(examPaperId);
	}

}
