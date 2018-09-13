package cc.sitec.kboot.service;

import cc.sitec.kboot.model.dao.CourseInfoDao;
import cc.sitec.kboot.model.pojo.CourseInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CourseService {
    @Autowired
    private CourseInfoDao courseInfoDao;

    public List<CourseInfo> selectAll(){
        return courseInfoDao.selectAll();
    }
    public PageInfo<CourseInfo>  selectAllPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(selectAll());
    }
}
