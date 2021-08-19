package com.nsapi.niceschoolapi.service.impl;

import com.nsapi.niceschoolapi.entity.TuitionDB;
import com.nsapi.niceschoolapi.mapper.StuTuitionMapper;
import com.nsapi.niceschoolapi.service.StuTuitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class StuTutionServiceImpl implements StuTuitionService {

    @Resource
    private StuTuitionMapper stuTuitionMapper;

    @Override
    public List<TuitionDB> listTuitions() {
        return stuTuitionMapper.listTuitions();
    }

    @Override
    public TuitionDB getTuition(Integer sid) {
        return stuTuitionMapper.getTuition(sid);
    }

    @Override
    public Integer addTuition(TuitionDB tuition) {
        return stuTuitionMapper.addTuition(tuition);
    }

    @Override
    public Integer updateTuition(Integer sid) {
        return stuTuitionMapper.updateTuition(sid);
    }
}
