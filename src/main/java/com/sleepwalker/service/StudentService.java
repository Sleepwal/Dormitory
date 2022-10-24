package com.sleepwalker.service;

import com.sleepwalker.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sleepwalker.form.SearchForm;
import com.sleepwalker.form.StudentForm;
import com.sleepwalker.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SleepWalker
 * @since 2022-10-09
 */
public interface StudentService extends IService<Student> {
    Boolean mySave(Student student);
    PageVO list(Integer page, Integer size);
    PageVO search(SearchForm searchForm);
    Boolean update(StudentForm studentForm);
    Boolean delete(Integer id);
}
