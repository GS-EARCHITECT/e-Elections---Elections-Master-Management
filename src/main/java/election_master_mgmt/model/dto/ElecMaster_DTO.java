package election_master_mgmt.model.dto;

import java.io.Serializable;

public class ElecMaster_DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6891265252207487380L;

	private Long electionSeqNo;
	private String election;

	public Long getElectionSeqNo() {
		return this.electionSeqNo;
	}

	public void setElectionSeqNo(Long electionSeqNo) {
		this.electionSeqNo = electionSeqNo;
	}

	public String getElection() {
		return this.election;
	}

	public void setElection(String election) {
		this.election = election;
	}

	public ElecMaster_DTO(Long electionSeqNo, String election) {
		super();
		this.electionSeqNo = electionSeqNo;
		this.election = election;
	}

	public ElecMaster_DTO() {
		super();
	}

}