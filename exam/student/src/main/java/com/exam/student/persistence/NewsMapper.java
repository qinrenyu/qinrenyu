package com.exam.student.persistence;

import com.exam.common.domain.news.News;
import com.exam.common.util.Page;

import java.util.List;

public interface NewsMapper {

	public List<News> getNewsList(Page<News> page);
	
	public News getNewsById(int newsId);
}
