package in.prachi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.prachi.dto.PlanDto;
import in.prachi.service.PlanService;

@RestController
public class PlanRestController {

	@Autowired
	
	private PlanService planService;
	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody PlanDto planDto){
		boolean savePlan = planService.savePlan(planDto);
		if (savePlan) {
			return new ResponseEntity<>("Plan Saved", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Failed To Save", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/plans")
	public ResponseEntity<List<PlanDto>>getPlans(){
		List<PlanDto> plans = planService.getPlans();
		return new ResponseEntity<>(plans,HttpStatus.OK);
	}
	@GetMapping("/plan/{planId}")
	public ResponseEntity<PlanDto>getPlan(@PathVariable ("planId")Integer planId){
		PlanDto plan = planService.getPlan(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}
	@PutMapping("/plan/{planId}/{activeSw}")
	public ResponseEntity<String>updatePlanStatus(@PathVariable ("planId")Integer planId, @PathVariable("activeSw") String acticeSw){
		boolean status = planService.updatePlan(planId, acticeSw);
		if (status) {
			return new ResponseEntity<>("Plan Updated", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Update Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
