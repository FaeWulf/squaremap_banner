package faewulf.squaremap.banner.dataType;

import faewulf.squaremap.banner.Squaremapbanner;
import net.minecraft.text.TextColor;
import net.minecraft.util.DyeColor;
import xyz.jpenilla.squaremap.api.Key;
import xyz.jpenilla.squaremap.api.SquaremapProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class icon {
    public static final Key BLACK = register("BLACK");
    public static final Key BLUE = register("BLUE");
    public static final Key BROWN = register("BROWN");
    public static final Key CYAN = register("CYAN");
    public static final Key GRAY = register("GRAY");
    public static final Key GREEN = register("GREEN");
    public static final Key LIGHT_BLUE = register("LIGHT_BLUE");
    public static final Key LIGHT_GRAY = register("LIGHT_GRAY");
    public static final Key LIME = register("LIME");
    public static final Key MAGENTA = register("MAGENTA");
    public static final Key ORANGE = register("ORANGE");
    public static final Key PINK = register("PINK");
    public static final Key PURPLE = register("PURPLE");
    public static final Key RED = register("RED");
    public static final Key WHITE = register("WHITE");
    public static final Key YELLOW = register("YELLOW");

    private static Key register(String name) {
        String filename = "assets/squaremap-banner/textures/icons/" + name + ".png";
        Key key = Key.of(name);

        BufferedImage image = getBufferImage(filename);
        SquaremapProvider.get().iconRegistry().register(key, image);
        return key;
    }

    public static Key getIcon(DyeColor type) {
        return switch (type) {
            case BLACK -> BLACK;
            case BLUE -> BLUE;
            case BROWN -> BROWN;
            case CYAN -> CYAN;
            case GRAY -> GRAY;
            case GREEN -> GREEN;
            case LIGHT_BLUE -> LIGHT_BLUE;
            case LIGHT_GRAY -> LIGHT_GRAY;
            case LIME -> LIME;
            case MAGENTA -> MAGENTA;
            case ORANGE -> ORANGE;
            case PINK -> PINK;
            case PURPLE -> PURPLE;
            case RED -> RED;
            case YELLOW -> YELLOW;
            default -> WHITE;
        };
    }

    private static BufferedImage getBufferImage(String resourcePath) {
        try (InputStream inputStream = icon.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }
            return ImageIO.read(inputStream);
        } catch (IOException e) {
            Squaremapbanner.LOGGER.warn("Failed to register banners icon", e);
            return null;
        }
    }

    public static TextColor getTextColor(DyeColor type) {
        return switch (type) {
            case BLACK -> TextColor.fromRgb(0x2c2c2c); // Black
            case BLUE -> TextColor.fromRgb(0x0000FF); // Blue
            case BROWN -> TextColor.fromRgb(0x8B4513); // Brown
            case CYAN -> TextColor.fromRgb(0x00FFFF); // Cyan
            case GRAY -> TextColor.fromRgb(0x808080); // Gray
            case GREEN -> TextColor.fromRgb(0x008000); // Green
            case LIGHT_BLUE -> TextColor.fromRgb(0xADD8E6); // Light Blue
            case LIGHT_GRAY -> TextColor.fromRgb(0xD3D3D3); // Light Gray
            case LIME -> TextColor.fromRgb(0x00FF00); // Lime
            case MAGENTA -> TextColor.fromRgb(0xFF00FF); // Magenta
            case ORANGE -> TextColor.fromRgb(0xFFA500); // Orange
            case PINK -> TextColor.fromRgb(0xFFC0CB); // Pink
            case PURPLE -> TextColor.fromRgb(0x800080); // Purple
            case RED -> TextColor.fromRgb(0xFF0000); // Red
            case YELLOW -> TextColor.fromRgb(0xFFFF00); // Yellow
            default -> TextColor.fromRgb(0xFFFFFF); // White
        };
    }
}
