package Stream_Service;


import DTOS.StreamDTO;
import Entites.Subscription;
import io.livekit.server.*;
import livekit.LivekitModels;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Streaming {

    @Value("${livekit.api.url}")
    private String LiveKit_Api_Url;
    @Value("${livekit.api.key}")
    private String LiveKit_Api_Key;
    @Value("${livekit.api.secret}")
    private String LiveKit_Sec;

    public StreamDTO CreateToken(String RoomName,String UserId,boolean CanPublish,boolean CanPublishData){
        if(RoomName== null || UserId == null){
             throw new NullPointerException("Null Keys !");
        }
        AccessToken accessToken = new AccessToken(LiveKit_Api_Key,LiveKit_Sec);
        accessToken.setIdentity(UserId);
        accessToken .addGrants(
                new RoomJoin(true),
                        new RoomName(RoomName),
                        new CanPublish(true),
                        new CanSubscribe(CanPublish),
                       new CanPublishData(CanPublishData)
        );
          String Jwt = accessToken.toJwt();
        return new StreamDTO(LiveKit_Api_Url,Jwt);
    }
}
