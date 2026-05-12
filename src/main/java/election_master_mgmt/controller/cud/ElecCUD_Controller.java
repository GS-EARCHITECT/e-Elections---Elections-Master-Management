package election_master_mgmt.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import election_master_mgmt.model.dto.ElecMaster_DTO;
import election_master_mgmt.service.cud.I_ElecMasterCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/electionCUDMgmt")
public class ElecCUD_Controller 
{

	private static final Logger logger = LoggerFactory.getLogger(ElecCUD_Controller.class);

	@Autowired
	private I_ElecMasterCUD_Service electionMasterCUDServ;

	@PostMapping("/new")
	public ResponseEntity<ElecMaster_DTO> newElection(@RequestBody ElecMaster_DTO serviceRequestDTO) 
	{
		CompletableFuture<ElecMaster_DTO> future = electionMasterCUDServ.newElection(serviceRequestDTO);
		ElecMaster_DTO srvRequestDTO = null;		
		srvRequestDTO = future.join();		
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(srvRequestDTO, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updElection")
	public void updElection(@RequestBody ElecMaster_DTO electionDTO) 
	{
		CompletableFuture<Void> future = electionMasterCUDServ.updElection(electionDTO);				
		future.join();
	}
	
	@DeleteMapping("/delSelectElections")
	public void delSelectElections(@RequestBody CopyOnWriteArrayList<Long> cList) 
	{
		CompletableFuture<Void> future = electionMasterCUDServ.delSelectElections(cList);
		future.join();
	}

	@DeleteMapping("/delAllElections")
	public void deleteAllElecs() 
	{
		CompletableFuture<Void> future = electionMasterCUDServ.delAllElections();
		future.join();
	
	}

}