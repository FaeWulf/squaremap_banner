package faewulf.squaremap.banner.dataType;

import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.Point;
import xyz.jpenilla.squaremap.api.marker.Icon;
import xyz.jpenilla.squaremap.api.marker.Marker;

import java.io.IOException;

// Custom TypeAdapter for Data record
public class DataTypeAdapter extends TypeAdapter<Data> {

    @Override
    public void write(JsonWriter out, Data data) throws IOException {
        if (data == null) {
            out.nullValue();
            return;
        }

        out.beginObject();
        out.name("key").value(data.key().getKey());
        out.name("name").value(data.name());
        out.name("type").value(data.keyIconType().getKey());
        out.name("x").value(data.marker().point().x());
        out.name("z").value(data.marker().point().z());
        out.endObject();
    }

    @Override
    public Data read(JsonReader in) throws IOException {
        String keyString = null;
        String name = null;
        String keyIconType = null;
        int x = 0, z = 0;

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "key":
                    keyString = in.nextString();
                    break;
                case "name":
                    name = in.nextString();
                    break;
                case "type":
                    keyIconType = in.nextString();
                    break;
                case "x":
                    x = in.nextInt();
                    break;
                case "z":
                    z = in.nextInt();
                    break;

            }
        }
        in.endObject();

        if (keyString == null || name == null || keyIconType == null) {
            throw new JsonIOException("Missing fields in JSON input");
        }

        // Create a new Data object, assuming Marker has a default constructor or appropriate value
        Key key = Key.of(keyString);
        Key keyIcon = Key.of(keyIconType);
        Icon icon = Marker.icon(Point.point(x, z), key, 16);
        return new Data(icon, keyIcon, name, key);
    }
}
