package SERVICES;

import DTO.ProfileDTO;
import ENTITY.Profile;
import JPA.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateProfile {
    private final ProfileRepository profileRepository;

    @Transactional
    public void Create(ProfileDTO profileDTO){

         var GetId = profileRepository.getReferenceById(profileDTO.getProfileId());


         Profile profile1 = Profile
                .builder()
                .Age(profileDTO.getAge())
                .Hobby(profileDTO.getHobby())
                .ProfileId(profileDTO.getProfileId())
                .CreatedBy(profileDTO.getCreatedBy())
                .Preferences(profileDTO.getPreferences())
                .TribeId(profileDTO.getTribeId())
                .Interests(profileDTO.getInterests())
                .Gender(profileDTO.getGender())
                .Location(profileDTO.getLocation())
                 .DateOfBirth(profileDTO.getDateOfBirth())
                .RelationShipStatus(profileDTO.getRelationShipStatus())
                .TribeName(profileDTO.getTribeName())
                .build();
         profileRepository.save(profile1);

    }



}
