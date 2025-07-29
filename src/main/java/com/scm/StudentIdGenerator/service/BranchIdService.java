package com.scm.StudentIdGenerator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.StudentIdGenerator.BranchSequence;
import com.scm.StudentIdGenerator.Repo.BranchSequenceRepository;

import jakarta.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class BranchIdService {

    private static final String BASE_PREFIX = "22TP1";
    private static final String BASE_SUFFIX = "05";

    private static final Map<String, Character> branchCodeMap = new HashMap<>();

    static {
        branchCodeMap.put("CSE", 'A');
        branchCodeMap.put("ECE", 'B');
        branchCodeMap.put("MECH", 'C');
        branchCodeMap.put("CIVIL", 'D');
        branchCodeMap.put("EE", 'E');
        // Add more as needed
    }

    @Autowired
    private BranchSequenceRepository repo;

    @Transactional
    public String generateNextId(String branchName) {
        branchName = branchName.toUpperCase();

        if (!branchCodeMap.containsKey(branchName)) {
            throw new IllegalArgumentException("Unknown branch: " + branchName);
        }

        BranchSequence seq = repo.findById(branchName)
                .orElse(new BranchSequence(branchName, 0));

        int sequence = seq.getSequence();
        char branchChar = branchCodeMap.get(branchName);
        char letter = (char) ('A' + (sequence / 10));
        int digit = sequence % 10;

        if (letter > 'Z') {
            throw new IllegalStateException("ID limit exceeded for branch: " + branchName);
        }

        String id = BASE_PREFIX + branchChar + BASE_SUFFIX + letter + digit;

        seq.setSequence(sequence + 1);
        repo.save(seq);

        return id;
    }
}
