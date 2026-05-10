# GoldenEye 64 — Minecraft Battle Royale Mod

A GoldenEye 007 (N64)-inspired gun mod for **Minecraft Java Edition 1.20.1** using the Fabric mod loader. Eleven iconic weapons, a roster of agents, optional NPC villains, and a `/br` command that runs a shrinking-border battle royale match.

**Everything you need to build, run, host, and share this mod is free.** You only need Minecraft itself, which you already own.

---

## What's in the box

- **14 guns**: PP7, PP7 Silenced, DD44 Dostovei, Cougar Magnum, Klobb, ZMG (9mm), D5K Deutsche, RC-P90, KF7 Soviet, AR-33, Sniper Rifle, Shotgun, Automatic Shotgun, **Golden Gun** (one shot kill)
- **6 ammo types**: 9mm, Rifle, Magnum, Shells, Sniper, Golden
- **3 explosives**: Proximity Mine, Remote Mine, Timed Mine
- **Agent Selector**: cycle through 11 agents, sneak-right-click to apply that loadout
- **Villain NPCs (toggleable)**: Boris, Ouromov, Jaws, Oddjob, Xenia, Trevelyan (006), May Day, Baron Samedi, Dr. No — each on a vanilla mob body with custom name + signature weapon
- **`/br` command**: start/stop battle royale matches, give kits, set codenames, spawn villains
- **Hostable landing page**: a single-file HTML site in `website/` you can drop on GitHub Pages

---

## The free toolchain

| What | Tool | Cost |
|---|---|---|
| Java runtime | [Adoptium Temurin 17](https://adoptium.net/temurin/releases/?version=17) | Free |
| Mod loader | [Fabric Installer](https://fabricmc.net/use/installer/) | Free |
| Mod API | [Fabric API on Modrinth](https://modrinth.com/mod/fabric-api) | Free |
| Build tool | Gradle (already in this repo as `gradlew`) | Free |
| IDE | [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) | Free |
| Source control | [Git](https://git-scm.com/) + [GitHub](https://github.com/) | Free |
| Website hosting | [GitHub Pages](https://pages.github.com/) | Free, comes with GitHub |
| Server hosting | Self-host on your PC, or [Aternos](https://aternos.org/) / [Minehut](https://minehut.com/) | Free |

**Total cost: $0** (you already own Minecraft).

---

## Building the mod

### 1. Install Java 17

Download [Adoptium Temurin 17](https://adoptium.net/temurin/releases/?version=17) and install. Verify in a terminal:

```bash
java -version
```

You should see `openjdk version "17..."`.

### 2. Get the Gradle wrapper

This repo doesn't ship the `gradle-wrapper.jar` (it's a binary). You have two options to fetch it:

**Option A — easiest:** clone the official Fabric example mod and copy its wrapper files over.

```bash
git clone https://github.com/FabricMC/fabric-example-mod temp-fabric
cp -r temp-fabric/gradle/wrapper/gradle-wrapper.jar gradle/wrapper/
cp temp-fabric/gradlew gradlew
cp temp-fabric/gradlew.bat gradlew.bat
chmod +x gradlew
rm -rf temp-fabric
```

**Option B — IntelliJ does it for you:** open this folder in IntelliJ IDEA, click "Trust Project," and IntelliJ will set up the wrapper automatically.

### 3. Build the jar

```bash
./gradlew build
```

(On Windows: `gradlew.bat build`)

The compiled mod will be at `build/libs/goldeneye64-0.1.0.jar`.

### 4. Install in Minecraft

1. Run [Fabric Installer](https://fabricmc.net/use/installer/) → pick MC **1.20.1** → install.
2. Open the Minecraft launcher → pick the new "Fabric Loader 1.20.1" profile → run it once.
3. Drop two files into your `.minecraft/mods/` folder:
   - `goldeneye64-0.1.0.jar` (the file you just built)
   - [`fabric-api-0.92.3+1.20.1.jar`](https://modrinth.com/mod/fabric-api/version/0.92.3+1.20.1)
4. Launch the Fabric profile. You should see "GoldenEye 64" in your mods list.

---

## In-game usage

Open a creative world (or join your server) and press `e` for inventory → find the **GoldenEye 64** creative tab.

### `/br` command reference

| Command | Effect |
|---|---|
| `/br help` | Show all commands |
| `/br start [radius] [shrinkSec]` | Start match — sets world border, gives every player a random kit. Defaults: 500 radius, 600s shrink. **Op only.** |
| `/br stop` | End the match, reset the border. **Op only.** |
| `/br kit <agent>` | Give yourself an agent's loadout (e.g. `/br kit 007`) |
| `/br agent <agent>` | Set your displayed codename (e.g. `/br agent Xenia`) |
| `/br agents` | List all agents |
| `/br villains on` | Enable NPC villains — `/br start` will scatter them around the border. **Op only.** |
| `/br villains off` | Disable NPC villains (default). **Op only.** |
| `/br villains list` | List all villains |
| `/br spawn <villain>` | Spawn one villain in front of you (e.g. `/br spawn jaws`). **Op only.** |

### Game flow for a family match

1. Everyone joins.
2. Decide: pure PVP or co-op vs. NPCs?
   - **Pure PVP** (default): just `/br start 300 300` — everyone gets a random agent kit, border shrinks over 5 minutes.
   - **Co-op vs. villains**: `/br villains on`, then `/br start 500 600` — players + 6 villain NPCs scattered around the map.
3. Last team / last player standing wins.

---

## The website

`website/index.html` is a single-file landing page you can host **for free on GitHub Pages**:

1. Create a free GitHub account.
2. Make a new public repo called `goldeneye64` (or whatever).
3. Upload the contents of the `website/` folder to that repo.
4. Repo Settings → Pages → Source: `main` branch, root → Save.
5. Your site is live at `https://YOURUSERNAME.github.io/goldeneye64/` within ~2 minutes.

Edit `website/index.html` to swap in your real server IP before sharing.

**For an even shorter URL**, use a free URL shortener like [tinyurl.com](https://tinyurl.com/) or [bit.ly](https://bit.ly/) — make `tinyurl.com/andros-br` point at your GitHub Pages URL. People type the short link, get the install instructions.

---

## Hosting the server (free)

Two free paths:

**Self-host on your PC** (best for family LAN):
1. Run a Minecraft server with Fabric Loader 1.20.1.
2. Drop `goldeneye64-0.1.0.jar` + Fabric API in the server's `mods/` folder.
3. Family on the same Wi-Fi just connects to your local IP.

**Free cloud server** (for friends across the internet):
- [Aternos](https://aternos.org/) — free, supports Fabric mods, server sleeps when nobody's on.
- [Minehut](https://minehut.com/) — free tier, smaller but reliable.

---

## What's missing / planned

- **True proximity mine detection** — current version uses a long fuse instead of auto-trigger when an enemy is near.
- **Remote mine detonator** — current version uses a long fuse; a separate detonator item is planned for v2.
- **Map pack**: a downloadable creative-built version of Facility / Temple / Stack.
- **Skin pack**: PNG skins for each agent (apply manually via the Minecraft launcher).
- **Custom mob AI** — villains currently use vanilla AI. Real ranged shooting from NPCs would need a custom entity.

---

## Project structure

```
Minecraft mod/
├── README.md                  ← you are here
├── build.gradle               ← Gradle build config
├── settings.gradle
├── gradle.properties
├── gradle/wrapper/            ← (you'll add wrapper jar — see step 2 above)
├── src/main/
│   ├── java/com/androsorthodontics/goldeneye64/
│   │   ├── GoldenEyeMod.java          ← entry point
│   │   ├── item/                       ← all guns, ammo, mines, agent selector
│   │   ├── command/BattleRoyaleCommand.java
│   │   └── game/                       ← Agent roster, VillainSpawner
│   └── resources/
│       ├── fabric.mod.json
│       └── assets/goldeneye64/
│           ├── lang/en_us.json
│           ├── models/item/*.json
│           └── textures/item/*.png    ← placeholder pixel art (replace freely)
└── website/index.html         ← drop on GitHub Pages
```

---

## Legal

GoldenEye 007 is a registered trademark of MGM / Eon Productions / Rare / Nintendo. This mod is a fan project for personal/family use, not affiliated with or endorsed by any of those companies. The character names and weapon names are used as references to the original 1997 game.

Don't redistribute textures or skins that are direct rips from the original game.
