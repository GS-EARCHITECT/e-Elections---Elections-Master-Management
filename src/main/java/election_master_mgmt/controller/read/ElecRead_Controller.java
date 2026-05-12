package election_master_mgmt.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import election_master_mgmt.model.dto.ElecMaster_DTO;
import election_master_mgmt.service.read.I_ElecMasterRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/electionReadMgmt")
public class ElecRead_Controller 
{

	private static final Logger logger = LoggerFactory.getLogger(ElecRead_Controller.class);

	@Autowired
	private I_ElecMasterRead_Service electionMasterReadServ;

	@GetMapping(value = "/getAllElections", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ElecMaster_DTO>> getAllElections() 
	{
		CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> future = electionMasterReadServ.getAllElections();
		CopyOnWriteArrayList<ElecMaster_DTO> elecMaster_DTOs = null;		
		elecMaster_DTOs = future.join();
		return new ResponseEntity<>(elecMaster_DTOs, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getSelectElections", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<ElecMaster_DTO>> getSelectElections(@RequestBody CopyOnWriteArrayList<Long> elecSeqNos ) 
	{
		CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> future = electionMasterReadServ.getSelectElections(elecSeqNos);
		CopyOnWriteArrayList<ElecMaster_DTO> elecMaster_DTOs = null;		
		elecMaster_DTOs = future.join();
		return new ResponseEntity<>(elecMaster_DTOs, HttpStatus.CREATED);
	}	
	
}