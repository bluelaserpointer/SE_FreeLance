package com.example.accessingdatamysql.daoimpl;

import com.alibaba.fastjson.JSONObject;
import com.example.accessingdatamysql.dao.ItemDao;
import com.example.accessingdatamysql.dao.MissionDao;
import com.example.accessingdatamysql.repository.*;
import com.example.accessingdatamysql.entity.*;

// import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// import java.io.Console;
import java.util.*;

@Repository
public class MissionDaoImpl implements MissionDao {
    @Autowired
    private MissionRepository MissionRepository;
    @Autowired
    private MissionDetailsRepository MissionDetailsRepository;

    @Override
    public Mission getOneMission(Integer MissionId) {
        Mission Mission = MissionRepository.getOne(MissionId);
        Optional<MissionDetails> MissionDetails = MissionDetailsRepository
                .findMissionDetailsByMissionIdEquals(MissionId);
        MissionDetails.ifPresent(Mission::setMissionDetails);
        return Mission;
    }

    // public void modifyStorage(Mission Mission) {
    // MissionRepository.updateStorageStatus(Mission.getStorage(),
    // Mission.getMissionId());
    // return;
    // }

    // public MissionDetails findOneDetail(Integer id) {
    // return MissionDetailsRepository.findById(id);
    // }

    public Mission addNewMission(Mission newMission) {
        // System.out.println(MissionDescription);
        Mission Mission = new Mission(newMission.getType(), newMission.getMissionName());
        Mission.setAwardItems(newMission.getAwardItems());
        // System.out.println("new Mission has an Id of : " + n.getMissionId());
        MissionRepository.save(Mission);
        MissionDetails MissionDetails = new MissionDetails(Mission.getMissionId(),
                newMission.getMissionDetails().getMissionDescription());
        MissionDetailsRepository.save(MissionDetails);
        Mission.setMissionDetails(MissionDetails);
        return Mission;

    }

    public Mission updateMission(Mission updateMission) {

        Mission Mission = MissionRepository.getOne(updateMission.getMissionId());
        // System.out.println("old Mission has an Id of : " + n.getMissionId());
        Mission.setMission(updateMission.getType(), updateMission.getMissionName());
        Mission.setAwardItems(updateMission.getAwardItems());

        MissionRepository.updateMissionStatus(Mission, updateMission.getMissionId());

        Optional<MissionDetails> optMissionDetails = MissionDetailsRepository
                .findMissionDetailsByMissionIdEquals(updateMission.getMissionId());
        MissionDetails MissionDetails = new MissionDetails(updateMission.getMissionId(), "");
        if (optMissionDetails.isPresent()) {
            System.out.println("Mission Exists");
            MissionDetails = optMissionDetails.get();
        } else {
            System.out.println("Mission doesn't exist");
        }

        MissionDetails.setMissionDescription(updateMission.getMissionDetails().getMissionDescription());
        MissionDetailsRepository.save(MissionDetails);
        Mission.setMissionDetails(MissionDetails);
        return Mission;

    }

    public List<Mission> getAllMissions() {
        List<Mission> Missions = MissionRepository.findAll();
        for (int i = 0; i < Missions.size(); i++) {
            Mission Mission = Missions.get(i);
            Optional<MissionDetails> MissionDetails = MissionDetailsRepository
                    .findMissionDetailsByMissionIdEquals(Mission.getMissionId());
            MissionDetails.ifPresent(Mission::setMissionDetails);
            Missions.set(i, Mission);
        }
        return Missions;
    }

    public String deleteMissions(List<Integer> MissionIds) {
        for (int i = 0; i < MissionIds.size(); i++) {
            MissionRepository.deleteById(MissionIds.get(i));
            MissionDetailsRepository.deleteMissionDetailsByMissionIdEquals(MissionIds.get(i));
        }
        return "Deleted Missions by id";
    }

    public String deleteAll() {
        MissionRepository.deleteAll();
        MissionDetailsRepository.deleteAll();
        return "Deleted All Missions";
    }

    public List<Mission> deleteMission(Integer MissionId) {
        MissionRepository.deleteById(MissionId);
        MissionDetailsRepository.deleteMissionDetailsByMissionIdEquals(MissionId);
        return getAllMissions();
    }

    @Override
    public JSONObject ListPage(Integer page_token, Integer page_size) {
        JSONObject response = new JSONObject();

        // get the result data
        Integer start = (page_token - 1) * page_size;
        // Integer end = page_token * page_size - 1;
        List<Mission> missions = MissionRepository.ListPage(start, page_size);
        for (int i = 0; i < missions.size(); i++) {
            Mission Mission = missions.get(i);
            Optional<MissionDetails> MissionDetails = MissionDetailsRepository
                    .findMissionDetailsByMissionIdEquals(Mission.getMissionId());
            MissionDetails.ifPresent(Mission::setMissionDetails);
            missions.set(i, Mission);
        }

        // get the nextPageToken
        Integer nextPageToken;
        if ((MissionRepository.findAll().size() - (page_token * page_size)) <= 0) {
            response.put("nextPageToken", "");
        } else {
            nextPageToken = page_token + 1;
            response.put("nextPageToken", nextPageToken);
        }

        // get the total pages of the result
        Integer totalPages = MissionRepository.findAll().size() / page_size;
        if ((totalPages - page_size * totalPages) > 0) {
            totalPages += 1;
        }
        response.put("result", missions);
        response.put("totalPages", totalPages);

        return response;
    }
}
