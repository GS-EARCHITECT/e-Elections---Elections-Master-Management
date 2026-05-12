package election_master_mgmt.service.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import election_master_mgmt.model.dto.ElecMaster_DTO;

public interface I_ElecMasterCUD_Service 
{
	public CompletableFuture<ElecMaster_DTO> newElection(ElecMaster_DTO electionDTO);
	public CompletableFuture<Void> updElection(ElecMaster_DTO electionDTO);	
	public CompletableFuture<Void> delAllElections();
	public CompletableFuture<Void> delSelectElections(CopyOnWriteArrayList<Long> electionSeqNos);
}