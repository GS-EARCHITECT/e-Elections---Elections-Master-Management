package election_master_mgmt.service.read;

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
import election_master_mgmt.model.repo.read.ElecMasterRead_Repo;

@Service("electionMasterReadServ")
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class ElecMasterRead_Service implements I_ElecMasterRead_Service 
{
	// private static final Logger logger =
	// LoggerFactory.getLogger(ElecMasterService.class);

	@Autowired
	private ElecMasterRead_Repo electionMasterReadRepo;
	
	@Autowired
	private Executor asyncExecutor;

	public CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> getAllElections() 
	{
		CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<ElecMaster> electionList = electionMasterReadRepo.getAllElections();
		CopyOnWriteArrayList<ElecMaster_DTO> cDTOs = new CopyOnWriteArrayList<ElecMaster_DTO>();
		cDTOs = electionList != null ? this.getElecMasterDtos(electionList) : null;
		return cDTOs;		
		},asyncExecutor);
		
		return future;
	}

	public CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> getSelectElections(CopyOnWriteArrayList<Long> electionSeqNos) 
	{
		CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
		CopyOnWriteArrayList<ElecMaster_DTO> cDTOs = new CopyOnWriteArrayList<ElecMaster_DTO>();
		CopyOnWriteArrayList<ElecMaster> electionMasters = (CopyOnWriteArrayList<ElecMaster>) electionMasterReadRepo.getSelectElections(electionSeqNos);
		cDTOs = electionMasters != null ? this.getElecMasterDtos(electionMasters) : null;
		return cDTOs;
		},asyncExecutor);		
		return future;
	}

	private CopyOnWriteArrayList<ElecMaster_DTO> getElecMasterDtos(CopyOnWriteArrayList<ElecMaster> cMasters) {
		ElecMaster_DTO cDTO = null;
		CopyOnWriteArrayList<ElecMaster_DTO> cDTOs = new CopyOnWriteArrayList<ElecMaster_DTO>();

		for (int i = 0; i < cMasters.size(); i++) {
			cDTO = this.getElecMaster_DTO(cMasters.get(i));
			cDTOs.add(cDTO);
		}
		return cDTOs;
	}

	private ElecMaster_DTO getElecMaster_DTO(ElecMaster cMaster) {
		ElecMaster_DTO cDTO = null;
		cDTO = new ElecMaster_DTO();
		cDTO.setElectionSeqNo(cMaster.getElectionSeqNo());
		cDTO.setElection(cMaster.getElection());
		return cDTO;
	}


}