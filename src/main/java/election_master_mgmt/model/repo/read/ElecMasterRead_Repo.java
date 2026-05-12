package election_master_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import election_master_mgmt.model.master.ElecMaster;

@Repository("elecMasterReadRepo")
public interface ElecMasterRead_Repo extends JpaRepository<ElecMaster, Long> 
{
	@Query(value = "select * FROM ELEC_MASTER WHERE election_seq_no IN :elecSeqNos", nativeQuery = true)
	CopyOnWriteArrayList<ElecMaster> getSelectElections(@Param("elecSeqNos") CopyOnWriteArrayList<Long> elecSeqNos);

	@Query(value = "select * FROM ELEC_MASTER", nativeQuery = true)
	CopyOnWriteArrayList<ElecMaster> getAllElections();

}