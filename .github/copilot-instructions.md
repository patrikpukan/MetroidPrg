# Copilot instructions for MetroidPrg

Use these rules when proposing code or edits in this repository. Keep outputs concise, modern, and aligned with the project setup.

Project stack and constraints
- Language: Kotlin only (no Java).
- Android Gradle Plugin: 8.13.0, Kotlin: 2.0.21, JVM target: 11.
- SDK targets: minSdk 36, target/compileSdk 36. Prefer Android 15 APIs; avoid deprecated or legacy shims.
- UI: Jetpack Compose with Material 3 (Material You). Do not introduce XML view layouts unless explicitly requested.
- Architecture: MVVM with AndroidX ViewModel; unidirectional data flow.
- Dependency management: Use Gradle Version Catalog (gradle/libs.versions.toml) and plugin aliases. Prefer the Compose BOM. Do not add new repositories.
- Module: Single app module at app/ with namespace tech.pukan.metroidprg.

Gradle and dependencies
- Enable Compose in app/build.gradle.kts: buildFeatures { compose = true } and set composeOptions { kotlinCompilerExtensionVersion = <from catalog> }.
- Use the Compose BOM (platform(libs.compose.bom)) to align compose libs when available in the catalog. Typical libs: material3, activity-compose, navigation-compose, lifecycle-runtime-compose, ui, ui-tooling, ui-test-junit4.
- Coroutines and Flow are allowed/preferred for async and streams.
- Hilt is recommended but optional; only introduce if a feature needs DI.
- Keep AGP/Kotlin/JDK levels as-is unless explicitly requested to upgrade.

App structure and naming
- Package: tech.pukan.metroidprg. New classes go under app/src/main/java/tech/pukan/metroidprg.
- Composable naming: PascalCase (e.g., HomeScreen, LineList, TimetableCard). One top-level composable per file where practical.
- ViewModel naming: Suffix ViewModel (e.g., HomeViewModel), scoped to a screen/feature.
- Resource naming:
  - Strings/colors/dimens: snake_case keys in res/values.
  - Drawables: vector preferred; names like ic_tram, ic_metro.
- Strings: Never hardcode user-visible text; use strings.xml. Provide previews with fake data.

Activities, navigation, and UI composition
- Single-Activity pattern preferred. Entry point: MainActivity extends ComponentActivity, setContent { AppTheme { AppNavHost() } }.
- Navigation: Use androidx.navigation.compose NavHost/Composable destinations. Avoid Fragments unless a platform API requires it.
- Scaffold usage: Use Material3 Scaffold with TopAppBar/NavigationBar/FAB as needed.
- State hoisting: Hoist state to ViewModel or a higher-level composable; pass events downward.
- Theming: Provide a Material 3 theme (dynamic color optional on Android 12+ if later adopted).

Kotlin style and safety
- Use null-safety, val by default, and explicit state types (State<T>, Immutable lists) where relevant.
- Prefer sealed classes/enums for UI state and events. Avoid magic numbers/strings.
- Use extension functions for localized helpers.

Asynchrony and background work
- Use Kotlin coroutines and Flow with viewModelScope. Expose StateFlow for UI, collectAsStateWithLifecycle in Compose.
- Avoid leaking Context; prefer applicationContext when needed in ViewModel via savedStateHandle or injected dependencies.

Permissions and storage
- Request runtime permissions via accompanist-permissions (optional) or platform APIs; show rationale.
- Use SAF or modern pickers on Android 15 when accessing files/media.

Testing
- Unit tests: app/src/test with JUnit4 (libs.junit). Keep logic isolated and deterministic.
- Compose UI tests: androidx.compose.ui:ui-test-junit4 and testing rules. Use createAndroidComposeRule<ComponentActivity>() and semantics matchers. Avoid sleeps; use awaitIdle/Idling where applicable.
- Name tests clearly and cover a happy path plus at least one edge case per unit.

Accessibility and i18n
- Provide contentDescription for icons/images. Ensure minimum touch targets in Composables.
- All user-visible text via strings.xml; use plurals where relevant.

Performance and UX
- Keep compositions small and reusable. Use remember/derivedStateOf carefully; avoid unnecessary recompositions.
- Prefer LazyColumn/LazyRow for lists. Use keys for stable item identity.

Documentation and comments
- Add KDoc for public classes/functions. Keep comments short and practical.
- For new screens, include a brief header describing purpose and navigation route.

What not to do
- Don’t introduce XML view layouts or ViewBinding unless explicitly requested.
- Don’t use findViewById or synthetic view access.
- Don’t change minSdk/targetSdk, language level, or Gradle plugin versions without approval.
- Don’t use deprecated APIs.

Ready-made templates Copilot can generate
- Main activity + app theme + NavHost
  - MainActivity: ComponentActivity with setContent { AppTheme { AppNavHost() } }.
  - AppNavHost: NavHost(navController, startDestination = "home") with composable("home") { HomeScreen() }.
  - Theme: Material3 colorScheme/typography and previews.
- Screen composable + ViewModel
  - ViewModel exposes UIState via StateFlow and accepts UIEvent via methods.
  - Composable collects via collectAsStateWithLifecycle and renders a Scaffold + content.
- LazyColumn list with Diff keys and pull-to-refresh (if using appropriate libs), item click callbacks, and previews.
- Compose UI test skeleton using createAndroidComposeRule and semantics assertions.

Quality gates
- Build should pass with gradlew assembleDebug.
- Lint warnings: keep to a minimum; prefer project defaults.
- Tests: when adding logic or UI, include minimal unit/UI tests.

If something is underspecified
- Make the smallest, most conventional Compose choice matching the current stack and document it in a short comment.
- If an action requires new dependencies (e.g., compose BOM entries), update the version catalog and explain briefly in the PR.
