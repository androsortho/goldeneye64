# How to test the mod (no IntelliJ, no Java install)

GitHub builds the JAR for you in the cloud. You just upload the project, wait 3 minutes, and download.

---

## Step 1 — Make a free GitHub account

Go to **[github.com/signup](https://github.com/signup)**. Pick a username (e.g. `andros-orthodontics`). It's free.

## Step 2 — Create a new repository

Once logged in, click the **"+"** in the top-right → **New repository**.
- Name: `goldeneye64` (or anything)
- Public or Private: doesn't matter, both work
- Skip the "initialize with README" checkbox
- Click **Create repository**

## Step 3 — Upload the project files

On the new empty repo page, click **"uploading an existing file"** (the link in the middle of the page).

Then **drag the entire contents of `C:\Users\puyit\Documents\Claude\Projects\Minecraft mod\`** onto the upload zone. GitHub will accept the whole folder structure.

When upload finishes, scroll down and click **Commit changes**.

## Step 4 — Wait for the build

Click the **Actions** tab at the top of the repo. You'll see "Build GoldenEye 64 mod" running (yellow dot = building, green check = done).

**Takes about 3 minutes.** Refresh occasionally.

## Step 5 — Download the JAR

When the build finishes (green check):
- Click **Releases** on the right sidebar of the repo home page
- The newest release will have `goldeneye64-0.1.0.jar` attached
- Click to download

## Step 6 — Install in Minecraft

1. Install the [Fabric Installer](https://fabricmc.net/use/installer/) → pick MC **1.20.1** → install.
2. Also download [Fabric API for 1.20.1](https://modrinth.com/mod/fabric-api/version/0.92.3+1.20.1).
3. Put **both** JARs in your `.minecraft/mods/` folder:
   - `goldeneye64-0.1.0.jar` (from your GitHub release)
   - `fabric-api-0.92.3+1.20.1.jar`
4. Launch Minecraft → pick the Fabric 1.20.1 profile → load any world.
5. Press `e` for inventory → look for the **GoldenEye 64** creative tab.

Try `/br help` in chat to see all the match commands.

---

## If something fails

The Actions tab shows logs. Click any failed step → copy the error → paste it back to me. The most likely failure is the Gradle wrapper download — if Fabric changes their repo, the URL in `.github/workflows/build.yml` may need updating.
