# SquareMap Banner Mod

<p align="center" >
  <img alt="preview" src="https://raw.githubusercontent.com/FaeWulf/squaremap_banner/master/images/preview.png" />
</p>


A fabric mod that is an extension for the [SquareMap](https://github.com/jpenilla/squaremap) mod for Minecraft Fabric.
It adds
additional functionality related to banners within the game. Inspired
by [squaremap-banners plugins](https://github.com/jpenilla/squaremap-addons/tree/master/addons/banners)

## Features

- **Banner marker**: Sneaking right-click with a named banner while holding a filled map will add that banner to the
  world map.
- **Banner Removal**: Sneaking left-click with a named banner while holding a filled map, or breaking the banner, will
  remove it from the world map.
- **Area Notification**: Displays a message when entering an area marked by a banner.

<p align="center">
  <img alt="preview_extra" src="https://raw.githubusercontent.com/FaeWulf/squaremap_banner/master/images/preview_extra.png" />
</p>

## Configuration

The mod provides a JSON configuration file with the following options:

- `radius`: Specifies the size of the area declared by the banner.
- `announce_when_near_banner`: Enable/disable the message when entering the banner's area.
- `blacklist`: Contains a list of words to prevent from being registered to the world map.

## Mod dependents

- Squaremap mod itself

## Permissions

- **squaremap.banner.use**: Permission required for players to use the features provided by the mod.

## Support and Feedback

If you encounter any issues or have suggestions for improvement, please feel free to submit an issue on the GitHub
repository.
