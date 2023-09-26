<!-- Suppress IDEA Warnings -->
<!--suppress ALL -->

<a name="readme-top"></a>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]
[![Discord][discord-shield]][discord-url]
[![Modrinth][modrinth-shield]][modrinth-url]

<br />
<div align="center">
<h3 align="center">ModMenu Badges Lib</h3>
  <p align="center">
      More badge!!!
    <br />
    <a href="https://discord.gg/pbwnMwnUD6">Support</a>
    ·
    <a href="https://github.com/syorito-hatsuki/modmenu-badges-lib/issues">Report Bug</a>
    ·
    <a href="https://github.com/syorito-hatsuki/modmenu-badges-lib/issues">Request Feature</a>
  </p>
</div>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

## About The Project

![In-Game ScreenShot][screenshot]

Just a simple library for adding your own badges to ModMenu.

For example, [Tschipcraft](https://github.com/Tschipcraft) uses it for the Data Pack badge in [Dynamic Lights](https://github.com/Tschipcraft/dynamiclights)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Usage

1. Add Gradle dependency into `build.gradle` or `build.gradle.kts`
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
2. Add the dependency recommend in your fabric.mod.json
```json
"recommends": {
  "modmenu-badges-lib": "*"
}
```

3. Add the next lines in our `fabric.mod.json`. For color calculating I can recommend this site https://argb-int-calculator.netlify.app

```json5
{
  ...
  "custom": {
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
        //Next badge
      }
    ]
  },
  ...
}
```

## Roadmap

- [ ] Search by badge
- [ ] Own online badge editor with real-time preview

See the [open issues](https://github.com/syorito-hatsuki/modmenu-badges-lib/issues) for a full list of proposed
features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also
simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


[contributors-shield]: https://img.shields.io/github/contributors/syorito-hatsuki/modmenu-badges-lib.svg?style=for-the-badge

[contributors-url]: https://github.com/syorito-hatsuki/modmenu-badges-lib/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/syorito-hatsuki/modmenu-badges-lib.svg?style=for-the-badge

[forks-url]: https://github.com/syorito-hatsuki/modmenu-badges-lib/network/members

[stars-shield]: https://img.shields.io/github/stars/syorito-hatsuki/modmenu-badges-lib.svg?style=for-the-badge

[stars-url]: https://github.com/syorito-hatsuki/modmenu-badges-lib/stargazers

[issues-shield]: https://img.shields.io/github/issues/syorito-hatsuki/modmenu-badges-lib.svg?style=for-the-badge

[issues-url]: https://github.com/syorito-hatsuki/modmenu-badges-lib/issues

[license-shield]: https://img.shields.io/github/license/syorito-hatsuki/modmenu-badges-lib.svg?style=for-the-badge

[license-url]: https://github.com/syorito-hatsuki/modmenu-badges-lib/blob/master/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://linkedin.com/in/kit-lehto

[discord-shield]: https://img.shields.io/discord/1032138561618726952?logo=discord&logoColor=white&style=for-the-badge&label=Discord

[discord-url]: https://discord.gg/pbwnMwnUD6

[modrinth-shield]: https://img.shields.io/modrinth/v/modmenu-badges-lib?label=Modrinth&style=for-the-badge

[modrinth-url]: https://modrinth.com/mod/modmenu-badges-lib

[screenshot]: https://user-images.githubusercontent.com/33298273/233725872-902aa00e-618d-48d3-b594-990d2cad85ae.png
