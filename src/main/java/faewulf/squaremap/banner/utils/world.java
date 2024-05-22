package faewulf.squaremap.banner.utils;

import faewulf.squaremap.banner.Squaremapbanner;
import faewulf.squaremap.banner.dataType.Data;
import faewulf.squaremap.banner.dataType.Position;
import xyz.jpenilla.squaremap.api.*;
import xyz.jpenilla.squaremap.api.marker.Icon;
import xyz.jpenilla.squaremap.api.marker.Marker;
import xyz.jpenilla.squaremap.api.marker.MarkerOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class world {
    private final Map<Position, Data> data = new HashMap<>();
    String identifier;
    String provider;

    public world(String identifier) {
        this.identifier = identifier;
    }

    public void setProvider(SimpleLayerProvider provider, String name) {
        Squaremap api = SquaremapProvider.get();

        api.getWorldIfEnabled(WorldIdentifier.parse(this.identifier)).ifPresent(mapWorld -> {
            Key key = Key.of(name);
            mapWorld.layerRegistry().register(key, provider);
        });

        this.provider = name;
    }

    public SimpleLayerProvider getProvider() {
        Squaremap api = SquaremapProvider.get();
        MapWorld mp = api.getWorldIfEnabled(WorldIdentifier.parse(this.identifier)).orElse(null);
        if (mp != null)
            return (SimpleLayerProvider) mp.layerRegistry().get(Key.of(this.provider));
        return null;
    }

    public void addBanner(Position pos, Key keyIconType, String name, Key key) {
        //check if pos already has a banner
        //then delete it and add the new one (aka update)
        if (data.containsKey(pos)) {
            removeBanner(pos);
        }

        //extra step to null check the name
        name = name == null ? "null" : name;

        Squaremapbanner.LOGGER.info("Added banner: " + pos.toString() + " icon: " + keyIconType + " name: " + name + " id: " + key);

        Icon icon = Marker.icon(pos.point(), keyIconType, 16);
        icon.markerOptions(MarkerOptions.builder().hoverTooltip(name));

        this.getProvider().addMarker(key, icon);
        this.data.put(pos, new Data(icon, keyIconType, name, key));
    }

    public void removeBanner(Position pos) {
        Data target = this.data.get(pos);

        if (target == null)
            return;

        this.getProvider().removeMarker(target.key());
        this.data.remove(pos);
        Squaremapbanner.LOGGER.info("Remove banner: " + pos.toString() + " icon: " + target.keyIconType().getKey() + " name: " + target.name() + " id: " + target.key().getKey());
    }

    public Set<Position> getBanners() {
        return this.data.keySet();
    }

    public Map<Position, Data> getData() {
        return data;
    }
}

