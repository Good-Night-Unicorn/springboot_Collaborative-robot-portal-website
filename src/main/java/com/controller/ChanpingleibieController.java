package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ChanpingleibieEntity;
import com.entity.view.ChanpingleibieView;

import com.service.ChanpingleibieService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 产品类别
 * 后端接口
 * @author 
 * @email 
 * @date 2024-04-21 14:06:52
 */
@RestController
@RequestMapping("/chanpingleibie")
public class ChanpingleibieController {
    @Autowired
    private ChanpingleibieService chanpingleibieService;




    



    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ChanpingleibieEntity chanpingleibie,
		HttpServletRequest request){
        EntityWrapper<ChanpingleibieEntity> ew = new EntityWrapper<ChanpingleibieEntity>();

		PageUtils page = chanpingleibieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpingleibie), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ChanpingleibieEntity chanpingleibie, 
		HttpServletRequest request){
        EntityWrapper<ChanpingleibieEntity> ew = new EntityWrapper<ChanpingleibieEntity>();

		PageUtils page = chanpingleibieService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, chanpingleibie), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ChanpingleibieEntity chanpingleibie){
       	EntityWrapper<ChanpingleibieEntity> ew = new EntityWrapper<ChanpingleibieEntity>();
      	ew.allEq(MPUtil.allEQMapPre( chanpingleibie, "chanpingleibie")); 
        return R.ok().put("data", chanpingleibieService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ChanpingleibieEntity chanpingleibie){
        EntityWrapper< ChanpingleibieEntity> ew = new EntityWrapper< ChanpingleibieEntity>();
 		ew.allEq(MPUtil.allEQMapPre( chanpingleibie, "chanpingleibie")); 
		ChanpingleibieView chanpingleibieView =  chanpingleibieService.selectView(ew);
		return R.ok("查询产品类别成功").put("data", chanpingleibieView);
    }
	
    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ChanpingleibieEntity chanpingleibie = chanpingleibieService.selectById(id);
        return R.ok().put("data", chanpingleibie);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ChanpingleibieEntity chanpingleibie = chanpingleibieService.selectById(id);
        return R.ok().put("data", chanpingleibie);
    }
    



    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ChanpingleibieEntity chanpingleibie, HttpServletRequest request){
        if(chanpingleibieService.selectCount(new EntityWrapper<ChanpingleibieEntity>().eq("chanpingleibie", chanpingleibie.getChanpingleibie()))>0) {
            return R.error("产品类别已存在");
        }
    	//ValidatorUtils.validateEntity(chanpingleibie);
        chanpingleibieService.insert(chanpingleibie);
        return R.ok();
    }
    
    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ChanpingleibieEntity chanpingleibie, HttpServletRequest request){
        if(chanpingleibieService.selectCount(new EntityWrapper<ChanpingleibieEntity>().eq("chanpingleibie", chanpingleibie.getChanpingleibie()))>0) {
            return R.error("产品类别已存在");
        }
    	//ValidatorUtils.validateEntity(chanpingleibie);
        chanpingleibieService.insert(chanpingleibie);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ChanpingleibieEntity chanpingleibie, HttpServletRequest request){
        //ValidatorUtils.validateEntity(chanpingleibie);
        if(chanpingleibieService.selectCount(new EntityWrapper<ChanpingleibieEntity>().ne("id", chanpingleibie.getId()).eq("chanpingleibie", chanpingleibie.getChanpingleibie()))>0) {
            return R.error("产品类别已存在");
        }
        chanpingleibieService.updateById(chanpingleibie);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        chanpingleibieService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
