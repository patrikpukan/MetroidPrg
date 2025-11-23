# Repository Guidelines

## Project Structure & Module Organization
- Single Android app module in `app/` using Gradle Kotlin DSL (`build.gradle.kts`, `settings.gradle.kts`, `gradle/libs.versions.toml` for version catalog).
- Source sets live under `app/src/main` with `AndroidManifest.xml` and `res/` for UI assets; Kotlin sources will live in `app/src/main/java/tech/pukan/metroidprg`.
- Tests: JVM unit tests in `app/src/test/java/tech/pukan/metroidprg`, instrumentation tests in `app/src/androidTest/java/tech/pukan/metroidprg`.
- Generated outputs land in `app/build/`; keep this directory untracked. Keep local SDK paths and secrets in `local.properties` (already gitignored).

## Build, Test, and Development Commands
- Sync/build in Android Studio, or from CLI:
  - `./gradlew assembleDebug` — compile the debug APK.
  - `./gradlew test` — run JVM unit tests.
  - `./gradlew connectedAndroidTest` — run instrumentation tests on an attached/emulated device (API 36 to match `minSdk`/`targetSdk`).
  - `./gradlew clean` — remove build outputs if you hit cache issues.
- Use the latest Android Studio/SDK tools; the project targets API level 36.

## Coding Style & Naming Conventions
- Language: Kotlin with planned Jetpack Compose UI; prefer idiomatic Kotlin (expression-based code, nullable-safety, scoped functions when clear).
- Formatting: 4-space indent, trailing commas where Kotlin allows for cleaner diffs, and organized imports. Align with Android/Kotlin style defaults.
- Names: `PascalCase` for classes/objects, `camelCase` for functions/vars, `UPPER_SNAKE_CASE` for const vals. Resource files stick to `snake_case`.
- Packages stay under `tech.pukan.metroidprg`; avoid creating parallel namespaces. Add dependencies through `gradle/libs.versions.toml` so versions remain centralized.

## Testing Guidelines
- Prefer small, deterministic tests. Place JVM tests alongside the code they cover in `app/src/test`, using the `*Test.kt` suffix.
- Instrumentation/UI tests go in `app/src/androidTest` using `AndroidJUnit4`/Espresso; annotate slow or device-only cases clearly.
- When adding features, include at least one unit test and expand instrumentation coverage for user-facing flows.

## Commit & Pull Request Guidelines
- Write concise, imperative commit subjects; optional prefixes seen here (`feat:`, `chore:`) are welcome when helpful.
- PRs should describe the change, link issues if any, list commands/tests run, and attach screenshots or screen recordings for UI-impacting work.
- Keep changes scoped; prefer follow-up PRs for refactors. Update docs or sample code when altering build or entry points.

## Configuration & Security Notes
- Do not commit secrets or API keys; keep them in `local.properties` or Gradle properties and reference via buildConfig or DI.
- Check compatibility with API level 36 devices/emulators before merging; align new libraries with the version catalog to avoid drift.
