package faewulf.squaremap.banner.dataType;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.HashSet;

public class ModConfigs {
    @SerializedName("banner_radius")
    public int radius = 80;

    @SerializedName("announce_when_near_banner")
    public boolean bannerEnterAnnouncer = true;

    public HashSet<String> blacklist = new HashSet<>(Arrays.asList("nigga", "slut"));
}
