//package com.cg.repository;
//
//import com.cg.model.Transfer;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface TransferRepository extends JpaRepository<Transfer, Long> {
//    @Query("SELECT NEW com.cg.model.dto.TransferHistoryDTO (" +
//            "t.id, " +
//            "t.sender.id, " +
//            "t.sender.fullName, " +
//            "t.recipient.id, " +
//            "t.recipient.fullName, " +
//            "t.transferAmount, " +
//            "t.fees, " +
//            "t.feesAmount " +
//            ") " +
//            "FROM Transfer AS t"
//    )
//    List<TransferHistoryDTO> findAllHistories();
//}
