package in.prachi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import in.prachi.dto.PlanDto;
import in.prachi.entity.PlanMaster;
import in.prachi.repo.PlanMasterRepo;
@Service
public class PlanServiceImpl implements PlanService {

	private PlanMasterRepo planMasterRepo;
	@Override
	public boolean savePlan(PlanDto planDto) {
		PlanMaster entity= new PlanMaster();
		BeanUtils.copyProperties(planDto, entity);
		entity.setActiveSw("Y");
		PlanMaster savedPlan = planMasterRepo.save(entity);
		return savedPlan.getPlanId()!=null;
	}

	@Override
	public List<PlanDto> getPlans() {
		List<PlanMaster> entities = planMasterRepo.findAll();
		List<PlanDto> dtos= new ArrayList<>();
		entities.forEach(e -> {
			PlanDto dto= new PlanDto();
			BeanUtils.copyProperties(e,dto);
			dtos.add(dto);
		});
		return dtos;
	}

	@Override
	public PlanDto getPlan(Integer planId) {
		PlanMaster entity = planMasterRepo.findById(planId).orElseThrow();
		PlanDto dto= new PlanDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
		
	}

	@Override
	public boolean updatePlan(Integer planId, String status) {
		PlanMaster entity = planMasterRepo.findById(planId).orElseThrow();
		entity.setActiveSw(status);
		planMasterRepo.save(entity);
		return true;
	}

}
