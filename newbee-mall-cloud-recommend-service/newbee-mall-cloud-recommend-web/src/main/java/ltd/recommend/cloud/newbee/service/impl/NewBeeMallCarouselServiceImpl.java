/**
 * 严肃声明：
 * 开源版本请务必保留此注释头信息，若删除我方将保留所有法律责任追究！
 * 本软件已申请软件著作权，受国家版权局知识产权以及国家计算机软件著作权保护！
 * 可正常分享和学习源码，不得用于违法犯罪活动，违者必究！
 * Copyright (c) 2023 程序员十三 all rights reserved.
 * 版权所有，侵权必究！
 */
package ltd.recommend.cloud.newbee.service.impl;

import ltd.common.cloud.newbee.enums.ServiceResultEnum;
import ltd.common.cloud.newbee.dto.PageQueryUtil;
import ltd.common.cloud.newbee.dto.PageResult;
import ltd.common.cloud.newbee.util.BeanUtil;
import ltd.recommend.cloud.newbee.controller.vo.NewBeeMallIndexCarouselVO;
import ltd.recommend.cloud.newbee.dao.CarouselMapper;
import ltd.recommend.cloud.newbee.entity.Carousel;
import ltd.recommend.cloud.newbee.service.NewBeeMallCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewBeeMallCarouselServiceImpl implements NewBeeMallCarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    @Override
    public PageResult getCarouselPage(PageQueryUtil pageUtil) {
        List<Carousel> carousels = carouselMapper.findCarouselList(pageUtil);
        int total = carouselMapper.getTotalCarousels(pageUtil);
        PageResult pageResult = new PageResult(carousels, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String saveCarousel(Carousel carousel) {
        if (carouselMapper.insertSelective(carousel) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String updateCarousel(Carousel carousel) {
        Carousel temp = carouselMapper.selectByPrimaryKey(carousel.getCarouselId());
        if (temp == null) {
            return ServiceResultEnum.DATA_NOT_EXIST.getResult();
        }
        temp.setCarouselRank(carousel.getCarouselRank());
        temp.setRedirectUrl(carousel.getRedirectUrl());
        temp.setCarouselUrl(carousel.getCarouselUrl());
        temp.setUpdateTime(new Date());
        if (carouselMapper.updateByPrimaryKeySelective(temp) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public Carousel getCarouselById(Integer id) {
        return carouselMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean deleteBatch(Long[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除数据
        return carouselMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<NewBeeMallIndexCarouselVO> getCarouselsForIndex(int number) {
        List<NewBeeMallIndexCarouselVO> newBeeMallIndexCarouselVOS = new ArrayList<>(number);
        List<Carousel> carousels = carouselMapper.findCarouselsByNum(number);
        if (!CollectionUtils.isEmpty(carousels)) {
            newBeeMallIndexCarouselVOS = BeanUtil.copyList(carousels, NewBeeMallIndexCarouselVO.class);
        }
        return newBeeMallIndexCarouselVOS;
    }

}
