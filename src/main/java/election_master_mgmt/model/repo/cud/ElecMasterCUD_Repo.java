package election_master_mgmt.model.repo.cud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import election_master_mgmt.model.master.ElecMaster;

@Repository("elecMasterCUDRepo")
public interface ElecMasterCUD_Repo extends JpaRepository<ElecMaster, Long> 
{}