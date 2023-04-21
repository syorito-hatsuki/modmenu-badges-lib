# ModMenu Badges Lib

## Description

Just simple library for add own badges to ModMenu

![image](https://user-images.githubusercontent.com/33298273/233725872-902aa00e-618d-48d3-b594-990d2cad85ae.png)

## Adding the dependency

```gradle
repositories {
    maven("https://api.modrinth.com/maven")
}

dependencies {
    // Option 1: Include ModMenu Badges Lib to project for it available within your own jar (additional ~5kb)
    include(modImplementation("maven.modrinth", "modmenu-badges-libb", "<version>"))
    
    // Option 2: Depend on ModMenu Badges Lib, but require that users install it manually
    modImplementation("maven.modrinth", "modmenu-badges-lib", "<version>")
}
```

```json5
// Also add dependency recommend in your fabric.mod.json
"recommends": {
  "modmenu-badges-lib": "*"
}
```

## Usage

Add next lines in our `fabric.mod.json`. For color calculating I can recommmend this site https://argb-int-calculator.netlify.app

```json5
{
  ...
  "custom": {
    // ModMenu setup is optional
    "modmenu": {
      ...
    },
    "mcb": [
      {
        // Badge text
        "name": "Example Badge",
        // Fill color
        "fillColor": -2003942227,
        // Outline color
        "outlineColor": -2003084874
      },
      {
        ...
      }
    ]
  },
  ...
}
```
