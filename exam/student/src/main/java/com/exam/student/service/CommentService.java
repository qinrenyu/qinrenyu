package com.exam.student.service;

import com.exam.common.domain.question.Comment;
import com.exam.common.util.Page;

import java.util.List;


public interface CommentService {

	public List<Comment> getCommentByTypeAndReferId(int referType, int referId, int indexId, Page<Comment> page);
	
	public void addComment(Comment comment);
}
