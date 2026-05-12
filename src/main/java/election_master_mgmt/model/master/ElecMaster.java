package election_master_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the ELEC_MASTER database table.
 * 
 */
@Entity
@Table(name = "ELEC_MASTER")
public class ElecMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ELECTION_SEQUENCE")
	@SequenceGenerator(name = "ELECTION_SEQUENCE", sequenceName = "ELECTION_SEQUENCE", allocationSize = 1)
	@Column(name = "ELECTION_SEQ_NO")
	private long electionSeqNo;

	@Column(name = "ELECTION")
	private String election;

	public ElecMaster() {
	}

	public long getElectionSeqNo() {
		return this.electionSeqNo;
	}

	public void setElectionSeqNo(long electionSeqNo) {
		this.electionSeqNo = electionSeqNo;
	}

	public String getElection() {
		return this.election;
	}

	public void setElection(String election) {
		this.election = election;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (electionSeqNo ^ (electionSeqNo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElecMaster other = (ElecMaster) obj;
		if (electionSeqNo != other.electionSeqNo)
			return false;
		return true;
	}

	public ElecMaster(long electionSeqNo, String election) {
		super();
		this.electionSeqNo = electionSeqNo;
		this.election = election;
	}

}