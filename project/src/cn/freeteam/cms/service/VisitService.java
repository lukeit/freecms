package cn.freeteam.cms.service;

import java.util.List;
import java.util.UUID;

import cn.freeteam.base.BaseService;
import cn.freeteam.cms.dao.VisitMapper;
import cn.freeteam.cms.model.Info;
import cn.freeteam.cms.model.InfoExample;
import cn.freeteam.cms.model.Visit;
import cn.freeteam.cms.model.VisitExample;
import cn.freeteam.cms.model.VisitExample.Criteria;

public class VisitService extends BaseService{

	private VisitMapper visitMapper;
	
	public VisitService() {
		initMapper("visitMapper");
	}

	/**
	 * 添加
	 * @return
	 */
	public String add(Visit visit){
		visit.setId(UUID.randomUUID().toString());
		visitMapper.insert(visit);
		DBCommit();
		return visit.getId();
	}
	/**
	 * 栏目访问统计 
	 * @param info
	 * @return
	 */
	public List<Visit> channelVisit(Visit visit){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		return visitMapper.channelVisit(example);
	}
	/**
	 * 栏目访问统计 
	 * @param info
	 * @return
	 */
	public List<Visit> channelVisit(Visit visit,int currPage,int pageSize){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		example.setCurrPage(currPage);
		example.setPageSize(pageSize);
		return visitMapper.channelVisitPage(example);
	}
	/**
	 * 栏目访问统计
	 * @param info
	 * @return
	 */
	public int channelVisitCount(Visit visit){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		return visitMapper.channelVisitCount(example);
	}
	/**
	 * 栏目访问合计
	 * @param info
	 * @return
	 */
	public int channelVisitSum(Visit visit){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		return visitMapper.channelVisitSum(example);
	}
	/**
	 * 站点访问合计
	 * @param info
	 * @return
	 */
	public int siteVisitSum(Visit visit){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		return visitMapper.siteVisitSum(example);
	}
	/**
	 * 站点访问统计 
	 * @param info
	 * @return
	 */
	public List<Visit> siteVisit(Visit visit){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		return visitMapper.siteVisit(example);
	}
	/**
	 * 站点访问统计 
	 * @param info
	 * @return
	 */
	public List<Visit> siteVisit(Visit visit,int currPage,int pageSize){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		example.setCurrPage(currPage);
		example.setPageSize(pageSize);
		return visitMapper.siteVisitPage(example);
	}
	/**
	 * 站点访问统计
	 * @param info
	 * @return
	 */
	public int siteVisitCount(Visit visit){
		VisitExample example=new VisitExample();
		Criteria criteria=example.createCriteria();
		proSearchParam(visit, criteria);
		return visitMapper.siteVisitCount(example);
	}
	/**
	 * 处理查询条件
	 * @param info
	 * @param criteria
	 */
	public void proSearchParam(Visit visit,Criteria criteria){
		if (visit!=null ) {
			if (visit.getChannelname()!=null && visit.getChannelname().trim().length()>0) {
				criteria.andSql(" c.name like '%"+visit.getChannelname().trim()+"%'");
			}
			if (visit.getSitename()!=null && visit.getSitename().trim().length()>0) {
				criteria.andSql(" s.name like '%"+visit.getSitename().trim()+"%'");
			}
			if (visit.getStarttime()!=null) {
				criteria.andVisitAddtimeGreaterThanOrEqualTo(visit.getStarttime());
			}
			if (visit.getEndtime()!=null) {
				criteria.andVisitAddtimeLessThanOrEqualTo(visit.getEndtime());
			}
			if ("channel".equals(visit.getStatType())) {
				criteria.andChannelidIsNotNull();
			}else if ("site".equals(visit.getStatType())) {
				criteria.andSiteidIsNotNull();
			}else if ("info".equals(visit.getStatType())) {
				criteria.andInfoidIsNotNull();
			}
		}
	}
	public VisitMapper getVisitMapper() {
		return visitMapper;
	}

	public void setVisitMapper(VisitMapper visitMapper) {
		this.visitMapper = visitMapper;
	}
}
