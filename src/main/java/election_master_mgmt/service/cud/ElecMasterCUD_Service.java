package election_master_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import election_master_mgmt.model.dto.ElecMaster_DTO;
import election_master_mgmt.model.master.ElecMaster;
import election_master_mgmt.model.repo.cud.ElecMasterCUD_Repo;

@Service("electionMasterCUDServ")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ElecMasterCUD_Service implements I_ElecMasterCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(ElecMasterService.class);

	@Autowired
	private ElecMasterCUD_Repo electionMasterCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<ElecMaster_DTO> newElection(ElecMaster_DTO cDTO) {
		CompletableFuture<ElecMaster_DTO> future = CompletableFuture.supplyAsync(() -> {
			ElecMaster_DTO eDTO = null;
			if (!electionMasterCUDRepo.existsById(cDTO.getElectionSeqNo())) {
				ElecMaster electionMaster = electionMasterCUDRepo.save(this.setElecMaster(cDTO));
				eDTO = getElecMaster_DTO(electionMaster);
			}
			return eDTO;
		}, asyncExecutor);

		return future;
	}

	public CompletableFuture<Void> updElection(ElecMaster_DTO eDTO) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
			ElecMaster elecMaster = null;
			if (electionMasterCUDRepo.existsById(eDTO.getElectionSeqNo())) 
			{
				elecMaster = this.setElecMaster(eDTO);				
				elecMaster.setElectionSeqNo(eDTO.getElectionSeqNo()); 
				electionMasterCUDRepo.save(elecMaster);
			}
			return;
			},asyncExecutor);
		
		return future;
	}

	public CompletableFuture<Void> delAllElections() 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
			electionMasterCUDRepo.deleteAll();
			return;
		},asyncExecutor);		
		return future;
	
	}

	public CompletableFuture<Void> delSelectElections(CopyOnWriteArrayList<Long> electionSeqNos) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
		{
			electionMasterCUDRepo.deleteAllById(electionSeqNos);
			return;
		},asyncExecutor);		
		return future;
	}

	private synchronized ElecMaster_DTO getElecMaster_DTO(ElecMaster cMaster) {
		ElecMaster_DTO cDTO = null;
		cDTO = new ElecMaster_DTO();
		cDTO.setElectionSeqNo(cMaster.getElectionSeqNo());
		cDTO.setElection(cMaster.getElection());
		return cDTO;
	}

	private synchronized ElecMaster setElecMaster(ElecMaster_DTO cDTO) {
		ElecMaster cMaster = new ElecMaster();
		cMaster.setElection(cDTO.getElection());
		return cMaster;
	}

}