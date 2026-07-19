package SCORE_SERVICE;


import DTO.PreferencesDTO;
import DTO.ScoreDTO;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import static java.lang.Math.*;

@Service
public class ModernScore {




    public int ProfileScore(ScoreDTO scoreDTO){
        int Score =0;

        if (scoreDTO.getPhotoCnt()>=1){
            Score+=20;
        }
        if(scoreDTO.getBio()!=null && scoreDTO.getBio().length()>=20) {
            Score+=35;
        }
        if(scoreDTO.getLikesCnt()>30){
            Score+=18;
        }
        if (scoreDTO.getInterests()!=null && scoreDTO.getInterests().length()>5){
            Score+=19;
        }


        return min(Score,100);
    }
    public Double Distance(ScoreDTO scoreDTO){
        int Score = 0;
        if (scoreDTO.getDistance() <= 2) return 100.00;
        if (scoreDTO.getDistance() <= 5) return 90.00;
        if (scoreDTO.getDistance() <= 10) return 75.00;
        if (scoreDTO.getDistance() <= 20) return 60.00;
        if (scoreDTO.getDistance() <= 35) return 40.00;
        if (scoreDTO.getDistance() <= 50) return 20.00;

        return  Double.min(Score,100.00);

    }
    public int preferences(ScoreDTO scoreDTO, PreferencesDTO preferencesDTO) {
        int score = 0;

        if (scoreDTO.getAge() == preferencesDTO.getAge()) {
            score += 30;
        }
        if (scoreDTO.getAge()<=preferencesDTO.getAge()){
            score+=14;
        }
        if (Objects.equals(scoreDTO.getGender(), preferencesDTO.getGender())) {
            score += 25;
        }
        if (Objects.equals(scoreDTO.getHobby(), preferencesDTO.getHobby())) {
            score += 10;
        }
        if (Objects.equals(scoreDTO.getInterests(), preferencesDTO.getInterests())) {
            score += 26;
        }
        if (Objects.equals(scoreDTO.getTribeName(), preferencesDTO.getTribeName())) {
            score += 50;
        }
        if (Objects.equals(
                scoreDTO.getRelationShipStatus(),
                preferencesDTO.getRelationShipStatus()
        )) {
            score += 14;
        }
        return Math.min(score, 100);
    }
    public int Report(ScoreDTO scoreDTO,PreferencesDTO preferencesDTO){
        int Score = 0;

        if(scoreDTO.getReportCnt()>=3){
             Score+=20;
        }
        if (scoreDTO.getBio().isBlank()){
            Score+=10;
        }
        if (scoreDTO.getPhotoCnt()==0 || scoreDTO.getPhotoCnt()<=2){
            Score+=13;
        }
        if (scoreDTO.getLikesCnt()<=13){
            Score+=10;
        }

        return  Math.min(Score,100);
    }
    public double TotalScore(ScoreDTO scoreDTO,PreferencesDTO preferencesDTO){
        int report = Report(scoreDTO,preferencesDTO);
        int preferences = preferences(scoreDTO,preferencesDTO);
        int profile = ProfileScore(scoreDTO);
        double dis = Distance(scoreDTO);
        double total =
                profile * 0.35 +
                        preferences * 0.40 +
                        dis * 0.15 +
                        report * 0.10;

        return Math.rint(total);
    }
    }


