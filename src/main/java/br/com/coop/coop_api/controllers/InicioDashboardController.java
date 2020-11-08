package br.com.coop.coop_api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coop.coop_api.entities.Doacao;
import br.com.coop.coop_api.services.InicioDashboardService;
import lombok.RequiredArgsConstructor;

@RestController 
@CrossOrigin 
@RequestMapping("/api/Iniciodashboard") 
@RequiredArgsConstructor
public class InicioDashboardController {
	private final InicioDashboardService DashService; 
	@GetMapping
	public List<Object[]> getLista(){
		
	   return DashService.getLista(); 	
	}
	


}
