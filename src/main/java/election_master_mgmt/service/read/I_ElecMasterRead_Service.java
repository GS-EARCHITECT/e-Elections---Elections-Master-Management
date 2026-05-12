package election_master_mgmt.service.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import election_master_mgmt.model.dto.ElecMaster_DTO;

public interface I_ElecMasterRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> getAllElections();
	public CompletableFuture<CopyOnWriteArrayList<ElecMaster_DTO>> getSelectElections(CopyOnWriteArrayList<Long> electionSeqNos);
}