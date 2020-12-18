package com.exam.student.service;

import com.exam.common.domain.news.News;
import com.exam.common.util.Page;
import com.exam.student.persistence.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	@Override
	public List<News> getNewsList(Page<News> page) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsList(page);
	}

	@Override
	public News getNewsById(int newsId) {
		// TODO Auto-generated method stub
		return newsMapper.getNewsById(newsId);
	}

}
