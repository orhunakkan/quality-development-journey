# Markdown Book Maker

Java 21 / JavaFX 21 desktop app that bundles every `.md` file in a chosen folder into a single, nicely formatted PDF book.

## Prerequisites
- Java 21
- Maven 3.x
- For native installers: `jpackage` (included with JDK 21) plus OS tooling:
  - Windows: WiX 3.0+ on PATH (for MSI/EXE packaging)
  - macOS: Xcode command line tools (for signing/notarization workflows; DMG creation)
  - Linux: `fakeroot` and `dpkg-deb` (for `.deb`) or `rpmbuild` (for `.rpm` if you switch bundle type)

## If the end user does NOT have Java or Maven
- Ship them the native installer you build with `jpackage` (EXE/DMG/DEB). It bundles a runtime, so they do not need Java or Maven installed.
- If you only distribute the plain JAR, the user must have a Java 21 runtime on their machine. Maven is never required for end users, only for building.

## Run the app
```bash
cd java-learning/my-app
mvn clean javafx:run
```

## Build native installers
Commands assume you run them on the target OS so `jpackage` picks up the right platform:

- Windows 11 (EXE): `mvn -Pinstaller-windows clean package`
- macOS (DMG): `mvn -Pinstaller-mac clean package`
- Linux (DEB): `mvn -Pinstaller-linux clean package`

Outputs land under `target/dist` (created by the plugin).

If you already installed a previous DEB and it won’t launch, rebuild with the latest changes and reinstall:
```bash
sudo dpkg -r markdown-book-maker  # remove old package
mvn -Pinstaller-linux clean package
sudo dpkg -i target/dist/markdown-book-maker_1.0-SNAPSHOT_amd64.deb
```

## Using it
- Click **Choose folder** and pick the directory that holds your markdown files (e.g., `java-learning/book`).
- The app lists the `.md` files (sorted by name) and defaults the output to `book.pdf` in the same folder.
- Adjust the output path if you want, then hit **Create Book**. A status log at the bottom shows progress/errors.

## Notes
- Markdown is converted with Flexmark; PDF rendering uses OpenHTMLtoPDF, so the output should look the same on Windows, macOS, and Linux.
- If you rename files to control ordering (e.g., `01-intro.md`, `02-basics.md`), that order is preserved in the merged PDF.
