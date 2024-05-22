package faewulf.squaremap.banner.utils;

import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import faewulf.squaremap.banner.Squaremapbanner;
import faewulf.squaremap.banner.dataType.Data;
import faewulf.squaremap.banner.dataType.DataTypeAdapter;
import faewulf.squaremap.banner.dataType.Position;
import faewulf.squaremap.banner.dataType.PositionTypeAdapter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class saveDataHandler {
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Data.class, new DataTypeAdapter())
            .registerTypeAdapter(Position.class, new PositionTypeAdapter())
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .create();

    private static final File DATA_FILE = new File("squaremap/banner.json");

    public static Map<String, world> loadData() {
        Type type = new TypeToken<Map<String, world>>() {
        }.getType();

        if (DATA_FILE.exists()) {
            try (FileReader reader = new FileReader(DATA_FILE)) {
                return GSON.fromJson(reader, type);
            } catch (Exception e) {
                Squaremapbanner.LOGGER.error("Error while reading data: ", e);
                return Maps.newHashMap();
            }
        } else {
            return Maps.newHashMap();
        }
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            GSON.toJson(bannerManager.bannerManager, writer);
        } catch (IOException e) {
            Squaremapbanner.LOGGER.error("Error while saving data: ", e);
        }
    }
}
