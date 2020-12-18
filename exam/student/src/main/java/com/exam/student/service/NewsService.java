package com.exam.student.service;

import com.exam.common.domain.news.News;
import com.exam.common.util.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {

	public List<News> getNewsList(Page<News> page);
	
	public News getNewsById(int newsId);
	
}
