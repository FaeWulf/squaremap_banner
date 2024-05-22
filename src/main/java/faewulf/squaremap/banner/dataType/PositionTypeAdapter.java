package faewulf.squaremap.banner.dataType;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.minecraft.util.math.BlockPos;

import java.io.IOException;

// Custom TypeAdapter for Data record
public class PositionTypeAdapter extends TypeAdapter<Position> {

    @Override
    public void write(JsonWriter out, Position pos) throws IOException {
        if (pos == null) {
            out.nullValue();
            return;
        }

        out.beginObject();
        out.name("x").value(pos.loc().getX());
        out.name("y").value(pos.loc().getY());
        out.name("z").value(pos.loc().getZ());
        out.endObject();
    }

    @Override
    public Position read(JsonReader in) throws IOException {
        int x = 0;
        int y = 0;
        int z = 0;

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "x":
                    x = in.nextInt();
                    break;
                case "y":
                    y = in.nextInt();
                    break;
                case "z":
                    z = in.nextInt();
                    break;
            }
        }
        in.endObject();

        // Create a new Data object, assuming Marker has a default constructor or appropriate value
        BlockPos blockPos = new BlockPos(x, y, z);
        return new Position(blockPos);
    }
}
