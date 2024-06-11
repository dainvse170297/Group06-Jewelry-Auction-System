package com.fpt.edu.controller;

import com.fpt.edu.dto.AuctionSessionDTO;
import com.fpt.edu.dto.ViewLiveAuctionSessionDetailDTO;
import com.fpt.edu.entity.AuctionSession;
import com.fpt.edu.service.IAuctionSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/auction")
@RequiredArgsConstructor
public class AuctionSessionController {

    private final IAuctionSessionService auctionSessionService;

    @PostMapping("/create-session")
    public ResponseEntity<AuctionSession> createAuctionSession(@RequestParam("name") String name,
                                                               @RequestParam("description") String description,
                                                               @RequestParam("startTime") LocalDateTime startDate,
                                                               @RequestParam("endTime") LocalDateTime endDate,
                                                               @RequestParam("startingBid") LocalDateTime startingBid,
                                                               @RequestParam("staffId") int staffId,
                                                               @RequestParam("image") MultipartFile image) throws IOException {
    AuctionSession auction = auctionSessionService.createSession(name, description, startDate, endDate, startingBid, staffId, image);

        return ResponseEntity.ok(auction);
    }

    @GetMapping("/all-session")
    public ResponseEntity<List<AuctionSession>> getAllAuctionSession() {
        return ResponseEntity.ok(auctionSessionService.getAllAuctionSession());
    }

    @GetMapping("/session/{id}")
    public ResponseEntity<AuctionSession> getAuctionSessionById(@PathVariable int id) {
        return ResponseEntity.ok(auctionSessionService.getAuctionSessionById(id));
    }

    @GetMapping("/all-created-session")
    public ResponseEntity<List<AuctionSession>> getAllAuctionSessionByCreatedStatus() {
        return ResponseEntity.ok(auctionSessionService.getAllAuctionSessionByCreatedStatus());
    }

    @PostMapping("/add-lot-to-session")
    public ResponseEntity<AuctionSession> addLotToSession(@RequestParam("lotId") int lotId,
                                                          @RequestParam("sessionId") int sessionId) {
        return ResponseEntity.ok(auctionSessionService.addLotToSession(lotId, sessionId));
    }

    @GetMapping("/session/upcoming")
    public ResponseEntity<List<AuctionSessionDTO>> getUpcomingAuctionSession() {
        return ResponseEntity.ok(auctionSessionService.getUpcomingAuctionSession());
    }

    @GetMapping("/session/view-live-auction-session-detail")
    public ResponseEntity<?> viewLiveAuctionSessionDetail(@RequestParam("sessionId") Integer sessionId,
                                                          @RequestParam("memberId") Integer memberId) {
        ResponseEntity<?> response = auctionSessionService.viewLiveAuctionSessionDetail(sessionId, memberId);
        return response;
    }

}